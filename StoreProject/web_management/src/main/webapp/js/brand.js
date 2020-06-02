new Vue({
	el:"#app",
	data:{
		brandList:[],
		brand:{
			name:'',
			firstChar:''
		},
		searchBrand:{
			name:'',
			firstChar:''
		},
		page: 1,  //显示的是哪一页
        pageSize: 5, //每一页显示的数据条数
        total: 150, //记录总数
        maxPage:9,
        selectId:[]//选中的数据id
	},
	methods:{
		findAllBrands:function(){//请求所有品牌数据
			var _this=this;
			axios.get("../brand/findAllBrands.do").then(function(response){
				console.log(response.data);
				_this.brandList=response.data;
			}).catch(function(reason){
				console.log(reason)
			})
		},
		pageHandler: function (page) {
			if(page==null){
				page=1;
			}
			this.page=page;
            var _this=this;
			axios.post("../brand/findPage.do?page="+this.page+"&pageSize="+this.pageSize,this.searchBrand).then(function(response){
				console.log(response.data);
				_this.total=response.data.total;
				_this.brandList=response.data.rows;
			}).catch(function(reason){
				console.log(reason)
			})
        },
        brandSave:function(){
        	var url;
        	if(this.brand.id!=null){
        		//更新
        		url='../brand/update.do';
        	}else{
        		//添加
        		url='../brand/add.do';
        	}
        	var _this=this;
        	axios.post(url,this.brand).then(function(response){
        		if(response.data.success){
        			alert(response.data.message);
        			_this.pageHandler(1);
        		}else{
        			alert(response.data.message);
        		}
			}).catch(function(reason){
				console.log(reason)
			})
        },
        findById:function(id){
        	var _this=this;
        	axios.get("../brand/findById.do",{params:{id:id}}).then(function(response){
        		_this.brand=response.data;
			}).catch(function(reason){
				console.log(reason)
			})
        },
        deleteSelection:function(event,id){
        	if(event.target.checked){
        		//选中
        		this.selectId.push(id);
        	}else{
        		//取消选中
        		var idx=this.selectId.indexOf(id);
        		this.selectId.splice(idx,1);
        	}
        	console.log(this.selectId);
        },
        deleteBrand:function(){
        	var _this=this;
        	axios.post("../brand/delete.do",Qs.stringify({idx:this.selectId},{indices:false})).then(function(response){
        		if(response.data.success){
        			alert(response.data.message);
        			_this.pageHandler(1);
        		}else{
        			alert(response.data.message);
        		}
			}).catch(function(reason){
				console.log(reason)
			})
        }
	},
	created:function(){
		this.pageHandler();
	}
})