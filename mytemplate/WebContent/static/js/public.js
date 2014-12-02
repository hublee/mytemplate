;(function($){
	var cuslayer = function(params){
		var defaults = {
			mode:'normal',
			type:1, //0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			title:false,
			shade: [0.5, '#000'], //[遮罩透明度, 遮罩颜色]
			border:[5, 0.5, '#666'],
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
		console.log(params)
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
		if(mode == 'page'){
			params.height = parseInt(params.height)+35+'';
			$.ajax({
				url:params.url,data:params.data,type:'post'
			}).done(function(data){
				
				var index = $.layer({
				    type : 1,
				    title : params.title,
				    maxmin: params.maxmin,
				    closeBtn: params.closeBtn,
				    area : [params.width,params.height],
				    page : {html:data},
				    success:function(layero){
				    	layero.find('.xuboxPageHtml').css({'overflowY':'auto','height':parseInt(params.height)-35});
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

//属性模式
$(document).on('click','a[data-mode]',function(){
	var data = $(this).data();
	if(undefined != data['data'] && typeof data['data'] != "object") {
		data['data'] = eval("("+data.data+")");
	}
	$.cuslayer(data);
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
