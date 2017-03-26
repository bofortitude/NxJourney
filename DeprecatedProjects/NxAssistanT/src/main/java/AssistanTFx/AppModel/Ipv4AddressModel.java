package AssistanTFx.AppModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bofei on 1/25/2017.
 */
public class Ipv4AddressModel {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty ipAddress = new SimpleStringProperty("");

    public Ipv4AddressModel(String name, String ipAddress){
        this.name.set(name);
        this.ipAddress.set(ipAddress);
    }

    public String getName(){
        return this.name.get();
    }

    public String getIpAddress(){
        return this.ipAddress.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress.set(ipAddress);
    }
}
