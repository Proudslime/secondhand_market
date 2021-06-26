import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/index.js'
import Vuex from 'vuex'

Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.use(Vuex)

Vue.prototype.VueAxios = VueAxios
Vue.prototype.$axios = axios

Vue.prototype.HOST = 'http://localhost:8110/'


new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
