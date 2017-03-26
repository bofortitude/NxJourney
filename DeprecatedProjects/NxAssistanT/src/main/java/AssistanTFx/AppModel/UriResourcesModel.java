package AssistanTFx.AppModel;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bofei on 1/25/2017.
 */
public class UriResourcesModel {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty uri = new SimpleStringProperty("");

    public UriResourcesModel(String name, String uri){
        this.name.set(name);
        this.uri.set(uri);

    }

    public String getName(){
        return this.name.get();
    }

    public String getUri(){
        return this.uri.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setUri(String uri){
        this.uri.set(uri);
    }


}
