<template>
    <section>

        <el-tabs v-model="activeName2"  @tab-click="handleClick" type="border-card" >
        <el-tab-pane label="Topology" name="tab_topology" >
            <div id = 'tool_panel' class="toolbar">
                <el-button type="primary" @click='refresh_method' >Refresh</el-button>
                <el-button type="primary" @click='goto_vcenter' >vCenter</el-button>
            </div>
            <div id="us_lab_topology" v-loading.body="loading"></div>
        </el-tab-pane>
        <el-tab-pane label="Edit Topology" name="tab_topo_model">tab2</el-tab-pane>
        </el-tabs>
        <!-- <div id="us_lab_topology"></div> -->
    </section>

</template>

<script>
  import cytoscape from 'cytoscape';
  import NProgress from 'nprogress';

  export default {

    data() {
      return {
        activeName2: 'tab_topology',
        cy: 'cy string',
        loading: false,
        vcenterUrl: 'https://10.106.4.246',

      };
    },
    methods: {
      handleClick(tab, event) {
        // console.log(tab, event);
      },
      refresh_method(){
        this.draw_lab_topology();
      },
      goto_vcenter(){
        window.open(this.vcenterUrl,'_blank');
      },
      getTopologyData(){
        console.log('Trying to get the backend data.');

      },

      draw_lab_topology(){
        NProgress.start();
        this.loading = true;
        this.getTopologyData();
        var cy = cytoscape({
          // this.cy = cytoscape({
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

          elements: {
            nodes: [
              // xpadding: 200, ypadding: 40
              { data: { id: 'Rack8' , name: 'Rack8\nDev'} },
              // { data: { id: 'Rack9' } },
              // { data: { id: 'Rack10' } },
              // { data: { id: 'Rack11', name: 'R11_Tac' } },
              { data: { id: 'Rack12', name: 'Rack12\nPerformance' } },
              { data: { id: 'Rack13', name: 'Rack13\nPerformance' } },
              // { data: { id: 'Rack14' } },
              { data: { id: 'Rack15', name: 'Rack15\nPlatform' } },
              { data: { id: 'Rack16', name: 'Rack16\nQA' } },

              // { data: { id: 'R9_UP_SW', parent: 'Rack9', name:'UP_SW' }, position: {x:10, y:10} },
              // { data: { id: 'R10_UP_SW', parent: 'Rack10', name: 'UP_SW' }, position: {x:210, y:10} },
              // { data: { id: 'R11_UP_SW', parent: 'Rack11', name: 'UP_SW' }, position: {x:410, y:10} },
              { data: { id: 'R12_UP_SW', parent: 'Rack12', name: 'UP_SW' }, position: {x:610, y:10} },
              { data: { id: 'R12_SW249', parent: 'Rack12', name:'SW249' }, position: {x:610, y:50} },
              { data: { id: 'R12_SW193', parent: 'Rack12', name:'SW193' }, position: {x:610, y:90} },
              { data: { id: 'R12_TESTER252', parent: 'Rack12', name:'Tester252' }, position: {x:610, y:130}, classes: 'device_tester' },
              { data: { id: 'R12_TESTER250', parent: 'Rack12', name:'Tester250' }, position: {x:610, y:170}, classes: 'device_tester' },
              { data: { id: 'R12_TS254', parent: 'Rack12', name:'TS254' }, position: {x:610, y:210}, classes: 'device_terminalserver' },
              { data: { id: 'R13_UP_SW', parent: 'Rack13', name: 'UP_SW' }, position: {x:810, y:10} },
              // { data: { id: 'R14_UP_SW', parent: 'Rack14', name: 'UP_SW'}, position: {x:1010, y:10} },
              { data: { id: 'R15_UP_SW', parent: 'Rack15', name: 'UP_SW' }, position: {x:1010, y:10} },
              { data: { id: 'R15_TS249', parent: 'Rack15', name: 'TS249' }, position: {x:1010, y:50}, classes: 'device_terminalserver' },
              { data: { id: 'R16_UP_SW', parent: 'Rack16', name: 'UP_SW' }, position: {x:1210, y:10} },
              { data: { id: 'R8_UP_SW', parent: 'Rack8', name:'UP_SW' }, position: {x:1410, y:10} },
              { data: { id: 'R8_SW247', parent: 'Rack8', name:'SW247' }, position: {x:1410, y:50} },
              { data: { id: 'R8_TS242', parent: 'Rack8', name:'TS242' }, position: {x:1410, y:90}, classes: 'device_terminalserver' },
              { data: { id: 'R8_VM188', parent: 'Rack8', name:'VM188' }, position: {x:1410, y:130}, classes: 'device_vm' },
              { data: { id: 'R8_VM243', parent: 'Rack8', name:'VM243' }, position: {x:1410, y:170}, classes: 'device_vm' },
              { data: { id: 'R8_VM244', parent: 'Rack8', name:'VM244' }, position: {x:1410, y:210}, classes: 'device_vm' },
              { data: { id: 'R8_VM244', parent: 'Rack8', name:'VM244' }, position: {x:1410, y:210}, classes: 'device_vm' },
              { data: { id: 'R8_VM246', parent: 'Rack8', name:'VM246' }, position: {x:1410, y:250}, classes: 'device_vm' },


              { data: { id: 'R13_SW179', parent: 'Rack13', name:'SW179' }, position: {x:810, y:50} },
              { data: { id: 'R13_SW2', parent: 'Rack13', name:'SW2' }, position: {x:810, y:90} },
              { data: { id: 'R13_ADC1', parent: 'Rack13', name:'FortiADC1' }, position: {x:810, y:130}, classes: 'device_adc' },
              { data: { id: 'R13_ADC2', parent: 'Rack13', name:'FortiADC2' }, position: {x:810, y:170}, classes: 'device_adc' },
              { data: { id: 'R16_SW250', parent: 'Rack16', name:'SW250' }, position: {x:1210, y:50} },
              { data: { id: 'R16_VM245', parent: 'Rack16', name:'VM245' }, position: {x:1210, y:90}, classes: 'device_vm' },
              { data: { id: 'R16_VM240', parent: 'Rack16', name:'VM240' }, position: {x:1210, y:130}, classes: 'device_vm' },
              { data: { id: 'R16_VM241', parent: 'Rack16', name:'VM241' }, position: {x:1210, y:170},  classes: 'device_vm'},
              { data: { id: 'R16_VM242', parent: 'Rack16', name:'VM242' }, position: {x:1210, y:210}, classes: 'device_vm' },
              { data: { id: 'R16_VM200', parent: 'Rack16', name:'VM200' }, position: {x:1210, y:250}, classes: 'device_vm' },

            ],
            edges: [
              // { data: { id: 'ad', source: 'a', target: 'd' } },
              // { data: { id: 'eb', source: 'e', target: 'b' } }
              { data: { id: 'SW249_to_SW179', source: 'R12_SW249', target: 'R13_SW179', 'label': 'port31-32 <==> port49-50' } },
              // { data: { id: 'SW249_to_SW193', source: 'R12_SW249', target: 'R12_SW193', 'label': 'port29-30 <==> port49-50' } },
              { data: { id: 'SW249_to_SW193', source: 'R12_SW249', target: 'R12_SW193'} },
              { data: { id: 'SW193_to_SW2', source: 'R12_SW193', target: 'R13_SW2', 'label': 'port43 <==> uplink2' } },
              { data: { id: 'TESTER252_to_SW193', source: 'R12_SW193', target: 'R12_TESTER252'} },
              { data: { id: 'TESTER250_to_SW193', source: 'R12_SW193', target: 'R12_TESTER250'} },
              { data: { id: 'SW179_to_SW250', source: 'R13_SW179', target: 'R16_SW250', 'label': 'port47  <==> port47'} },
              { data: { id: 'SW250_to_SW2', source: 'R16_SW250', target: 'R8_SW247', 'label': 'port48  <==> port48'} },
              { data: { id: 'SW2_to_ADC1', source: 'R13_SW2', target: 'R13_ADC1', 'label': ''} },
              { data: { id: 'SW179_to_ADC2', source: 'R13_SW179', target: 'R13_ADC2', 'label': ''} },
              { data: { id: 'SW250_to_VM240', source: 'R16_SW250', target: 'R16_VM240', 'label': ''} },
              { data: { id: 'SW250_to_VM241', source: 'R16_SW250', target: 'R16_VM241', 'label': ''} },
              { data: { id: 'SW250_to_VM242', source: 'R16_SW250', target: 'R16_VM242', 'label': ''} },
              { data: { id: 'SW250_to_VM200', source: 'R16_SW250', target: 'R16_VM200', 'label': ''} },
              { data: { id: 'R16UPSW_to_R8UPSW', source: 'R16_UP_SW', target: 'R8_UP_SW', 'label': 'port2 <==> port47'} },

            ]
          },

          style: [
            {
              selector: 'node',
              css: {
                // 'content': 'data(name)',
                'label': 'data(name)',
                'text-valign': 'center',
                'text-halign': 'center',
                'background-color': '#6fb1fc',
                'shape': 'roundrectangle',
                'width': '100',
                'text-wrap': 'wrap'

              }
            },
            {
              selector: '$node > node',
              css: {
                'padding-top': '10px',
                'padding-left': '10px',
                'padding-bottom': '10px',
                'padding-right': '10px',
                'text-valign': 'top',
                'text-halign': 'center',
                'background-color': '#eff5f5'
              }
            },
            {
              selector: 'edge',
              css: {
                // 'target-arrow-shape': 'triangle',
                'curve-style': 'bezier',
                // 'curve-style': 'unbundled-bezier',
                'edge-text-rotation': 'autorotate',
                'label': 'data(label)',
                // 'width': 2,
                'line-color': '#ccc',
                'font-size': '8',
                'source-label': 'data(sourcelabel)',
                'target-label': 'data(targetlabel)',
                'text-rotation': 'autorotate',

                // 'source-text-offset': '10',
                // 'target-text-offset': '10',
                // 'source-text-margin-x': '20',
                // 'source-text-rotation': 'autorotate',

              }
            },
            {
              selector: ':selected',
              css: {
                'background-color': 'black',
                'line-color': 'black',
                'target-arrow-color': 'black',
                'source-arrow-color': 'black'
              }
            },
            {
              selector: '.device_vm',
              css: {
                'background-color': '#00ff00',
              }
            },
            {
              selector: '.device_tester',
              css: {
                'background-color': '#ff0000',
              }
            },
            {
              selector: '.device_adc',
              css: {
                'background-color': '#0000ff',
              }
            },
            {
              selector: '.device_terminalserver',
              css: {
                'background-color': '#cc00ff',
              }
            },

            {
              selector: '#TESTER252_to_SW193',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -80,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#TESTER250_to_SW193',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': 100,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#SW179_to_SW250',
              css:{
                // 'control-point-distances': '-200 10 10'，
                'control-point-distances': -200,
                'control-point-weights': 0.5,
                'curve-style': 'unbundled-bezier',
                // 'edge-distances': 'node-position'
              }
            },
            {
              selector: '#SW2_to_ADC1',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -80,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#SW179_to_ADC2',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -120,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#SW250_to_VM240',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -50,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#SW250_to_VM241',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -100,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#SW250_to_VM242',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -150,
                'control-point-weights': 0.5,
              }
            },
            {
              selector: '#SW250_to_VM200',
              css:{
                'curve-style': 'unbundled-bezier',
                'control-point-distances': -200,
                'control-point-weights': 0.5,
              }
            },

          ],

        });

        cy.on('tap', function (evt) {
          // body...

          // console.log('tap '+ evt.cyTarget.id());
          // console.log((evt.cyTarget == cy));
          if (evt.cyTarget !== cy){
            console.log(evt.cyTarget);

          };

        })

        this.cy = cy;
        this.loading = false;
        NProgress.done();
      },
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