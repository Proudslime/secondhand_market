<template>
	<div>
		<el-container>
			<el-aside width="200px">
				<el-menu
				      default-active="2"
				      class="el-menu-vertical-demo"
				      @open="handleOpen"
				      @close="handleClose"
				      background-color="#545c64"
				      text-color="#fff"
				      active-text-color="#ffd04b">
				      <el-menu-item index="1" @click="toUserData">
				        <i class="el-icon-menu"></i>
				        <span slot="title">详细信息</span>
				      </el-menu-item>
				      <el-menu-item index="2" @click="toAddress">
				        <i class="el-icon-office-building"></i>
				        <span slot="title">收货地址</span>
				      </el-menu-item>
				      <el-menu-item index="3" @click="toPurse">
				        <i class="el-icon-suitcase"></i>
				        <span slot="title">我的钱包</span>
				      </el-menu-item>
					  <el-submenu index="4" >
					      <template slot="title" ><i class="el-icon-star-off"></i>收藏</template>
					      <el-menu-item index="4-1" @click="toSC()"><i class="el-icon-s-shop"></i>店铺</el-menu-item>
					      <el-menu-item index="4-2" @click="toPC()"><i class="el-icon-goods"></i>商品</el-menu-item>
					  </el-submenu>
					  <el-menu-item index="5" @click="toOders()"><i class="el-icon-notebook-2"></i>我的订单</el-menu-item>
					  <!-- <el-menu-item index="6" @click="toFootOn()"><i class="el-icon-edit"></i>我的足迹</el-menu-item> -->
					  <el-menu-item index="7" @click="signout()"><i class="el-icon-thumb"></i>注销</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main>
				
				<router-view></router-view>
				
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import qs from 'qs'
	export default {
		data:function() {
			return {
				UserData:this.$route.query.UserData,
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			toUserData() {
				let url = this.HOST + 'main/userData';
				let data = new FormData();
				data.append("userName",this.UserData.userName);
				this.$axios.post(url,data)
				.then((response) => {
					console.log(response);
					if (response.data.code == 200) {
						this.$router.push({
							path:'/userData',
							query:{
								detailData: response.data.data
							}
						})
					} else {
						alert(response.data.message);
					}
				})
				.catch((error) => {
					
				})
			},
			toAddress() {
				let url = this.HOST + 'main/userAddress'
				let data = new FormData();
				console.log(this.UserData);
				data.append("userName", this.UserData.userName);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					if (response.data.code == 200) {
						this.$router.push({
							path:'/address',
							query:{
								userAddress: response.data.data,
								userData: this.UserData
							}
						})
					} else {
						alert(response.data.message);
					}
				})
				.catch((error) => {
					
				})
			},
			toPurse() {
				
				let url = this.HOST + 'main/userData';
				let data = new FormData();
				data.append("userName", this.UserData.userName);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					
					if (response.data.code == 200) {
						this.$router.push({
							path:'/purse',
							query:{
								detailData: response.data.data
							}
						})
					} else {
						alert(response.data.message);
					}
				})
				.catch((error) => {
					
				})
			},
			toPC(){
				this.$router.push('/ProductCollection');
			},
			toSC(){
				this.$router.push('/StoreCollection');
			},
			toOders(){
				
				let url = this.HOST + 'main/userDealing';
				let data = new FormData();
				data.append('userName',this.UserData.userName);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					if (response.data.code == 200) {
						this.$router.push({
							path: '/Orders',
							query: {
								dealingList: response.data.data,
							}
						})
					}
				})
				.catch((error) => {
					console.log(error);
				})
			},
			toFootOn(){
				this.$router.push('/FootOn');
			},
			signout() {
				this.$router.replace('/');
			}
		},
		created() {
			
		},
		mounted() {
			this.toUserData()
		}
	}
</script>

<style>
	
</style>
