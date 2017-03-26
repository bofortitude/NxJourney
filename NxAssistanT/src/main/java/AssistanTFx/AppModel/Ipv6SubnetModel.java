package AssistanTFx.AppModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bofei on 1/25/2017.
 */
public class Ipv6SubnetModel {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty ipv6Subnet = new SimpleStringProperty("");

    public Ipv6SubnetModel(String name, String ipv6Subnet){
        this.name.set(name);
        this.ipv6Subnet.set(ipv6Subnet);
    }

    public String getName(){
        return this.name.get();
    }

    public String getIpv6Subnet(){
        return this.ipv6Subnet.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setIpv6Subnet(String ipv6Subnet){
        this.ipv6Subnet.set(ipv6Subnet);
    }


}
