<template>
	<div id="login">
		<el-container>
		  <el-header>
			  <span style="font-size: 30px;">校园二手市场</span>
			  <el-button type="text" @click="ulogin" style="float: right;margin-right: 30px;">用户登录</el-button>
		  </el-header>
		  <el-container>
		    <el-aside width="200px">
				<img src="../../assets/logo.png" />
			</el-aside>
		    <el-container>
		      <el-main>
				  <el-form ref="storeData"
				  	class="login-store"
				  	:model="storeData"
				  	:rules="rules"
				  	label-width="80px">
				  	<el-form-item label="商家姓名" prop="storeName">
				  		<el-input v-model="storeData.storeName" style="width: 400px;float: left;"></el-input>
				  	</el-form-item>
				  	<el-form-item label="密码" prop="password">
				  		<el-input type="password" v-model="storeData.password" style="width: 400px;float: left;"></el-input>
				  	</el-form-item>
				  	<br />
				  	<el-form-item style="float: left;">
				  		<el-button type="primary" @click="submit('storeData')">登录</el-button>
				  	</el-form-item>
				  </el-form>
				  <el-button type="primary" @click="register" style="float: left;margin-left: 40px;">注册</el-button>
			  </el-main>
		      <el-footer>
				  <span>广告位招租</span>
			  </el-footer>
		    </el-container>
		  </el-container>
		</el-container>
	</div>
</template>

<script>
	import qs from 'qs'
	export default {
		props: {
			width: {
				type: String,
				default: '100px'
			},
			height: {
				type: String,
				default: '40px'
			},
			length: {
				type: Number,
				default: 4
			}
		},
		data:function() {
			return {
				storeData:{
					storeName:'',
					password:''
				},
				rules: {
					storeName: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}]
				},
			}
		},
		methods:{
			submit(storeData) {
				let url = this.HOST + "storeLogin";
				let data = new FormData();
				data.append("storeName",this.storeData.storeName);
				data.append("password",this.storeData.password);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					this.$router.push({
						path:'/storemain',
						query:{
							storeData:response.data.data
						}
					})
				})
				.catch((error) => {
					
				})
			},
			register() {
				this.$router.replace('/StoreRegiste');
			},
			ulogin() {
				this.$router.push('/');
			}
		}
	}
</script>

<style>
</style>
