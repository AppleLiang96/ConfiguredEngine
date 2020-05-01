package edu.shu.base;

public interface ITreeNode {
    String getNodeId();

    String getParentId();

    Object getMetaData();

    void setMetaData(Object obj);

    void setNodeId(String nodeId);

    void setParentId(String parentId);
}
