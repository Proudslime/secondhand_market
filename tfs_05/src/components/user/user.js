import userMsg from './userMsg.vue'
import userData from './userData.vue'
import address from './Address.vue'
import purse from './purse.vue'
import userOrder from './Orders.vue'

export default [
	{
		path: '/userMsg',
		name: 'userMsg',
		component: userMsg,
		children: [
			{
				path: '/userData',
				name: 'userData',
				component: userData,
			},
			{
				path: '/address',
				name: 'address',
				component: address,
			},
			{
				path: '/purse',
				name: 'purse',
				component: purse,
			},
			{
				path: '/Orders',
				name: 'Orders',
				component: userOrder,
			}
		]
	}
]