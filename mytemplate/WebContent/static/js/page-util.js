
/*function paging(formId,pageNo){
	var $form = $("#"+formId);
	var pageNoInput = $form.find('input[name="pageNum"]');
	if(pageNoInput.size() == 0){
		$form.append("<input type='hidden'  name = 'pageNum' value='1'/>");
		pageNoInput = $form.find('input[name="pageNum"]');
	}
	pageNoInput.val(pageNo);
	$form.ajaxSubmit($form.data("ajaxOptions"));
	return false;
};*/


(function($){
	
	$.fn.ajaxForms = function(settings){
		return this.each(function(){
			this.opt = $.extend({}, $.fn.ajaxForms.defaults, settings);
			
			var $this = $(this),self = this;
			
			if(self.opt.btnId === undefined ){
				if($("#"+$this.attr("id")+"-btn").size() == 0){
					$this.after("<button style='display:none;' id='"+$this.attr("id")+"-btn'></button>");
				}
				self.opt.btnId = "#"+$this.attr("id")+"-btn";
			}
			if(self.opt.beforeSubmit === undefined){
				self.opt.beforeSubmit = function(formData, jqForm, options){
					if(self.opt.spinner === undefined){
						self.opt.spinner = new Spinner({color: '#3d9bce',width:20,radius:20}).spin($(self.opt.target)[0]);
					}
					var queryString = $.param(formData);
					return true;
				};
			}
			if(self.opt.success === undefined){
				self.opt.success = function(data, statusText, xhr, $form){
					$(self.opt.target).html(data);
					if (self.opt.spinner) {
						(self.opt.spinner).stop();
					}
				};
			}
			
			if( undefined == self.opt.url){
				self.opt.url = $this.attr("action");
			}
			
			$this.data("ajaxOptions",self.opt); //设置ajaxOptions
			//阻止默认的表单提交
			$(document).bind("submit",function(){return false;});
			
			$(document).off("click",self.opt.btnId).on("click",self.opt.btnId,function(){
				if(self.opt.btnClickFn != undefined){
					var flag = self.opt.btnClickFn();
					if(flag == false) return false; 
				}
				if(self.opt.isPage){
					paging($this.attr("id"),1);
				}else{
					$this.ajaxSubmit($this.data("ajaxOptions"));
				}
				return false;
			});
			
			if(self.opt.trigger && self.opt.isPage){
				$(self.opt.btnId).trigger("click");
			}
		});
	};
	
	$.fn.formReload = function(){
		paging($(this).attr("id"),1);
	};
	
	$.fn.ajaxForms.defaults={
		target:null,  //要填充到的元素(必须元素),格式:"#target"
		dataType:"html",  //预定义返回的类型
		type:"post", // 重新设置 form 表单的 method 属性值：'get' or 'post'
		url:undefined, // 重新指定表单的提交地址
		clearForm:false,  //提交成功后清空表单域
		resetForm:false,  //提交成功后重置表单域
		beforeSubmit:$.noop(),  //ajax提交表单前的操作
		success:$.noop(), //成功后的回调,不设置默认回填html 参数 (data, statusText, xhr, $form)
		btnId:undefined,  //触发提交表单的按钮,如不设置默认添加一个btn提交,格式: "#btn"
		trigger:true, //是否立即触发提交，默认是
		spinner:undefined, //loading样式
		btnClickFn:$.noop(),  //按钮提交切入自定义函数
		isPage:true,  //默认是分页模式
	};
	
})(jQuery);



;(function($){
	$.fn.test = function(settings){
		return this.each(function(){
			
			this.opt = $.extend({}, $.fn.test.defaults, settings);
			var $this = $(this),isPage = this.opt.page;
			
			if(isPage){
				this.opt.callback = function(data){
					console.log(data.status);
					$(this.target).html(data);
				}
			}
			
			var valid = $this.Validform(this.opt); //绑定表单
			
			if(isPage){ //分页模式
				paging($this.attr("id"),1);
			}
			
		});
	}
	
	$.fn.test.defaults={
		page:true, //分页模式
		target:undefined, //回填的目标id(#target)
		btnSubmit:"#btn_sub", //指定当前表单下的哪一个按钮触发表单提交事件，如果表单下有submit按钮时可以省略该参数
		btnReset:".btn_reset",//".btn_reset"是该表单下要绑定点击重置表单事件的按钮;
		tiptype:1, 
		ignoreHidden:false, //默认为false，当为true时对:hidden的表单元素将不做验证;
		dragonfly:false, //默认false，当为true时，值为空时不做验证；
		tipSweep:true, //默认为false，在各种tiptype下， 为true时提示信息将只会在表单提交时触发显示，各表单元素blur时不会触发信息提示；
		label:".label", //在没有绑定nullmsg时查找要显示的提示文字，默认查找".Validform_label"下的文字；
		showAllError:false, //提交表单时所有错误提示信息都会显示；false：一碰到验证不通过的对象就会停止检测后面的元素，只显示该元素的错误信息；
		postonce:true, //指定是否开启二次提交防御，true开启，不指定则默认关闭；为true时，在数据成功提交后，表单将不能再继续提交。
		ajaxPost:true,//使用ajax方式提交表单数据，将会把数据POST到config方法或表单action属性里设定的地址；
		datatype:{
			"*6-20": /^[^\s]{6,20}$/,
			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/,
			"username":function(gets,obj,curform,regxp){
				//参数gets是获取到的表单元素值，obj为当前表单元素，
				//curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
				var reg1=/^[\w\.]{4,16}$/,
					reg2=/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,8}$/;
	 
				if(reg1.test(gets)){return true;}
				if(reg2.test(gets)){return true;}
				return false;
	 
				//注意return可以返回true 或 false 或 字符串文字，
				//true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
			},
			"phone":function(){
				// 5.0 版本之后，要实现二选一的验证效果，datatype 的名称 不 需要以 "option_" 开头;	
			}
		},
		beforeCheck:function(curform){
			//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话将不会继续执行验证操作;	
		},
		beforeSubmit:function(curform){
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
		},
		callback:function(data){
			//返回数据data是json对象，{"info":"demo info","status":"y"}
			//info: 输出提示信息;
			//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败;
			//你也可以在ajax_post.php文件返回更多信息在这里获取，进行相应操作；
			//ajax遇到服务端错误时也会执行回调，这时的data是{ status:**, statusText:**, readyState:**, responseText:** }；
	 
			//这里执行回调操作;
			//注意：如果不是ajax方式提交表单，传入callback，这时data参数是当前表单对象，回调函数会在表单验证全部通过后执行，然后判断是否提交表单，如果callback里明确return false，则表单不会提交，如果return true或没有return，则会提交表单。
		}
	};
	
})(jQuery);
