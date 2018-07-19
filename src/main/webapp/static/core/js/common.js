

	$(function(){
	    //当前分页页码自动选中
		$(".pagination a").each(function(){
			if($(this).attr("data")==$("#pageNo").val()){
				$(this).addClass("selected");
			}
		});
		
		//分页按钮第N页
		$('.pagination input:eq(0)').keypress(function(e){
			if(e.keyCode==13){
				$("#pageNo").val($(this).val());
				$("#searchForm").submit();
			}
			//e.preventDefault();//无法修改文本
	    });
		
		//分页按钮每页第N条
		$('.pagination input:eq(1)').keypress(function(e){
			if(e.keyCode==13){
				$("#pageSize").val($(this).val());
				$("#searchForm").submit();
			}
			//e.preventDefault();//无法修改文本
	    });
		//全选按钮
		$("input[name='ckAll']").click(function () {
	        $("input[name='ckChild']").prop("checked", $(this).prop("checked"));
	    });
		
	});
	
	
	//查询翻页公共函数
	function page(n, s) {
	    $("#pageNo").val(n);
	    $("#pageSize").val(s);
	    $("#searchForm").submit();
	    return false;
	}
	
	//选中多个
	function selectIds() {
	    var ids = "";
	    $("input[name='ckChild']:checked").each(function (i, item) {
	        ids += $(this).val() + ",";
	    });
	    if (ids == "") {
	        layer.alert("请选择要操作的项");
	        return false;
	    }
	    ids = ids.substring(0, ids.lastIndexOf(","));
	    return ids;
	}
	
	//选中一个
	function selectId() {
	    var ids = [];
	    $("input[name='ckChild']:checked").each(function (i, item) {
	        ids.push($(this).val())
	    });
	    if (ids.length<=0) {
	        layer.alert("请选择要操作的项！");
	        return false;
	    }
	    if (ids.length>1) {
	        layer.alert("请选择一项进行操作！");
	        return false;
	    }
	    return ids.join(",");
	}

	//get请求
	function get(url,data,isParent) {
		$.get(url,data,function(result){
			if(result.status==1) {
				layer.alert(result.message,function(){
					if(isParent && isParent==1){
						parent.location.reload();
					}else{
						location.reload();
					}
				});
			} else {
				layer.alert(result.message);
			}
		});
	}
	
	//post请求
	function post(url,data,isParent) {
		$.post(url,data,function(result){
			if(result.status==1) {
				layer.alert(result.message,function(){
					if(isParent && isParent==1){
						parent.location.reload();
					}else{
						location.reload();
					}
				});
			} else {
				layer.alert(result.message);
			}
		});
	}
	
	//基本弹窗
	function dialog(title, url, width, height) {
		layer.open({
			type: 2,
			title: title,
			shade: 0,
			area: [width, height],
			content: url
		});
	}
	
	function confirm(title, url, width, height) {
		layer.open({
			type: 2,
			title: title,
			shade: 0,
			area: [width, height],
			content: url,
			btn: ['确定', '取消'],
			yes: function(index, layero){
				//得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				var iframeChild = window[layero.find('iframe')[0]['name']];
		    	//调用授权提交方法
				iframeChild.save();
				layer.close(index);
			}
		});
	}
/*function getCurrentDateTime(){
	 var date=new Date();
	 var year=date.getFullYear();
	 var month=date.getMonth()+1;
	 var day=date.getDate();
	 var hours=date.getHours();
	 var minutes=date.getMinutes();
	 var seconds=date.getSeconds();
	 return year+"-"+formatZero(month)+"-"+formatZero(day)+" "+formatZero(hours)+":"+formatZero(minutes)+":"+formatZero(seconds);
 }


function getCurrentDate(){
	 var date=new Date();
	 var year=date.getFullYear();
	 var month=date.getMonth()+1;
	 var day=date.getDate();
	 return year+"-"+formatZero(month)+"-"+formatZero(day);
}


 function formatZero(n){
	 if(n>=0&&n<=9){
		 return "0"+n;
	 }else{
		 return n;
	 }
 }*/
 
 
 