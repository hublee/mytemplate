@var treeData = treeData!"[]";
@var order = order!"";
@var name = name!"";
@var value = value!"";

<div class="btn-group">
	<span data-toggle="dropdown" class="btn btn-primary btn-white dropdown-toggle">
		<span id="pullDownTreeCurName${order}">全部</span> <i class="ace-icon fa fa-angle-down icon-on-right"></i>
	</span>

	<div class="dropdown-menu dropdown-caret">
		<div class="padding-10">
			<div style="width:300px;padding-bottom: 10px;">
				<span class="">搜索：</span>
				<input type="text" id="pullDownTreeSearch${order}" style="width:250px;" />
			</div>
			<ul class="ztree" id="pullDownTree${order}"></ul>
		</div>
	</div>
</div>
<input type="hidden" name="${name}" value="${value}" id="pullDownTreeCurId${order}"/>

<script>
$(function() { 
	$("div.dropdown-menu").on("click", ".ztree .switch,#pullDownTreeSearch${order}", function(e) {e.stopPropagation(); }); 
	
	var pullDownTree${order} = {
		view:{
			expandSpeed:100,
			selectedMulti : false,
			fontCss:function(treeId, treeNode) {
				return (!!treeNode.highlight) ? {"font-weight":"bold","color":"red"} : {"font-weight":"normal","color":"#333"};
			}
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId"
			},
			key:{name:"name"}
		},
		callback:{
			onClick: selectClick,
			beforeClick:beforeClick
		}
	};
	
	
	var root = {id:0,name:"全部",open:true};
	${treeData}[${treeData}.length] = root;
	var tree${order} = $.fn.zTree.init($("#pullDownTree${order}"), pullDownTree${order}, ${treeData});
	var nodes = tree${order}.getNodesByParam("level", 0);
	for(var i=0; i<nodes.length; i++) {
		tree${order}.expandNode(nodes[i], true, false, false);
	}
	
	function beforeClick(treeId, treeNode){
	}
	function selectClick(e, treeId, treeNode){
		$("#pullDownTreeCurId${order}").val(treeNode.id);
		$("#pullDownTreeCurName${order}").text(treeNode.name);
	}
	
	var pullDownTreeList${order} = [];
	$("#pullDownTreeSearch${order}").bind("change keydown cut input propertychange", searchNode);
	
	function searchNode() {
		// 取得输入的关键字的值
		var value = $.trim($("#pullDownTreeSearch${order}").get(0).value);
		
		// 按名字查询
		var keyType = "name";
		
		// 如果要查空字串，就退出不查了。
		if (value === "") {
			updateNodes(false);
			return;
		}
		updateNodes(false);
		pullDownTreeList${order} = tree${order}.getNodesByParamFuzzy(keyType, value);
		updateNodes(true);
	};
	function updateNodes(highlight) {
		for(var i=0, l=pullDownTreeList${order}.length; i<l; i++) {
			pullDownTreeList${order}[i].highlight = highlight;				
			tree${order}.updateNode(pullDownTreeList${order}[i]);
			tree${order}.expandNode(pullDownTreeList${order}[i].getParentNode(), true, false, false);
		}
	};
	
}); 
</script>