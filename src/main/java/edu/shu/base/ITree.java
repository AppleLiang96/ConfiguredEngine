package edu.shu.base;

import edu.shu.domain.TreeNodeWrapper;

import java.util.List;


public interface ITree {
    List<TreeNodeWrapper> getTree();

    List<TreeNodeWrapper> getRoot();

    TreeNodeWrapper getTreeNode(String nodeId);
}
