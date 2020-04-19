package edu.shu.domain;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import edu.shu.base.ITreeNode;

/**
 * @author liang
 * @create 2020/4/16 8:48 下午
 * treeNode包装类
 */
public class TreeNodeWrapper implements ITreeNode {

    @JSONField(ordinal = 1)
    private String nodeId;

    @JSONField(ordinal = 3)
    private String parentId;

    private TreeNodeWrapper parent;

    //真正的记录信息
    private ITreeNode metaData;

    @JSONField(ordinal = 6)
    private List<TreeNodeWrapper> children = new ArrayList<TreeNodeWrapper>();

    private List<TreeNodeWrapper> allChildren = new ArrayList<TreeNodeWrapper>();

    public TreeNodeWrapper() {

    }

    public TreeNodeWrapper(ITreeNode obj) {
        this.nodeId = obj.getNodeId();
        this.parentId = obj.getParentId();
        this.metaData = obj;
    }

    public void addChild(TreeNodeWrapper treeNode) {
        this.children.add(treeNode);
    }

    public void removeChild(TreeNodeWrapper treeNode) {
        this.children.remove(treeNode);
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }

    @Override
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public TreeNodeWrapper getParent() {
        return parent;
    }

    public void setParent(TreeNodeWrapper parent) {
        this.parent = parent;
    }

    public List<TreeNodeWrapper> getChildren() {
        if (children == null || children.isEmpty()) {
            return null;
        }
        return children;
    }

    public void setChildren(List<TreeNodeWrapper> children) {
        this.children = children;
    }

    public ITreeNode getMetaData() {
        return metaData;
    }

    public void setMetaData(ITreeNode metaData) {
        this.metaData = metaData;
    }

    public List<TreeNodeWrapper> getAllChildren() {
        if (this.allChildren.isEmpty()) {
            for (TreeNodeWrapper treeNode : this.children) {
                this.allChildren.add(treeNode);
                this.allChildren.addAll(treeNode.getAllChildren());
            }
        }
        return this.allChildren;
    }
}
