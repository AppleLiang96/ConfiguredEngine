package edu.shu.utils;

import edu.shu.base.AbstractTreeNode;
import edu.shu.base.ITreeNode;
import edu.shu.domain.TreeDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author liang
 * @create 2020/4/19 10:33 上午
 */

public class TreeUtils {

    private static final Logger log = LoggerFactory.getLogger(TreeUtils.class);

    public static List<ITreeNode> nodeList = new ArrayList<>();

    /**
     * @param rootList
     * @return
     */
    public static List<TreeDTO> createListTree(List<ITreeNode> rootList) {
        List<TreeDTO> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(rootList)) {
            rootList.forEach(o -> {
                result.add(createTree(o));
                nodeList.clear();
            });
        }
        return result;
    }


    /**
     * 通过一个DTO的根节点，产生一颗树
     *
     * @param root 根节点
     * @return
     */
    public static TreeDTO createTree(ITreeNode root) {
        if (root == null) {
            return new TreeDTO(Collections.emptyList());
        }
        setNodeIdAndParentId(root, "");
        return new TreeDTO(nodeList);
    }

    /**
     * 递归给DTO设置nodeId pid
     *
     * @param node
     * @param parentId
     */
    private static void setNodeIdAndParentId(ITreeNode node, String parentId) {
        String nodeId;
        //设置id
        if (StringUtils.isEmpty(node.getNodeId())) {
            nodeId = UUID.randomUUID().toString();
            node.setNodeId(nodeId);
        } else {
            nodeId = node.getNodeId();
        }
        //设置pid
        if (StringUtils.isEmpty(node.getParentId()) && StringUtils.isNotEmpty(parentId)) {
            node.setParentId(parentId);
        }
        nodeList.add(node);

        List<Field> fields = new ArrayList<>(Arrays.asList(node.getClass().getDeclaredFields()));

        if (CollectionUtils.isNotEmpty(fields)) {
            //获得直接子类
            fields.stream().filter(o -> ITreeNode.class.isAssignableFrom(ReflectUtils.getValueByField(o, node).orElse(new Object()).getClass()))
                    .forEach(o -> {
                        try {
                            setNodeIdAndParentId((ITreeNode) ReflectUtils.getValueByField(o, node).orElseThrow(IllegalAccessException::new), nodeId);
                        } catch (Exception e) {
                            log.info("[TreeUtils]::[setNodeIdAndParentId]::node = [{}], o = [{}], e:{}", node, o, e);
                        }
                    });

            //获得List子类
            fields.stream().filter(o -> List.class.isAssignableFrom(ReflectUtils.getValueByField(o, node).orElse(new Object()).getClass()))
                    .forEach(o -> {
                        if (ITreeNode.class.isAssignableFrom(ReflectUtils.getListGenericType(o).orElse(Object.class))) {
                            List<AbstractTreeNode> list = null;
                            try {
                                list = (List<AbstractTreeNode>) ReflectUtils.getValueByField(o, node).orElseThrow(IllegalAccessException::new);
                            } catch (Exception e) {
                                log.info("[TreeUtils]::[setNodeIdAndParentId]::node = [{}], o = [{}], e:{}", node, o, e);
                            }
                            list.forEach(p -> setNodeIdAndParentId(p, nodeId));
                        }
                    });
        }

    }



}