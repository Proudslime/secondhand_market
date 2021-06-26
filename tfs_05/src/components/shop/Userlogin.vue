<template>
	<div id="login">
		<el-container>
		  <el-header>
			<span style="font-size: 30px;">校园二手市场</span>
			<el-button 
				type="text" 
				style="float: right;margin-right: 20px;color: red;" 
				@click="storeLogin">商家登录</el-button>
		  </el-header>
		  <el-container>
		    <el-aside width="200px">
				<img src="../../assets/logo.png" />
			</el-aside>
		    <el-container>
		      <el-main>
				  <el-form ref="UserLoginData"
				  	class="login-user"
				  	:model="UserLoginData"
				  	:rules="rules"
				  	label-width="80px">
				  	<el-form-item label="用户名" prop="userName">
				  		<el-input v-model="UserLoginData.userName" ></el-input>
				  	</el-form-item>
				  	<el-form-item label="密码" prop="password">
				  		<el-input type="password" v-model="UserLoginData.password" ></el-input>
				  	</el-form-item>
				  	<el-form-item label="验证码" style="display: inline;">
				  		<el-input @keyup.enter.native="submit('loginData')"
				  			v-model="input" style="width: 170px;float: left;margin-right: 20px;"></el-input>
				  		<div class="ValidCode disabled-select" :style="`width:120px; height:35px;`" @click="refreshCode" align="center">
				  			<span v-for="(item, index) in codeList" :key="index" :style="getStyle(item)">{{item.code}}</span>
				  		</div>
				  	</el-form-item>
				  	<br />
				  	<el-form-item>
				  		<el-button type="primary" @click="submit('loginData')" style="margin-right: 50px;width: 100px;">登录</el-button>
				  		<el-button type="primary" @click="register" style="margin-right: 20px;width: 100px;">注册</el-button>
				  	</el-form-item>
				  </el-form>
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
	export default {
		name: 'login',
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
				UserLoginData: {
					userName: '',
					password: '',
				},
				rules: {
					userName: [{
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
				codeList: [],
				input: '',
			}
		},
		methods: {
			submit(UserLoginData) {
				
				let url = this.HOST + 'userLogin';
				let data = new FormData();
				data.append("userName",this.UserLoginData.userName);
				data.append("password",this.UserLoginData.password);
				this.$axios.post(url,data)
				.then((response) => {
					console.log(response);
					
					if (response.data.code == 400) {
						alert(response.data.message);
						this.UserLoginData.userName = null;
						this.UserLoginData.password = null;
						this.input = null;
					} else {
						this.$router.replace({
							path: '/main',
							query:{
								UserData: response.data.data
							}
						});
					}
				})
				.catch((error) => {
					console.log(error);
					this.$router.replace('/');
				})
				
			},
			register() {
				this.$router.push('/UserRegiste');
			},
			cancel() {
				this.$refs.UserLoginData.resetFields();
			},
			storeLogin(){
				this.$router.push('/StoreLogin')
			},
			refreshCode() {
				this.createCode();
			},
			createCode() {
				let len = this.length,
				codeList = [],
				chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz0123456789',
				charsLen = chars.length
				// 生成
				for (let i = 0; i < len; i++) {
					let rgb = [Math.round(Math.random() * 220), Math.round(Math.random() * 240), Math.round(Math.random() * 200)]
					codeList.push({
					code: chars.charAt(Math.floor(Math.random() * charsLen)), // 随机码
					color: 'rgb(${rgb})', // 随机颜色
					fontSize: '1${[Math.floor(Math.random() * 10)]}px', // 随机字号
					padding: '${[Math.floor(Math.random() * 10)]}px', // 随机内边距
					transform: 'rotate(${Math.floor(Math.random() * 90) - Math.floor(Math.random() * 90)}deg)' // 随机旋转角度
					})
				}
				// 指向
				this.codeList = codeList
				// 将当前数据派发出去
				this.$emit('update:value', codeList.map(item => item.code).join(''))
			},
			getStyle (data) {
				return 'color: ${data.color}; font-size: ${data.fontSize}; padding: ${data.padding}; transform: ${data.transform}'
			},
		},
		created() {
			this.createCode()
		}
	}
</script>

<style>
	body {
		background-image: url(../../assets/timg2.jpg);
		background-size: 100%;
		background-repeat: no-repeat;
		background-position: center center;
	}
	.ValidCode {
		border-style: solid;
		display: flex;
		justify-content: center;
		align-items: center;
		cursor: pointer;
		span{
			display: inline-block;
		}
	}
	.login-user {
		width: 400px;
	}
</style>
