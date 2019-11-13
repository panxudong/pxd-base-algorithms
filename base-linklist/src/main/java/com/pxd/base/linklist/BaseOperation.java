package com.pxd.base.linklist;

import com.pxd.base.commons.linklist.LinkListBuilder;
import com.pxd.base.commons.linklist.LinkListCounter;
import com.pxd.base.commons.linklist.LinkListNode;
import com.pxd.base.commons.linklist.LinkListPrinter;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
@Builder
public class BaseOperation {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();

        LinkListNode node = baseOperation.create();
        baseOperation.print(node);
        System.out.println("size:" + baseOperation.count(node));
    }

    public LinkListNode create() {
        LinkListBuilder linkListBuilder = LinkListBuilder.newInstance();
        linkListBuilder.appendNodes(Arrays.asList(2, 8, 5, 0, 7, 6, 1, 9, 4, 3));
        return linkListBuilder.buildForSingle();
    }

    public void print(LinkListNode node) {
        LinkListPrinter.print(node);
    }

    public int count(LinkListNode node) {
        return LinkListCounter.count(node);
    }

}
