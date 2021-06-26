<template>
	<div id="purse">
		<div id="one" style="width: 500px;height: 300px ;margin-left: 50px;background-color: #ededed;">
			<el-row>
				<span style="float: left;font-size: 20px;display: block;overflow-wrap:break-word ;">我的积分：{{userData.integral}}</span>
			</el-row>
			<el-row>
				<span style="float: left;font-size: 20px;">我的余额：{{userData.wallet}}</span>
			</el-row>
			<el-row style="text-align: left;">
				<el-button @click="dialogVisbleP = true">充值</el-button>
			</el-row>
		</div>
		<div>
			<el-dialog title="充值中心" :visible.sync="dialogVisbleP" width="30%" :before-close="handleClose">
				<el-row>
					<el-input v-model="input" placeholder="请输入充值金额" ></el-input>
				</el-row>
				<!-- <el-row style="display: inline;">
					<el-col>
						<el-button @click="input = '10'">10</el-button>
					</el-col>
					<el-col>
						<el-button @click="input = '30'">30</el-button>
					</el-col>
					<el-col>
						<el-button @click="input = '50'">50</el-button>
					</el-col>
					<el-col>
						<el-button @click="input = '100'">100</el-button>
					</el-col>
					<el-col>
						<el-button @click="input = '200'">200</el-button>
					</el-col>
				</el-row> -->
				<el-row style="margin-top: 10px;">
					<el-button style="margin-left: 200px;" @click="recharge">充值</el-button>
					<el-button @click="cancelThis">取消</el-button>
				</el-row>
				
			</el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				userData: this.$route.query.detailData,
				input:'',
				dialogVisbleP:false
			}
		},
		methods:{
			recharge() {
				let url = this.HOST + 'main/userData/recharge';
				let data = new FormData();
				data.append('userName',this.userData.userName);
				data.append('money', this.input);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					if (response.data.code == 200) {
						this.dialogVisbleP = false;
						let url2 = this.HOST + 'main/userData';
						let data2 = new FormData();
						data2.append('userName', this.userData.userName);
						this.$axios.post(url2, data2)
						.then((response) => {
							if (response.data.code == 200) {
								this.userData = response.data.data;
							} else {
								alert(response.data.message);
							}
							
						})
					} else {
						dialogVisbleP = false;
						alert(response.data.message);
					}
				})
				.catch((error) => {
					console.log(error);
				})
			},
			handleClose(done) {
				this.$confirm('确认关闭？').then(_ => {
					done();
				}).catch(_ => {});
			},
			cancelThis() {
				
			}
		}
	}
</script>

<style>
</style>
