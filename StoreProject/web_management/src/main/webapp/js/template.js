Vue.component('v-select', VueSelect.VueSelect);
new Vue({
    el:"#app",
    data:{
        tempList:[],
        temp:{},
        searchTemp:{},
        page: 1,  //显示的是哪一页
        pageSize: 5, //每一页显示的数据条数
        total: 150, //记录总数
        maxPage:9,
        selectIds:[], //记录选择了哪些记录的id
        brandsOptions: [],
        placeholder: '可以进行多选',
        selectBrands: [],
        sel_brand_obj: [],
        
        specOptions: [],
        selectSpecs: [],
        sel_spec_obj: [],
    },
    methods:{
    	pageHandler: function (page) {
            _this = this;
            this.page = page;
            axios.post("../temp/search.do?page="+this.page+"&pageSize="+this.pageSize,this.searchTemp)
                .then(function (response) {
                    //取服务端响应的结果
                    _this.tempList = response.data.rows;
                    _this.total = response.data.total;
                    console.log(response);
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        jsonToStr:function(jsonStr,key){
        	var jsonObject=JSON.parse(jsonStr);
        	var value='';
        	
        	for(var i=0;i<jsonObject.length;i++){
        		if(i>0){
        			value+=",";
        		}
        		value+=jsonObject[i][key];
        	}
        	return value;
        },
        selected_brand: function(values){
//            this.selectBrands =values.map(function(obj){
//                return obj.id
//            });
            console.log(this.sel_brand_obj)
//        	return this.sel_brand_obj;
        },
        selected_spec: function(values){
//            this.selectSpecs =values.map(function(obj){
//                return obj.id
//            });
            console.log(this.sel_spec_obj);
        },
        selLoadData:function () {
            _this = this;
            axios.get("../brand/selectOptionList.do")
                .then(function (response) {
                    _this.brandsOptions = response.data;
                }).catch(function (reason) {
                console.log(reason);
            });
            axios.get("../spec/selectOptionList.do")
            .then(function (response) {
                _this.specOptions = response.data;
            }).catch(function (reason) {
            console.log(reason);
            });
        },
        save:function () {
            var entity = {
                name:this.addname,
                specIds:this.sel_spec_obj,
                brandIds:this.sel_brand_obj,
                customAttributeItems:this.otherExtends
            };
            axios.post("../temp/add.do",entity)
                .then(function (response) {
                   console.log(response);
                   if(response.data.success){
                	   _this.pageHandler(1);   
                   }else{
                	   alert(response.data.message)
                   }
                }).catch(function (reason) {
                console.log(reason);
            });
        }
    },
    created: function() {
        this.pageHandler(1);
        this.selLoadData();
    }
});