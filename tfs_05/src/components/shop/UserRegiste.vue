<template>
	<div id="userRegister">
		<P>用户注册:</P>
		<el-form :model="userData" :rules="rules" ref="userData" label-width="100px" class="demo-userData">
		  <el-form-item label="用户名" prop="userName">
		    <el-input v-model="userData.userName"></el-input>
		  </el-form-item>
		  <el-form-item label="密码" prop="password">
		      <el-input type="password" v-model="userData.password" autocomplete="off"></el-input>
		  </el-form-item>
		    <el-form-item label="确认密码" prop="checkPass">
		      <el-input type="password" v-model="userData.checkPass" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="性别" required>
				<el-col :span="11">
					<el-form-item prop="gender">
						<el-radio v-model="userData.gender" label="0">女</el-radio>
						<el-radio v-model="userData.gender" label="1">男</el-radio>
					</el-form-item>
				</el-col>
			</el-form-item>
		  <el-form-item label="生日" required>
			<el-col :span="11">
				<el-form-item prop="birth">
				  <el-input type="date" v-model="userData.birth"></el-input>
				</el-form-item>
			</el-col>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="submitForm('userData')">立即创建</el-button>
		    <el-button @click="resetForm('userData')">重置</el-button>
			<el-button @click="signout">返回登陆</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script>
	import qs from 'qs'
	export default {
		name: "userRegister",
	    data:function() {
			// var validatePass = (rule, value, callback) => {
			//         if (value === '') {
			//           callback(new Error('请输入密码'));
			//         } else {
			//           if (this.userData.checkPass !== '') {
			//             this.$refs.rules.validatePass('checkPass');
			//           }
			//           callback();
			//         }
			//       };
			// var validatePass2 = (rule, value, callback) => {
			// 	if (value === '') {
			// 		callback(new Error('请再次输入密码'));
			// 	} else if (value !== this.userData.password) {
			// 	callback(new Error('两次输入密码不一致!'));
			// 	} else {
			// 		callback();
			// 	}
			// };
	      return {
	        userData: {
				userName: '',
				password: '',
				checkpass: '',
				date: '',
				gender: ''
	        },
	        rules: {
	          userName: [
	            { required: true, message: '请输入用户昵称', trigger: 'blur' },
	            { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
	          ],
			  password: [
				{
					 required: true, trigger: 'blur'
				}
			  ],
			  checkPass: [
				{
					 required: true, trigger: 'blur'
				}
			  ],
	          date: [
				{
					required: true, message: '请选择日期', trigger: 'change',
				}
	          ],
	        }
	      };
	    },
	    methods: {
			signout() {
				this.$router.replace('/');
			},
	      submitForm(userData) {
	        this.$refs[userData].validate((valid) => {
	          if (valid) {
	            alert('submit!');
				
				console.log(this.userData);
				let url = this.HOST + 'userRegister'
				this.$axios.post(url, qs.stringify(this.userData))
				.then((response) => {
					console.log(response);
					this.$router.replace('/');
				})
				.catch((error) => {
					this.$router.replace('/');
				})
				
	          } else {
	            console.log('error submit!!');
	            return false;
	          }
	        });
	      },
	      resetForm(userData) {
	        this.$refs[userData].resetFields();
	      }
	    }
	}
</script>

<style>
</style>
