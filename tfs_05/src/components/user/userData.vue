<template>
    <div id="one">
		<div id="one" style="width: 500px;height: 600px ;margin-left: 50px;">
			<div>
				<el-form :model="userData" label-width="100px">
					<el-form-item label="姓名">
						<el-input :disabled="true" v-model="userData.userName"></el-input>
					</el-form-item>
					<el-form-item label="生日">
						<el-input :disabled="true" v-model="userData.birth"></el-input>
					</el-form-item>
					<el-form-item label="性别">
						<el-radio disabled v-model="userData.gender" label="0">女</el-radio>
						<el-radio disabled v-model="userData.gender" label="1">男</el-radio>
					</el-form-item>
					<el-form-item label="手机">
						<el-input :disabled="true" v-model="userData.phoneNumber"></el-input>
					</el-form-item>
					<el-form-item label="邮箱">
						<el-input :disabled="true" v-model="userData.email"></el-input>
					</el-form-item>
					<el-form-item label="银行帐号">
						<el-input :disabled="true" v-model="userData.bankNumber"></el-input>
					</el-form-item>
					<el-form-item label="所在城市">
						<el-input :disabled="true" v-model="userData.city"></el-input>
					</el-form-item>
					<el-form-item style="display: inline;">
						<el-button @click="dialogVisble = true" style="margin-left: 190px;">修改信息</el-button>
					</el-form-item>
				</el-form>
			</div>
			<div>
				<el-dialog title="修改个人信息" :visible.sync="dialogVisble" width="30%" :before-close="handleClose">
					<el-form :model="editData" label-width="100px">
						<el-form-item label="姓名">
							<el-input v-model="editData.userName"></el-input>
						</el-form-item>
						<el-form-item label="性别">
							<el-radio v-model="editData.gender" label="0">女</el-radio>
							<el-radio v-model="editData.gender" label="1">男</el-radio>
						</el-form-item>
						<el-form-item label="手机">
							<el-input v-model="editData.phoneNumber"></el-input>
						</el-form-item>
						<el-form-item label="邮箱">
							<el-input v-model="editData.email"></el-input>
						</el-form-item>
						<el-form-item label="银行帐号">
							<el-input v-model="editData.bankNumber"></el-input>
						</el-form-item>
						<el-form-item label="所在城市">
							<el-input v-model="editData.city"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submit(userData)">提交修改</el-button>
							<el-button @click="handleClose">取消</el-button>
						</el-form-item>
					</el-form>
				</el-dialog>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				dialogVisble: false,
				userData: this.$route.query.detailData,
				editData: this.$route.query.detailData,
			}
		},
		methods: {
			submit(userData) {
				this.$confirm('确认提交？').then(_ => {
					this.dialogVisble = false;
					console.log(userData);
					let url = this.HOST + 'main/userData/edit'
					let data = new FormData();
					data.append('user',this.userData);
					this.$axios.post(url, data)
					.then((response) => {
						console.log(response);
					})
					done();
				}).catch(_ => {});
			},
			handleClose(done) {
				this.$confirm('确认关闭？').then(_ => {
					done();
				}).catch(_ => {});
			}
		},
		created() {
			console.log(this.$route.query.detailData);
		}
	}
</script>

<style>

</style>