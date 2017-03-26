import Login from './pages/Login.vue'
import NotFound from './pages/404.vue'
import Home from './pages/Home.vue'
import us_lab_topology from './pages/us_lab/us_lab_topology.vue'
import us_lab_plan from './pages/us_lab/us_lab_plan.vue'
import tester_environment from './pages/us_lab/tester_environment.vue'
import lab_tools from './pages/us_lab/lab_tools.vue'
import team_pages from './pages/public/team_pages.vue'
import fortinet_pages from './pages/public/fortinet_pages.vue'
import contacts from './pages/public/contacts.vue'



let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: 'US Lab',
        iconCls: 'el-icon-setting',//图标样式class
        children: [
            { path: '/us_lab_topology', component: us_lab_topology, name: 'US Lab Topology' },
            { path: '/us_lab_plan', component: us_lab_plan, name: 'US Lab Plan' },
            { path: '/tester_environment', component: tester_environment, name: 'Tester Environment' },
            { path: '/lab_tools', component: lab_tools, name: 'Lab Tools' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: 'Public',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/team_pages', component: team_pages, name: 'Team Pages' },
            { path: '/fortinet_pages', component: fortinet_pages, name: 'Fortinet Pages' },
            { path: '/contacts', component: contacts, name: 'Contacts' }
        ]
    },
   
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;