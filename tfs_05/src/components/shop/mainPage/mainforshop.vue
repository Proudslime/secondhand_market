<template>
	<div id="shopmain">
		<el-container>
			<el-main>
				<div class="grid-content" style="margin-left: 10%;margin-right: 10%;">
					<div 
					class="goodslist" 
					style="margin-left: 10px;margin-bottom: -40px;background-color: aqua;float: left;border: 2px solid black;width: 200px;height: 200px;" 
					v-for="(item,index) in goodsList" @click="toDetail(index)">
						<img :src="item.imgurl" width="90px" height="90px"/>
						<span>{{item.goodsName}}</span>
						<span>{{item.price}}</span>
					</div>
				</div>
			</el-main>
			<el-footer>
				
			</el-footer>
		</el-container>
	</div>
</template>

<script>
	export default {
		data(){
			return{
				UserData: this.$route.query.UserData,
				goodsList: this.$route.query.goodsList,
				
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
			toDetail(index) {
				console.log(this.goodsList[index]);
				this.$router.push({
					path: '/detail',
					query: {
						UserData: this.UserData,
						detailGood: this.goodsList[index],
					}
				});
			}
		}
	}
</script>

<style>
	 .el-row {
	    margin-bottom: 20px;
	    &:last-child {
	      margin-bottom: 0;
	    }
	  }
	  .el-col {
	    border-radius: 4px;
	  }
	  .mainData {
		  float: left;
		  width: 320px;
	  }
	  .goodslist{
		  display: inline;
		  margin: 80px;
	  }
</style>
