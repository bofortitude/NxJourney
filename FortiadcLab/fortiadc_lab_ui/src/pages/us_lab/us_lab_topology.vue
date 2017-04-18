<template>
    <section>

        <el-tabs v-model="activeName2"  @tab-click="handleTabClick" type="border-card" >
          <el-tab-pane label="Topology" name="tab_topology" >
            <div id = 'tool_panel' class="toolbar">
                <el-button type="primary" @click='refresh_method' >Refresh</el-button>
                <el-button type="primary" @click='click_vcenter_button' >vCenter</el-button>
                <el-button type="primary" @click='click_ys_button' >Yongsheng Team Resource</el-button>
            </div>
            <div id="us_lab_topology" v-loading.body="loading"></div>

          </el-tab-pane>
          <el-tab-pane label="Edit Topology" name="tab_topo_model">
            <div :span="24" class="toolbar" style="padding-bottom: 7px;">
              <!-- <el-button type="primary" v-on:click="refreshEditTopologyTable">Refresh Table</el-button> -->
              <el-button type="primary" @click="addNewTopologyItem" >New Item</el-button>
            </div>

            <el-table stripe border :data="topologyDataTableRaw" highlight-current-row v-loading="editTopologyTableLoading"  style="width: 100%;">
              <el-table-column prop="name" width="200" label="Name" >
              </el-table-column>
              <el-table-column prop="type" label="Type" width="100" fit>
              </el-table-column>
              <el-table-column prop="content" label="Content" fit>
              </el-table-column>
              <el-table-column label="Action" width="150" fit>
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">Delete</el-button>
                </template>
              </el-table-column>
            </el-table>


          <!-- <div :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size='50' :total="editTopologyTotalPages" style="float:right;">
            </el-pagination>

          </div> -->
          </el-tab-pane>
        </el-tabs>

        <el-dialog title="Node Profile" v-model="dialogVisible" :close-on-click-modal="false" @close="close_node_profile_dialog" >
          <el-table :data="node_profile_table_data" style="width: 100%" :show-header='false' :fit='true' stripe >
            <el-table-column prop="key" label="key" >
            </el-table-column>
            <el-table-column prop="value" label="value" >
            </el-table-column>
          </el-table>
          <hr>

          <el-button type='primary' :disabled="dialogAccessButton.disableHttpButton" @click="dialogHttpButtonGoto">HTTP</el-button>
          <el-button type='primary' :disabled="dialogAccessButton.disableHttpsButton" @click="dialogHttpsButtonGoto">HTTPS</el-button>
          <el-button type='primary' :disabled="dialogAccessButton.disableTelnetButton" @click="dialogTelnetButtonGoto">Telnet</el-button>
          <el-button type='primary' :disabled="dialogAccessButton.disableSshButton" @click="dialogSshButtonGoto">SSH</el-button>
          <el-button type='primary' :disabled="dialogAccessButton.disableConsoleButton" @click="dialogConsoleButtonGoto">Console</el-button>
          <el-button type='primary' :disabled="dialogAccessButton.disableVmWebConsoleButton" @click="dialogVmWebConsoleButtonGoto">VM Web Console</el-button>

        </el-dialog>

        <el-dialog title="vCenter" v-model="vcenterDialogVisible" :close-on-click-modal="false" @close="close_vcenter_dialog" >
          <h2>Username: {{vcenterUsername}}</h2>
          <h2>Password: {{vcenterPassword}}</h2>
          <hr>
          <el-button type='primary' @click='goto_vcenter'>Go</el-button>
        </el-dialog>


        <el-dialog title="New Topology Item" v-model="addTopologyItemVisible" :close-on-click-modal="false">
          <el-form :model="addTopologyItemModel" label-width="100px" :rules="addTopologyItemFormRules" ref="addTopologyItemForm">
            <el-form-item label="Name" prop="name">
              <el-input v-model="addTopologyItemModel.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Type" prop="type">
              <el-select v-model="addTopologyItemModel.type" placeholder="Pleae select">
                <el-option
                  v-for="item in addTopologyItemTypeOptions"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Content" prop="content">
              <el-input
                type="textarea"
                :rows="5"
                placeholder="Please input the content."
                v-model="addTopologyItemModel.content">
              </el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="addTopologyItemVisible = false">Cancel</el-button>
            <el-button type="primary" @click.native="addTopologyItemSubmit" :loading="addTopologyItemLoading">Submit</el-button>
          </div>
        </el-dialog>

        <el-dialog title="Edit Topology Item" v-model="editTopologyItemVisible" :close-on-click-modal="false">
          <el-form :model="editTopologyItemModel" label-width="100px" :rules="editTopologyItemFormRules" ref="editTopologyItemForm">
            <el-form-item label="Name" prop="name">
              <el-input v-model="editTopologyItemModel.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Type" prop="type">
              <el-select v-model="editTopologyItemModel.type" placeholder="Pleae select">
                <el-option
                  v-for="item in editTopologyItemTypeOptions"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Content" prop="content">
              <el-input
                type="textarea"
                :rows="5"
                placeholder="Please input the content."
                v-model="editTopologyItemModel.content">
              </el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="editTopologyItemVisible = false">Cancel</el-button>
            <el-button type="primary" @click.native="editTopologyItemSubmit" :loading="editTopologyItemLoading">Submit</el-button>
          </div>
        </el-dialog>

    </section>

