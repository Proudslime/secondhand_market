<template>
	<div id="addAndsub">
		<div id="add">
			
			<el-row :gutter="20">
				<el-col :span="12" :offset="6">
					<el-button type="primary" @click="dialogVisible = true">新增商品</el-button>
				</el-col>
			</el-row>
			
				<el-dialog
				  title="提示"
				  :visible.sync="dialogVisible"
				  width="30%"
				  :before-close="handleClose">
				  
				  <el-form ref="form" :model="editGoods" label-width="80px" size="mini">
					<el-form-item label="商品名称">
						<el-input v-model="editGoods.goodsName"></el-input>
					</el-form-item>
					
					<el-form-item label="添加数量">
						<el-input v-model="present"></el-input>
					</el-form-item>
					
					<el-form-item label="商品类型">
						<el-input v-model="editGoods.goodsType"></el-input>
					</el-form-item>
					<el-form-item label="商品简述">
						<el-input v-model="editGoods.instrutions"></el-input>
					</el-form-item>
					<el-form-item label="商品状态">
						<el-input v-model="editGoods.status"></el-input>
					</el-form-item>
					<el-form-item label="商品成色">
						<el-input v-model="editGoods.condition"></el-input>
					</el-form-item>
					<el-form-item label="商品折扣">
						<el-input v-model="editGoods.discount"></el-input>
					</el-form-item>
					<el-form-item label="商品最大价格">
						<el-input v-model="editGoods.maxExp"></el-input>
					</el-form-item>
					<el-form-item label="最小价格">
						<el-input v-model="editGoods.miniExp"></el-input>
					</el-form-item>
					
					<el-form-item label="图片地址">
						<!-- <el-upload
						  class="upload-demo"
						  drag
						  action="https://jsonplaceholder.typicode.com/posts/"
						  multiple>
						  <i class="el-icon-upload"></i>
						  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
						</el-upload> -->
						
						<!--  -->
					</el-form-item>
					
					<el-form-item>
						<el-button type="primary" @click="emitData()">提交商品信息</el-button>
						<el-button @click="dialogVisible = false">取消</el-button>
					</el-form-item>
				  </el-form>
				</el-dialog>
		</div>
		<br />
		<div id="else">
			<el-table
				:data="goodsList"
				class="shopCart">
				
				<el-table-column prop="imgurl"
					label="图片" width="80">
					<template slot-scope="scope">
						<img :src="scope.row.imgurl" width="20px" height="20px"/>
					</template>
				</el-table-column>
				
				<el-table-column prop="wareID"
					label="仓库ID">
				</el-table-column>
				<el-table-column prop="present"
					label="仓库现存">
				</el-table-column>
				<el-table-column prop="goodsID"
					label="商品ID" width="80">
				</el-table-column>
				<el-table-column prop="maxStock"
					label="仓库最大容量">
				</el-table-column>
				
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button size="mini"
							@click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
  export default {
    data() {
      return {
		dialogVisible: false,
		goodsList: this.$route.query.goodsList,
		storeID: this.$route.query.storeID,
		storeName: this.$route.query.storeName,
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
		present: "",
		dialogImageUrl: "",
      };
    },
    methods: {
		handleRemove(file, fileList) {
			console.log(file, fileList);
		},
		handlePictureCardPreview(file) {
			this.dialogImageUrl = file.url;
		},
		handleClose(done) {
			this.$confirm('确认关闭？').then(_ => {
					done();
				}).catch(_ => {});
		},
		emitData() {
			console.log(this.editGoods);
			this.$confirm('确认提交？').then(_ => {
				
				let url = this.HOST + 'storeMain/addGood';
				let data = new FormData();
				data.append("goods",this.editGoods);
				data.append("storeID",this.storeID);
				data.append("storeName",this.storeName);
				data.append("present",this.present);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
				})
				.catch((error) => {
					console.log(error);
				})
				
					done();
				}).catch(_ => {});
		},
		handleDelete(index,row) {
			console.log(index);
			console.log(row);
			
			
		},
		handleEdit(index,row) {
			console.log(index);
			console.log(row);
		}
    }
  };
</script>

<style>

</style>
