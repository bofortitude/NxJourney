package AssistanTFx.ComponentsController;

import AssistanTFx.AppModel.ReceiversModel;
import AssistanTFx.AppModel.UriResourcesModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bofei on 1/25/2017.
 */
public class UriResourcesController implements Initializable {

    @FXML
    private TableView<UriResourcesModel> uriResourcesTable;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField uriInput;


    @FXML
    void clickOnAddButton(MouseEvent event) {
        ObservableList<UriResourcesModel> data = uriResourcesTable.getItems();
        if (nameInput.getText().equals("")){

        }else {
            data.add(new UriResourcesModel(nameInput.getText(), uriInput.getText()));
        }

    }

    @FXML
    void clickOnDeleteButton(MouseEvent event) {
        UriResourcesModel selectedItem = uriResourcesTable.getSelectionModel().getSelectedItem();
        uriResourcesTable.getItems().remove(selectedItem);

    }


    // .......................................................................................................
    public void initialize(URL location, ResourceBundle resources) {

    }

    // .........................................................................................................
    public TableView<UriResourcesModel> getTableView(){
        return this.uriResourcesTable;
    }


}
