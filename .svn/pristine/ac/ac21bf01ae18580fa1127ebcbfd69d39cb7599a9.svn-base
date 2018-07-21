/**
 * 
 */

document.write("<script language=javascript src='dx.js'></script>");



//通过radioButton查询担保环
function searchDBCircle(skip){
	$("input[type='checkbox']").prop("checked","true");
	 var num=3;
/*	 if(document.getElementById('radiobutton1').checked){ num=1;}*/
	 if(document.getElementById('radiobutton2').checked){ num=2;}
	 if(document.getElementById('radiobutton3').checked){ num=3;}
	 if(document.getElementById('radiobutton4').checked){ num=4;}
	 if(document.getElementById('radiobutton5').checked){ num=5;}
	 
		jQuery.ajax({
			type: 'POST',
			url: 'searchDBcircle.do',
			dataType: 'json',
			data:{num:num,
				  skip:skip},
			beforeSend:function (){
		        showModal();  
		    }, 
			success: function(data){
				hideModal();
				var length=data.nodes.length;
				if(length==0){
					alert("没有查询结果！请重新输入。");
					$("#nex").attr('disabled',true);
				}else{
					$("#nex").attr('disabled',false);
					for(var i=0;i<length;i++)
					{
						if(data.nodes[i].label!="C") continue;
						if(data.nodes[i].khmc.indexOf("小额贷款")>=0||data.nodes[i].khmc.indexOf("小贷")>=0)
						data.nodes[i].xiaodai=true;
						if(data.nodes[i].khmc.indexOf("地产")>=0)
							data.nodes[i].dichan=true;
					}
					
					svg.select("g").selectAll("g").attr("transform","translate(0,0)scale(1)");
					zooma.scale(1);
					zooma.translate([0,0]);
					update(data);
					showInfo();
				}
			},
			error: function(){
				hideModal();
				alert("连接失败！");
			}
		}); 
}
//通过输入框查询担保环
function searchDBCircleByInput(skip){
	var num=document.getElementById("searchDBchain").value;
	
	jQuery.ajax({
		type: 'POST',
		url: 'searchDBcircleByInput.do',
		dataType: 'json',
		data:{num:num,skip:skip},
		beforeSend:function(){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有这种类型的担保环！请重新输入。");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				 svg.select("g").selectAll("g").attr("transform","translate(0,0)scale(1)");
				zooma.scale(1);
				zooma.translate([0,0]);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败！");
		}
	}); 
	
}
//显示摘要图
function show(){
	
		jQuery.ajax({
			type: 'POST',
			url: 'graph.do',
			dataType: 'json',
			beforeSend:function(){
		        showModal();  
		    }, 
			success: function(data){
				alert("更新数据库完成！");
				hideModal();
//				console.log(data);
//				update(data);
//				$("#shownode").prop("checked",false);
//				$("#showlink").prop("checked",false);
//				showNodetext($("#shownode"));
//				showLinktext($("#showlink"));
//				showInfo();
			
		},
		error: function(){
			hideModal();
			alert("连接失败！");
		}
	}); 
 }

//通过姓名模糊查找节点
function search(){

	var name=document.getElementById("searchAcount").value;
	var options = $("#searchSort option:selected");
	console.log(options.val());
	switch(options.val()){
		case "accName":url = 'findByCountName.do';break;
		case "accNum":url = 'findByCountNumber.do';break;
		case "comName":url = 'findByCompanyName.do';break;
		case "comId":url = 'findByCompanyJgdm.do';break;
	}
	console.log(url);
	jQuery.ajax({
		type: 'POST',
		data: {name:name},
		url: url,
		dataType: 'json',
		beforeSend:function (){
        	showModal();  
    	}, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果！请重新输入。");
			}else{
				update(data);
			    showInfo();}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	}); 
}

//查询交易链
function searchJYChain(){
	var min=document.getElementById("minJY").value;
	var max=document.getElementById("maxJY").value;
		jQuery.ajax({
			type: 'POST',
			url: 'findJYChain.do',
			dataType: 'json',
			data:{
				min:min,
				max:max
				},
			success: function(data){
			if(data.nodes.length==0){
				alert("没有查询结果！请重新输入。");
			}else{
				update(data);
			    showInfo();}
			},
			error: function(){
			}
		}); 
 }

