package edu.shu.utils;

import edu.shu.base.ITreeNode;
import edu.shu.domain.TreeDTO;
import edu.shu.domain.TreeNodeWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BooleanSupplier;

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
    public static List<TreeDTO> createListTree(List<Object> rootList) {
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
    public static TreeDTO createTree(Object root) {
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
    private static void setNodeIdAndParentId(Object node, String parentId) {
        if (node == null) {
            return;
        }

        TreeNodeWrapper treeNodeWrapper = wrapperNode(node, parentId);

        String nodeId = treeNodeWrapper.getNodeId();

        List<Field> fields = new ArrayList<>(Arrays.asList(node.getClass().getDeclaredFields()));

        if (CollectionUtils.isNotEmpty(fields)) {
            fields.stream()
                    .filter(o -> isDirectTransformClass(node, o))
                    .forEach(o -> {
                        try {
                            setNodeIdAndParentId(ReflectUtils.getValueByField(o, node).orElseThrow(IllegalAccessException::new), nodeId);
                        } catch (Exception e) {
                            log.info("[TreeUtils]::[setNodeIdAndParentId]::node = [{}], o = [{}], e:{}", node, o, e);
                        }
                    });

            fields.stream()
                    .filter(o -> isAssignableFromList(node, o))
                    .forEach(o -> {
                        if (validClass(ReflectUtils.getValueByField(o, node).orElseThrow(IllegalAccessError::new).getClass())) {
                            List<Object> list = null;
                            try {
                                list = (List<Object>) ReflectUtils.getValueByField(o, node).orElseThrow(IllegalAccessException::new);
                            } catch (Exception e) {
                                log.info("[TreeUtils]::[setNodeIdAndParentId]::node = [{}], o = [{}], e:{}", node, o, e);
                            }
                            list.forEach(p -> setNodeIdAndParentId(p, nodeId));
                        }
                    });
        }

    }

    /**
     * 是不是List类或其子类
     *
     * @param node
     * @param o
     * @return
     */
    private static boolean isAssignableFromList(Object node, Field o) {
        return List.class.isAssignableFrom(ReflectUtils.getValueByField(o, node).orElse(new Object()).getClass());
    }

    /**
     * 是否可以直接转化的子类？
     * 不是List类或其子类 && 不是基本数据类型或其包装类
     *
     * @param node
     * @param o
     * @return
     */
    private static boolean isDirectTransformClass(Object node, Field o) {
        return !isAssignableFromList(node, o)
                && validClass(ReflectUtils.getValueByField(o, node).orElseThrow(IllegalAccessError::new).getClass());
    }

    private static TreeNodeWrapper wrapperNode(Object node, String parentId) {
        TreeNodeWrapper treeNodeWrapper = new TreeNodeWrapper();
        String nodeId = UUID.randomUUID().toString();
        treeNodeWrapper.setNodeId(nodeId);
        treeNodeWrapper.setParentId(parentId);
        treeNodeWrapper.setMetaData(node);

        nodeList.add(treeNodeWrapper);
        return treeNodeWrapper;
    }

    /**
     * 如果是基本类型或其包装类或者是string类型  则无需树节点化
     *
     * @param clazz
     * @return
     */
    public static boolean validClass(Class clazz) {
        if (clazz == null) {
            return false;
        } else if (clazz.isPrimitive()) {
            return false;
        } else if (isWrapClass(clazz)) {
            return false;
        } else if (String.class.equals(clazz)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }


}