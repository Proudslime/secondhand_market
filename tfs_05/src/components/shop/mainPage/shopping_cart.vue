<template>
	<div id="gouwuche">
		<el-table
			ref="editCart"
			:data="shopCart"
			@selection-change="handleSelectionChange"
			class="shopCart">
			<el-table-column
			      type="selection"
			      width="55">
			    </el-table-column>
			<el-table-column prop="imgurl"
			fixed
				label="图片" width="80">
				<template slot-scope="scope">
					<img :src="scope.row.imgurl" width="20px" height="20px"/>
				</template>
			</el-table-column>
			<el-table-column prop="goods.goodsName"
				label="商品名称" width="80">
			</el-table-column>
			<el-table-column prop="goods.goodType"
				label="商品类型" width="80">
			</el-table-column>
			<el-table-column label="价格" width="80">
				<el-row style="height: 10px;font-size: 5px ;">
					<el-table-column label="原价" prop="goods.maxExp"></el-table-column>
				</el-row>
				<el-row style="height: 10px;font-size: 5px ;">
					<el-table-column label="折扣" prop="goods.discount"></el-table-column>
				</el-row>
				<el-row style="height: 10px;font-size: 5px ;">
					<el-table-column label="现价" prop="goods.price"></el-table-column>
				</el-row>
			</el-table-column>
			<el-table-column label="数量" prop="amout">
				<template slot-scope="scope">
					<el-input-number v-model="scope.row.amout" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
				</template>
			</el-table-column>
		</el-table>
		<el-button type="primary" @click="print()" style="float: right;;margin-right:20px;margin-top: 10px;">支付</el-button>
		<el-button type="danger" @click="deleteCart()" style="float: right;;margin-right:20px;margin-top: 10px;">删除</el-button>
	</div>
</template>

<script>
	import qs from 'qs'
	export default {
		name: 'gouwuche',
		data:function() {
			return {
				userName: this.$route.query.userName,
				shopCart: this.$route.query.shopCart,
				editCart: {}
			}
		},
		methods:{
			print() {
				console.log(this.editCart);
				
				this.editCart.forEach((item, index) => {
					console.log(item.goods);
					console.log(item.amout);
					let url = this.HOST + 'main/userDealing/add';
					let data = new FormData();
					data.append('price',item.goods.price);
					data.append('storeID',item.goods.storeID);
					data.append('goodsID',item.goods.goodsID);
					data.append('amout',item.amout);
					data.append('userName', this.userName);
					this.$axios.post(url, data)
					.then((response) => {
						console.log(response);
						if (response.data.code == 200) {
							console.log(response);
						}
					})
				})
			},
			deleteCart() {
				console.log(this.editCart);
				this.editCart.forEach((item, index) => {
					let url = this.HOST + 'main/shopCart/delete';
					let data = new FormData();
					data.append('CartID',item.cartID);
					data.append('userName',this.userName);
					console.log(data);
					this.$axios.post(url, data)
					.then((response) => {
						console.log(response);
						if (response.data.code == 200) {
							console.log(response);
							
						}
					})
				})
			},
			handleChange(value) {
				console.log(value);
			},
			handleSelectionChange(val) {
				this.editCart = val;
				console.log(this.editCart);
			}
		}
	}
</script>

<style>
	.shopCart {
		width: 100%;
	}
</style>
