package AssistanTFx.ComponentsController;

import AssistanTFx.AppModel.FortiadcModel;
import AssistanTFx.AppModel.Ipv4AddressModel;
import AssistanTFx.AppModel.ReceiversModel;
import AssistanTFx.AppModel.UriResourcesModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by bofei on 1/20/2017.
 */
public class CrashDetectorController extends BasicBusinessComponentsContrller implements Initializable {

    @FXML
    private Button testButton;

//    public TableView<ReceiversModel> receiversModelTableView = null;



    public CrashDetectorController(){

    }

//    public void initData(TableView<ReceiversModel> receiversModelTableView){
//        this.receiversModelTableView = receiversModelTableView;
//
//    }

    @FXML
    void clickOnTestButton(MouseEvent event) {
        System.out.println("========================================================");
        System.out.println("--------------receiversModelTableView-----------------");
        ObservableList<ReceiversModel> data = this.receiversModelTableView.getItems();
        for (ReceiversModel i :data){
            System.out.println(i.getName());
            System.out.println(i.getLocalNotification());
            System.out.println(i.getEmail());
            System.out.println(i.getWechat());
            System.out.println(".........................");
        }

        System.out.println("--------------fortiadc------------");
        ObservableList<FortiadcModel> dataFortiadc = this.fortiadcModelTableView.getItems();
        for (FortiadcModel i : dataFortiadc){
            System.out.println(i.getName());
            System.out.println(i.getMgmtIp());
            System.out.println(i.getUsername());
            System.out.println(i.getPassword());
            System.out.println(i.getTelnetPort());
            System.out.println(i.getSshPort());
            System.out.println(i.getHttpPort());
            System.out.println(i.getHttpsPort());
            System.out.println(i.getConsole());
            System.out.println(".......................................");
        }

        System.out.println("------------------ipv4Address------------");
        ObservableList<Ipv4AddressModel> dataIpv4Add = this.ipv4AddressModelTableView.getItems();
        for (Ipv4AddressModel i: dataIpv4Add){
            System.out.println(i.getName());
            System.out.println(i.getIpAddress());
            System.out.println(".........................................");
        }



        System.out.println("-----------------uriResources-------------------");
        ObservableList<UriResourcesModel> dataUri = this.uriResourcesModelTableView.getItems();
        for (UriResourcesModel i: dataUri){
            System.out.println(i.getName());
            System.out.println(i.getUri());
            System.out.println("...............................................");
        }

    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
