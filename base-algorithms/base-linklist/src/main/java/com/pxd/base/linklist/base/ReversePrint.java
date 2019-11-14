package com.pxd.base.linklist.base;

import com.pxd.base.commons.linklist.LinkListBuilder;
import com.pxd.base.commons.linklist.LinkListNode;
import com.pxd.base.commons.linklist.LinkListPrinter;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/14.
 * Description: 从尾到头打印单链表
 */
@Builder
public class ReversePrint {

    public static void main(String[] args) {
        LinkListNode node = LinkListBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).buildForSingle();
        LinkListPrinter.print(node);

        ReversePrint reversePrint = ReversePrint.builder().build();
        reversePrint.printByStack(node);
        reversePrint.printByRecursion(node);
    }

    /**
     * 通过栈（先进后出）实现，先将元素依次压栈，然后依次出栈打印即可
     *
     * @param node
     */
    public void printByStack(LinkListNode node) {
        if (node == null) {
            return;
        }

        LinkedList<LinkListNode> stack = new LinkedList<>();
        for (LinkListNode temp = node; temp != null; temp = temp.getAfter()) {
            stack.addFirst(temp);
        }

        while (CollectionUtils.isNotEmpty(stack)) {
            LinkListNode temp = stack.pollFirst();
            System.out.print(temp.getValue());
            if (stack.size() != 0) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    /**
     * 通过递归实现，代码简单，但是如果链表长度过大，那么方法会递归过多，导致StackOverflowError
     *
     * @param node
     */
    public void printByRecursion(LinkListNode node) {
        if (node == null) {
            return;
        }

        this.printByRecursion(node.getAfter());
        System.out.print(node.getValue() + " ");
    }
}
