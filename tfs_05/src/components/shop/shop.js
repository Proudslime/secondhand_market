import UserLogin from '@/components/shop/Userlogin.vue'
import UserRegiste from '@/components/shop/UserRegiste.vue'
import StoreLogin from '@/components/shop/StoreLogin.vue'
import StoreRegiste from '@/components/shop/StoreRegiste.vue'

import mainPage from '@/components/shop/mainPage/main.vue'
import goodDetail from '@/components/shop/mainPage/detail.vue'
import shopPage from '@/components/shop/mainPage/mainforshop.vue'
import shopcart from '@/components/shop/mainPage/shopping_cart.vue'
import search from '@/components/shop/mainPage/search.vue'

import user from '@/components/user/user.js'

export default [
	{
		path: '/',
		name: 'Userlogin',
		component: UserLogin,
	},
	{
		path: '/UserRegiste',
		name: 'UserRegiste',
		component: UserRegiste,
	},
	{
		path: '/StoreLogin',
		name: 'StoreLogin',
		component: StoreLogin,
	},
	{
		path: '/StoreRegiste',
		name: 'StoreRegiste',
		component: StoreRegiste,
	},
	{
		path: '/main',
		name: 'main',
		component: mainPage,
		children: [
			{
				path: '/cart',
				name: 'cart',
				component: shopcart,
			},
			{
				path: '/search',
				name: 'search',
				component: search,
			},
			{
				path: '/mainforshop',
				name: 'mainforshop',
				component: shopPage,
			},
			{
				path: '/detail',
				name: 'detail',
				component: goodDetail,
			},
			// 还需要添加用户
			...user
		]
	},
]