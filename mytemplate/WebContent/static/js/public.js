;(function($){
	var cuslayer = function(params){
		var defaults = {
			mode:'normal',
			type:1, //0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			title:false,
			shade: [0.5, '#000'], //[遮罩透明度, 遮罩颜色]
			border:[5, 0.5, '#666'],
			closeBtn:[0, true],
			pageUrl:'', //请求回来弹窗的url
			pageData:undefined, //请求弹窗携带的参数
			maxmin:true, //是否输出窗口最小化/全屏/还原按钮。 
			width:'550',
			height:'600',
			btns:2,
			btn:['确 定','取 消'],
			msg:'',
		};
		
		params = $.extend(defaults, params);
		
		if(undefined != params.pageData){
			params.pageUrl = params.pageUrl +"?" + $.param(params.pageData);
		}
		
		var mode = params.mode;
		
		if(mode == 'del'){
			 params.type=0;
			 params.width='auto';
			 params.height='auto';
		}
		if(mode == 'save') params.height = parseInt(params.height)+80+'';
		
		var index = $.layer({
		    type: params.type,
		    shade: params.shade,
		    border : params.border,
		    area: [params.width,params.height ],
		    title: params.title,
		    closeBtn: params.closeBtn,
		    maxmin: params.maxmin,
		    border : params.border,
		    dialog: {
		    	msg:params.msg,
		        type: 4,
		        btns:params.btns,
		        btn: params.btn
		    },
		    page: {
		    	url:params.pageUrl, 
		    	ok: function(datas){
		    		if(mode == "save"){
		    			var f = $(datas).find('form');
			    		$("#xuboxPageHtml"+index).data('form',f);
		    		}
		    		$(".xubox_min").hide();
		    		$("#xuboxPageHtml"+index).css({'overflowY':'auto','height':params.height});
		    	}
		    },
		    full:function(layero){
		    	setTimeout(function() {
		    		$("#xuboxPageHtml"+index).css({'overflowY':'auto','height':layero.height()-80});
		        },101);
		    },
		    restore: function(layero){
		    	$(".xubox_min").hide();
		    	$("#xuboxPageHtml"+index).css({'overflowY':'auto','height':layero.height()-80});
		    },
		    btns: params.btns,                    
	        btn: params.btn,
	        yes: function(index){
	        	if(mode == 'save'){
	        		var $form = $("#xuboxPageHtml"+index).data('form');
	        		$form.Validform({
	        			ajaxPost:true,
	        			beforeCheck:function(curform){
	        				//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
	        				//这里明确return false的话将不会继续执行验证操作;	
	        				alert("dd")
	        			},
	        			beforeSubmit:function(curform){
	        				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
	        				//这里明确return false的话表单将不会提交;	
	        				alert("3424")
	        			},
	        			tiptype:function(msg,o,cssctl){
	        				if(!o.obj.is("form")){
	        					layer.tips(msg,o.obj, {
	        						guide: 1,
	        				        style: ['background-color:#F26C4F; color:#fff', '#F26C4F'],
	        				    });
	        				}
	        			},
	        			tipSweep:true
	        		});
	        		$form.submit();
	        	}
	        	if(mode == 'del' || mode == 'delete'){
	        		$.ajax({url:params.pageUrl,data:params.pageData,type:'post'}).done(function(data){
	        			if(data>0) {
	        				layer.msg('删除成功', 1, 1,function(){
	        					reloadUrl();
	        				});
	        			}
	        		}).fail(function(error){alert("操作失败")});
	        	}
	        }, no: function(index){
	            layer.close(index);
	        }
		});
		
		
	};
	$.cuslayer = cuslayer;
})(jQuery);

//得到url的参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
};

//刷新url
function reloadUrl(){
	window.location.href = (window.location.href).split("?")[0]+"?menuid="+$curmenu.attr("id");
}

//分页
function paging(formId,pageNo){
	var $form = $("#"+formId),$target = $("#"+$form.attr('target')),spinner;
	var pageNoInput = $form.find('input[name="pageNum"]');
	if(pageNoInput.size() == 0){
		$form.append("<input type='hidden'  name = 'pageNum' value='1'/>");
		pageNoInput = $form.find('input[name="pageNum"]');
	}
	pageNoInput.val(pageNo);
	$.ajax({
		url:$form.attr('action'),
		type:'post',
		dataType:'html',
		data:$form.serialize(),
		beforeSend:function(){
			spinner = new Spinner({color: '#3d9bce',width:20,radius:20}).spin($target[0]);
		}
	}).done(function(data){
		if ($target) {
			$target.stop();
		}
		$target.html(data);
	}).fail(function(error){
		alert("请求错误!");
	})
	return false;
};
