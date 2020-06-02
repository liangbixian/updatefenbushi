new Vue({
	el: "#app",
	data:{
		categoryList:[],
		page: 1,  //显示的是哪一页
        pageSize: 5, //每一页显示的数据条数
        total: 150, //记录总数
        maxPage:9,
        id:0,
        grade:1,
        gradeEntity1:{},
		gradeEntity2:{}
	},
	methods: {
		pageHandler: function (page) {
			_this = this;
			this.page = page;
			axios.post("../itemCat/findByParentId.do?page="+this.page+"&pageSize="+this.pageSize+"&parentId="+this.id)
			.then(function (response) {
				//取服务端响应的结果
				_this.categoryList =response.data.rows;
				_this.total = response.data.total;
				console.log(response);
			}).catch(function (reason) {
				console.log(reason);
			})
		},
		nextGrade:function(item){
			this.id=item.id;
			if(this.grade==1){
				this.gradeEntity1={};
				this.gradeEntity2={};
			}
			if(this.grade==2){
				this.gradeEntity1=item;
				this.gradeEntity2={};
			}
			if(this.grade==3){
				this.gradeEntity2=item;
			}
			this.pageHandler(1);
		},
		setGrade:function(grade){
			this.grade=grade;
		}
	},
	created: function () {
		this.pageHandler(1);
	}
});
