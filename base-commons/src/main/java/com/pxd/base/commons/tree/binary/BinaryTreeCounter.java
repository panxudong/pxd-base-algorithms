package com.pxd.base.commons.tree.binary;

import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
public class BinaryTreeCounter {

    public static final int count(BinaryTreeNode node) {
        LinkedList<BinaryTreeNode> list = new LinkedList<>();

        int count = 1;
        list.addFirst(node);
        while (CollectionUtils.isNotEmpty(list)) {
            BinaryTreeNode temp = list.removeLast();
            if (temp.getLeft() != null) {
                count++;
                list.addFirst(temp.getLeft());
            }
            if (temp.getRight() != null) {
                count++;
                list.addFirst(temp.getRight());
            }
        }
        return count;
    }

    public static final int count(BinaryTreeNode[] nodes) {
        if (nodes == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[i].getNode() != null && !nodes[i].getNode().isNull()) {
                count++;
            }
        }
        return count;
    }

    public static final int count(List<BinaryTreeNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != null && nodes.get(i).getNode() != null && !nodes.get(i).getNode().isNull()) {
                count++;
            }
        }
        return count;
    }

}