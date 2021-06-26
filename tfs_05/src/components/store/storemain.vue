<template>
	<div id="store">
		<el-container>
			<el-header>
				<el-menu default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
					<el-submenu index="1">
						<template #title>{{storeData.storeName}}</template>
						<el-menu-item index="1-1" @click="getStoreData">商家信息</el-menu-item>
						<el-menu-item index="1-2" @click="loginout">注销</el-menu-item>
					</el-submenu>
					<el-submenu index="2">
						<template #title>我的商品</template>
						<el-menu-item index="2-1" @click="showGoodlist">查看</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
						<template #title>商品售卖情况</template>
						<el-menu-item index="3-1" @click="showSaleslist">查看</el-menu-item>
					</el-submenu>
					<el-submenu index="4">
						<template #title>我的仓库</template>
						<el-menu-item index="4-1" @click="addAndsub">查看</el-menu-item>
					</el-submenu>
				</el-menu>
			</el-header>
			<el-main>
				<router-view></router-view>
			</el-main>
			<el-footer>
				
			</el-footer>
		</el-container>
	</div>
</template>

<script>
	import qs from 'qs'
	export default {
		data:function() {
			return {
				storeData:this.$route.query.storeData,
				dialogVisible: false,
			}
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key,keyPath);
			},
			getStoreData() {
				console.log(this.storeData);
				this.$router.push({
					path: '/storeData',
					query: {
						detailData: this.storeData
					}
				})
			},
			loginout() {
				this.$router.replace('/storeLogin');
			},
			showGoodlist() {
				let url = this.HOST + 'storeMain/goods'
				this.$axios.post(url, qs.stringify(this.storeData))
				.then((response) => {
					console.log(response);
					this.$router.push({
						path: '/goodlist',
						query: {
							goodsList: response.data.data,
							storeID: this.storeData.storeID,
							storeName: this.storeData.storeName
						}
					})
				})
				
				// this.$router.push('/goodlist');
			},
			showSaleslist() {
				let url = this.HOST + 'storeMain/storeDealing';
				let data = new FormData();
				data.append('storeName',this.storeData.storeName);
				data.append('storeID',this.storeData.storeID);
				this.$axios.post(url,data).then((response) => {
					console.log(response);
					this.$router.push({
						path:'/sales',
						query:{
							dealingList: response.data.data,
							storeData: this.storeData
						}
					})
				})
				
				
			},
			addAndsub() {
				let url = this.HOST + 'storeMain/ware'
				this.$axios.post(url, qs.stringify(this.storeData))
				.then((response) => {
					console.log(response);
					this.$router.push({
						path: '/aas',
						query: {
							goodsList: response.data.data,
							storeID: this.storeData.storeID,
							storeName: this.storeData.storeName
						}
					})
				})
			}
		},
		mounted() {
			this.getStoreData()
		}
		
	}
</script>

<style>
</style>
