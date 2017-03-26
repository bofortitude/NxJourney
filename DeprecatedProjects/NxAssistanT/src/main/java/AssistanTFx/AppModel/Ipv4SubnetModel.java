package AssistanTFx.AppModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bofei on 1/25/2017.
 */
public class Ipv4SubnetModel {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty ipSubnet = new SimpleStringProperty("");

    public Ipv4SubnetModel(String name, String ipSubnet){
        this.name.set(name);
        this.ipSubnet.set(ipSubnet);
    }

    public String getName(){
        return this.name.get();
    }

    public String getIpSubnet(){
        return this.ipSubnet.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setIpSubnet(String ipSubnet){
        this.ipSubnet.set(ipSubnet);
    }

}
