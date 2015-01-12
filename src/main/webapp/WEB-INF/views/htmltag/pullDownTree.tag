@ //提交的input id:  pullDownTreeCurId${order} id , pullDownTreeCurName${order} name 
@ //树对象 pullDownTreeCurTree${order}  pullDownTreeCurPid${order}
@var treeData = treeData!"[]";
@var order = order!""; //序号
@var name = name!"";
@var value = value!"0";
@var pName = pName!"";
@var cName = cName!"";
@var cId = cId!"";
@var pId = pId!"";
@var width = width!"";
@var class = class!;
@var rootNodeName = rootNodeName!"全部";

<div class="btn-group ${class!}" style="width:${width};">
	<span data-toggle="dropdown" class="btn btn-primary btn-white dropdown-toggle width-100">
		<span id="pullDownTreeCurName${order}">全部</span> <i class="ace-icon fa fa-angle-down icon-on-right"></i>
	</span>

	<div class="dropdown-menu dropdown-caret" >
		<div class="padding-15">
			<div style="padding-bottom: 10px;">
				<input type="text" id="pullDownTreeSearch${order}" placeholder="搜索" class="width-100"/>
			</div>
			<ul class="ztree" id="pullDownTree${order}"></ul>
		</div>
	</div>
</div>
<input type="hidden" name="${name}" value="${value}" id="pullDownTreeCurId${order}"/>
<input type="hidden" name="${pName}" value="${pId}" id="pullDownTreeCurPid${order}"/>
<input type="hidden" name="${cName}" value="${cId}" id="pullDownTreeCurCid${order}"/>

<script>

$(function() { 
	$("div.dropdown-menu").on("click", ".ztree .switch,#pullDownTreeSearch${order}", function(e) {e.stopPropagation(); }); 
	
	pullDownTreeSetting${order} = {
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
			onClick: selectClick
		}
	};
	
	var treeData${order} = ${treeData!"[]"};
	var root = {"id":0,"name":"${rootNodeName}","open":true};
	treeData${order}[treeData${order}.length] = root;
	pullDownTreeCurTree${order} = $.fn.zTree.init($("#pullDownTree${order}"), pullDownTreeSetting${order}, treeData${order});
	
	var nodes = pullDownTreeCurTree${order}.getNodesByParam("level", 0);
	for(var i=0; i<nodes.length; i++) {
		pullDownTreeCurTree${order}.expandNode(nodes[i], true, false, false);
	}
	
	function selectClick(e, treeId, treeNode){
		$("#pullDownTreeCurId${order}").val(treeNode.id);
		$("#pullDownTreeCurName${order}").text(treeNode.name);
		${tagBody!}
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
			return;
		}
		updateNodes(false);
		pullDownTreeList${order} = pullDownTreeCurTree${order}.getNodesByParamFuzzy(keyType, value);
		updateNodes(true);
	};
	function updateNodes(highlight) {
		for(var i=0, l=pullDownTreeList${order}.length; i<l; i++) {
			pullDownTreeList${order}[i].highlight = highlight;				
			pullDownTreeCurTree${order}.updateNode(pullDownTreeList${order}[i]);
			pullDownTreeCurTree${order}.expandNode(pullDownTreeList${order}[i].getParentNode(), true, false, false);
		}
	};
	
}); 
</script>