</template>

<script>
  import cytoscape from 'cytoscape';
  import NProgress from 'nprogress';
  import { getUsTopologyData, addTopologyItem, editTopologyItem, deleteTopologyItem, getUsLabSettings  } from '../../api/api';

  export default {

    data() {
      return {
        activeName2: 'tab_topology',
        cy: 'cy string',
        loading: false,
        vcenterUrl: '',
        vcenterUsername: '',
        vcenterPassword: '',
        vcenterDialogVisible: false,

        yongshengTeamResourceLink: '',

        editTopologyTableLoading: false,
        editTopologyTotalPages: 0,
        addTopologyItemVisible: false,
        addTopologyItemModel:{
          name: '',
          type:'',
          content:''
        },
        addTopologyItemFormRules:{
          name: [
            { required: true, message: 'Pleae input the item name', trigger: 'blur' }
          ],
          type:[
            { required: true, message: 'Pleae select item type', trigger: 'blur' }
          ],
          content: [
            {required: true, message: 'Pleae input the JSON content.', trigger: 'blur'}
          ]
        },
        addTopologyItemLoading:false,
        addTopologyItemTypeOptions: [
          {label:'node', value:'node'},
          {label:'edge', value:'edge'},
          {label:'style', value:'style'}
        ],
        editTopologyItemVisible:false,
        editTopologyItemModel:{
          name: '',
          type:'',
          content:''
        },
        editTopologyItemFormRules:{
          name: [
            { required: true, message: 'Pleae input the item name', trigger: 'blur' }
          ],
          type:[
            { required: true, message: 'Pleae select item type', trigger: 'blur' }
          ],
          content: [
            {required: true, message: 'Pleae input the JSON content.', trigger: 'blur'}
          ]
        },
        editTopologyItemLoading:false,
        editTopologyItemTypeOptions: [
          {label:'node', value:'node'},
          {label:'edge', value:'edge'},
          {label:'style', value:'style'}
        ],


        dialogVisible: false,
        dialogAccessButton: {
          disableHttpButton: true,
          disableHttpsButton: true,
          disableTelnetButton: true,
          disableSshButton: true,
          disableConsoleButton: true,
          disableVmWebConsoleButton: true,
          httpUrl: '',
          httpsUrl: '',
          telnetUrl: '',
          sshUrl: '',
          consoleUrl: '',
          vmWebConsoleUrl: '',
        },
        node_profile_table_data: [],

        topologyDataTableRaw: [],
        handled_usTopologyData:{}


      };
    },

    methods: {
      click_ys_button(){
        window.open(this.yongshengTeamResourceLink,'_blank');
      },

      get_us_lab_settings(){
        console.log('running the get us lab settings method')
        // NProgress.start();
        getUsLabSettings().then((res)=>{
          console.log('the us lab settings response is:')
          console.log(res)
          for (let num in res){
            let item = res[num];
            console.log(item)
            if (item.hasOwnProperty('vcenter_url')){
              console.log('vcenter_url is :')
              console.log(item['vcenter_url'])

              this.vcenterUrl = item['vcenter_url']
            }

            if (item.hasOwnProperty('vcenter_username')){
              console.log('vcenter username is:')
              console.log(item['vcenter_username'])
              this.vcenterUsername = item['vcenter_username']
            }

            if (item.hasOwnProperty('vcenter_password')){
              this.vcenterPassword = item['vcenter_password']
            }

            if (item.hasOwnProperty('yongsheng_team_resource_link')){
              this.yongshengTeamResourceLink = item['yongsheng_team_resource_link']
            }
          }

          // NProgress.done();
        })
      },

      handleCurrentChange: function () {
        // body...
      },
      handleEdit(index, row){
        this.editTopologyItemVisible = true;
        this.editTopologyItemModel = Object.assign({}, row);

      },
      handleDel(index, row){
        this.$confirm('Are you sure to delete this item?', 'Waning', {
          type: 'warning'
        }).then(() => {
          this.editTopologyTableLoading = true;
          NProgress.start();
          // let para = { name: row.name };
          let para = row.name;
          deleteTopologyItem(para).then((res) => {
            this.editTopologyTableLoading = false;
            NProgress.done();
            this.$notify({
              title: 'Sucess',
              message: 'Delete successfully!',
              type: 'success'
            });
            this.editTopologyTableLoading = true
                NProgress.start();
                getUsTopologyData().then((res) => {
                  // this.topologyDataTableRaw = res.data.usTopologyData;
                  this.topologyDataTableRaw = res.data;
                  this.editTopologyTableLoading = false
                  NProgress.done();
                });
            // this.getUsers();
          });
        }).catch(() => {
          console.log('catch')

        });

      },
      refreshEditTopologyTable(){},
      addNewTopologyItem(){this.addTopologyItemVisible=true},
      addTopologyItemSubmit: function () {
        console.log(this.$refs.addTopologyItemForm)
        this.$refs.addTopologyItemForm.validate((valid) => {
          console.log(valid)
          if (valid) {
            this.$confirm('Are you sure to submit?', 'Add Topology Item', {}).then(() => {
              this.addTopologyItemLoading = true;
              NProgress.start();
              let para = Object.assign({}, this.addTopologyItemModel);
              console.log('post data to server, the param is:')
              console.log(para)

              addTopologyItem(para).then((res) => {
                console.log(res.status)
                this.addTopologyItemLoading = false;
                NProgress.done();
                this.$notify({
                  title: 'Success',
                  message: 'Submit successfully!',
                  type: 'success'
                });
                this.$refs['addTopologyItemForm'].resetFields();
                this.addTopologyItemVisible = false;
                this.editTopologyTableLoading = true
                NProgress.start();
                getUsTopologyData().then((res) => {
                  // this.topologyDataTableRaw = res.data.usTopologyData;
                  this.topologyDataTableRaw = res.data;
                  this.editTopologyTableLoading = false
                  NProgress.done();
                });

              });
            });
          }
        });
      },
      editTopologyItemSubmit: function () {
        this.$refs.editTopologyItemForm.validate((valid) => {
          if (valid) {
            this.$confirm('Are you sure to submit?', 'Edit Topology Item', {}).then(() => {
              this.editTopologyItemLoading = true;
              NProgress.start();
              let para = Object.assign({}, this.editTopologyItemModel);
              editTopologyItem(this.editTopologyItemModel.name, para).then((res) => {
                this.editTopologyItemLoading = false;
                NProgress.done();
                this.$notify({
                  title: 'Success',
                  message: 'Submit successfully!',
                  type: 'success'
                });

                this.$refs['editTopologyItemForm'].resetFields();
                this.editTopologyItemVisible = false;
                this.editTopologyTableLoading = true
                NProgress.start();
                getUsTopologyData().then((res) => {
                  // this.topologyDataTableRaw = res.data.usTopologyData;
                  this.topologyDataTableRaw = res.data;
                  this.editTopologyTableLoading = false
                  NProgress.done();
                });

              });
            });
          }
        });
      },

      handleTabClick(tab, event) {
        // console.log(tab, event);
        if (this.activeName2 === "tab_topology"){
          this.draw_lab_topology()
        };
      },
      refresh_method(){
        this.draw_lab_topology();
      },
      click_vcenter_button(){
        this.vcenterDialogVisible = true;
      },
      close_vcenter_dialog(){
        this.vcenterDialogVisible = false;
      },
      goto_vcenter(){window.open(this.vcenterUrl,'_blank');},


      dialogHttpButtonGoto: function(){window.open(this.dialogAccessButton.httpUrl, '_blank'); },
      dialogHttpsButtonGoto: function () {window.open(this.dialogAccessButton.httpsUrl, '_blank')},
      dialogTelnetButtonGoto: function () {window.open(this.dialogAccessButton.telnetUrl, '_blank')},
      dialogSshButtonGoto: function () {window.open(this.dialogAccessButton.sshUrl, '_blank')},
      dialogConsoleButtonGoto: function () {window.open(this.dialogAccessButton.consoleUrl, '_blank')},
      dialogVmWebConsoleButtonGoto: function () {window.open(this.dialogAccessButton.vmWebConsoleUrl, '_blank')},

      show_node_profile(data){

        this.node_profile_table_data = [];

        if (data){
          for (let i in data){
            this.node_profile_table_data.push({'key':i, 'value':data[i]})
          };
          this.dialogAccessButton.disableHttpButton = true;
          this.dialogAccessButton.disableHttpsButton = true;
          this.dialogAccessButton.disableTelnetButton = true;
          this.dialogAccessButton.disableSshButton = true;
          this.dialogAccessButton.disableConsoleButton = true;
          this.dialogAccessButton.disableVmWebConsoleButton = true;

          if (data.hasOwnProperty('mgmt_ip')){
            if (data.hasOwnProperty('http_port')){
              this.dialogAccessButton.httpUrl = 'http://'+data['mgmt_ip']+':'+data['http_port'];
              this.dialogAccessButton.disableHttpButton = false;
            };
            if (data.hasOwnProperty('https_port')){
              this.dialogAccessButton.httpsUrl = 'https://'+data['mgmt_ip']+':'+data['https_port'];
              this.dialogAccessButton.disableHttpsButton = false;
            };
            if (data.hasOwnProperty('vmware_web_console')){
              this.dialogAccessButton.vmWebConsoleUrl = 'https://'+data['mgmt_ip']+'/ui/#/login'
              this.dialogAccessButton.disableVmWebConsoleButton = false;

            };

          };
        }

        this.dialogVisible = true;
      },
      close_node_profile_dialog(){
        this.node_profile_table_data = [];
        this.dialogAccessButton = {
          disableHttpButton: true,
          disableHttpsButton: true,
          disableTelnetButton: true,
          disableSshButton: true,
          disableConsoleButton: true,
          disableVmWebConsoleButton:true,
          httpUrl: '',
          httpsUrl: '',
          telnetUrl: '',
          sshUrl: '',
          consoleUrl: '',
          vmWebConsoleUrl: '',
        };
      },

      usTopologyDataRaw_to_formal(){

        let node_list = [];
        let edge_list = [];
        let style_list = [];

        for (let num in this.topologyDataTableRaw){
          let item = this.topologyDataTableRaw[num];
          // console.log('current item is :')
          // console.log(item)


          if (item['type'] === 'node'){
            node_list.push(JSON.parse(item['content']))


          }else if (item['type'] === 'edge') {
            edge_list.push(JSON.parse(item['content']))

          }else if (item['type'] === 'style'){
            style_list.push(JSON.parse(item['content']))
          }

        };

        return {'nodeList': node_list, 'edgeList':edge_list, 'styleList': style_list};

      },

      // draw_lab_topology(){},
      only_draw_lab_topology(retransformTopoData){

        if (retransformTopoData) {
          console.log('to start transforming')
          this.handled_usTopologyData = this.usTopologyDataRaw_to_formal();
        }
        var cy = cytoscape({
            container: document.getElementById('us_lab_topology'),
            boxSelectionEnabled: true,
            autounselectify: true,

            layout: {
              name: 'preset',
              // padding: 100
            },

            ready: function (argument) {
              // body...
            },

            elements: {nodes:this.handled_usTopologyData['nodeList'], edges:this.handled_usTopologyData['edgeList']},
            style: this.handled_usTopologyData['styleList'],

          });
          cy.on('tap', 'node', { showDialog: this.show_node_profile }, function (evt) {
            // body...
            if (evt.cyTarget !== cy){

              evt.data.showDialog(evt.cyTarget.data().profile);
            };
          });

          this.cy = cy;


      },

      draw_lab_topology(){
        // this.loading = true;
        NProgress.start();
        console.log('start to draw the lab topology')

        getUsTopologyData().then((res) => {
            // console.log(res)
            console.log(res)
            console.log(res.data)
            // this.topologyDataTableRaw = res.data.usTopologyData;
            this.topologyDataTableRaw = res.data;
            this.only_draw_lab_topology(true);

          NProgress.done();
        });

      },


////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////
      showTopologyTips(){
        this.$notify.info({
          title: 'Topology Tips',
          message: '(1). Zoom in or zoom out via mouse wheel. (2).  Hold  page to move. (3). Select the node to operate.',
          duration: 10000,
          offset: 130
        });

      },
    },
    mounted() {
      this.get_us_lab_settings();
      this.draw_lab_topology();
      this.showTopologyTips();
    }

  };

</script>
<style scoped lang="scss">
    #us_lab_topology {
            width: 100%;
            height: 80vh;
            top: 0px;
            left: 0px;
        }

</style>