$(document).ready(function(){
	
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
	
	//覆盖弹窗标题的方法，允许标题解析html形式的
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;';
			if (("title_html" in this.options) && this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	
	//html标签自定义方式弹窗
	$(document).on("click","[cus-mode]",function(){
		$this = $(this);
		var mode = $this.attr("cus-mode");
		var title = $this.attr("cus-title"),
			backFillUrl = $this.attr("cus-backfill-url"),
			backfillData = eval('('+$this.attr("cus-backfill-data")+')'),
			width = parseInt($this.attr("cus-width")),
			height = parseInt($this.attr("cus-height")),
			maxHeight = parseInt($this.attr("cus-maxHeight")),
			formId = $this.attr("cus-fromId"),
			delParam = $this.attr("cus-delParam"),
			delParamUrl = $this.attr("cus-delParam-url"),
			delParamData = eval('('+$this.attr("cus-delParam-data")+')'),
			delText = $this.attr("cus-text"),
			modeSuccess = new Function('return '+ $this.attr("cus-mode-success"));
		
		switch (mode) {
		case "add":
		case "edit":
			cuslayer({
				title:title,
				backfill:{
					url:backFillUrl,
					data:backfillData || {}
				},
				mode:mode,
				modeSuccess:modeSuccess,
				fromId:formId,
				width:width || 'auto',
				maxHeight:maxHeight || false
			});
			break;
		case "delete":
			cuslayer({
				mode:mode,
				delText:delText,
				modeSuccess:modeSuccess,
				delParam:{url:delParamUrl,data:delParamData || {}}
			});
			break;
		default:
			break;
		}
	});
	
	/*
	     用法:$.gdialog({})
	     参数:{
			ok:确定按钮事件,
			okText:确定按钮文本,默认"确定"
			okClass:确定按钮class,
			cancel:取消按钮事件
			cancelText:取消按钮文本,默认"关闭窗口"
			cancelClass:取消按钮class,
			isBackfill:是否为ajax回填html模式,默认 true,
			backfill:{url:'请求url',data:{附带参数}},此参数isBackfill:true有效,
			content:内容,此参数isBackfill:false有效,自定义内容,一般用于非回填
			hideBottom:false(是否隐藏底部),
			okHide:false,  隐藏ok按钮
			cancelHide:false 隐藏取消按钮
			以上参数为自定义参数，如要使用jquery dialog的参数请查api，传参即可
		}
	*/
	(function($){
		var gdialog = function(params){
			var defaults = {
				title:params.title || "提示",
				title_html : true, //允许html形式
				modal:true, //遮盖
				resizable: false,  //改变尺寸
				draggable:true,   //拖动
				//dialogClass: "no-close", //隐藏右上角关闭
				width:'auto',
				//closeOnEscape:false,
				close : function() {
					$(this).dialog("destroy");
				},
				buttons: [
			  	    {
			  	    	text: params.okText || "确定",
			  	    	"class": params.okClass || "btn btn-primary btn-minier",
			  	    	click: function() {
			  	    		if(undefined != params.ok && null != params.ok){
			  	    			params.ok.call($(this), this);
			  	    		}
			  	    		$(this).dialog( "destroy" );
			  	    	}
			  	    },
			  	    {
			  	    	text: params.cancelText || "关闭窗口",
			  	    	"class": params.cancelClass || "btn btn-minier",
			  	    	click: function() {
			  	    		if(undefined != params.cancel && null != params.cancel){
			  	    			params.cancel.call($(this), this);
			  	    		}
			  	    		$(this).dialog( "destroy" );
			  	    	}
			  	    }
		  	    ],
		  	    isBackfill:true
			};
			
			params = $.extend(defaults, params);
			
			if(params.okHide){
				params.buttons.splice(0,1);
			}
			if(params.cancelHide){
				params.buttons.splice(1,1);
			}
			
			params.title = "<div class='widget-header no-padding' style='min-height:28px;'>"+
				"<h4 class='smaller center' style='margin:7px 0px;'>"+
				params.title + "</h4></div>";
			
			var g = $("<div class='floatDiv' style='padding:0px;'></div>");
			if(params.isBackfill){
				if(params.backfill == null || params.backfill == undefined){
					alert("此模式为isBackfill:true,请填写backfill必要的参数或者用isBackfill:false");
					return;
				}
				var spinner;
				$.ajax({
					url:params.backfill.url,
					type:"post",
					dataType:"text",
					data:params.backfill.data || {},
					beforeSend:function(){
						spinner = new Spinner({color: '#3d9bce',width:10,radius:10}).spin(g[0]);
					},
					success:function(data){
						g.html(data);
						var wrap = g.closest(".ui-dialog");
						var _scrollHeight = $(document).scrollTop();
						var _windowHeight = $(window).height();
						var _windowWidth = $(window).width();
						var oheight = wrap.outerHeight();
						var owidth = wrap.outerWidth();
						var _posiTop = (_windowHeight - oheight)/2 + _scrollHeight;
						var _posiLeft = (_windowWidth - owidth)/2;
						if(oheight != null && oheight != undefined 
								&& owidth !=null && owidth != undefined){
							wrap.css({top:_posiTop+'px',left:_posiLeft+'px'});
						}
						if (spinner) {spinner.stop();}
						if(params.hideBottom == true){$(".ui-dialog-buttonpane").hide();}
					}
				});
			}else{
				g.html(params.content);
			}
			
			return g.dialog(params);
		};
		$.gdialog = gdialog;
	})(jQuery);
	
});

/**
 * mode {delete,add,update}
 * delete模式
 * {
 * 	mode:"delete",
	delParam:{url:"删除请求路径",data:{参数}}
 * }
 * add,update模式
 * {
 * 	backfill:{
		url:"",
		data:{"mode":1}
	},
	mode:"add",
	fromId:"#menu-add",
	width:"500"
 * }
 * 如用到弹窗的参数直接按照上面的参数直接传参即可
 */
function cuslayer(params){
	var mode = params.mode, //模式
		fromId = params.formId,  //请求表单的id
		beforeFn = params.beforeFn, //请求之前回调
		delText = params.delText || "确定要删除此数据吗?",
		modeSuccess = params.modeSuccess;
	if(mode != null && mode != undefined){
		var prompt = (params.mode=="add")?"添加成功":"编辑成功";
		switch (mode) {
			case "delete":
				$.gdialog({
					isBackfill:false,
					cancelText:"取消删除",
					okText:"确认删除",
					content:"<div style='text-align: center;line-height: 69px;'>"+
					"<span class='center label label-xlg label-danger bigger-120'>"+delText+"</span></div>",
					ok:function(){
						$.ajax({
							url:params.delParam['url'],
							type:"post",
							dataType:"text",
							data:params.delParam['data'] || {},
					 		success:function(data){
								if(parseInt(data) >= 1){
									if(undefined != modeSuccess && null != modeSuccess){
										modeSuccess.call($(this), this);
									}else{
										$curmenu.trigger('click');
									}
								}else{
									alert("操作失败");
								}
							}
						});
					}
				});
			return;
			
			case "add":
			case "edit":
				/*if(beforeFn != null && beforeFn != undefined){
					params.beforeFn.call($(this),this);
				}*/
				params['ok'] = function(){
					$(params.fromId).ajaxForms({
		        		isPage:false,
		    			success:function(data){
		    				if(parseInt(data) >= 1 || null!=data){
		    					$.gdialog({
		    						isBackfill:false,
		    						cancelHide:true,
		    						width:200,
		    						content:"<div style='text-align: center;line-height: 69px;'>"+
		    						"<span class='center label label-xlg label-success " +
		    						"arrowed arrowed-right'>"+prompt+"</span></div>",
		    						ok:function(){
		    							if(undefined != modeSuccess && null != modeSuccess){
		    								modeSuccess.call($(this), this);
										}else{
											$curmenu.trigger('click');
										}
		    						}
		    					});
		    					return;
		    				}
		    			}
					});
				}; 
				break;
				
			default:
				break;
		}
	};
	$.gdialog(params);
}

//得到url的参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

//刷新url
function reloadUrl(){
	window.location.href = (window.location.href).split("?")[0]+"?menuid="+$curmenu.attr("id");
}
