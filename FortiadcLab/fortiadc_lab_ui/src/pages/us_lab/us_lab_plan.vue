<template>
    <section>

        <!-- <el-col :span="24" class="toolbar" style="padding-bottom: 0px;"> -->
        <!-- <div :span="24" class="toolbar" style="padding-bottom: 0px;"> -->
        <el-tabs v-model="activeName2" @tab-click="handleTabClick" type="border-card" >
            <el-tab-pane label="IP Address" name="ip_address" >
                <div class="toolbar">
                    <el-form :inline="true" :model="ip_address_filters">
                        <el-form-item>
                            <el-input v-model="ip_address_filters.name" placeholder="Name"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" v-on:click="ip_address_getUsers">Search</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="ip_address_handleAdd">New</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="ip_address_handleRefresh" type="success" style="float:right;" >Refresh</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <!-- </el-col> -->


                <!-- <el-table stripe border :data="ip_address_table_data" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;"> -->
                <el-table stripe border :data="ip_address_table_data" highlight-current-row v-loading="ip_address_listLoading" style="width: 100%;">
                    <!-- <el-table-column type="selection" width="55">
                    </el-table-column> -->
                    <!-- <el-table-column type="index" width="60">
                    </el-table-column> -->
                    <el-table-column prop="name" label="Name" width="200" sortable fit>
                    </el-table-column>
                    <el-table-column prop="minIp" label="From" width="200" fit>
                    </el-table-column>
                    <el-table-column prop="maxIp" label="To" width="200" fit>
                    </el-table-column>
                    <el-table-column prop="ip_prefix" label="IP Prefix" width="100" fit>
                    </el-table-column>
                    <el-table-column prop="gateway" label="Gateway" width="200" fit>
                    </el-table-column>
                    <!-- <el-table-column prop="vlan" label="VLAN" width="160" fit>
                    </el-table-column>
                    <el-table-column prop="ha_id" label="HA ID" width="140" fit>
                    </el-table-column>
                    <el-table-column prop="ospf_area_id" label="OSPF Area ID" min-width="100" fit>
                    </el-table-column> -->
                    <el-table-column prop="description" label="Description" min-width="180" fit>
                    </el-table-column>
                    <el-table-column label="Action" width="150" fit>
                        <template scope="scope">
                            <el-button size="small" @click="ip_address_handleEdit(scope.$index, scope.row)">Edit</el-button>
                            <el-button type="danger" size="small" @click="ip_address_handleDel(scope.$index, scope.row)">Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <!--工具条-->

                <!-- <div :span="24" class="toolbar"> -->
                <div class="toolbar">
                    <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">Delete All</el-button> -->
                    <el-pagination layout="prev, pager, next" @current-change="ip_address_handleCurrentChange" :page-size='ip_address_table_page_size' :total="ip_address_total" style="float:right;">
                    </el-pagination>
                </div>

            </el-tab-pane>


            <el-tab-pane label="VLAN" name="vlan" >
                <div class="toolbar">
                    <el-form :inline="true" :model="vlan_filters">
                        <el-form-item>
                            <el-input v-model="vlan_filters.name" placeholder="Name"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" v-on:click="vlan_getUsers">Search</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="vlan_handleAdd">New</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="vlan_handleRefresh" type="success" style="float:right;" >Refresh</el-button>
                        </el-form-item>
                    </el-form>
                </div>

                <el-table stripe border :data="vlan_table_data" highlight-current-row v-loading="vlan_listLoading" style="width: 100%;">
                    <el-table-column prop="name" label="Name" width="200" sortable fit>
                    </el-table-column>
                    <el-table-column prop="minVlan" label="From" width="140" fit>
                    </el-table-column>
                    <el-table-column prop="maxVlan" label="To" width="140" fit>
                    </el-table-column>
                    <el-table-column prop="description" label="Description" min-width="180" fit>
                    </el-table-column>
                    <el-table-column label="Action" width="150" fit>
                        <template scope="scope">
                            <el-button size="small" @click="vlan_handleEdit(scope.$index, scope.row)">Edit</el-button>
                            <el-button type="danger" size="small" @click="vlan_handleDel(scope.$index, scope.row)">Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>


                <div class="toolbar">
                    <el-pagination layout="prev, pager, next" @current-change="vlan_handleCurrentChange" :page-size='vlan_table_page_size' :total="vlan_total" style="float:right;">
                    </el-pagination>
                </div>
            </el-tab-pane>



            <el-tab-pane label="HA ID" name="ha_id" >
                <div class="toolbar">
                    <el-form :inline="true" :model="ha_id_filters">
                        <el-form-item>
                            <el-input v-model="ha_id_filters.name" placeholder="Name"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" v-on:click="ha_id_getUsers">Search</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="ha_id_handleAdd">New</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="ha_id_handleRefresh" type="success" style="float:right;" >Refresh</el-button>
                        </el-form-item>
                    </el-form>
                </div>

                <el-table stripe border :data="ha_id_table_data" highlight-current-row v-loading="ha_id_listLoading" style="width: 100%;">
                    <el-table-column prop="name" label="Name" width="200" sortable fit>
                    </el-table-column>
                    <el-table-column prop="minHaId" label="From" width="120" fit>
                    </el-table-column>
                    <el-table-column prop="maxHaId" label="To" width="120" fit>
                    </el-table-column>
                    <el-table-column prop="description" label="Description" min-width="180" fit>
                    </el-table-column>
                    <el-table-column label="Action" width="150" fit>
                        <template scope="scope">
                            <el-button size="small" @click="ha_id_handleEdit(scope.$index, scope.row)">Edit</el-button>
                            <el-button type="danger" size="small" @click="ha_id_handleDel(scope.$index, scope.row)">Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>


                <div class="toolbar">
                    <el-pagination layout="prev, pager, next" @current-change="ha_id_handleCurrentChange" :page-size='ha_id_table_page_size' :total="ha_id_total" style="float:right;">
                    </el-pagination>
                </div>
            </el-tab-pane>

        </el-tabs>

        <!--Edit IP Address-->
        <el-dialog title="Edit" v-model="ip_address_editFormVisible" :close-on-click-modal="false">
            <el-form :model="ip_address_editForm" label-width="80px" :rules="ip_address_editFormRules" ref="ip_address_editForm">
                <el-form-item label="name" prop="ip_address_name">
                    <el-input v-model="ip_address_editForm.name" auto-complete="on"></el-input>
                </el-form-item>
                <el-form-item label="IP Range">
                    <el-col :span="10">
                        <el-input v-model="ip_address_editForm.minIp" auto-complete="on"></el-input>
                    </el-col>
                    <el-col class="line" :span="1">to</el-col>
                    <el-col :span="10">
                        <el-input v-model="ip_address_editForm.maxIp" auto-complete="on"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Prefix">
                    <el-input-number v-model="ip_address_editForm.ip_prefix" :min="0" :max="128"></el-input-number>
                </el-form-item>
                <el-form-item label="Gateway">
                    <el-input v-model="ip_address_editForm.gateway" auto-complete="on"></el-input>
                </el-form-item>
                <!-- <el-form-item label="HA ID">
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
                </el-form-item> -->
                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="ip_address_editForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ip_address_editFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="ip_address_editSubmit" :loading="ip_address_editLoading">Submit</el-button>
            </div>
        </el-dialog>

        <!--Add IP Address-->
        <el-dialog title="New" v-model="ip_address_addFormVisible" :close-on-click-modal="false">
            <el-form :model="ip_address_addForm" label-width="80px" :rules="ip_address_addFormRules" ref="ip_address_addForm">
                <el-form-item label="Name" prop="ip_address_name">
                    <el-input v-model="ip_address_addForm.name" auto-complete="on"></el-input>
                </el-form-item>
                <el-form-item label="IP">
                    <el-col :span="10">
                        <el-input v-model="ip_address_addForm.minIp" auto-complete="on"></el-input>
                    </el-col>
                    <el-col class="line" :span="1">to</el-col>
                    <el-col :span="10">
                        <el-input v-model="ip_address_addForm.maxIp" auto-complete="on"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Prefix">
                    <el-input-number v-model="ip_address_addForm.ip_prefix" :min="0" :max="128"></el-input-number>
                </el-form-item>
                <el-form-item label="Gateway">
                    <el-input v-model="ip_address_addForm.gateway" auto-complete="on"></el-input>
                </el-form-item>
                <!-- <el-form-item label="HA ID">
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
                </el-form-item> -->
                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="ip_address_addForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ip_address_addFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="ip_address_addSubmit" :loading="ip_address_addLoading">Submit</el-button>
            </div>
        </el-dialog>


        <!-- Edit VLAN -->
        <el-dialog title="Edit" v-model="vlan_editFormVisible" :close-on-click-modal="false">
            <el-form :model="vlan_editForm" label-width="80px" :rules="vlan_editFormRules" ref="vlan_editForm">
                <el-form-item label="name" prop="vlan_name">
                    <el-input v-model="vlan_editForm.name" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item label="VLAN Range">
                    <el-col :span="7">
                        <el-input-number v-model="vlan_editForm.minVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="vlan_editForm.maxVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                </el-form-item>
                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="vlan_editForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="vlan_editFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="vlan_editSubmit" :loading="vlan_editLoading">Submit</el-button>
            </div>
        </el-dialog>

        <!-- Add VLAN -->
        <el-dialog title="New" v-model="vlan_addFormVisible" :close-on-click-modal="false">
            <el-form :model="vlan_addForm" label-width="80px" :rules="vlan_addFormRules" ref="vlan_addForm">
                <el-form-item label="Name" prop="vlan_name">
                    <el-input v-model="vlan_addForm.name" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item label="VLAN Range">
                    <el-col :span="7">
                        <el-input-number v-model="vlan_addForm.minVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="vlan_addForm.maxVlan" :min="0" :max="4095"></el-input-number>
                    </el-col>
                </el-form-item>

                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="vlan_addForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="vlan_addFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="vlan_addSubmit" :loading="vlan_addLoading">Submit</el-button>
            </div>
        </el-dialog>

        <!-- Edit HA ID -->
        <el-dialog title="Edit" v-model="ha_id_editFormVisible" :close-on-click-modal="false">
            <el-form :model="ha_id_editForm" label-width="80px" :rules="ha_id_editFormRules" ref="ha_id_editForm">
                <el-form-item label="name" prop="ha_id_name">
                    <el-input v-model="ha_id_editForm.name" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item label="HA ID">
                    <el-col :span="7">
                        <el-input-number v-model="ha_id_editForm.minHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="ha_id_editForm.maxHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                </el-form-item>

                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="ha_id_editForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ha_id_editFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="ha_id_editSubmit" :loading="ha_id_editLoading">Submit</el-button>
            </div>
        </el-dialog>



        <!-- Add HA ID -->
        <el-dialog title="New" v-model="ha_id_addFormVisible" :close-on-click-modal="false">
            <el-form :model="ha_id_addForm" label-width="80px" :rules="ha_id_addFormRules" ref="ha_id_addForm">
                <el-form-item label="Name" prop="ha_id_name">
                    <el-input v-model="ha_id_addForm.name" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item label="HA ID">
                    <el-col :span="7">
                        <el-input-number v-model="ha_id_addForm.minHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                    <el-col class="line" :span="2">to</el-col>
                    <el-col :span="7">
                        <el-input-number v-model="ha_id_addForm.maxHaId" :min="0" :max="32"></el-input-number>
                    </el-col>
                </el-form-item>

                <el-form-item label="Description">
                    <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="Please input the content."
                    v-model="ha_id_addForm.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="ha_id_addFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click.native="ha_id_addSubmit" :loading="ha_id_addLoading">Submit</el-button>
            </div>
        </el-dialog>





    </section>

