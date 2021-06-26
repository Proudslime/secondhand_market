<template>
	<div id="two">
		<el-table class="tb"
			:data="userAddress"
			ref="singleTable"
			border>
			<el-table-column prop="content"
				label="收货地址">
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button size="mini" :disabled="scope.row.isMainAddress != 0"
						@click="handleEdit(scope.$index, scope.row)">设为主地址</el-button>
					<el-button size="mini" type="danger"
						@click="handleDelete(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		
		
		<br />
		<el-button @click="dialogFormVisible = true">添加新地址</el-button>
		
		<el-dialog title="收货地址" :visible.sync="dialogFormVisible">
		  <el-form :model="editAddress">
			<el-form-item label="详细地址">
				<el-input v-model="editAddress.address"></el-input>
			</el-form-item>
		  </el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="dialogFormVisible = false">取消</el-button>
		    <el-button type="primary" @click="submit">提交</el-button>
		  </div>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				userAddress:this.$route.query.userAddress,
				currentRow: null,
				dialogFormVisible: false,
				userData: this.$route.query.userData,
				editAddress: {
					address: "",
				}
			}
		},
		methods: {
			handleDelete(index,row) {
				console.log(index);
				console.log(row)
			},
			handleEdit(index,row) {
				let url = this.HOST + 'main/userAddress/selectMainAddress';
				let data = new FormData();
				data.append("userName", this.userData.userName);
				data.append("addressID", row.addressID);
				
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					if (response.data.code == 200) {
						alert(response.data);
					} else {
						alert(response.data.message);
					}
				})
				.catch((error) => {
					console.log(error);
				})
			},
			submit(userData) {
				this.$confirm('确认提交？').then(_ => {
					this.dialogVisble = false;
					console.log(userData);
					
					let url = this.HOST + 'main/userAddress/add';
					let data = new FormData();
					data.append("userName", this.userData.userName);
					data.append("address", this.editAddress.address);
					
					this.$axios.post(url, data)
					.then((response) => {
						console.log(response);
					})
					.catch((error) => {
						
					})
					
					done();
				}).catch(_ => {});
			},
			handleClose(done) {
				this.$confirm('确认关闭？').then(_ => {
					this.dialogVisble = false;
					done();
				}).catch(_ => {});
			}
		}
	}
</script>

<style>
	.tb .el-table__header .el-table-column--selection .cell .el-checkbox {
		display:none
	}
</style>