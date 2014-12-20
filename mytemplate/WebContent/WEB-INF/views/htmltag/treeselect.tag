@var class = class!''; //样式
@var width = width!'350'; //弹窗宽度
@var height = height!'350'; //弹窗的高度
@var modelId = modelId!'parentId'; //隐藏要提交的id (实体属性)
@var modelName = modelName!''; //要提交的name (实体属性)
@var modelIdValue = modelIdValue!'0'; //id初始值value 
@var modelNameValue = modelNameValue!''; //name初始值value
@var id = id!'treeselectpid'; //隐藏id名,页面多个时候需要指定
@var nameId = nameId!'treeselectname';  //名称
@var url = url!''; //树数据url,必填,从管理路径之后添起，如menu/tree
@var pIdKey = pIdKey!'parentId'; //父级的model中属性名字
@var selectIds = selectIds!''; //默认选择节点
@var curId = curId!'-1'; //当前节点的id,如要验证不能选择当前节点需要填写,多个时必须填写，详见office-save页面
@var isCheck = isCheck!'';

<div class="width-100 clearfix ${class}">
	<input class="form-control pull-left width-80" type="text" readonly
	id="${nameId}" name="${modelName}" value="${modelNameValue}"
	@if(!isEmpty(isCheck)){
		datatype="*"  nullmsg=${isCheck}
	@}
	>
	<span class="input-group-btn pull-left width-20">
		<button class="btn btn-sm btn-purple width-100" type="button" style="height: 34px;"
		data-mode="page" data-url="${rootPath}/tag/treeselect"
		data-data="{url:'${url}',id:'${id}',nameId:'${nameId}',pIdKey:'${pIdKey}',selectIds:'${selectIds}',curId:'${curId}'}" 
		data-title="请选择" data-width="${width!}" data-height="${height}">
			<i class="ace-icon fa fa-search bigger-110"></i>查找
		</button>
	</span>
	<input type="hidden" name="${modelId}" id="${id}" value="${modelIdValue}"/>
</div>
