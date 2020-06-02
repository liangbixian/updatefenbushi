new Vue({
	el:"#app",
	data:{
		specList:[],
		spec:{
			specName:''
		},
		searchSpec:{
			specName:''
		},
		page: 1,  //显示的是哪一页
        pageSize: 5, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage:9,
        specEntity:{
        	spec:{},
        	specOption:[]
        },
        selectId:[]//选中的数据id
	},
	methods:{
		pageHandler: function (page) {
			if(page==null){
				page=1;
			}
			this.page=page;
            var _this=this;
			axios.post("../spec/search.do?page="+this.page+"&pageSize="+this.pageSize,this.searchSpec).then(function(response){
				console.log(response.data);
				_this.total=response.data.total;
				_this.specList=response.data.rows;
			}).catch(function(reason){
				console.log(reason)
			})
        },
        addRow:function(){
        	this.specEntity.specOption.push({});
        },
        deleteRow:function(index){
        	this.specEntity.specOption.splice(index,1);
        },
        save:function(){
        	var _this=this;
        	var url='';
        	if(_this.specEntity.spec.id!=null){
        		//更新
        		url="../spec/update.do";
        	}else{
        		//添加
        		url="../spec/save.do";
        	}
 			axios.post(url,this.specEntity).then(function(response){
 				if(response.data.success){
 					alert(response.data.message);
 					_this.specEntity.spec={};
 					_this.specEntity.specOption=[];
 					_this.pageHandler();
 				}else{
 					alert(response.data.message);
 				}
 			}).catch(function(reason){
 				console.log(reason)
 			})
        },
        findSpecWithId:function(id){
        	var _this=this;
 			axios.get("../spec/findSpecWithId.do?id="+id).then(function(response){
 				_this.specEntity=response.data;
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
        deleteSpec:function(){
        	var _this=this;
        	axios.post("../spec/delete.do",Qs.stringify({idx:this.selectId},{indices:false})).then(function(response){
        		if(response.data.success){
        			alert(response.data.message);
        			_this.pageHandler();
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