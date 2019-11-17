package com.pxd.base.linklist.base;

import com.pxd.base.commons.linklist.LinkListBuilder;
import com.pxd.base.commons.linklist.LinkListNode;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/17.
 * Description: 环相关题目
 */
@Builder
public class RingSubject {

    public static void main(String[] args) {
        LinkListNode node = LinkListBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).buildForSingle();

        LinkListNode ring = null;
        for (LinkListNode temp = node; temp != null; temp = temp.getAfter()) {
            if (temp.getValue() == 3) {
                ring = temp;
                continue;
            }
            if (temp.getValue() == 10) {
                temp.setAfter(ring);
                break;
            }
        }

        RingSubject ringSubject = RingSubject.builder().build();

        LinkListNode repeatNode = ringSubject.isRing(node);
        System.out.println("isRing:" + (repeatNode != null));

        int length = ringSubject.length(repeatNode);
        System.out.println("ringLength:" + length);

        System.out.println("startNode 1:" + ringSubject.startPosition(node, length));
        System.out.println("startNode 2:" + ringSubject.startPosition(node, repeatNode));
    }

    /**
     * 判断是否有环
     *
     * @param node
     * @return
     */
    public LinkListNode isRing(LinkListNode node) {
        if (node == null || node.getAfter() == null) {
            return null;
        }

        LinkListNode first = node, second = node;
        while (second != null) {
            first = first.getAfter();
            second = second.getAfter().getAfter();

            if (first == second) {
                return first;
            }
        }
        return null;
    }

    /**
     * 环长度
     *
     * @param node
     * @return
     */
    public int length(LinkListNode node) {
        int size = 1;
        for (LinkListNode temp = node.getAfter(); temp.getValue() != node.getValue(); temp = temp.getAfter(), size++) {
        }
        return size;
    }

    /**
     * 环的起点解法一
     * <p>
     * 先让一个指针走length步,另一个指针在开始位置;两个指针再一起走就是环的起点
     *
     * @param node
     * @param length
     * @return
     */
    public LinkListNode startPosition(LinkListNode node, int length) {
        LinkListNode first = node, second = node;
        for (int i = 0; i < length; i++, first = first.getAfter()) {
        }
        return this.getStartPosition(first, second);
    }

    /**
     * 环的起点解法二
     * <p>
     * 慢指针路程:2 * (AB + BC);快指针路程：AB + BC + n * L
     * AB = n * L - BC = (n - 1) * L -CB
     * <p>
     * 将慢指针移动到链表起点，然后快慢指针同时以每次一步的速率向前，相交之点就是起点
     *
     * @param node
     * @param repeatNode
     * @return
     */
    public LinkListNode startPosition(LinkListNode node, LinkListNode repeatNode) {
        LinkListNode first = node, second = repeatNode;
        return this.getStartPosition(first, second);
    }

    private LinkListNode getStartPosition(LinkListNode first, LinkListNode second) {
        while (first != null && second != null) {
            if (first.getValue() == second.getValue()) {
                return first;
            }

            first = first.getAfter();
            second = second.getAfter();
        }
        return null;
    }

}