/*交易分析*/
function searchJYByFD(){
	var min = document.getElementById("minJYM").value;
	var max = document.getElementById("maxJYM").value;

	var hop = $("#hop option:selected").val();
	

	var date = document.getElementById("dateRange").value.replace(/-/g,'').replace(/\s+/g, "");
	var startDate = date.substring(0,8);
	var endDate = date.substring(8,16);
	
	var ID;
	arcd.attr("godbye",function(d){ID=d.id;});
	var nodes=JSON.stringify(graph.nodes);
	var links=JSON.stringify(graph.links);
	
	console.log("hop="+hop);
	console.log("ID="+ID);
	
	jQuery.ajax({
		type: 'POST',
		url: 'extendByInput.do',
		dataType: 'json',
		data:{
			id:ID,
			nodes:nodes,
			links:links,
			minJYJE:min,
			maxJYJE:max,
			hop:hop,
			minJYRQ:startDate,
			maxJYRQ:endDate
		},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果！请重新输入。");
			}else{
				update(data);
			    showInfo();}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});

}
/*查找前n的担保环*/
function searchTopDbcircleOflength(type){

	jQuery.ajax({
		type: 'POST',
		url: 'searchTopDbcircle.do',
		data:{type:type},
		dataType: 'json',
		beforeSend:function(){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			update(data);
			showInfo();
		},
		error: function(){
			hideModal();
			alert("连接失败！");
		}
	}); 
}
/*获取担保圈统计信息*/
var dbdata;
function getDbCircleInfo(){

	jQuery.ajax({
		type: 'POST',
		url: 'searchDBcircleInfo.do',
		async: false,
		dataType: 'json',
		success: function(data){
			dbdata = data;
		},
		error: function(){
			alert("连接失败！");
		}
	}); 
}
/*获取担保列表*/
var dblist;
function getDBCircleList(){
	jQuery.ajax({
		type: 'POST',
		url: 'searchDBcircleListInfo.do',
		dataType: 'json',
		success: function(data){
			dblist = data;
		},
		error: function(){
			alert("连接失败！");
		}
	}); 
}
/*通过点击列表显示担保环*/
function searchDBCircleByList(num,skip){
	console.log("2");
	console.log(num+"------"+skip);
	jQuery.ajax({
		type: 'POST',
		url: 'searchDBcircleByInput.do',
		async: false,
		dataType: 'json',
		data:{num:num,skip:skip},
		beforeSend:function(){
	        showModal();  
	    }, 
		success: function(data){
			console.log("w3");
			hideModal();
			svg.select("g").selectAll("g").attr("transform","translate(0,0)scale(1)");
			zooma.scale(1);
			zooma.translate([0,0]);
			update(data);
			showInfo();
		},
		error: function(){
			hideModal();
			console.log("f3");
			alert("连接失败！");
			
		}
	}); 
	console.log("e3");
}
/*获取各种分析模型的总条数*/
var totalNum;//存储各模型（除担保环模型）总条数的对象数组
function getTotalNum(){
	jQuery.ajax({
		type: 'POST',
		url: 'searchPatternNumInfo.do',
		dataType: 'json',
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			totalNum = data.PattenNumInfo;
			
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}

/*贷款转入银承保证金*/
function searchYDZC(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkyyycbz.do',
		data:{skip:skip},
		dataType: 'json',
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}
/*贷款转入房地产*/
function searchDkzrfdc(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkzrfdc.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				$("#nex").attr('disabled',true);
				alert("没有查询结果");
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}
/*贷款转入融资担保类公司*/
function searchDkzrrzdb(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkzrrzdb.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				$("#nex").attr('disabled',true);
				alert("没有查询结果");
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}
/*贷款回流至担保人*/
function searchDkhlzdbr(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkhlzdbr.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}
/*贷款以旧还新*/
function searchDkyjhx(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkyjhx.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}
/*贷款回流至法人代表账户*/
function searchDkhlzfrdb(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkhlzfrdb.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}
/*向本公司员工发放贷款*/
function searchDkffzyg(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'dkffzyg.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}

/*消费贷资金违规进入房地产行业*/
function searchXfdjrfdc(skip){
	jQuery.ajax({
		type: 'POST',
		url: 'xfdkzrfdc.do',
		dataType: 'json',
		data:{skip:skip},
		beforeSend:function (){
	        showModal();  
	    }, 
		success: function(data){
			hideModal();
			if(data.nodes.length==0){
				alert("没有查询结果");
				$("#nex").attr('disabled',true);
			}else{
				$("#nex").attr('disabled',false);
				update(data);
				showInfo();
			}
		},
		error: function(){
			hideModal();
			alert("连接失败");
		}
	});
}