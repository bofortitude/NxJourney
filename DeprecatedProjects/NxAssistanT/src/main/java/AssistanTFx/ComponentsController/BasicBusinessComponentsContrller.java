package AssistanTFx.ComponentsController;

import AssistanTFx.AppModel.*;
import javafx.scene.control.TableView;

/**
 * Created by bofei on 1/24/2017.
 */
public class BasicBusinessComponentsContrller {

    public TableView<FortiadcModel> fortiadcModelTableView = null;
    public TableView<Ipv4AddressModel> ipv4AddressModelTableView = null;
    public TableView<Ipv4SubnetModel> ipv4SubnetModelTableView = null;
    public TableView<Ipv6AddressModel> ipv6AddressModelTableView = null;
    public TableView<Ipv6SubnetModel> ipv6SubnetModelTableView = null;
    public TableView<ReceiversModel> receiversModelTableView = null;
    public TableView<UriResourcesModel> uriResourcesModelTableView = null;



    public void initData(TableView<FortiadcModel> fortiadcModelTableView,
                         TableView<Ipv4AddressModel> ipv4AddressModelTableView,
                         TableView<Ipv4SubnetModel> ipv4SubnetModelTableView,
                         TableView<Ipv6AddressModel> ipv6AddressModelTableView,
                         TableView<Ipv6SubnetModel> ipv6SubnetModelTableView,
                         TableView<ReceiversModel> receiversModelTableView,
                         TableView<UriResourcesModel> uriResourcesModelTableView){
        this.fortiadcModelTableView = fortiadcModelTableView;
        this.ipv4AddressModelTableView = ipv4AddressModelTableView;
        this.ipv4SubnetModelTableView = ipv4SubnetModelTableView;
        this.ipv6AddressModelTableView = ipv6AddressModelTableView;
        this.ipv6SubnetModelTableView = ipv6SubnetModelTableView;
        this.receiversModelTableView = receiversModelTableView;
        this.uriResourcesModelTableView = uriResourcesModelTableView;

    }
}
