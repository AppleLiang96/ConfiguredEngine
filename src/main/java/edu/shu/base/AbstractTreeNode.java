package edu.shu.base;

/**
 * @author liang
 * @create 2020/4/19 10:16 上午
 */
public abstract class AbstractTreeNode implements ITreeNode {
    private String nodeId;
    private String parentId;

    @Override
    public String getNodeId() {
        return nodeId;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
