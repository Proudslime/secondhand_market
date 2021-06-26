<template>
	<div id="goodlist">
		<el-row style="float: left;">
			<el-button type="primary" @click="dialogVisibleA = true">新增商品</el-button>
		</el-row>
		<el-table :data="goodsList" class="shopCart">
			<el-table-column prop="imgurl" label="图片" width="80">
				<template slot-scope="scope">
					<img :src="scope.row.imgurl" width="20px" height="20px" />
				</template>
			</el-table-column>
			<el-table-column prop="goodsID" label="ID" width="80">
			</el-table-column>
			<el-divider direction="vertical"></el-divider>
			<el-table-column prop="goodsName" label="名称" width="80">
			</el-table-column>
			<el-table-column prop="goodsType" label="类型" width="100">
			</el-table-column>
			<el-table-column prop="instrutions" label="简述" width="80">
			</el-table-column>
			<el-table-column prop="status" label="状态" width="80">
			</el-table-column>
			<el-table-column prop="launchTime" label="上架时间" width="80">
			</el-table-column>
			<el-table-column prop="condition" label="成色" width="80">
			</el-table-column>
			<el-table-column prop="discount" label="折扣" width="80">
			</el-table-column>
			<el-table-column prop="maxExp" label="最大价格" width="80">
			</el-table-column>
			<el-table-column prop="miniExp" label="最小价格" width="80">
			</el-table-column>
			<el-table-column prop="price" label="实际价格" width="80">
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
				</template>
			</el-table-column>
		</el-table>
	
		<div>
			<el-dialog title="更改商品" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
				<el-form :model="editGoods" label-width="100%" style="margin-right: 360px;">
					<el-form-item label="ID">
						<el-input :disabled="true" v-model="editGoods.goodsID" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="名称">
						<el-input v-model="editGoods.goodsName" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="类型">
						<el-input v-model="editGoods.goodsType" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="简述">
						<el-input v-model="editGoods.instrutions" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="状态">
						<el-input v-model="editGoods.status" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="成色">
						<el-input v-model="editGoods.condition" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="折扣">
						<el-input v-model="editGoods.discount" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="最大价格">
						<el-input v-model="editGoods.maxExp" class="ip"></el-input>
					</el-form-item>
					<el-form-item label="最小价格">
						<el-input v-model="editGoods.miniExp" class="ip"></el-input>
					</el-form-item>
					<el-form-item style="display: inline;" class="ip">
						<el-button type="primary" @click="submit('editGoods')" style="width: 100px;float: left;">提交修改</el-button>
						<el-button @click="handleClose">取消</el-button>
					</el-form-item>
				</el-form>
			</el-dialog>
		</div>
		<div>
			<el-dialog
			  title="提示"
			  :visible.sync="dialogVisibleA"
			  width="40%"
			  :before-close="handleClose">
			  
			  <el-form ref="form" :model="editGoods" label-width="80px" size="mini">
				<el-form-item label="名称">
					<el-input v-model="editGoods.goodsName" class="addinput"></el-input>
				</el-form-item>
				
				<el-form-item label="类型">
					<el-input v-model="editGoods.goodsType" class="addinput"></el-input>
				</el-form-item>
				<el-form-item label="简述">
					<el-input v-model="editGoods.instrutions" class="addinput"></el-input>
				</el-form-item>
				<el-form-item label="状态">
					<el-input v-model="editGoods.status" class="addinput"></el-input>
				</el-form-item>
				<el-form-item label="成色">
					<el-input v-model="editGoods.condition" class="addinput"></el-input>
				</el-form-item>
				<el-form-item label="折扣">
					<el-input v-model="editGoods.discount" class="addinput"></el-input>
				</el-form-item>
				<el-form-item label="最大价格">
					<el-input v-model="editGoods.maxExp" class="addinput"></el-input>
				</el-form-item>
				<el-form-item label="最小价格">
					<el-input v-model="editGoods.miniExp" class="addinput"></el-input>
				</el-form-item>
				
				<el-form-item label="图片地址">
					<el-upload
					  class="upload-demo"
					  drag
					  action="https://jsonplaceholder.typicode.com/posts/"
					  multiple
					  style="float: left;"
					  >
					  <i class="el-icon-upload"></i>
					  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
					  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
					</el-upload>
				</el-form-item>
				
				<el-form-item>
					<el-button type="primary" @click="emitData()" style="float: left;">提交商品信息</el-button>
					<el-button @click="dialogVisible = false" style="float: left;margin-left: 150px;">取消</el-button>
				</el-form-item>
			  </el-form>
			</el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				goodsList: this.$route.query.goodsList,
				storeID: this.$route.query.storeID,
				storeName: this.$route.query.storeName,
				dialogVisble: false,
				dialogVisibleA: false,
				editGoods: {
					condition: "",
					discount: "",
					goodsID: "",
					goodsName: "",
					goodsType: "",
					instrutions: "",
					launchTime: "",
					maxExp: "",
					miniExp: "",
					price: "",
					status: "",
				},
				
			}
		},
		methods: {
			handleEdit(index, row) {
				console.log(this.goodsList[index]);
				console.log(row);
				this.editGoods = row;
				console.log(this.editGoods);
				this.dialogVisble = true;
			},
			handleClose(done) {
				this.$confirm('确认关闭？').then(_ => {
					done();
				}).catch(_ => {});
			},
			submit(editGoods) {
				let url = this.HOST + 'storeMain/goods/update'
				
				// this.$axios({
				// 	methods: 'post',
				// 	url: url,
				// 	params: {
				// 		goods: this.editGoods,
				// 		storeID: this.storeID,
				// 		storeName: this.storeName,
				// 	}
				// }).then((response) => {
				// 	console.log(response)
				// }).catch((error) => {
				// 	console.log(error)
				// })
			},
			emitData() {
				let url = this.HOST + 'storeMain/addGood';
				let data = new FormData();
				data.append('goodsName',this.editGoods.goodsName);
				data.append('goodsType',this.editGoods.goodsType);
				data.append('condition',this.editGoods.condition);
				data.append('instrutions',this.editGoods.instrutions);
				data.append('discount',this.editGoods.discount);
				data.append('status',this.editGoods.status);
				data.append('MaxExp',this.editGoods.maxExp);
				data.append('MiniExp',this.editGoods.miniExp);
				data.append('storeID',this.storeID);
				data.append('storeName',this.storeName);
				data.append('present',"1");
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					dialogVisible = false;
				})
				.catch((error) => {
					
				})
				
			}
		}
	}
</script>

<style>
	.shopCart {
		width: 100%;
	}
	.ip{
		width: 200px;
	}
	.addinput{
		width: 350px;
		float: left;
	}
</style>
