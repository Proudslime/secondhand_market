<template>
	<div id="sales">
		<el-row style="background-color: aliceblue;height: 40px;margin-bottom: 0px;">
			<span style="font-size: 20px;float: left;">未发货</span>
		</el-row>
		<el-table
			:data="dealingList"
			class="shopCar">
			<el-table-column prop="dealID"
				label="订单号" width="80">
			</el-table-column>
			<el-table-column prop="imgurl"
				label="图片" width="80">
				<template slot-scope="scope">
					<img :src="scope.row.imgurl" width="20px" height="20px"/>
				</template>
			</el-table-column>
			<el-table-column prop="goodsID"
				label="商品编号" width="80">
			</el-table-column>
			<el-table-column prop="userID"
				label="用户编号" width="80">
			</el-table-column>
			<el-table-column prop="finalPrice"
			label="价格" width="80">
				<template slot-scope="scope">
					<el-input v-model="scope.row.finalPrice"></el-input>
				</template>
			</el-table-column>
			<!-- <el-table-column prop="count"
				label="数量" width="80">
			</el-table-column> -->
			<el-table-column prop="dealTime"
				label="上架时间" width="120">
			</el-table-column>
			<el-table-column label="操作" width="80">
				<template slot-scope="scope">
					<el-button style="background-color: deepskyblue;" @click="fh(scope.$index, scope.row)">发货</el-button>
				</template>
			</el-table-column>
		</el-table>
		<!-- <el-row style="background-color: #ffffff;margin-bottom: -25px;">
			<el-divider></el-divider>
		</el-row> -->
		<el-row style="background-color: aliceblue;height: 40px;margin-bottom: 0px;">
			<span style="font-size: 20px;float: left;">已完成</span>
		</el-row>
		<el-table
			:data="finishList"
			class="shopCar">
			<el-table-column prop="id"
				label="订单号" width="80">
			</el-table-column>
			<el-table-column prop="imgurl"
				label="图片" width="80">
				<template slot-scope="scope">
					<img :src="scope.row.imgurl" width="20px" height="20px"/>
				</template>
			</el-table-column>
			<el-table-column prop="goodsID"
				label="商品编号" width="80">
			</el-table-column>
			<el-table-column prop="isSuccessfulDeal"
				label="是否交易完成" width="80">
			</el-table-column>
			<el-table-column prop="finalPrice"
				label="交易价格">
			</el-table-column>
			<el-table-column prop="amout"
				label="数量" width="80">
			</el-table-column>
			<el-table-column prop="time"
				label="购买时间" width="80">
			</el-table-column>
			<el-table-column prop="dealingTime"
				label="交易结束时间" width="150">
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				dealingList: this.$route.query.dealingList,
				finishList: this.$route.query.dealingList,
				storeData: this.$route.query.storeData,
			}
		},
		methods: {
			add(index) {
				index.count++;
			},
			del(index) {
				index.count--;
			},
			print() {
				console.log(this);
			},
			fh(index, row) {
				let url = this.HOST + 'storeMain/storeDealing/access';
				let data = new FormData();
				data.append('dealID',row.dealID);
				data.append('price',row.finalPrice);
				data.append('storeName',this.storeData.storeName);
				data.append('storeID',this.storeData.storeID);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					alert(response.data.message);
				})
				
			}
		},
		created() {
			
		},
		mounted() {
			
		}
	}
</script>

<style>
	.shopCar {
		width: 100%;
	}
</style>
