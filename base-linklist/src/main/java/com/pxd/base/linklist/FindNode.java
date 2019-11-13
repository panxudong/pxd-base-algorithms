package com.pxd.base.linklist;

import com.pxd.base.commons.linklist.LinkListCounter;
import com.pxd.base.commons.linklist.LinkListNode;
import lombok.Builder;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
@Builder
public class FindNode {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        LinkListNode node = baseOperation.create();
        baseOperation.print(node);

        FindNode findNode = FindNode.builder().build();

        LinkListNode found = findNode.findBackKNode(node, 4);
        System.out.println(found.toString());

        found = findNode.findMiddleNode(node);
        System.out.println(found.toString());
    }

    /**
     * 查找单链表中的倒数第k个节点
     *
     * @param node
     * @param k
     * @return
     */
    public LinkListNode findBackKNode(LinkListNode node, int k) {
        LinkListNode first, second = node;

        LinkListNode temp = this.getNode(node, k);

        if (temp == null) {
            return null;
        }
        first = temp;

        while (first != null) {
            first = first.getAfter();
            second = second.getAfter();
        }

        return second;
    }

    /**
     * 查找单链表的中间节点
     * 或者使用快慢指针，一个走一步，一个走两步，一次循环即可处理
     *
     * @param node
     * @return
     */
    public LinkListNode findMiddleNode(LinkListNode node) {
        int size = LinkListCounter.count(node);
        int middle = size / 2;
        return this.getNode(node, middle);
    }

    private LinkListNode getNode(LinkListNode node, int k) {
        int i = 0;
        LinkListNode temp = node;
        while (temp != null && i < k) {
            temp = temp.getAfter();
            i++;
        }
        return temp;
    }

}
