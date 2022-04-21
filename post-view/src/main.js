//element-ui
import Element from 'element-ui'

import "element-ui/lib/theme-chalk/index.css"
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/en'
//axios
import axios from 'axios'
//Global intercept
import "./axios"
//vue
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// mavonEditor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
//jquery
import $ from 'jquery'
//v-viewer
import 'viewerjs/dist/viewer.css'
import Viewer from 'v-viewer'
//directive
import './util/directive.js'

//waypoints.js
import 'waypoints/lib/jquery.waypoints.min'

//markdown-it-vue-light
import MarkdownItVueLight from 'markdown-it-vue/dist/markdown-it-vue-light.umd.min.js'
import 'markdown-it-vue/dist/markdown-it-vue-light.css'
//loading to vue
Vue.prototype.$axios = axios
// Vue.use(Element)
Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(Viewer)
Vue.use(MarkdownItVueLight)
//Using English version
Vue.use(ElementUI, { locale })




new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
