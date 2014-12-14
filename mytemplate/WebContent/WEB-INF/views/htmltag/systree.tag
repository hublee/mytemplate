@var editUrl=editUrl!; //  示例menu/edit/showlayer
@var delUrl=delUrl!;
@var addUrl=addUrl!;
@var treeData=treeData!;
@var form=form!'search-form';
@var searchBtn=searchBtn!'search-btn';
@var searchInput=searchInput!'search-input';
@var searchAllBtn=searchAllBtn!'search-all-btn';
@var height = height!'520';
@var width = width!'600';

<script type="text/javascript">
	var setting = {
		view:{
			expandSpeed:100,
			selectedMulti : false,
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			fontCss:function(treeId, treeNode) {
				return (!!treeNode.highlight) ? {"font-weight":"bold","color":"red"} : {"font-weight":"normal","color":"#333"};
			}
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: function(treeId,treeNode){
				return treeNode.level == 0 ? false:true;
			},
			showRenameBtn: function(treeId,treeNode){
				return treeNode.level == 0 ? false:true;
			},
			removeTitle : "删除",
			renameTitle : "编辑"
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId"
			}
		},
		callback: {
			onClick: onClickNode,
			beforeRemove:beforeRemove,
			beforeEditName: beforeEditName,
			beforeDrag: function(){return false;}
		}
	};
	
	//编辑菜单
	function beforeEditName(treeId, treeNode) {
		$.cuslayer({
			mode:'page',
			title:(treeNode.name)+'编辑',
			height:"${height}",
			width:"${width}",
			url:"${editUrl}",
			data:{"id":treeNode.id,"parentId":treeNode.getParentNode().id}
		});
		return false;
	}
	
	//删除菜单
	function beforeRemove(treeId, treeNode){
		var id = treeNode.id;
		$.cuslayer({
			mode:'del',
			msg:'你确定删除'+treeNode.name+'节点及其所有的子节点吗?(慎重操作)?',
			title:'删除操作',
			url:'${delUrl}',
			data:{"id":id},
			reloadurl:true
		});
		return false;
	}
	
	//划过显示添加按钮,添加菜单
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='添加' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			$.cuslayer({
				mode:'page',
				title:'添加资源',
				height:"${height}",
				width:"${width}",
				url:'${addUrl}',
				data:{"parentId":treeNode.id}
			});
			return false;
		});
	};
	
	//移除添加按钮
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	//节点点击事件
	function onClickNode(event, treeId, treeNode) {
		$("#${form}").find("input[name=name]").val("");
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		$("#${form}").find("input[name=id]").val(treeNode.id);
		paging("${form}",1); //刷新表单
		for(var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = false;				
			treeObj.updateNode(nodeList[i]);
		}
	};
	var treeObj;
	$(function(){
		//树结构初始化
		$.fn.zTree.init($("#treeMenu"), setting, ${treeData});
		treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		// 默认展开一级节点
		var nodes = treeObj.getNodesByParam("level", 0);
		for(var i=0; i<nodes.length; i++) {
			treeObj.expandNode(nodes[i], true, false, false);
		}
	});
	
	//分页
	$("#${searchAllBtn}").click(function(){
		$("#${form}").find("input[name=id]").val("");
		$("#${form}").find("input[name=name]").val("");
		paging("${form}",1);
		var node = treeObj.getNodeByParam("id", 0);
		treeObj.selectNode(node,false);
	}).trigger("click");
	
	$("#${searchBtn}").click(function(e){
		$("#${form}").find("input[name=id]").val("");
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		treeObj.cancelSelectedNode();
		paging("${form}",1);
		searchNode(e);
	});
	
	var key = $("#${searchInput}"),nodeList = [];
	function searchNode(e) {
		// 取得输入的关键字的值
		var value = $.trim(key.get(0).value);
		
		// 按名字查询
		var keyType = "name";
		if (key.hasClass("empty")) {
			value = "";
		}
		
		// 如果要查空字串，就退出不查了。
		if (value === "") {
			return;
		}
		updateNodes(false);
		nodeList = treeObj.getNodesByParamFuzzy(keyType, value);
		updateNodes(true);
	};
	function updateNodes(highlight) {
		for(var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;				
			treeObj.updateNode(nodeList[i]);
			treeObj.expandNode(nodeList[i].getParentNode(), true, false, false);
		}
	};
	
</script>
