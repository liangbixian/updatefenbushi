new Vue({
    el:"#app",
    data:{
        categoryList1:[],//分类1数据列表
        categoryList2:[],//分类2数据列表
        categoryList3:[],//分类3数据列表
        grade:1,  //记录当前级别
        catselected1:-1,
        catselected2:-1,
        catselected3:-1,
        typeId:0,
        brandList:[],
        selBrand:-1
    },
    methods:{
    	loadCateData: function (id) {
            _this = this;
            axios.post("../shopitemCat/findByParentIdeasy.do?parentId="+id)
                .then(function (response) {
                    if (_this.grade == 1){
                        //取服务端响应的结果
                        _this.categoryList1 = response.data;
                    }
                    if (_this.grade == 2){
                        //取服务端响应的结果
                        _this.categoryList2 =response.data;
                    }
                    if (_this.grade == 3){
                        //取服务端响应的结果
                        _this.categoryList3 =response.data;
                    }
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        getCategorySel:function(grade){
        	if(grade==1){
        		this.catselected2=-1;
        		categoryList2=[];
        		
        		this.catselected3=-1;
        		categoryList3=[];
        		
        		
        		this.grade=grade+1;
        		this.loadCateData(this.catselected1);
        	}
        	if(grade==2){
        		this.catselected3=-1;
        		categoryList3=[];
        		
        		this.grade=grade+1;
        		this.loadCateData(this.catselected2);
        	}
        	if(grade==3){
        		var _this=this
        		axios.post("../shopitemCat/findOneCategory.do?id="+this.catselected3)
                .then(function (response) {
                    _this.typeId=response.data.typeId;
                }).catch(function (reason) {
                console.log(reason);
            })
        	}
        	
        }
//        

    },
    watch:{
    	typeId:function(newValue,oldValue){
    		_this = this;
            _this.brandList =[];
            _this.selBrand = -1;
            axios.post("../temp/findOne.do?id="+newValue)
                .then(function (response) {
                    console.log(response.data);
                    _this.brandList = JSON.parse(response.data.brandIds);
                    console.log(_this.brandList);
                }).catch(function (reason) {
                console.log(reason);
            });
    	}
    },
    created: function() {
        this.loadCateData(0);
    }
});
