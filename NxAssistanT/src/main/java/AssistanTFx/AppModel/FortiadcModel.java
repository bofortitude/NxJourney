package AssistanTFx.AppModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArray;

/**
 * Created by bofei on 1/21/2017.
 */
public class FortiadcModel {
    private SimpleStringProperty name = new SimpleStringProperty("") ;
    private SimpleStringProperty mgmtIp = new SimpleStringProperty("");
    private SimpleStringProperty username = new SimpleStringProperty("");
    private SimpleStringProperty password = new SimpleStringProperty("");
    private SimpleIntegerProperty telnetPort = new SimpleIntegerProperty(0) ;
    private SimpleIntegerProperty sshPort = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty httpPort = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty httpsPort = new SimpleIntegerProperty(0);
    private SimpleStringProperty console = new SimpleStringProperty("");

    public FortiadcModel(String name, String mgmtIp, String username, String password, int telnetPort, int sshPort, int httpPort, int httpsPort, String console){
        this.name.set(name);
        this.mgmtIp.set(mgmtIp);
        this.username.set(username);
        this.password.set(password);
        this.telnetPort.set(telnetPort);
        this.sshPort.set(sshPort);
        this.httpPort.set(httpPort);
        this.httpsPort.set(httpsPort);
        this.console.set(console);
    }

    public String getName(){
        return this.name.get();
    }

    public String getMgmtIp(){
        return this.mgmtIp.get();
    }

    public String getUsername(){
        return this.username.get();
    }

    public String getPassword(){
        return this.password.get();
    }

    public int getTelnetPort(){
        return this.telnetPort.get();
    }

    public int getSshPort(){
        return this.sshPort.get();
    }

    public int getHttpPort(){
        return this.httpPort.get();
    }

    public int getHttpsPort(){
        return this.httpsPort.get();
    }

    public String getConsole(){
        return this.console.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setMgmtIp(String mgmtIp){
        this.mgmtIp.set(mgmtIp);
    }

    public void setUsername(String username){
        this.username.set(username);
    }

    public void setPassword(String password){
        this.password.set(password);
    }

    public void setTelnetPort(int telnetPort){
        this.telnetPort.set(telnetPort);
    }

    public void setSshPort(int sshPort){
        this.sshPort.set(sshPort);
    }

    public void setHttpPort(int httpPort){
        this.httpPort.set(httpPort);
    }

    public void setHttpsPort(int httpsPort){
        this.httpsPort.set(httpsPort);
    }

    public void setConsole(String console){
        this.console.set(console);
    }

}
