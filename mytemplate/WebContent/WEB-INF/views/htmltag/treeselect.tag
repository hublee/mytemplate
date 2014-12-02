@var class = class!''; //样式
@var width = width!'350'; //弹窗宽度
@var height = height!'350'; //弹窗的高度
@var modelId = modelId!'parentId'; //隐藏要提交的id
@var modelName = modelName!''; //要提交的name
@var modelIdValue = modelIdValue!'0'; //id初始值value
@var modelNameValue = modelNameValue!''; //name初始值value
@var id = id!'treeselectpid'; //隐藏id名,页面多个时候需要指定
@var nameId = nameId!'treeselectname';  //名称
@var url = url!''; //树数据url,必填,从管理路径之后添起，如menu/tree
@var pIdKey = pIdKey!'parent_id'; //父级的model中属性名字

<div class="width-100 clearfix ${class}">
	<input class="form-control pull-left width-80" type="text" readonly
	id="${nameId}" name="${modelName}" value="${modelNameValue}">
	<span class="input-group-btn pull-left width-20">
		<button class="btn btn-sm btn-purple width-100" type="button" style="height: 34px;"
		data-mode="page" data-url="${ctxPath}/${adminPath}/tag/treeselect"
		data-data="{url:'${url}',id:'${id}',nameId:'${nameId}',pIdKey:'${pIdKey}'}" 
		data-title="请选择" data-width="350" data-height="350">
			<i class="ace-icon fa fa-search bigger-110"></i>查找
		</button>
	</span>
	<input type="hidden" name="${modelId}" id="${id}" value="${modelIdValue}"/>
</div>
