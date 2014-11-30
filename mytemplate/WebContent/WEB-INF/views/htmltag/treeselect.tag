@var id = id!'treeselectId'; //触发弹窗的id
@var inputId = inputId; //显示
@var modelIdName = modelIdName!;  //提交的id属性名
@var checked = checked!'false'; //是否显示checkbox
@var treePid = treePid!'parent_id'; //父id属性名称
@var treeId = treeId!'id'; //id属性名称
@var name = name!'name'; //节点名称的属性名称
@var treeurl = treeurl!; //请求返回树结构的url
@var title = title!; //弹窗标题
@var height = height!'400'; //弹窗高度
@var width = width!'300'; //弹窗宽度
<div class="input-append">
	<input type="hidden" name="${modelIdName}" value="0" />
</div>

<script type="text/javascript">
	$("#${id}").click(function(){
		cuslayer({
			title:"${title}",
			height:"${height}",
			width:"${width}",
			backfill:{
				url:"${ctxPath}/${adminPath}/tag/treeselect",
				data:{
					"url":"${treeurl}",
					"checked":"${checked}",
					"treeId":"${treeId}",
					"treePid":"${treePid}"
				}
			},
			ok:function(){
				node = tree.getSelectedNodes()[0];
				if(undefined == node || null == node){
					$.gritter.add({
						text: '<h1 class="center">请选择区域</h1>',
						class_name: 'gritter-error gritter-center',
						fade:'fast',
						time:'3000',
						before_open: function(){
							if($('.gritter-item-wrapper').length >= 1) return false;
						},
					});
					return false;
				}
				$("#${inputId}").val(node["${name}"]);
				$("input[name='${modelIdName}']").val(node["${treeId}"]);
			}
		});
	});
</script>