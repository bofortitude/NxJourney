package AssistanTFx.ComponentsController;

import AssistanTFx.AppModel.ReceiversModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bofei on 1/23/2017.
 */
public class ReceiversController implements Initializable {

    @FXML
    private TableView<ReceiversModel> receiversTable;


    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> localNotificationColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TableColumn<?, ?> wechatColumn;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField localNotificationInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField wechatInput;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;






    public ReceiversController(){

    }


    @FXML
    void clickOnAddButton(MouseEvent event) {
        ObservableList<ReceiversModel> data = receiversTable.getItems();
        if (nameInput.getText().equals("")){

        }else {
            data.add(new ReceiversModel(nameInput.getText(), localNotificationInput.getText(), emailInput.getText(), wechatInput.getText()));
        }
    }

    @FXML
    void clickOnDeleteButton(MouseEvent event) {
        ReceiversModel selectedItem = receiversTable.getSelectionModel().getSelectedItem();
        receiversTable.getItems().remove(selectedItem);
    }



    // ................................................................................................................
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ReceiversModel> data = receiversTable.getItems();
        data.add(new ReceiversModel("bofei","Yes","bofei@fortinet.com","bofei"));



    }

    // ................................................................................................................
    public TableView<ReceiversModel> getTableView(){
        return receiversTable;
    }

}

