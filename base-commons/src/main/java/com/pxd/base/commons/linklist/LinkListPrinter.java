package com.pxd.base.commons.linklist;

import java.util.List;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
public class LinkListPrinter {

    public static final void print(LinkListNode node) {
        while (node != null) {
            System.out.print(node.getValue());
            if (node.getAfter() != null) {
                System.out.print("->");
            }
            node = node.getAfter();
        }
        System.out.println();
    }

    public static final void print(LinkListNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].getValue());
            if (i != nodes.length - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public static final void print(List<LinkListNode> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).getValue());
            if (i != nodes.size() - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

}