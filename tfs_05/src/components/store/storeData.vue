<template>
	<div id="storeDat">
		
		<div>
			<el-form :model="storeData" label-width="100px">
				<el-form-item label="用户ID">
					<el-input :disabled="true" v-model="storeData.storeID"></el-input>
				</el-form-item>
				<el-form-item label="用户姓名">
					<el-input :disabled="true" v-model="storeData.storeName"></el-input>
				</el-form-item>
				<el-form-item label="用户性别">
					<el-input :disabled="true" v-model="storeData.gender"></el-input>
				</el-form-item>
				<el-form-item label="用户手机">
					<el-input :disabled="true" v-model="storeData.phoneNumber"></el-input>
				</el-form-item>
				<el-form-item label="用户邮箱">
					<el-input :disabled="true" v-model="storeData.email"></el-input>
				</el-form-item>
				<el-form-item label="用户银行帐号">
					<el-input :disabled="true" v-model="storeData.bankNumber"></el-input>
				</el-form-item>
				<el-form-item label="用户所在城市">
					<el-input :disabled="true" v-model="storeData.auditStatus"></el-input>
				</el-form-item>
				
			</el-form>
		</div>
		
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				storeData: this.$route.query.detailData
			}
		},
		methods:{
			
		}
	}
</script>

<style>
	.storeDat{
		
	}
</style>
