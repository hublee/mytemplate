@var class = class!'';
@var name = name!'icon';
@var value = value!'';
<div class="width-100 clearfix ${class!}">
	<input type="hidden" value="${value}" name="${name}" id="${name}-input"/>
	<i class="ace-icon ${value} bigger-200 pink" style="vertical-align: middle;padding-right: 10px;"></i>
	<button class="btn btn-sm btn-purple" type="button" id="${name}-icon-btn">
		<i class="ace-icon fa fa-search bigger-110"></i>查找
	</button>
</div>
<script>
$(function(){
	$("#${name}-icon-btn").click(function(){
		$.layer({
		    type: 2,
		    title: '请单击选择图标',
		    maxmin: true,
		    iframe: {src : rootPath+'/tag/fontawesome?id=${name}-input'},
		    area: ['1000px' , '550px']
		}); 
	});
});
</script>
