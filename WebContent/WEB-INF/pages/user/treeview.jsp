<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
var data = [{
    "id": 0,
    "text": "名称-0",
    "sequence": "sequence-0",
    "state": null,
    "icon": "glyphicon glyphicon-leaf",
    "nodes": [{
        "id": 1,
        "text": "名称-0-1",
        "sequence": "sequence-0-1",
        "state": null,
        "icon": "glyphicon glyphicon-leaf"
    }, {
        "id": 2,
        "text": "名称-0-2",
        "sequence": "sequence-0-2",
        "state": null,
        "icon": "glyphicon glyphicon-leaf",
        "nodes": [{
            "id": 1,
            "text": "名称-0-2-1",
            "sequence": "sequence-0-2-1",
            "state": {
            	checked:true,
            	selected:true,
            },
            "icon": "glyphicon glyphicon-leaf"
        }]
    }]
}];

var nodeCheckedSilent = false;

        function nodeChecked(event, node) {
            if (nodeCheckedSilent) {
                return;
            }
            nodeCheckedSilent = true;
            checkAllParent(node);
            checkAllSon(node);
            nodeCheckedSilent = false;
        }

        var nodeUncheckedSilent = false;

        function nodeUnchecked(event, node) {
            if (nodeUncheckedSilent)
                return;
            nodeUncheckedSilent = true;
            uncheckAllParent(node);
            uncheckAllSon(node);
            nodeUncheckedSilent = false;
        }

        //选中全部父节点  
        function checkAllParent(node) {
            $('#roles').treeview('checkNode', node.nodeId, {
                silent: true
            });
            var parentNode = $('#roles').treeview('getParent', node.nodeId);
            if (!("nodeId" in parentNode)) {
                return;
            } else {
                checkAllParent(parentNode);
            }
        }
        //取消全部父节点  
        function uncheckAllParent(node) {
            $('#roles').treeview('uncheckNode', node.nodeId, {
                silent: true
            });
            var siblings = $('#roles').treeview('getSiblings', node.nodeId);
            var parentNode = $('#roles').treeview('getParent', node.nodeId);
            if (!("nodeId" in parentNode)) {
                return;
            }
            var isAllUnchecked = true; //是否全部没选中  
            for (var i in siblings) {
                if (siblings[i].state.checked) {
                    isAllUnchecked = false;
                    break;
                }
            }
            if (isAllUnchecked) {
                uncheckAllParent(parentNode);
            }

        }

        //级联选中所有子节点  
        function checkAllSon(node) {
            $('#roles').treeview('checkNode', node.nodeId, {
                silent: true
            });
            if (node.nodes != null && node.nodes.length > 0) {
                for (var i in node.nodes) {
                    checkAllSon(node.nodes[i]);
                }
            }
        }
        //级联取消所有子节点  
        function uncheckAllSon(node) {
            $('#roles').treeview('uncheckNode', node.nodeId, {
                silent: true
            });
            if (node.nodes != null && node.nodes.length > 0) {
                for (var i in node.nodes) {
                    uncheckAllSon(node.nodes[i]);
                }
            }
        }



	$("#roles").treeview({
		data:data,
		highlightSelected: true,// 选中项不高亮，避免和上述制定的颜色变化规则冲突
		multiSelect: false,// 不允许多选，因为我们要通过check框来控制
		showCheckbox: true,// 展示checkbox
		highlightSearchResults: true, // 高亮查询结果
		levels: 5, // 展开级别 Default: 2
		searchResultBackColor: '#CCC', // 查找背景
		//showTags: true,
		showIcon: false,
		onNodeChecked: nodeChecked,
    onNodeUnchecked: nodeUnchecked
		
	});

</body>
</html>