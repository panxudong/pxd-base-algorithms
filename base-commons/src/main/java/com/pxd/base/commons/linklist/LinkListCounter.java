package com.pxd.base.commons.linklist;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
public class LinkListCounter {

    public static final int count(LinkListNode node) {
        int i = 0;
        LinkListNode temp = node;
        while (temp != null) {
            i++;
            temp = temp.getAfter();
        }
        return i;
    }

    public static final int count(LinkListNode[] nodes) {
        if (nodes == null) {
            return 0;
        }
        return nodes.length;
    }

    public static final int count(List<LinkListNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return 0;
        }
        return nodes.size();
    }

}
