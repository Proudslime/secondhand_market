import storeMain from '@/components/store/storemain.vue'
import sales from '@/components/store/sales.vue'
import aas from '@/components/store/addAndsub.vue'
import goodlist from '@/components/store/goodlist.vue'
import storeData from '@/components/store/storeData.vue'

export default [
	{
		path: '/storemain',
		name: 'storemain',
		component: storeMain,
		children: [
			{
				path: '/goodlist',
				name: 'goodlist',
				component: goodlist,
			},
			{
				path: '/sales',
				name: 'sales',
				component: sales,
			},
			{
				path: '/aas',
				name: 'aas',
				component: aas,
			},
			{
				path: '/storeData',
				name: 'storeData',
				component: storeData,
			}
		]
	},
]