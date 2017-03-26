package AssistanTFx.AppModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bofei on 1/24/2017.
 */
public class ReceiversModel {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty localNotification = new SimpleStringProperty("");
    private SimpleStringProperty email = new SimpleStringProperty("");
    private SimpleStringProperty wechat = new SimpleStringProperty("");

    public ReceiversModel(String name, String localNotification, String email, String wechat){
        this.name.set(name);
        this.localNotification.set(localNotification);
        this.email.set(email);
        this.wechat.set(wechat);
    }

    public String getName(){
        return this.name.get();
    }

    public String getLocalNotification(){
        return this.localNotification.get();
    }

    public String getEmail(){
        return this.email.get();
    }

    public String getWechat(){
        return this.wechat.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setLocalNotification(String localNotification){
        this.localNotification.set(localNotification);
    }

    public void setEmail(String email){
        this.email.set(email);
    }

    public void setWechat(String wechat){
        this.wechat.set(wechat);
    }
}
