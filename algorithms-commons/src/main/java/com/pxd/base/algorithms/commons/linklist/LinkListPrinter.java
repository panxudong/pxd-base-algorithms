package com.pxd.base.algorithms.commons.linklist;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
public class LinkListPrinter {

    public static final void print(LinkListNode node) {
        LinkListNode temp = node;
        while (temp != null) {
            System.out.print(temp.getValue());
            if (temp.getAfter() != null) {
                System.out.print("->");
            }
            temp = temp.getAfter();
        }
        System.out.println();
    }

    public static final void print(LinkListNode[] nodes) {
        if (nodes == null) {
            return;
        }
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].getValue());
            if (i != nodes.length - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public static final void print(List<LinkListNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return;
        }
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).getValue());
            if (i != nodes.size() - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

}