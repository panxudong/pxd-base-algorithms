package com.pxd.base.commons.linklist;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by panxudong on 2019/11/13.
 * Description:
 */
public class LinkListBuilder {

    private List<LinkListNode> nodes;

    public LinkListBuilder() {
        this.nodes = new ArrayList<>();
    }

    public static final LinkListBuilder newInstance() {
        return new LinkListBuilder();
    }

    public LinkListBuilder appendNode(int value) {
        LinkListNode node = LinkListNode.builder().value(value).build();
        this.nodes.add(node);
        return this;
    }

    public LinkListBuilder appendNodes(List<Integer> values) {
        if (CollectionUtils.isEmpty(values)) {
            return this;
        }
        values.stream().forEach(value -> this.appendNode(value));
        return this;
    }

    public LinkListNode build() {
        Function<List<LinkListNode>, LinkListNode> linkListFunction = list -> {
            for (int i = 0; i < list.size(); i++) {
                if (i + 1 <= list.size() - 1) {
                    list.get(i).setAfter(list.get(i + 1));
                }
            }
            return list.get(0);
        };
        return this.build(this.nodes, linkListFunction);
    }

    public LinkListNode[] buildForArray() {
        Function<List<LinkListNode>, LinkListNode[]> linkListFunction = list -> {
            LinkListNode[] array = new LinkListNode[list.size()];
            return list.toArray(array);
        };
        return this.build(this.nodes, linkListFunction);
    }

    public List<LinkListNode> buildForList() {
        Function<List<LinkListNode>, List<LinkListNode>> linkListFunction = Function.identity();
        return this.build(this.nodes, linkListFunction);
    }

    private <T> T build(List<LinkListNode> nodes, Function<List<LinkListNode>, T> linkListFunction) {
        if (CollectionUtils.isEmpty(nodes)) {
            return null;
        }

        return linkListFunction.apply(nodes);
    }

}
