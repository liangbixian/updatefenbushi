new Vue({
		el:"#app",
		data:{
			enterprise: {}
		},
		methods:{
			register:function(){
				 axios.post("../seller/add.do",this.enterprise)
                 .then(function (response) {
                	 if(response.data.success){
                         console.log(response);
                         alert(response.data.message);
                         location.href="shoplogin.html";             		 
                	 }else{
                		 alert(response.data.message);
                	 }
   
                 }).catch(function (reason) {
                 console.log(reason);
             })
			}
		}
	})