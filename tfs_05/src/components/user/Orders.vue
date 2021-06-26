<template>
	<div>
		<div id="one" style="width: 900px;height: 600px ;margin-left: 50px;background-color: aliceblue;">
			<el-row><span style="float: left;font-size: 20px;margin-top: 20px;">我的订单</span></el-row>
			<el-row style="margin-top: -20px;"><el-divider></el-divider></el-row>
			<el-row style="margin-top: -45px;">
				<el-table :data="dealingList">
					<el-table-column prop="goodsID"
						label="商品编号">
					</el-table-column>
					<el-table-column prop="dealID"
						label="订单号">
					</el-table-column>
					<el-table-column prop="amout"
						label="数量">
					</el-table-column>
					<el-table-column prop="finalPrice"
						label="总价">
					</el-table-column>
					<el-table-column prop="dealTime"
						label="购买时间">
					</el-table-column>
				</el-table>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		data: function() {
			return {
				dealingList: this.$route.query.dealingList
			}
		}
	}
</script>

<style>
	
</style>
