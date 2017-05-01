<template>
    <section>
        <!-- <el-tabs v-model="activeName2"  @tab-click="handleTabClick" type="border-card" > -->
        <el-tabs v-model="activeName2" type="border-card" >
            <el-tab-pane label="Step" name="action_tab" >
                <div>Lab tools is on developing ...</div>
                <el-pagination layout="sizes, prev, pager, next"  @size-change="size_change" @current-change="handleCurrentChange" :page-sizes="[5, 100, 300, 800, 1000, 2000, 10000]" :page-size='page_size' :total="total" style="float:right;">
                    </el-pagination>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    export default {

        data() {
            return {
                activeName2:'action_tab',
                page_size:100,
                total:500,


            };

        },

        methods: {
            handleTabClick(tab, event) {
                // console.log(tab, event);

            },
            size_change(val){
                this.page_size = val;

            },
            handleCurrentChange(val){

            },

        },

        mounted() {

        }


    }

</script>


<style scoped lang="scss">


</style>