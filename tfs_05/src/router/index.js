import Vue from 'vue'
import Router from 'vue-router'

import shop from '../components/shop/shop.js'
import user from '../components/user/user.js'
import store from '../components/store/store.js'

Vue.use(Router)

export default new Router({
	routes: [
		...shop,
		...user,
		...store,
	],
	mode: 'history',
})