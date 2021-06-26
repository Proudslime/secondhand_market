<template>
	<div class="search">
		<div>
			<div class="head">
				<div style="float: left;margin-right: 10px;">
					<img src="../../../assets/logo.png" width="50px" height="50px" />
				</div>
				<div style="float: left;margin-right: 10px;">
					<el-input v-model="serachMsg" width="50px" height="50px">查询商品</el-input>
				</div>
				<div style="float: left;margin-right: 10px;">
					<el-button type="primary" @click="toshopmain()">查询</el-button>
				</div>
			</div>
			<br />
			<div style="text-align: left;margin-top: 60px;">
				<el-row style="background-color: #e4e4e4;text-align: left;margin-left: 60px;">
					<el-col :span="2">
						<div style="background-color: #dadada;">
							<el-button type="text" class="fl" disabled>类型：</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='工具;'">工具</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='娱乐;'">娱乐</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='资料;'">资料</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text"  @click="serachMsg='饮食;'">饮食</el-button>
						</div>
					</el-col>
				</el-row>
				<el-row style="background-color: #e4e4e4;text-align: left;margin-left: 60px;">
					<el-col :span="2">
						<div style="background-color: #dadada;">
							<el-button type="text" class="fl" disabled>电子产品：</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='手机;'">手机</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='电脑;'">电脑</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='平板;'">平板</el-button>
						</div>
					</el-col>
				</el-row>
				<el-row style="background-color: #e4e4e4;text-align: left;margin-left: 60px;">
					<el-col :span="2">
						<div style="background-color: #dadada;">
							<el-button type="text" class="fl" disabled>场景：</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='生活;'">生活</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='学习;'">学习</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='教育;'">教育</el-button>
						</div>
					</el-col>
					<el-col :span="2">
						<div>
							<el-button type="text" @click="serachMsg='娱乐;'">娱乐</el-button>
						</div>
					</el-col>
				</el-row>
			
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data:function() {
			return {
				serachMsg: '',
				UserData: this.$route.query.UserData
			}
		},
		methods: {
			toshopmain() {
				
				console.log(this.serachMsg);
				let url = this.HOST + 'main/search';
				let data = new FormData();
				data.append('searchContent',this.serachMsg);
				this.$axios.post(url, data)
				.then((response) => {
					console.log(response);
					this.$router.push({
						path: '/mainforshop',
						query:{
							UserData: this.UserData,
							goodsList: response.data.data
						}
					})
				})
				
				// this.$router.push({
				// 	path: '/mainforshop',
				// 	query: {
				// 		username: this.username
				// 	}
				// });
			},
		}
	}
</script>

<style>
</style>
