package com.pxd.base.algorithms.linklist;

import com.pxd.base.algorithms.commons.linklist.LinkListBuilder;
import com.pxd.base.algorithms.commons.linklist.LinkListNode;
import com.pxd.base.algorithms.commons.linklist.LinkListPrinter;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/14.
 * Description: 翻转链表
 */
@Builder
public class TurnLinkList {

    public static void main(String[] args) {
        LinkListNode node = LinkListBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        LinkListPrinter.print(node);

        TurnLinkList turnLinkList = TurnLinkList.builder().build();
        LinkListPrinter.print(turnLinkList.turn(node));
    }

    /**
     * 时间复杂度：O(n)，空间复杂度：O(2)
     *
     * @param node
     * @return
     */
    public LinkListNode turn(LinkListNode node) {
        if (node == null || node.getAfter() == null) {
            return node;
        }

        LinkListNode before = null;
        LinkListNode after = node.getAfter();

        while (after != null) {
            node.setAfter(before);
            before = node;
            node = after;
            after = node.getAfter();
        }

        node.setAfter(before);

        return node;
    }

}