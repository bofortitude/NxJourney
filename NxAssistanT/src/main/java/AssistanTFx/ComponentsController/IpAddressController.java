package AssistanTFx.ComponentsController;

import AssistanTFx.AppModel.*;
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
 * Created by bofei on 1/23/2017.
 */
public class IpAddressController implements Initializable {

    @FXML
    private TableView<Ipv4AddressModel> ipv4AddressTable;

    @FXML
    private TextField ipv4AddressNameInput;

    @FXML
    private TextField ipv4AddressIpAddressInput;

    @FXML
    private Button ipv4AddressAddButton;

    @FXML
    private Button ipv4AddressDeleteButton;

    @FXML
    private TableView<Ipv6AddressModel> ipv6AddressTable;

    @FXML
    private TextField ipv6AddressNameInput;

    @FXML
    private TextField ipv6AddressIpv6AddressInput;

    @FXML
    private Button ipv6AddressAddButton;

    @FXML
    private Button ipv6AddressDeleteButton;

    @FXML
    private TableView<Ipv4SubnetModel> ipv4SubnetTable;

    @FXML
    private TextField ipv4SubnetNameInput;

    @FXML
    private TextField ipv4SubnetIpSubnetInput;

    @FXML
    private Button ipv4SubnetAddButton;

    @FXML
    private Button ipv4SubnetDeleteButton;

    @FXML
    private TableView<Ipv6SubnetModel> ipv6SubnetTable;

    @FXML
    private TextField ipv6SubnetNameInput;

    @FXML
    private TextField ipv6SubnetIpv6SubnetInput;

    @FXML
    private Button ipv6SubnetAddButton;

    @FXML
    private Button ipv6SubnetDeleteButton;

    @FXML
    void clickOnIpv4AddressAddButton(MouseEvent event) {
        ObservableList<Ipv4AddressModel> data = ipv4AddressTable.getItems();
        if (ipv4AddressNameInput.getText().equals("")){

        }else {
            data.add(new Ipv4AddressModel(ipv4AddressNameInput.getText(), ipv4AddressIpAddressInput.getText()));
        }

    }

    @FXML
    void clickOnIpv4DeleteButton(MouseEvent event) {
        Ipv4AddressModel selectedItem = ipv4AddressTable.getSelectionModel().getSelectedItem();
        ipv4AddressTable.getItems().remove(selectedItem);

    }

    @FXML
    void clickOnIpv4SubnetAddButton(MouseEvent event) {
        ObservableList<Ipv4SubnetModel> data = ipv4SubnetTable.getItems();
        if (ipv4SubnetIpSubnetInput.getText().equals("")){

        }else {
            data.add(new Ipv4SubnetModel(ipv4SubnetNameInput.getText(), ipv4SubnetIpSubnetInput.getText()));
        }

    }

    @FXML
    void clickOnIpv4SubnetDeleteButton(MouseEvent event) {
        Ipv4SubnetModel selectedItem = ipv4SubnetTable.getSelectionModel().getSelectedItem();
        ipv4SubnetTable.getItems().remove(selectedItem);

    }

    @FXML
    void clickOnIpv6AddressAddButton(MouseEvent event) {
        ObservableList<Ipv6AddressModel> data = ipv6AddressTable.getItems();
        if (ipv6AddressNameInput.getText().equals("")){

        }else {
            data.add(new Ipv6AddressModel(ipv6AddressNameInput.getText(), ipv6AddressIpv6AddressInput.getText()));
        }

    }

    @FXML
    void clickOnIpv6AddressDeleteButton(MouseEvent event) {
        Ipv6AddressModel selectedItem = ipv6AddressTable.getSelectionModel().getSelectedItem();
        ipv6AddressTable.getItems().remove(selectedItem);
    }

    @FXML
    void clickOnIpv6SubnetAddButton(MouseEvent event) {
        ObservableList<Ipv6SubnetModel> data = ipv6SubnetTable.getItems();
        if (ipv6SubnetNameInput.getText().equals("")){

        }else {
            data.add(new Ipv6SubnetModel(ipv6SubnetNameInput.getText(), ipv6SubnetIpv6SubnetInput.getText()));
        }

    }

    @FXML
    void clickOnIpv6SubnetDeleteButton(MouseEvent event) {
        Ipv6SubnetModel selectedItem = ipv6SubnetTable.getSelectionModel().getSelectedItem();
        ipv6SubnetTable.getItems().remove(selectedItem);

    }





    // ...........................................................................................................
    public void initialize(URL location, ResourceBundle resources) {

    }

    // ............................................................................................................
    public TableView<Ipv4AddressModel> getIpv4AddressTable(){
        return this.ipv4AddressTable;
    }

    public TableView<Ipv4SubnetModel> getIpv4SubnetTable(){
        return this.ipv4SubnetTable;
    }

    public TableView<Ipv6AddressModel> getIpv6AddressTable(){
        return this.ipv6AddressTable;
    }

    public TableView<Ipv6SubnetModel> getIpv6SubnetTable(){
        return this.ipv6SubnetTable;
    }


}
