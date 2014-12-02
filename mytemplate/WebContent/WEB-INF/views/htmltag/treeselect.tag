@var class = class!'';
@var width = width!'350';
@var height = height!'350';
@var modelId = modelId!'parentId'; //隐藏要提交的id
@var modelName = modelName!''; //要提交的name
@var modelIdValue = modelIdValue!'0'; //id初始值
@var modelNameValue = modelNameValue!''; //name初始值
@var id = id!'treeselectpid'; 
@var nameId = nameId!'treeselectname';
@var url = url!''; //树数据url

<div class="input-group ${class}">
	<input type="text" class="form-control search-query" readonly 
	id="${nameId}" name="${modelName}" value="${modelNameValue}">
	<span class="input-group-btn">
		<button type="button" class="btn btn-purple btn-sm"
		data-mode="page" data-url="${ctxPath}/${adminPath}/tag/treeselect"
		data-data="{url:'${url}',id:'${id}',nameId:'${nameId}'}" 
		data-title="请选择" data-width="350" data-height="350"
		>
			<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
		</button>
	</span>
	<input type="hidden" name="${modelId}" id="${id}" value="${modelIdValue}"/>
</div>