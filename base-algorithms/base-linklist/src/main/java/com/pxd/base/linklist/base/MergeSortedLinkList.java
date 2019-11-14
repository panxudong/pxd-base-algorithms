package com.pxd.base.linklist.base;

import com.pxd.base.commons.linklist.LinkListBuilder;
import com.pxd.base.commons.linklist.LinkListNode;
import com.pxd.base.commons.linklist.LinkListPrinter;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/14.
 * Description:
 */
@Builder
public class MergeSortedLinkList {

    public static void main(String[] args) {
        LinkListNode first = LinkListBuilder.newInstance().appendNodes(Arrays.asList(1, 3, 5, 7, 9, 11)).buildForSingle();
        LinkListNode second = LinkListBuilder.newInstance().appendNodes(Arrays.asList(-1, 0, 2, 4, 6, 8)).buildForSingle();

        MergeSortedLinkList mergeSortedLinkList = MergeSortedLinkList.builder().build();
        LinkListNode result = mergeSortedLinkList.merge(first, second);
        LinkListPrinter.print(result);
    }

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     *
     * @param first
     * @param second
     * @return
     */
    public LinkListNode merge(LinkListNode first, LinkListNode second) {
        LinkListNode result = null, i = null;

        while (first != null && second != null) {
            LinkListNode res;
            if (first.getValue() > second.getValue()) {
                res = second;
                second = second.getAfter();
            } else {
                res = first;
                first = first.getAfter();
            }

            if (result == null) {
                result = i = res;
            } else {
                i.setAfter(res);
                i = i.getAfter();
            }
        }

        LinkListNode temp = null;
        if (first != null) {
            temp = first;
        }
        if (second != null) {
            temp = second;
        }

        if (temp != null) {
            while (temp != null) {
                i.setAfter(temp);
                i = i.getAfter();
                temp = temp.getAfter();
            }
        }
        return result;
    }
}
