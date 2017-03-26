package AssistanTFx.AppModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bofei on 1/25/2017.
 */
public class Ipv6AddressModel {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty ipv6Address = new SimpleStringProperty("");

    public Ipv6AddressModel(String name, String ipv6Address){
        this.name.set(name);
        this.ipv6Address.set(ipv6Address);
    }

    public String getName(){
        return this.name.get();
    }

    public String getIpv6Address(){
        return this.ipv6Address.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setIpv6Address(String ipv6Address){
        this.ipv6Address.set(ipv6Address);
    }


}
