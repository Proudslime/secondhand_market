<template>
	<div id="storeRegister">
		<span>商家注册:</span>
		<el-form :model="storeData" :rules="rules" ref="storeData" label-width="100px" class="demo-ruleForm">
		  <el-form-item label="商家名" prop="storeName">
		    <el-input v-model="storeData.storeName"></el-input>
		  </el-form-item>
		  <el-form-item label="密码" prop="pass">
		      <el-input type="password" v-model="storeData.password" autocomplete="off"></el-input>
		    </el-form-item>
		    <el-form-item label="确认密码" prop="checkPass">
		      <el-input type="password" v-model="storeData.checkPass" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="手机号">
				<el-input v-model="storeData.phoneNumber" autocomplete="off"></el-input>
			</el-form-item>
		  <el-form-item label="性别" required>
			<el-col :span="11">
				<el-form-item prop="gender">
				  <el-radio v-model="storeData.gender" label="0">女</el-radio>
				  <el-radio v-model="storeData.gender" label="1">男</el-radio>
				</el-form-item>
			</el-col>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
		    <el-button @click="resetForm('ruleForm')">重置</el-button>
			<el-button @click="signout">返回登陆</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script>
	import qs from 'qs'
	export default {
		name: "storeRegister",
	    data:function() {
			// var validatePass = (rule, value, callback) => {
			//         if (value === '') {
			//           callback(new Error('请输入密码'));
			//         } else {
			//           if (this.ruleForm.checkPass !== '') {
			//             this.$refs.ruleForm.validateField('checkPass');
			//           }
			//           callback();
			//         }
			//       };
			//       var validatePass2 = (rule, value, callback) => {
			//         if (value === '') {
			//           callback(new Error('请再次输入密码'));
			//         } else if (value !== this.ruleForm.passwrod) {
			//           callback(new Error('两次输入密码不一致!'));
			//         } else {
			//           callback();
			//         }
			//       };
	      return {
			storeData: {
				storeName: '',
				password: '',
				phoneNumber: '',
				checkPass: '',
				gender: '',
	        },
	        rules: {
				storeName: [
					{ required: true, message: '请输入商家姓名', trigger: 'blur' },
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
				]
	        }
	      };
	    },
	    methods: {
			signout() {
				this.$router.replace('/');
			},
	      submitForm(storeData) {
			  console.log(this.storeData);
			  
			  let url = this.HOST + 'storeRegister';
			  this.$axios.post(url, qs.stringify(this.storeData))
			  .then((response) => {
				  console.log(response);
				  if (response.data.code == 200) {
					  this.$router.replace('/StoreLogin');
				  }
			  })
	   //      this.$refs[storeData].validate((valid) => {
	   //        if (valid) {
	   //          alert('submit!');
				// console.log(this.storeData);
				
				// // let url = this.HOST + 'storeRegister'
				// // this.$axios.post(url, qs.stringify(this.storeData))
				// // .then((response) => {
				// // 	console.log(response);
				// // 	this.$router.replace('/StoreLogin');
				// // })
				// // .catch((error) => {
				// // 	this.$router.replace('/StoreLogin');
				// // })
	   //        } else {
	   //          console.log('error submit!!');
	   //          return false;
	   //        }
	   //      });
	      },
	      resetForm(storeData) {
	        this.$refs[storeData].resetFields();
	      }
	    }
	}
</script>

<style>
</style>
