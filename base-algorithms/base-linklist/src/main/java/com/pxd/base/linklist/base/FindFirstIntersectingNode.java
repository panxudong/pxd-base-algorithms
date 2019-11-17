package com.pxd.base.linklist.base;

import com.pxd.base.commons.linklist.LinkListBuilder;
import com.pxd.base.commons.linklist.LinkListCounter;
import com.pxd.base.commons.linklist.LinkListNode;
import com.pxd.base.commons.linklist.LinkListPrinter;
import lombok.Builder;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/17.
 * Description: 寻找两个相交链表的第一个相交节点
 */
@Builder
public class FindFirstIntersectingNode {

    public static void main(String[] args) {
        LinkListNode first = LinkListBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).buildForSingle();
        LinkListNode second = LinkListBuilder.newInstance().appendNodes(Arrays.asList(11, 12, 13, 14)).buildForSingle();

        LinkListNode iFirst = first, iSecond = second;
        while (iFirst.getAfter() != null && iSecond.getAfter() != null) {
            iFirst = iFirst.getAfter();
            iSecond = iSecond.getAfter();
        }

        if (iFirst.getAfter() == null) {
            iFirst.setAfter(iSecond.getAfter());
        }
        if (iSecond.getAfter() == null) {
            iSecond.setAfter(iFirst.getAfter().getAfter());
        }

        LinkListPrinter.print(first);
        LinkListPrinter.print(second);

        FindFirstIntersectingNode findFirstIntersectingNode = FindFirstIntersectingNode.builder().build();
        System.out.println(findFirstIntersectingNode.find(first, second).toString());
        System.out.println(findFirstIntersectingNode.findByTwoPointer(first, second).toString());
    }

    /**
     * 将两个链表分别压栈，然后挨个出栈，如果值不同，则下一个Node是相交的第一个元素
     *
     * @param first
     * @param second
     * @return
     */
    public LinkListNode find(LinkListNode first, LinkListNode second) {
        LinkedList<LinkListNode> stackFirst = new LinkedList<>();
        for (LinkListNode temp = first; temp != null; temp = temp.getAfter()) {
            stackFirst.addFirst(temp);
        }

        LinkedList<LinkListNode> stackSecond = new LinkedList<>();
        for (LinkListNode temp = second; temp != null; temp = temp.getAfter()) {
            stackSecond.addFirst(temp);
        }

        for (LinkListNode iFirst = stackFirst.removeFirst(), iSecond = stackSecond.removeFirst();
             iFirst != null && iSecond != null; iFirst = stackFirst.removeFirst(), iSecond = stackSecond.removeFirst()) {
            if (iFirst.getValue() != iSecond.getValue()) {
                return iFirst.getAfter();
            }
        }
        return null;
    }

    /**
     * 分别计算两个链表的长度(length_1, length_2),然后让长的链表先走 Math.abs(length_1-length_2) 步
     * 然后两个链表分别以每次一步的速率向前走，第一个值相等的点就是第一个相交节点
     *
     * @param first
     * @param second
     * @return
     */
    public LinkListNode findByTwoPointer(LinkListNode first, LinkListNode second) {
        int firstSize = LinkListCounter.count(first);
        int secondSize = LinkListCounter.count(second);

        LinkListNode iFirst = firstSize > secondSize ? first : second;
        LinkListNode iSecond = firstSize > secondSize ? second : first;

        int size = Math.abs(firstSize - secondSize);
        for (int i = 0; i < size; i++) {
            iFirst = iFirst.getAfter();
        }

        for (; iFirst.getValue() != iSecond.getValue(); iFirst = iFirst.getAfter(), iSecond = iSecond.getAfter()) {
        }

        return iFirst;
    }

}