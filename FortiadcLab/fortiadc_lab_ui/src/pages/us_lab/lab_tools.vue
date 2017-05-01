<template>
    <section>
        <!-- <el-tabs v-model="activeName2"  @tab-click="handleTabClick" type="border-card" > -->
        <el-tabs v-model="activeName3" type="border-card" >
            <el-tab-pane label="Step" name="action_tab" >
                <div>Lab tools is on developing ...</div>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    export default {

        data() {
            return {
                activeName3:'action_tab',
            };

        },

        methods: {
            handleTabClick(tab, event) {
                // console.log(tab, event);

            },

        },

        mounted() {

        }


    }

</script>


<style scoped lang="scss">


</style>