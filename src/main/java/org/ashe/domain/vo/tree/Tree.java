package org.ashe.domain.vo.tree;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("all")
public class Tree {


    private Tree() {

    }

    /**
     * 数据库中包含树结构的list 转真正的树list
     *
     * @param source 原始树 数组结构
     * @param rootId 根Id
     */
    public static <T extends Node> Collection<T> tree(Collection<T> source, Serializable rootId) {
        // 1. 获取根节点对象
        Collection<T> roots = findRoots(source, rootId);
        source.removeAll(roots);
        if (source.isEmpty()) {
            return roots;
        }
        // 2. 获取根节点下的子节点
        for (T root : roots) {
            if (source.isEmpty()) {
                break;
            }
            findLeaves(source, root);
        }
        return roots;
    }

    /**
     * 获取一级根节点数组对象(包含根节点对象)
     */
    private static <T extends Node> Collection<T> findRoots(Collection<T> source, Serializable rootId) {
        Collection<T> rootNode = new ArrayList<>();
        for (T node : source) {
            if ((rootId == null && node.getPid() == null) || (node.getPid() != null && node.getPid().equals(rootId))) {
                rootNode.add(node);
            }
        }
        return rootNode;
    }

    /**
     * 组装根节点下的叶子节点
     */
    private static <T extends Node> void findLeaves(Collection<T> source, T node) {
        if (!source.isEmpty()) {
            Collection<T> leaves = new ArrayList<>();
            for (T sourceNode : source) {
                if (node.getId().equals(sourceNode.getPid())) {
                    leaves.add(sourceNode);
                }
            }
            node.setNodes(leaves.isEmpty() ? null : leaves);
            if (!leaves.isEmpty()) {
                for (T nested : leaves) {
                    findLeaves(source, nested);
                }
                source.removeAll(leaves);
            }
        }
    }


}
