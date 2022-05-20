var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:"/userdelete/{id}",
		data:{uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			 var jsonObj = JSON.parse(JSON.stringify(data));
			 var result = jsonObj.delResult;
			if(datdelResult === "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(delResult === "false"){//删除失败
				alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				 // changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(delResult === "notexist"){
				alert("对不起，用户【"+obj.attr("username")+"】不存在");
				// changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
			// changeDLGContent("对不起，删除失败");
		}
	});
}
//显示提示框
function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
//改变提示框内容
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/user.do?method=view&uid="+ obj.attr("userid");
	});
	
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/user.do?method=modify&uid="+ obj.attr("userid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	/*$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});*/

	//自行设置的弹窗

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
	// 点击提示框中的"取消"按钮触发
	$('#no').click(function () {
		$('.zhezhao').css('display', 'none');
		$('#removeUse').fadeOut();
	});
	// 点击提示框中的"确定"按钮触发
	$('#yes').click(function () {
		deleteUser(obj);
	});
});
// 发送ajax请求删除内容
// function deleteExecute(obj){
// 	$.ajax({
// 		type:"GET",
// 		url:path+"/jsp/user.do",
// 		data:{method:"deluser",uid:obj.attr("userid")},
// 		dataType:"json",
// 		success:function(data){
// 			if(data.delResult == "true"){//删除成功：移除删除行
// 				changeDLGContent("删除成功");
// 				obj.parents("tr").remove();
// 			}else if(data.delResult == "false"){//删除失败
// 				changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
// 			}else if(data.delResult == "notexist"){
// 				changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
// 			}
// 		},
// 		error:function(data){
// 			changeDLGContent("对不起，删除失败");
// 		}
// 	});
// }
/*

	$(".deleteUser").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/jsp/user.do",
				data:{method:"deluser",uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，用户【"+obj.attr("username")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});
});*/
