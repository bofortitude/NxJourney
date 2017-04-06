<template>
    <section>
        <el-tabs v-model="activeName2"  @tab-click="handleTabClick" type="border-card" >
            <el-tab-pane label="" name="tab_topology" >
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    export default {

        data() {
            return {
                activeName2:'',

            };

        },

        methods: {

        },

        mounted() {

        }


    }

</script>


<style scoped lang="scss">


</style>