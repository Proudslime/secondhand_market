<template>
    <div id="Main">
		<el-container>
		  <el-header>
			<div style="background-color: #545c64; width: 100%;">
				<el-menu
					class="el-menu-demo"
					mode="horizontal"
					@select="handleSelect"
					background-color="#545c64"
					text-color="#fff"
					active-text-color="#ffd04b">
					<el-submenu index="1">
						<template slot="title">{{UserData.userName}}</template>
						<el-menu-item index="1-1" @click="toUserMsg">个人信息</el-menu-item>
					</el-submenu>
					<el-menu-item index="2" @click="returnTomain"><i class="el-icon-s-home"></i>主页</el-menu-item>
					<el-menu-item index="3" @click="shopcart"><i class="el-icon-shopping-cart-2"></i>购物车</el-menu-item>
					<!-- <el-menu-item index="4" @click=""><i class="el-icon-phone-outline"></i>联系客服</el-menu-item> -->
					<el-menu-item index="5" @click="search"><i class="el-icon-search"></i>搜索</el-menu-item>
				</el-menu>
			</div>
		  </el-header>
		  <br />
		  
		  <router-view></router-view>
		  
		</el-container>
	</div>
</template>

<script>
	import qs from 'qs'
    export default {
		name: 'Main',
		data:function() {
			return {
				UserData: this.$route.query.UserData
			}
		},
		methods:{
			signout() {
				this.$router.replace('/');
			},
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			search() {
				this.$router.push({
					path: '/search',
					query: {
						// username: this.username,
						UserData: this.UserData
					}
				});
			},
			shopcart() {
				
				let url = this.HOST + 'main/shopCart';
				let data = new FormData();
				data.append("userName", this.UserData.userName);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					if (response.data.code != 400) {
						this.$router.push({
							path:'/cart',
							query:{
								shopCart: response.data.data,
								userName: this.UserData.userName,
							}
						})
					} else {
						alert(response.data.message);
					}
				})
				.catch((error) => {
					console.log(error);
				})
				
			},
			toUserMsg() {
				let url = this.HOST + 'main/userData';
				let data = new FormData();
				data.append("userName", this.UserData.userName);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					if (response.data.code == 200) {
						this.$router.push({
							path: '/userMsg',
							query: {
								UserData: response.data.data
							}
						})
					} else {
						alert(response.data.message);
					}
				})
				.catch((error) => {
					console.log(error);
				})
				
				// this.$router.push({
				// 	path:'/userMsg',
				// 	query:{
				// 		UserData: this.$route.query.UserData
				// 	}
				// });
			},
			returnTomain() {
				this.$router.replace({
					path: '/main',
					query: {
						// username: this.username
						UserData: this.UserData
					}
				});
			}
		},
		created() {
			console.log(this.$route.query.UserData)
		}
    }
</script>

<style>
	body {
		background-image: '';
		background-size: 100%;
		background-repeat: no-repeat;
		background-position: center center;
	}
	.righty{
		float: right;
	}
	.el-menu-demo {
		margin: auto;
		float: center; 
	}
	.el-menu-vertical-demo{
		margin: auto;
		float: left;
		border-style: groove;
	}
	.main {
		height: 100%;
	}
</style>