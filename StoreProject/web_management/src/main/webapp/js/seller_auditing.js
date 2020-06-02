new Vue({
		el: "#app",
		data:{
			sellerList:[],
			seller:{},//添加品牌实体
			searchSellerObj:{},//搜索品牌实体
			page: 1,  //显示的是哪一页
			pageSize: 5, //每一页显示的数据条数
			total: 0, //记录总数
			maxPage:9,
			entity:{}
		},
		methods: {
			pageHandler: function (page) {
				_this = this;
				this.page = page;
				this.searchSellerObj.status = 0;
				axios.post("../seller/findPage.do?page="+this.page+"&pageSize="+this.pageSize,this.searchSellerObj)
						.then(function (response) {
							//取服务端响应的结果
							_this.sellerList =response.data.rows;
							_this.total = response.data.total;
						}).catch(function (reason) {
							console.log(reason);
				})
			},
			detail:function(id){
				axios.get("../seller/findOne.do?id="+id).then(function (response) {
					//取服务端响应的结果
					_this.seller =response.data;
					console.log(response.data);
				}).catch(function (reason) {
					console.log(reason);
				})
			},
			updateStatus:function(id,status){
				_this = this;
				axios.get("../seller/updateStatus.do?sellerId="+id+"&status="+status).then(function (response) {
					//取服务端响应的结果
					if(response.data.success){
						_this.pageHandler(1);
					}else{
						alert(response.data.message);
					}
					
				}).catch(function (reason) {
					console.log(reason);
				})
			}
		},
		created: function () {
			this.pageHandler(1);
		}
	});