package AssistanTFx.ComponentsController;

import AssistanTFx.AppModel.FortiadcModel;
import AssistanTFx.AppModel.ReceiversModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bofei on 1/23/2017.
 */
public class FortiadcController implements Initializable {


    @FXML
    private TableView<FortiadcModel> fortiadcTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> mgmtIpColumn;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    private TableColumn<?, ?> passwordColumn;

    @FXML
    private TableColumn<?, ?> telnetPortColumn;

    @FXML
    private TableColumn<?, ?> sshPortColumn;

    @FXML
    private TableColumn<?, ?> httpPortColumn;

    @FXML
    private TableColumn<?, ?> httpsPortColumn;

    @FXML
    private TableColumn<?, ?> consoleColumn;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField mgmtIpInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField telnetPortInput;

    @FXML
    private TextField sshPortInput;

    @FXML
    private TextField httpPortInput;

    @FXML
    private TextField httpsPortInput;

    @FXML
    private TextField consoleInput;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;





    // .............................................................................................................
    @FXML
    void clickOnAddButton(MouseEvent event) {
        ObservableList<FortiadcModel> data = fortiadcTable.getItems();
        if (nameInput.getText().equals("")){

        } else {
            data.add(new FortiadcModel(nameInput.getText(), mgmtIpInput.getText(), usernameInput.getText(),
                    passwordInput.getText(),
                    Integer.parseInt(telnetPortInput.getText()),
                    Integer.parseInt(sshPortInput.getText()),
                    Integer.parseInt(httpPortInput.getText()),
                    Integer.parseInt(httpsPortInput.getText()),
                    consoleInput.getText()));
        }

    }

    @FXML
    void clickOnDeleteButton(MouseEvent event) {
        FortiadcModel selectedItem = fortiadcTable.getSelectionModel().getSelectedItem();
        fortiadcTable.getItems().remove(selectedItem);
    }


    // .............................................................................................................
    public void initialize(URL location, ResourceBundle resources) {


    }

    // ............................................................................................................
    public TableView<FortiadcModel> getTableView(){
        return this.fortiadcTable;
    }



}