</template>

<script>
    import util from '../../common/js/util'
    import NProgress from 'nprogress'
    import { getUsLabResourceList, addUsLabResourceRecord, removeUsLabResourceRecord, editUsLabResourceRecord } from '../../api/api';
    import { getUsLabResourcePlanIpAddress, addUsLabResourcePlanIpAddress, removeUsLabResourcePlanIpAddress, editUsLabResourcePlanIpAddress } from '../../api/api';
    import { getUsLabResourcePlanHaId, addUsLabResourcePlanHaId, removeUsLabResourcePlanHaId, editUsLabResourcePlanHaId } from '../../api/api';
    import { getUsLabResourcePlanVlan, addUsLabResourcePlanVlan, removeUsLabResourcePlanVlan, editUsLabResourcePlanVlan } from '../../api/api';
    // import { getUsLabResourcePlanOspfId, addUsLabResourcePlanOspfId, removeUsLabResourcePlanOspfId, editUsLabResourcePlanOspfId } from '../../api/api';

    export default {
        data() {
            return {
                activeName2: 'ip_address',
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
                table_page_size:50,
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

                },

                //////////////////////////////////////////////////////////////////////////////////////////////////////////
                ip_address_filters:{
                    name: ""
                },
                ip_address_table_data: [
                    {
                        id: 1,
                        name:'bo fei',
                        minIp:'0.0.0.0',
                        maxIp:'0.0.0.0',
                        ip_prefix:'0',
                        gateway:'0.0.0.0',
                        description: ''
                    }

                ],

                ip_address_total: 0,
                ip_address_page: 1,
                ip_address_table_page_size:5,
                ip_address_listLoading: false,
                // sels: [],//列表选中列

                ip_address_editFormVisible: false,//编辑界面是否显示
                ip_address_editLoading: false,
                ip_address_editFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                ip_address_editForm: {
                    id: 0,
                    name: '',

                },

                ip_address_addFormVisible: false,//新增界面是否显示
                ip_address_addLoading: false,
                ip_address_addFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                ip_address_addForm: {
                    name: '',
                },

                //-------------------------------------------------------------------------------
                ha_id_filters:{
                    name: ""
                },
                ha_id_table_data: [
                    {
                        id: 1,
                        name:'bo fei',
                        minHaId:'1',
                        maxHaId:'5',
                        description: ''
                    }

                ],

                ha_id_total: 0,
                ha_id_page: 1,
                ha_id_table_page_size:5,
                ha_id_listLoading: false,
                // sels: [],//列表选中列

                ha_id_editFormVisible: false,//编辑界面是否显示
                ha_id_editLoading: false,
                ha_id_editFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                ha_id_editForm: {
                    id: 0,
                    name: '',

                },

                ha_id_addFormVisible: false,//新增界面是否显示
                ha_id_addLoading: false,
                ha_id_addFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                ha_id_addForm: {
                    name: '',
                },

                //-------------------------------------------------------------------------------
                vlan_filters:{
                    name: ""
                },
                vlan_table_data: [
                    {
                        id: 1,
                        name:'bo fei',
                        minVlan:'1000',
                        maxVlan:'1100',
                        description: ''
                    }

                ],

                vlan_total: 0,
                vlan_page: 1,
                vlan_table_page_size:5,
                vlan_listLoading: false,
                // sels: [],//列表选中列

                vlan_editFormVisible: false,//编辑界面是否显示
                vlan_editLoading: false,
                vlan_editFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                vlan_editForm: {
                    id: 0,
                    name: '',

                },

                vlan_addFormVisible: false,//新增界面是否显示
                vlan_addLoading: false,
                vlan_addFormRules: {
                    name: [
                        { required: true, message: 'Please input name', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                vlan_addForm: {
                    name: '',
                },



            }
        },
        methods: {
            handleTabClick: function (tab, event) {
                // body...
                // if (this.activeName2 === "tab_topology"){
                //     this.draw_lab_topology()
                // };
            },



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
                    para = para+'name='+this.filters.name+'&'
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
                        if (this.table_data_raw.length == 1 && this.total == 1){
                        } else {
                            if (this.table_data_raw.length == 1 ){
                                this.page = this.page-1;
                            }
                        }
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


            ////////////////////////////////////////////////////// IP Address Method //////////////////////////////////////////////////////////////
            ip_address_getUsers() {
                let para = ''
                if (this.ip_address_filters.name != ''){
                    para = para+'name='+this.ip_address_filters.name+'&'
                }

                para = para+'page_size='+this.ip_address_table_page_size+'&page='+this.ip_address_page
                this.ip_address_listLoading = true;
                NProgress.start();
                getUsLabResourcePlanIpAddress(para).then((res) => {
                    this.ip_address_total = res.data.count;
                    // this.table_data_raw = res.data.results;
                    // this.transformTableDataRawToFormal();
                    this.ip_address_table_data = res.data.results;
                    this.ip_address_listLoading = false;
                    NProgress.done();
                });
            },
            ip_address_refresh_table(){
                let para = 'page_size='+this.ip_address_table_page_size+'&page=1'
                this.ip_address_listLoading = true;
                NProgress.start();
                getUsLabResourcePlanIpAddress(para).then((res) => {
                    this.ip_address_total = res.data.count;
                    this.ip_address_table_data = res.data.results;
                    this.ip_address_listLoading = false;
                    NProgress.done();
                });

            },
            ip_address_handleRefresh: function () {
                // body...
                this.ip_address_refresh_table()
            },

            ip_address_handleCurrentChange(val) {
                this.ip_address_page = val;
                this.ip_address_getUsers();
            },
            ip_address_handleDel: function (index, row) {
                this.$confirm('Are you sure to delete it?', 'Warning', {
                    type: 'warning'
                }).then(() => {
                    this.ip_address_listLoading = true;
                    NProgress.start();
                    // let para = { id: row.id };
                    let para = row.id;
                    removeUsLabResourcePlanIpAddress(para).then((res) => {

                        this.ip_address_listLoading = false;
                        NProgress.done();
                        this.$notify({
                            title: 'Success',
                            message: 'Delete success!',
                            type: 'success'
                        });
                        if (this.ip_address_table_data.length == 1 && this.ip_address_total == 1){
                        } else {
                            if (this.ip_address_table_data.length == 1 ){
                                this.ip_address_page = this.ip_address_page-1;
                            }
                        }
                        this.ip_address_getUsers();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            ip_address_handleEdit: function (index, row) {
                this.ip_address_editFormVisible = true;
                let myRow = ''
                for (let num in this.ip_address_table_data){
                    if (this.ip_address_table_data[num]['id'] == row.id){
                        myRow = this.ip_address_table_data[num]
                    }
                }
                this.ip_address_editForm = Object.assign({}, myRow);

            },
            //显示新增界面
            ip_address_handleAdd: function () {
                this.ip_address_addFormVisible = true;
                this.ip_address_addForm = {
                    name: '',
                };
            },
            //编辑
            ip_address_editSubmit: function () {
                this.$refs.ip_address_editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Edit', {}).then(() => {
                            this.ip_address_editLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.ip_address_editForm);
                            editUsLabResourcePlanIpAddress(para.id, para).then((res) => {
                                this.ip_address_editLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['ip_address_editForm'].resetFields();
                                this.ip_address_editFormVisible = false;
                                this.ip_address_getUsers();
                            });
                        });
                    }
                });
            },
            //新增
            ip_address_addSubmit: function () {
                this.$refs.ip_address_addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Add', {}).then(() => {
                            this.ip_address_addLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.ip_address_addForm);
                            addUsLabResourcePlanIpAddress(para).then((res) => {
                                this.ip_address_addLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['ip_address_addForm'].resetFields();
                                this.ip_address_addFormVisible = false;
                                this.ip_address_getUsers();
                            });
                        });
                    }
                });
            },

            //------------------------------------------------ VLAN method ---------------------------------------------------------------------
            vlan_getUsers() {
                let para = ''
                if (this.vlan_filters.name != ''){
                    para = para+'name='+this.vlan_filters.name+'&'
                }

                para = para+'page_size='+this.vlan_table_page_size+'&page='+this.vlan_page
                this.vlan_listLoading = true;
                NProgress.start();
                getUsLabResourcePlanVlan(para).then((res) => {
                    this.vlan_total = res.data.count;
                    // this.table_data_raw = res.data.results;
                    // this.transformTableDataRawToFormal();
                    this.vlan_table_data = res.data.results;
                    this.vlan_listLoading = false;
                    NProgress.done();
                });
            },
            vlan_refresh_table(){
                let para = 'page_size='+this.vlan_table_page_size+'&page=1'
                this.vlan_listLoading = true;
                NProgress.start();
                getUsLabResourcePlanVlan(para).then((res) => {
                    this.vlan_total = res.data.count;
                    // this.table_data_raw = res.data.results;
                    // this.transformTableDataRawToFormal();
                    this.vlan_table_data = res.data.results;
                    this.vlan_listLoading = false;
                    NProgress.done();
                });

            },
            vlan_handleRefresh: function () {
                // body...
                this.vlan_refresh_table()
            },

            vlan_handleCurrentChange(val) {
                this.vlan_page = val;
                this.vlan_getUsers();
            },
            vlan_handleDel: function (index, row) {
                this.$confirm('Are you sure to delete it?', 'Warning', {
                    type: 'warning'
                }).then(() => {
                    this.vlan_listLoading = true;
                    NProgress.start();
                    // let para = { id: row.id };
                    let para = row.id;
                    removeUsLabResourcePlanVlan(para).then((res) => {

                        this.vlan_listLoading = false;
                        NProgress.done();
                        this.$notify({
                            title: 'Success',
                            message: 'Delete success!',
                            type: 'success'
                        });
                        if (this.vlan_table_data.length == 1 && this.vlan_total == 1){
                        } else {
                            if (this.vlan_table_data.length == 1 ){
                                this.vlan_page = this.vlan_page-1;
                            }
                        }
                        this.vlan_getUsers();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            vlan_handleEdit: function (index, row) {
                this.vlan_editFormVisible = true;
                let myRow = ''
                for (let num in this.vlan_table_data){
                    if (this.vlan_table_data[num]['id'] == row.id){
                        myRow = this.vlan_table_data[num]
                    }
                }
                this.vlan_editForm = Object.assign({}, myRow);

            },
            //显示新增界面
            vlan_handleAdd: function () {
                this.vlan_addFormVisible = true;
                this.vlan_addForm = {
                    name: '',
                };
            },
            //编辑
            vlan_editSubmit: function () {
                this.$refs.vlan_editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Edit', {}).then(() => {
                            this.vlan_editLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.vlan_editForm);
                            editUsLabResourcePlanVlan(para.id, para).then((res) => {
                                this.vlan_editLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['vlan_editForm'].resetFields();
                                this.vlan_editFormVisible = false;
                                this.vlan_getUsers();
                            });
                        });
                    }
                });
            },
            //新增
            vlan_addSubmit: function () {
                this.$refs.vlan_addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Add', {}).then(() => {
                            this.vlan_addLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.vlan_addForm);
                            addUsLabResourcePlanVlan(para).then((res) => {
                                this.vlan_addLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['vlan_addForm'].resetFields();
                                this.vlan_addFormVisible = false;
                                this.vlan_getUsers();
                            });
                        });
                    }
                });
            },




            //----------------------------------------------- HA ID ------------------------------------------------------------------------------------
            ha_id_getUsers() {
                let para = ''
                if (this.ha_id_filters.name != ''){
                    para = para+'name='+this.ha_id_filters.name+'&'
                }

                para = para+'page_size='+this.ha_id_table_page_size+'&page='+this.ha_id_page
                this.ha_id_listLoading = true;
                NProgress.start();
                getUsLabResourcePlanHaId(para).then((res) => {
                    this.ha_id_total = res.data.count;
                    // this.table_data_raw = res.data.results;
                    // this.transformTableDataRawToFormal();
                    this.ha_id_table_data = res.data.results;
                    this.ha_id_listLoading = false;
                    NProgress.done();
                });
            },
            ha_id_refresh_table(){
                let para = 'page_size='+this.ha_id_table_page_size+'&page=1'
                this.ha_id_listLoading = true;
                NProgress.start();
                getUsLabResourcePlanHaId(para).then((res) => {
                    this.ha_id_total = res.data.count;
                    // this.table_data_raw = res.data.results;
                    // this.transformTableDataRawToFormal();
                    this.ha_id_table_data = res.data.results;
                    this.ha_id_listLoading = false;
                    NProgress.done();
                });

            },
            ha_id_handleRefresh: function () {
                // body...
                this.ha_id_refresh_table()
            },

            ha_id_handleCurrentChange(val) {
                this.ha_id_page = val;
                this.ha_id_getUsers();
            },
            ha_id_handleDel: function (index, row) {
                this.$confirm('Are you sure to delete it?', 'Warning', {
                    type: 'warning'
                }).then(() => {
                    this.ha_id_listLoading = true;
                    NProgress.start();
                    // let para = { id: row.id };
                    let para = row.id;
                    removeUsLabResourcePlanHaId(para).then((res) => {

                        this.ha_id_listLoading = false;
                        NProgress.done();
                        this.$notify({
                            title: 'Success',
                            message: 'Delete success!',
                            type: 'success'
                        });
                        if (this.ha_id_table_data.length == 1 && this.ha_id_total == 1){
                        } else {
                            if (this.ha_id_table_data.length == 1 ){
                                this.ha_id_page = this.ha_id_page-1;
                            }
                        }
                        this.ha_id_getUsers();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            ha_id_handleEdit: function (index, row) {
                this.ha_id_editFormVisible = true;
                let myRow = ''
                for (let num in this.ha_id_table_data){
                    if (this.ha_id_table_data[num]['id'] == row.id){
                        myRow = this.ha_id_table_data[num]
                    }
                }
                this.ha_id_editForm = Object.assign({}, myRow);

            },
            //显示新增界面
            ha_id_handleAdd: function () {
                this.ha_id_addFormVisible = true;
                this.ha_id_addForm = {
                    name: '',
                };
            },
            //编辑
            ha_id_editSubmit: function () {
                this.$refs.ha_id_editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Edit', {}).then(() => {
                            this.ha_id_editLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.ha_id_editForm);
                            editUsLabResourcePlanHaId(para.id, para).then((res) => {
                                this.ha_id_editLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['ha_id_editForm'].resetFields();
                                this.ha_id_editFormVisible = false;
                                this.ha_id_getUsers();
                            });
                        });
                    }
                });
            },
            //新增
            ha_id_addSubmit: function () {
                this.$refs.ha_id_addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('Are you sure to submit?', 'Add', {}).then(() => {
                            this.ha_id_addLoading = true;
                            NProgress.start();
                            let para = Object.assign({}, this.ha_id_addForm);
                            addUsLabResourcePlanHaId(para).then((res) => {
                                this.ha_id_addLoading = false;
                                NProgress.done();
                                this.$notify({
                                    title: 'Success',
                                    message: 'Submit successfully!',
                                    type: 'success'
                                });
                                this.$refs['ha_id_addForm'].resetFields();
                                this.ha_id_addFormVisible = false;
                                this.ha_id_getUsers();
                            });
                        });
                    }
                });
            },


        },
        mounted() {
            this.ip_address_getUsers();
            this.vlan_getUsers();
            this.ha_id_getUsers();
        }
    }

</script>

<style scoped lang="scss">

</style>