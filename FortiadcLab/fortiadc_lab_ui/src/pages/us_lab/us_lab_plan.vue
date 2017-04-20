<template>
    <section>

        <!-- <el-col :span="24" class="toolbar" style="padding-bottom: 0px;"> -->
        <div :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="Name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getUsers">Search</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">New</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleRefresh">Refresh</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- </el-col> -->


        <el-table stripe border :data="table_data" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <!-- <el-table-column type="selection" width="55">
            </el-table-column> -->
            <!-- <el-table-column type="index" width="60">
            </el-table-column> -->
            <el-table-column prop="name" label="Name" width="120" sortable fit>
            </el-table-column>
            <el-table-column prop="ip" label="IP Address" width="220" fit>
            </el-table-column>
            <el-table-column prop="ip_prefix" label="IP Prefix" width="100" fit>
            </el-table-column>
            <el-table-column prop="gateway" label="Gateway" width="120" fit>
            </el-table-column>
            <el-table-column prop="vlan" label="VLAN" width="160" fit>
            </el-table-column>
            <el-table-column prop="ha_id" label="HA ID" width="140" fit>
            </el-table-column>
            <el-table-column prop="ospf_area_id" label="OSPF Area ID" min-width="100" fit>
            </el-table-column>
            <el-table-column prop="description" label="Description" min-width="180" fit>
            </el-table-column>
            <el-table-column label="Action" width="150" fit>
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->

        <!-- <div :span="24" class="toolbar"> -->
        <div :span="24" >
            <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">Delete All</el-button> -->
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size='table_page_size' :total="total" style="float:right;">
            </el-pagination>
        </div>

        <!--编辑界面-->
        <el-dialog title="Edit" v-model="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="name" prop="name">
                    <el-input v-model="editForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="IP">
                    <el-col :span="10">
                        <el-input v-model="editForm.minIp" auto-complete="off"></el-input>
                    </el-col>
                    <el-col class="line" :span="1">to</el-col>
                    <el-col :span="10">
                        <el-input v-model="editForm.maxIp" auto-complete="off"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Prefix">
                    <el-input-number v-model="editForm.ip_prefix" :min="1" :max="128"></el-input-number>
                </el-form-item>
                <el-form-item label="Gateway">
                    <el-input v-model="editForm.gateway" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="HA ID">
                    <el-col :span="7">
                        <el-input-number v-model="editForm.minHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="editForm.maxHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="VLAN">
                    <el-col :span="7">
                        <el-input-number v-model="editForm.minVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="editForm.maxVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="OSPF ID">
                    <el-col :span="7">
                        <el-input-number v-model="editForm.minOspfId" :min="0" :max="4095"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="editForm.maxOspfId" :min="0" :max="4095"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="editForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">Submit</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="New" v-model="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="Name" prop="name">
                    <el-input v-model="addForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="IP">
                    <el-col :span="10">
                        <el-input v-model="addForm.minIp" auto-complete="off"></el-input>
                    </el-col>
                    <el-col class="line" :span="1">to</el-col>
                    <el-col :span="10">
                        <el-input v-model="addForm.maxIp" auto-complete="off"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Prefix">
                    <el-input-number v-model="addForm.ip_prefix" :min="0" :max="128"></el-input-number>
                </el-form-item>
                <el-form-item label="Gateway">
                    <el-input v-model="addForm.gateway" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="HA ID">
                    <el-col :span="7">
                        <el-input-number v-model="addForm.minHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="addForm.maxHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="VLAN">
                    <el-col :span="7">
                        <el-input-number v-model="addForm.minVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="addForm.maxVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="OSPF ID">
                    <el-col :span="7">
                        <el-input-number v-model="addForm.minOspfId" :min="0" :max="4095"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="addForm.maxOspfId" :min="0" :max="4095"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="addForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">Submit</el-button>
            </div>
        </el-dialog>

    </section>

</template>

