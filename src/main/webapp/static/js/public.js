// 声明一个全局对象Namespace，用来注册命名空间
var Namespace = new Object();
// 全局对象仅仅存在register函数，参数为名称空间全路径，如"Grandsoft.GEA"
Namespace.register = function(fullNS){
    // 将命名空间切成N部分, 比如Grandsoft、GEA等
    var nsArray = fullNS.split('.');
    var sEval = "";
    var sNS = "";
    for (var i = 0; i < nsArray.length; i++)
    {
        if (i != 0) sNS += ".";
        sNS += nsArray[i];
        // 依次创建构造命名空间对象（假如不存在的话）的语句
        // 比如先创建Grandsoft，然后创建Grandsoft.GEA，依次下去
        sEval += "if (typeof(" + sNS + ") == 'undefined') " + sNS + " = new Object();"
    }
    if (sEval != "") eval(sEval);
};
Namespace.register("Angel");


var $curmenu,lastIndex;//最后弹窗索引
var webHistory = Webit.history;
$(function(){
	
	var aMenu = $("#sidebar-menu a[id]");
	aMenu.on("click",function(){
		var hash = webHistory.get(),href = $(this).attr("href");
		if( ("#"+hash) == href ){
			webHistory.justShow("#");
			webHistory.go(hash);
		}
		changeMenu($(this));
	});
	
	var $main_content = $("#fill-main-content");
	webHistory.add("ajax", function(str, action, token) {
	   $main_content.html(loadHtmlPage(str));
	   var curMenu = $("#sidebar-menu li").find("a[href='#"+token+"']");
	   changeMenu(curMenu);
	});
	webHistory.init();
	
	
	
});

function loadHtmlPage(path) {
    path = adminPath + "/" + path;
    var result;
    $.ajax({
        url: path,
        dataType: "text",
        async: false,
        success: function(data) {
            result = data;
        }
    });
    return result;
};

function changeMenu(obj){
	$this = $curmenu = obj,pli = $this.parents("li");
	var $sibling = $this.closest("li[data-level='1']").siblings("li.open");
	if($sibling.size()>0){
		$sibling.removeClass("open").find("li.open").removeClass("open");
		$sibling.find("ul.nav-show").attr("class","submenu nav-hide").hide();
	}
	if($this.attr('haschild') == "false"){
		$this.closest("li[data-level='1']").addClass("open");
		var pul = $this.parents("ul.submenu");
		pul.attr("class","submenu nav-show").show();
		$("#sidebar-menu").find("li").removeClass("active");
		pli.addClass("active");
		var mtext = pli.children("a").find("span.menu-text");
		$("#breadcrumb").empty();
		mtext.each(function(i){
			var last = '';
			if(i==mtext.length-1) {
				last = 'blue';
				$(".page-header h1").text($(this).text());
			}
			$("#breadcrumb").append("<li class='active "+last+"'>"+$(this).text()+"</li>");
		})
	}
}

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
			var loadi;
			layer.confirm(params.msg,function(index){
				$.ajax({
					url:params.url,
					data:params.data,
					type:'post',
					beforeSend:function(){
						loadi = layer.load(5,0);
					}
				}).done(function(data){
					layer.close(loadi);
        			if(data>0) {
        				layer.msg('<span class="red bigger-120">删除成功</span>', 1, 1,function(){
        					if(params.reloadurl){
        						location.reload();
        					}else{
        						$curmenu.trigger('click');
        					}
        				});
        			}else if(data<0){
        				layer.alert('<span class="red bigger-120">删除失败，数据正在被使用！</span>', 8, !1);
        			}
        		}).fail(function(error){
        			layer.msg('<span class="red bigger-120">删除失败</span>', 2, 8);
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
				    border:[4, 0.5, '#888'],
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

//条件查询分页
;(function($){
	$.fn.getPageList = function(settings){
		return this.each(function(){
			var $this = $(this);
			this.opt = $.extend({},$.fn.getPageList.defaults,settings);
			$("#"+this.opt.submitBtnId).on('click',function(){
				paging($this.attr("id"),1);
				return false;
			});
			if(this.opt.trigger) $("#"+this.opt.submitBtnId).trigger('click');
		});
	}
	
	$.fn.getPageList.defaults = {
		submitBtnId:"", //提交按钮
		trigger:true 
	}
})(jQuery);

//提示tip
var tip={
	errorTip:function(msg,obj,style){
		style = style == undefined?['background-color:#F26C4F; color:#fff','#F26C4F' ]:style;
		layer.tips(msg, obj, {
			guide:0,
			time: 4,
			style : style
		});
	}
};

Angel.downFile = function(formid,action){
   var curForm=$("#"+formid);
   var queryParams=curForm.serialize();
   queryParams = decodeURIComponent(queryParams,true);//将中文解码进行还原
   var $tempForm=$("<form style='display:none;'></form>");
   var paramArr=queryParams.split("&");
   for(var i=0;i<paramArr.length;i++){
	   var paramValue=paramArr[i].split("=");
	   var paramName=paramValue[0];
	   var paramValue=paramValue[1];
	   var $input=$("<input name='"+paramName+"' value='"+paramValue+"'/>");
	   $tempForm.append($input);
   }
   $("body").append($tempForm);
   $tempForm.attr("action",action);
   $tempForm.attr("method","post");
   $tempForm.submit();
   $tempForm.remove();
};

// Find the right method, call on correct element
function launchFullscreen(element) {

    if (!$("body").hasClass("full-screen")) {

        $("body").addClass("full-screen");

        if (element.requestFullscreen) {
            element.requestFullscreen();
        } else if (element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if (element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
        } else if (element.msRequestFullscreen) {
            element.msRequestFullscreen();
        }

    } else {

        $("body").removeClass("full-screen");

        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }

    }

}
