package com.pxd.base.commons.tree.binary;

import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
public class BinaryTreePrinter {

    public static final void print(BinaryTreeNode node) {
        LinkedList<BinaryTreeNode> list = new LinkedList<>();

        list.addFirst(node);
        while (CollectionUtils.isNotEmpty(list)) {
            BinaryTreeNode temp = list.removeLast();
            if (temp.getNode() == null || temp.getNode().isNull()) {
                System.out.print("   ");
            } else {
                System.out.print(temp.getNode().getValue() + " ");
            }

            if (temp.getLeft() != null) {
                list.addFirst(temp.getLeft());
            }
            if (temp.getRight() != null) {
                list.addFirst(temp.getRight());
            }
        }
        System.out.println();
        return;
    }

    public static final void print(BinaryTreeNode[] nodes) {
        if (nodes == null) {
            return;
        }
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[i].getNode() != null && !nodes[i].getNode().isNull()) {
                System.out.print(nodes[i].getNode().getValue() + " ");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
        return;
    }

    public static final void print(List<BinaryTreeNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return;
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != null && nodes.get(i).getNode() != null && !nodes.get(i).getNode().isNull()) {
                System.out.print(nodes.get(i).getNode().getValue() + " ");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
        return;
    }

}