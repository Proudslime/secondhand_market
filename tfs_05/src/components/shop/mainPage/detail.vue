<template>
	<div id="detailList">
	
		<el-row style="margin-top: 100px;background-color: aliceblue;width: 700px;height: 400px;margin-left: 200px;">
			<el-col style="width: 600px;margin-left: -120px;margin-top: 50px;">
				<div class="goodphoto">
					<img :src="detailGood.imgurl" />
				</div>
				<br />
				<span>111111</span>
				<br />
				<el-rate
				    v-model="value2"
				    :colors="colors">
				  </el-rate>
				
			</el-col>
			<el-col style="margin-top: 50px;margin-left: -120px;width: 200px;">
				<div class="gooddetail" style="float: left;">
					<h2>{{detailGood.goodsName}}</h2>
					<el-row style="text-align: left;">
						<span>原价：{{detailGood.maxExp}}</span>
					</el-row>
					<el-row style="text-align: left;">
						<span>折扣价：{{detailGood.price}}</span>
					</el-row>
					<el-row style="text-align: left;"><span>运费：20</span></el-row>
					<el-row style="text-align: left;">
						<el-input-number v-model="amout" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
					</el-row>
					<el-row style="float: left;width: 300px;">
						<el-col style="width: 120px;">
							<el-button 
							type="primary" 
							style="width: 100px;float: left;"
							@click="addToShopCart">加入购物车</el-button>
						</el-col>
						<el-col>
							<el-button style="width: 80px;">收藏</el-button>
						</el-col>
					</el-row>
				</div>
			</el-col>
		</el-row>
		<el-row style="margin-left: 13%;margin-right: 13%;">
			 <el-table
			      :data="mem"
			      style="width: 100%;">
				  <el-table-column
				    prop="name"
				    label="评论"
				    width="80">
				  </el-table-column>
			      <el-table-column
			        prop="comment"
			        label=""
			        width="1050">
			      </el-table-column>
			    </el-table>
		</el-row>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				UserData: this.$route.query.UserData,
				detailGood: this.$route.query.detailGood,
				mem: [{
					name:'11111'+':',
					comment:'11111111111111111111111111111111111111111111111111111111111111',
				},{
					name:'1234'+':',
					comment:'就这？'
				}],
				value2: null,
				colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
				amout: "1"
			}
		},
		methods:{
			signout() {
				this.$router.replace('/');
			},
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			addToShopCart() {
				let url = this.HOST + 'main/search/addGood';
				let data = new FormData();
				data.append('goodsID',this.detailGood.goodsID);
				data.append('storeID',this.detailGood.storeID);
				data.append('userName',this.UserData.userName);
				data.append('amout',this.amout);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
				})
			},
			handleChange() {
				console.log(this);
			}
		}
	}
</script>

<style>
	.goodimt {
		float: left;
		display: inline;
	
	}
	
	.goodphoto {
		display: inline;
	}
	
	.gooddetail {
		display: inline;
	}
	
	.goodcount {
		display: inline;
	}
</style>
