package AssistanTFx;

/**
 * Created by bofei on 1/18/2017.
 */

import AssistanTFx.AppModel.FortiadcModel;
import AssistanTFx.ComponentsController.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Button globalSettingsButton;

    @FXML
    private Accordion appAccordion;

    @FXML
    private TitledPane sharedObjectsPane;

    @FXML
    private Label fortiadcLabel;

    @FXML
    private Label ipAddressLabel;

    @FXML
    private Label receiversLabel;

    @FXML
    private Label uriResourcesLabel;

    @FXML
    private TitledPane fortiadcTestingAssistantPane;

    @FXML
    private Label crashDetectorLabel;

    @FXML
    private Label configurationLabel;

    @FXML
    private Label licenseLabel;

    @FXML
    private Label regularDetectorLabel;

    @FXML
    private Label upgradingAssistantLabel;

    @FXML
    private TitledPane haTestingAssistantPane;

    @FXML
    private Label haUpgradingLabel;

    @FXML
    private TitledPane workAssistantPane;

    @FXML
    private Label ftpServerLabel;

    @FXML
    private Label healthCheckLabel;

    @FXML
    private Label linuxRunLabel;


    // ...................................................................................................................
    public Node fortiadcLabelNode;
    public Node receiversLabelNode;
    public Node ipAddressLabelNode;
    public Node uriResourcesLabelNode;
    public Node crashDetectorLabelNode;





    // ...................................................................................................................

    public AppStatus appStatus;


    public Controller(){
        this.appStatus = new AppStatus();
    }



    @FXML
    void clickOnConfigurationLabel(MouseEvent event) {
        setLabelColorClicked(configurationLabel);

    }

    @FXML
    void clickOnCrashDetectorLabel(MouseEvent event) {
        setLabelColorClicked(crashDetectorLabel);
        rootPane.setCenter(crashDetectorLabelNode);

    }

    @FXML
    void clickOnFortiadcLabel(MouseEvent event) {
        setLabelColorClicked(fortiadcLabel);
        rootPane.setCenter(fortiadcLabelNode);

    }

    @FXML
    void clickOnFortiadcTestingAssistantPane(MouseEvent event) {
        if (!appStatus.appTtiledPaneClickedList.contains(fortiadcTestingAssistantPane)){
            appStatus.appTtiledPaneClickedList.clear();
            clickOnCrashDetectorLabel(event);
            appStatus.appTtiledPaneClickedList.add(fortiadcTestingAssistantPane);
        }

    }

    @FXML
    void clickOnFtpServerLable(MouseEvent event) {
        setLabelColorClicked(ftpServerLabel);

    }

    @FXML
    void clickOnHaTestingAssistantPane(MouseEvent event) {
        if (!appStatus.appTtiledPaneClickedList.contains(haTestingAssistantPane)){
            appStatus.appTtiledPaneClickedList.clear();
            clickOnHaUpgradingLabel(event);
            appStatus.appTtiledPaneClickedList.add(haTestingAssistantPane);
        }


    }

    @FXML
    void clickOnHaUpgradingLabel(MouseEvent event) {
        setLabelColorClicked(haUpgradingLabel);

    }

    @FXML
    void clickOnHealthCheckLabel(MouseEvent event) {
        setLabelColorClicked(healthCheckLabel);

    }

    @FXML
    void clickOnIpAddressLabel(MouseEvent event) {
        setLabelColorClicked(ipAddressLabel);
        rootPane.setCenter(ipAddressLabelNode);

    }

    @FXML
    void clickOnLicenseLabel(MouseEvent event) {
        setLabelColorClicked(licenseLabel);
    }

    @FXML
    void clickOnLinuxRunLabel(MouseEvent event) {
        setLabelColorClicked(linuxRunLabel);

    }


    @FXML
    void clickOnReceiversLabel(MouseEvent event) {
        setLabelColorClicked(receiversLabel);
        rootPane.setCenter(receiversLabelNode);

    }

    @FXML
    void clickOnRegularDetectorLabel(MouseEvent event) {
        setLabelColorClicked(regularDetectorLabel);
    }

    @FXML
    void clickOnSharedObjectsPane(MouseEvent event) {
        if (!appStatus.appTtiledPaneClickedList.contains(sharedObjectsPane)){
            appStatus.appTtiledPaneClickedList.clear();
            clickOnFortiadcLabel(event);
            appStatus.appTtiledPaneClickedList.add(sharedObjectsPane);
        }

    }

    @FXML
    void clickOnUpgradingAssistantLabel(MouseEvent event) {
        setLabelColorClicked(upgradingAssistantLabel);

    }

    @FXML
    void clickOnUriResourcesLabel(MouseEvent event) {
        setLabelColorClicked(uriResourcesLabel);
        rootPane.setCenter(uriResourcesLabelNode);
    }


    @FXML
    void clickOnWorkAssistantPane(MouseEvent event) {
        if (!appStatus.appTtiledPaneClickedList.contains(workAssistantPane)){
            appStatus.appTtiledPaneClickedList.clear();
            clickOnFtpServerLable(event);
            appStatus.appTtiledPaneClickedList.add(workAssistantPane);
        }

    }




    // ...................................................................................................................


    public void initialize(URL location, ResourceBundle resources) {
        try {

//            receiversLabelNode = (Node) FXMLLoader.load(this.getClass().getClassLoader().getResource("ReceiversLabelView.fxml"));

            FXMLLoader fortiadcLabelLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("FortiadcLabelView.fxml"));
            FXMLLoader ipAddressLabelLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("IpAddressLabelView.fxml"));
            FXMLLoader receiversLabelLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("ReceiversLabelView.fxml"));
            FXMLLoader uriResourcesLabelLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("UriResourcesLabelView.fxml"));
            FXMLLoader crashDetectorLabelLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("CrashDetectorLabelView.fxml"));

            fortiadcLabelNode = (Node) fortiadcLabelLoader.load();
            ipAddressLabelNode = (Node) ipAddressLabelLoader.load();
            receiversLabelNode = (Node) receiversLabelLoader.load();
            uriResourcesLabelNode = (Node) uriResourcesLabelLoader.load();
            crashDetectorLabelNode = crashDetectorLabelLoader.load();

            FortiadcController fortiadcController = fortiadcLabelLoader.getController();
            IpAddressController ipAddressController = ipAddressLabelLoader.getController();
            ReceiversController receiversController = receiversLabelLoader.getController();
            UriResourcesController uriResourcesController = uriResourcesLabelLoader.getController();
            CrashDetectorController crashDetectorController = crashDetectorLabelLoader.getController();


