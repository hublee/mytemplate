@var id = id!'treeselectId'; //点击按钮的id
@var modelId = modelId!;  //提交的id属性名
@var modelName = modelName!; //提交名称
@var treeurl = treeurl!; //请求返回树结构的url
<div class="input-append">
	<input type="hidden" name="${modelId}"  />
	<input type="hidden" name="${modelName}" />
</div>

<script type="text/javascript">
	$("#${id}").click(function(){
		cuslayer({
			title:"测试",
			backfill:{
				url:"${ctxPath}/${adminPath}/tag/treeselect",
				data:{
					"url":"${treeurl}"
				}
			}
		});
	});
</script>