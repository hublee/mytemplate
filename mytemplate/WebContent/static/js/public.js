var lastIndex;//最后弹窗索引
;(function($){
	var cuslayer = function(params){
		var defaults = {
			mode:'normal',
			type:1, //0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			title:false,
			shade: [0.5, '#000'], //[遮罩透明度, 遮罩颜色]
			border:[3, 0.5, '#666'],
			closeBtn:[0, true],
			url:undefined, //请求回来弹窗的url
			data:{}, //请求弹窗携带的参数
			maxmin:true, //是否输出窗口最小化/全屏/还原按钮。 
			width:'600',
			height:'600',
			btns:2,
			btn:['确 定','取 消'],
			msg:'',
			reloadurl:false //是否url刷新,默认false当前右侧刷新
		};
		
		params = $.extend(defaults, params);
		
		var mode = params.mode;
		
		if(undefined != params.closebtn){
			params.closeBtn = params.closebtn;
		}
		
		if(mode == 'del' || mode == 'delete' || mode == 'page'){
			if(undefined == params.url) {
				alert("请求url未填写");
				return false;
			}
		}
		if(mode == 'del' || mode == 'delete'){
			layer.confirm(params.msg,function(index){
				$.ajax({
					url:params.url,data:params.data,type:'post'
				}).done(function(data){
        			if(data>0) {
        				layer.msg('删除成功', 1, 1,function(){
        					if(params.reloadurl){
        						reloadUrl();
        					}else{
        						$curmenu.trigger('click');
        					}
        				});
        			}
        		}).fail(function(error){
        			layer.msg('操作失败', 2, 8);
        		});
			},params.title);
		}
		if(mode == 'page' || mode == 'detail'){
			var height = $.trim(params.height),width = $.trim(params.width);
			if(height.substr(height.length-1,1) != "%"){
				height = parseInt(height)+35+'';
			}
			var paddingBottom = (mode=='detail')?'0px':'30px';
			var loadi;
			$.ajax({
				url:params.url,
				data:params.data,
				type:'post',
				dataType:'html',
				beforeSend:function(){
					loadi = layer.load(5,0);
				}
			}).done(function(data){
				layer.close(loadi);
				lastIndex = $.layer({
				    type : 1,
				    title : params.title,
				    maxmin: params.maxmin,
				    closeBtn: params.closeBtn,
				    area : [width,height],
				    border:[2, 0.5, '#888'],//params.border,
				    page : {html:data},
				    success:function(layero){
				    	layero.find('.xuboxPageHtml').css({
				    		'overflowY':'auto',
				    		'height':layero.height()-35,
				    		'paddingBottom':paddingBottom
				    		});
				    	layero.find('.xubox_page').css({'width':'100%'});
				    },
				    full:function(layero){
				    	setTimeout(function() {
				    		layero.find('.xuboxPageHtml').css({'overflowY':'auto','height':layero.height()-35});
				        },101);
				    },
				    restore: function(layero){
				    	layero.find('.xuboxPageHtml').css({'overflowY':'auto','height':layero.height()-35});
				    },
				    close: function(index){
				    	layer.closeTips();
				    }
				});
			}).fail(function(err){
				layer.msg('操作失败', 2, 8);
			});
		}
		
	};
	$.cuslayer = cuslayer;
})(jQuery);

$(function(){
	//数组操作
	Array.prototype.indexOf = function(val) {              
	    for (var i = 0; i < this.length; i++) {  
	        if (this[i] == val) return i;  
	    }  
	    return -1;  
	};
	Array.prototype.remove = function(val) {  
	    var index = this.indexOf(val);  
	    if (index > -1) {  
	        this.splice(index, 1);  
	    }  
	};
	
	// 禁用Enter键表单自动提交
	document.onkeydown = function(event) {
		var target, code, tag;
		if (!event) {
			event = window.event; // 针对ie浏览器
			target = event.srcElement;
			code = event.keyCode;
			if (code == 13) {
				tag = target.tagName;
				if (tag == "TEXTAREA") {
					return true;
				} else {
					return false;
				}
			}
		} else {
			target = event.target; // 针对遵循w3c标准的浏览器，如Firefox
			code = event.keyCode;
			if (code == 13) {
				tag = target.tagName;
				if (tag == "INPUT") {
					return false;
				} else {
					return true;
				}
			}
		}
	};
	
	//属性模式
	$(document).on('click','[data-mode]',function(){
		var data = $(this).data();
		if(undefined != data['data'] && typeof data['data'] != "object") {
			data['data'] = eval("("+data.data+")");
		}
		$.cuslayer(data);
	});
	
});


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
	var pageSize = $form.find('input[name="pageSize"]');
	if(pageNoInput.size() == 0){
		$form.append("<input type='hidden'  name = 'pageNum' value='1'/>");
		pageNoInput = $form.find('input[name="pageNum"]');
	}
	if(pageSize.size() == 0){
		$form.append("<input type='hidden'  name = 'pageSize' value='10'/>");
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