//            crashDetectorController.initData(receiversController.getTableView());
            crashDetectorController.initData(
                    fortiadcController.getTableView(),
                    ipAddressController.getIpv4AddressTable(),
                    ipAddressController.getIpv4SubnetTable(),
                    ipAddressController.getIpv6AddressTable(),
                    ipAddressController.getIpv6SubnetTable(),
                    receiversController.getTableView(),
                    uriResourcesController.getTableView()
            );





            showDefaultPage();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // ...................................................................................................................
    private void clickOnTitledPane(TitledPane titledPane, MouseEvent event){
        if (!appStatus.appTtiledPaneClickedList.contains(titledPane)){
            appStatus.appTtiledPaneClickedList.clear();
            appStatus.appTtiledPaneClickedList.add(titledPane);
        }
    }

    private void setLabelColorClicked(Label label){
        for (Label i: appStatus.customLabelClickedList){
            i.setStyle("-fx-background-color: #2a3951");
        }
        appStatus.customLabelClickedList.clear();
        label.setStyle("-fx-background-color: #2073f9");
        appStatus.customLabelClickedList.add(label);
    }

    private void showDefaultPage(){

        appAccordion.setExpandedPane(sharedObjectsPane);
        setLabelColorClicked(fortiadcLabel);
        rootPane.setCenter(fortiadcLabelNode);
//        fortiadcLabel.requestFocus();
        appStatus.appTtiledPaneClickedList.clear();
        appStatus.appTtiledPaneClickedList.add(sharedObjectsPane);

    }


}