<script>
    import util from '../../common/js/util'
    import NProgress from 'nprogress'
    import { getUsLabResourceList, addUsLabResourceRecord, removeUsLabResourceRecord, editUsLabResourceRecord } from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                table_data: [
                    {
                        id: 1,
                        name: 'bo fei',
                        ip: '1.1.1.1-1.1.1.10',
                        ip_prefix:24,
                        gateway:'1.1.1.254',
                        vlan:'1000-1100',
                        ha_id:'1-5',
                        ospf_area_id:'10-20',
                        description:'some description'
                    },
                ],
                table_data_raw: [
                    {
                        id:1,
                        name:'bo fei',
                        minIp:'1.1.1.1',
                        maxIp:'1.1.1.10',
                        ip_prefix:'24',
                        gateway:'1.1.1.254',
                        minHaId:'1',
                        maxHaId:'5',
                        minVlan:'1000',
                        maxVlan:'1100',
                        minOspfId:10,
                        maxOspfId:20,
                        description:'some description'
                    },
                ],
                total: 0,
                page: 1,
                table_page_size:3,
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                editForm: {
                    id: 0,
                    name: '',

                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                addForm: {
                    name: '',

                }

            }
        },
        methods: {
            //性别显示转换
            handleRefresh: function () {
                // body...
                this.getUsers()
            },

            handleCurrentChange(val) {
                this.page = val;
                this.getUsers();
            },
            transformTableDataRawToFormal(){
                let new_table_data = []
                for (let item in this.table_data_raw){
                    let currentItem = this.table_data_raw[item]
                    let itemObj = {}
                    itemObj['id'] = currentItem['id']
                    itemObj['name'] = currentItem['name']

                    let prefixString = ''
                    let minIpString = ''
                    let maxIpString = ''
                    let minVlanString = ''
                    let maxVlanString = ''
                    let minHaIdString = ''
                    let maxHaIdString = ''
                    let minOspfIdString = ''
                    let maxOspfIdString = ''

                    if (currentItem['ip_prefix'] != null && currentItem['ip_prefix'] != 0){
                        prefixString = currentItem['ip_prefix']
                    }
                    if (currentItem['minIp'] != null){
                        minIpString = currentItem['minIp']
                    }
                    if (currentItem['maxIp'] != null){
                        maxIpString = currentItem['maxIp']
                    }
                    if (currentItem['minVlan'] != null && currentItem['minVlan'] != 0){
                        minVlanString = currentItem['minVlan']
                    }
                    if (currentItem['maxVlan'] != null && currentItem['maxVlan'] != 0){
                        maxVlanString = currentItem['maxVlan']
                    }
                    if (currentItem['minHaId'] != null && currentItem['minHaId'] != 0){
                        minHaIdString = currentItem['minHaId']
                    }
                    if (currentItem['maxHaId'] != null && currentItem['maxHaId'] !=0 ){
                        maxHaIdString = currentItem['maxHaId']
                    }
                    if (currentItem['minOspfId'] != null && currentItem['minOspfId'] != 0){
                        minOspfIdString = currentItem['minOspfId']
                    }
                    if (currentItem['maxOspfId'] != null && currentItem['maxOspfId'] != 0){
                        maxOspfIdString = currentItem['maxOspfId']
                    }

                    itemObj['ip'] = minIpString+'-'+maxIpString
                    itemObj['ip_prefix'] = prefixString
                    itemObj['gateway'] = currentItem['gateway']
                    itemObj['vlan'] = minVlanString+'-'+maxVlanString
                    itemObj['ospf_area_id'] = minOspfIdString+'-'+maxOspfIdString
                    itemObj['ha_id'] = minHaIdString+'-'+maxHaIdString
                    itemObj['description'] = currentItem['description']


                    new_table_data.push(itemObj)
                }

                this.table_data = new_table_data

            },
            //获取用户列表
            getUsers() {
                // let para = {
                //     page: this.page,
                //     name: this.filters.name,
                //     page_size: this.table_page_size
                // };
                let para = ''
                if (this.filters.name != ''){
                    para = para+this.filters.name+'&'
                }
                para = para+'page_size='+this.table_page_size+'&page='+this.page
                this.listLoading = true;
                NProgress.start();
                getUsLabResourceList(para).then((res) => {
                    this.total = res.data.count;
                    this.table_data_raw = res.data.results;
                    this.transformTableDataRawToFormal();
                    this.listLoading = false;
                    NProgress.done();
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('Are you sure to delete it?', 'Warning', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    NProgress.start();
                    // let para = { id: row.id };
                    let para = row.id;
                    removeUsLabResourceRecord(para).then((res) => {
                        this.listLoading = false;
                        NProgress.done();
                        this.$notify({
                            title: 'Success',
                            message: 'Delete success!',
                            type: 'success'
                        });
                        this.getUsers();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                let myRow = ''
                for (let num in this.table_data_raw){
                    if (this.table_data_raw[num]['id'] == row.id){
                        myRow = this.table_data_raw[num]
                    }

                }

                this.editForm = Object.assign({}, myRow);

                console.log(this.editForm)
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    name: '',
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Edit', {}).then(() => {
                            this.editLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            editUsLabResourceRecord(para.id, para).then((res) => {
                                this.editLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Add', {}).then(() => {
                            this.addLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            console.log('add debug:')
                            console.log(para)
                            addUsLabResourceRecord(para).then((res) => {
                                this.addLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getUsers();
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            // batchRemove: function () {
            //     var ids = this.sels.map(item => item.id).toString();
            //     this.$confirm('Are you sure to delete selected items?', 'Warning', {
            //         type: 'warning'
            //     }).then(() => {
            //         this.listLoading = true;
            //         NProgress.start();
            //         let para = { ids: ids };
            //         batchRemoveUsLabResourceRecord(para).then((res) => {
            //             this.listLoading = false;
            //             NProgress.done();
            //             this.$notify({
            //                 title: 'Success',
            //                 message: 'Delete successfully!',
            //                 type: 'success'
            //             });
            //             this.getUsers();
            //         });
            //     }).catch(() => {

            //     });
            // }
        },
        mounted() {
            this.getUsers();
        }
    }

</script>

<style scoped lang="scss">

</style>