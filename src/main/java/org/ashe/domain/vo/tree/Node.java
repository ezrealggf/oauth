package org.ashe.domain.vo.tree;

import java.io.Serializable;
import java.util.Collection;

public interface Node extends Serializable {

    /**
     * 获取Id
     *
     * @return id
     */
    Serializable getId();

    /**
     * 获取PID
     *
     * @return PID
     */
    Serializable getPid();

    /**
     * 设置子节点
     *
     * @param nodes 子节点
     */
    void setNodes(Collection<? extends Node> nodes);


}
