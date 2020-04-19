package edu.shu.domain;

import edu.shu.base.ITree;
import edu.shu.base.ITreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


public class TreeDTO implements ITree {
	private HashMap<String, TreeNodeWrapper> treeNodesMap = new LinkedHashMap<String, TreeNodeWrapper>();
    private List<TreeNodeWrapper> treeNodesList = new ArrayList<TreeNodeWrapper>();

    public TreeDTO(List<ITreeNode> list){
        initTreeNodeMap(list);
        initTreeNodeList();
    }

    private void initTreeNodeMap(List<ITreeNode> list){
    	TreeNodeWrapper treeNode = null;
        for(ITreeNode item : list){
            treeNode = new TreeNodeWrapper(item);
            treeNodesMap.put(treeNode.getNodeId(), treeNode);
        }

        Iterator<TreeNodeWrapper> iter = treeNodesMap.values().iterator();
        TreeNodeWrapper parentTreeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getParentId() == null || treeNode.getParentId() == ""){
                continue;
            }

            parentTreeNode = treeNodesMap.get(treeNode.getParentId());
            if(parentTreeNode != null){
                treeNode.setParent(parentTreeNode);
                parentTreeNode.addChild(treeNode);
            }
        }
    }

    private void initTreeNodeList(){
        if(treeNodesList.size() > 0){
            return;
        }
        if(treeNodesMap.size() == 0){
            return;
        }
        Iterator<TreeNodeWrapper> iter = treeNodesMap.values().iterator();
        TreeNodeWrapper treeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getParent() == null){
                this.treeNodesList.add(treeNode);
                this.treeNodesList.addAll(treeNode.getAllChildren());
            }
        }
    }

    @Override
    public List<TreeNodeWrapper> getTree() {
        return this.treeNodesList;
    }

    @Override
    public List<TreeNodeWrapper> getRoot() {
        List<TreeNodeWrapper> rootList = new ArrayList<TreeNodeWrapper>();
        if (this.treeNodesList.size() > 0) {
            for (TreeNodeWrapper node : treeNodesList) {
                if (node.getParent() == null) {
                    rootList.add(node);
                }
            }
        }
        return rootList;
    }

    @Override
    public TreeNodeWrapper getTreeNode(String nodeId) {
        return this.treeNodesMap.get(nodeId);
    }
}
