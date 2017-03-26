<template>
    <section>
    
        <el-tabs v-model="activeName2"  @tab-click="handleClick" type="border-card">
        <el-tab-pane label="Topology" name="tab_topology">
            <div id="us_lab_topology"></div>
        </el-tab-pane>
        <el-tab-pane label="Edit Topology" name="tab_topo_model">tab2</el-tab-pane>
        </el-tabs>
        <!-- <div id="us_lab_topology"></div> -->
    </section>
    
</template>

<script>
  import cytoscape from 'cytoscape';
  

  export default {

    data() {
      return {
        activeName2: 'tab_topology'
      };
    },
    methods: {
      handleClick(tab, event) {
        // console.log(tab, event);
      },

      ///////////////////////////////////////////////////
      drawDemo1(){

      },
  //////////////////////////////////////////////////////////

      drawDemo2(){
        var cy = cytoscape({
          container: document.getElementById('us_lab_topology'),
          boxSelectionEnabled: true,
          autounselectify: true,

          elements: {
            nodes: [
              // xpadding: 200, ypadding: 40
              // { data: { id: 'Rack8' } },
              { data: { id: 'Rack9' } },
              { data: { id: 'Rack10' } },
              { data: { id: 'Rack11' } },
              { data: { id: 'Rack12' } },
              { data: { id: 'Rack13' } },
              // { data: { id: 'Rack14' } },
              // { data: { id: 'Rack15' } },
              // { data: { id: 'Rack16' } },
              
              { data: { id: 'R9_UP_SW', parent: 'Rack9' }, position: {x:10, y:100} },
              { data: { id: 'R10_UP_SW', parent: 'Rack10' }, position: {x:210, y:100} },
              { data: { id: 'R11_UP_SW', parent: 'Rack11' }, position: {x:410, y:100} },
              { data: { id: 'R12_UP_SW', parent: 'Rack12' }, position: {x:610, y:100} },
              { data: { id: 'R13_UP_SW', parent: 'Rack13' }, position: {x:810, y:100} },
              // { data: { id: 'R14_UP_SW', parent: 'Rack14' }, position: {x:1010, y:100} },
              // { data: { id: 'R15_UP_SW', parent: 'Rack15' }, position: {x:1210, y:100} },
              // { data: { id: 'R16_UP_SW', parent: 'Rack16' }, position: {x:1410, y:100} },
              // { data: { id: 'R8_UP_SW', parent: 'Rack8' }, position: {x:1610, y:100} },

    




            ],
            edges: [
              // { data: { id: 'ad', source: 'a', target: 'd' } },
              // { data: { id: 'eb', source: 'e', target: 'b' } }
      
            ]
          },

          

          layout: {
            name: 'preset',
            // padding: 100

          },

          ready: function (argument) {
            // body...
          },

          style: [
            {
              selector: 'node',
              css: {
                'content': 'data(id)',
                'text-valign': 'center',
                'text-halign': 'center',
                'background-color': '#6fb1fc',

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
                'curve-style': 'bezier'
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
            }
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

    
      },
//////////////////////////////////////////////////////////
      showTopologyTips(){
        this.$notify.info({
          title: 'Topology Tips',
          message: '(1). Zoom in or zoom out via mouse wheel. (2).  Hold  page to move. (3). Select the node to operate.',
          duration: 15000,
          offset: 130
        });
         
      },
    },
    mounted() {
      this.drawDemo2();
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