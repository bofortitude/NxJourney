package SetupEnv;

import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by root on 1/10/17.
 */
public class ClearLinuxNetwork {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private SshManager sshManager;
    private String remoteIp;

    public ClearLinuxNetwork(SshManager sshManager){
        this.sshManager = sshManager;
        remoteIp = this.sshManager.getHost();
    }

    private String [] getRemoteInterfaceList(){
        String interfaceListString = this.sshManager.sendCommand("ip -o link|awk '{print $2}'|awk -F \":\" '{print $1}'");
        return interfaceListString.split("\n");
    }

    private String mergeCmd(List<String> cmdArray){
        String mergedCmd = "";
        for (String i: cmdArray){
            if (mergedCmd.equals("")){
                mergedCmd = i;
            }else {
                mergedCmd = mergedCmd+"; "+i;
            }
        }
        return mergedCmd;
    }

    private void clearVlanInterface(){
        logger.info("Starting to clear the VLAN interface on \""+this.remoteIp+"\" .");
        Pattern vlanIntPattern = Pattern.compile("^.*@.*$");
        String [] interfaceArray = this.getRemoteInterfaceList();
        for (String i: interfaceArray){
            logger.debug("Check the interface \""+i+"\" on \""+this.remoteIp+"\" .");
            if (vlanIntPattern.matcher(i).find()){
                String vlanInterfaceName = i.split("@")[0];
                this.sshManager.sendCommand("ip link del "+vlanInterfaceName);
                logger.warn("The VLAN interface \""+vlanInterfaceName+"\" has been deleted!");
            }
        }
        logger.info("All the VLAN interfaces on \""+this.remoteIp+"\" have been deleted.");
    }

    private void flushIpAddress(){
        logger.info("Starting to flush IP address process on \""+this.remoteIp+"\" .");

        String [] interfaceArray = this.getRemoteInterfaceList();

        // get mgmt interface
        // ip -o add show |grep 10.0.12.11|awk '{print $2}'
        String mgmtInterface = this.sshManager.sendCommand("ip -o add show |grep "+this.remoteIp+"|awk '{print $2}'").replace("\n", "");
        logger.debug("The management interface on \""+this.remoteIp+"\" is \""+mgmtInterface+"\" .");

        // get mgmt original ip & subnet
        String originalMgmtSubnet = this.sshManager.sendCommand("ip -o add show |grep "+this.remoteIp+"|awk '{print $4}'").replace("\n", "");
        String mgmtInterfaceIpRecoveryCmd = "ip add add "+originalMgmtSubnet+" dev "+mgmtInterface;
        logger.info("Management IP recovery command on \""+this.remoteIp+"\" is \""+mgmtInterfaceIpRecoveryCmd+"\" .");


        // get original route related to mgmt interface
        // ip route |grep eth0 | grep -v "proto kernel"|awk -F "dev" '{print $1}'
        String [] originalRouteArray =
                this.sshManager.sendCommand("ip route |grep "+mgmtInterface+" | grep -v \"proto kernel\"|awk -F \"dev\" '{print $1}'").split("\n");
        List<String> routeCmdList = new ArrayList<String>();
        for (String routeCmd: originalRouteArray){
            if (!routeCmd.equals("")){
                routeCmdList.add("ip route add "+routeCmd);
            }

        }
        String mgmtRouteRecoveryCmd = this.mergeCmd(routeCmdList);
        logger.info("Management route recovery command on \""+this.remoteIp+"\" is \""+mgmtRouteRecoveryCmd+"\" .");


        // start to flush ip address except mgmt interface
        logger.info("Starting to flush IP addresses on \""+this.remoteIp+"\" ...");
        for (String i: interfaceArray){
            if (!i.equals(mgmtInterface)){
                // not mgmt interface
                this.sshManager.sendCommand("ip address flush dev "+i);
            }
        }

        // ls /etc/ | grep redhat > /dev/null 2>/dev/null ; echo $?
        // ip link set eth0 up
        // ip add add 10.0.12.31/16 dev eth0
        // ip route add default via 10.0.10.198
        String restartNetworkStr;
        if (this.sshManager.sendCommand("ls /etc/ | grep redhat > /dev/null 2>/dev/null ; echo $?").replace("\n", "").equals("0")){
            // centos
            restartNetworkStr = "systemctl restart network";
        }else {
            // ubuntu
            restartNetworkStr = "systemctl restart networking";
        }

        this.sshManager.sendCommand("ip add add 127.0.0.1/8 dev lo");
        logger.info("127.0.0.1/8 has been added to lo interface.");

        List<String> recoveryCmdList = new ArrayList<String>();
        recoveryCmdList.add(restartNetworkStr);
        recoveryCmdList.add("ip link set "+mgmtInterface+" up");
        recoveryCmdList.add(mgmtInterfaceIpRecoveryCmd);
        recoveryCmdList.add(mgmtRouteRecoveryCmd);
        String recoveryCmd = this.mergeCmd(recoveryCmdList);
        logger.info("Management recovery command to be executed on \""+this.remoteIp+"\" :\n"+recoveryCmd);
        this.sshManager.sendCommand(recoveryCmd);
        logger.debug("Management recovery command has been executed on \""+this.remoteIp+"\", it is:\n"+recoveryCmd);
        logger.info("All the IP addresses on \""+this.remoteIp+"\" have been cleared.");

    }

    private void clearIptablesRule(){
        logger.info("Starting to clear iptables rules on \""+this.remoteIp+"\" .");
        this.sshManager.sendCommand("iptables -F");
        this.sshManager.sendCommand("iptables -t nat -F");
        this.sshManager.sendCommand("iptables -t raw -F");
        this.sshManager.sendCommand("iptables -t mangle -F");
        logger.info("iptables rules on \""+this.remoteIp+"\" have been cleared.");
    }

    private void clearTcRule(){
        logger.info("Starting to clear the tc rules \""+this.remoteIp+"\" created by \"easy-tc.sh\" .");
        //tc qdisc del dev eth1 root handle 1111: prio >/dev/null 2>/dev/null
        for (String i: this.getRemoteInterfaceList()){
            this.sshManager.sendCommand("tc qdisc del dev "+i+" root handle 1111: prio >/dev/null 2>/dev/null");
        }
        logger.info("tc rules \""+this.remoteIp+"\" created by \"easy-tc.sh\" have been cleared.");
    }

    public void clearNetworkConfig(){
        this.clearVlanInterface();
        this.flushIpAddress();
        this.clearIptablesRule();
        this.clearTcRule();
    }

    public void initNetworkConfig(){
        logger.info("Start to init the network config on \""+this.remoteIp+"\" .");
        this.sshManager.sendCommand("sysctl -w net.ipv4.ip_forward=1");
        this.sshManager.sendCommand("sysctl -w net.ipv6.conf.all.forwarding=1");
        logger.info("The route forwarding has been enabled on \""+this.remoteIp+"\" .");
        for (String i: this.getRemoteInterfaceList()){
            this.sshManager.sendCommand("ip link set "+i+" up");
        }
        logger.info("All interfaces on \""+this.remoteIp+"\" have been enabled.");
        logger.info("Basic network config has been configured on \""+this.remoteIp+"\" .");
    }


    public void clean(){
        this.sshManager.checkConnection();
        this.clearNetworkConfig();
        this.initNetworkConfig();
    }



}
