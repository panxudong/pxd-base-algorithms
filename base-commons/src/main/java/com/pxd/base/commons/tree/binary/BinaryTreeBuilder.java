package com.pxd.base.commons.tree.binary;

import com.pxd.base.commons.tree.TreeNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by panxudong on 2019/11/18.
 * Description:
 */
public class BinaryTreeBuilder {

    private List<BinaryTreeNode> nodes;

    public BinaryTreeBuilder() {
        this.nodes = new ArrayList<>();
    }

    public static final BinaryTreeBuilder newInstance() {
        return new BinaryTreeBuilder();
    }

    public BinaryTreeBuilder appendNode(int value) {
        return this.appendNode(value, true);
    }

    public BinaryTreeBuilder appendNode(int value, boolean isNull) {
        TreeNode node = TreeNode.builder().value(value).isNull(isNull).build();
        BinaryTreeNode binaryTreeNode = BinaryTreeNode.builder().node(node).build();
        this.nodes.add(binaryTreeNode);
        return this;
    }

    public BinaryTreeBuilder appendNodes(List<Integer> values) {
        if (CollectionUtils.isEmpty(values)) {
            return this;
        }
        values.stream().forEach(value -> this.appendNode(value));
        return this;
    }

    public BinaryTreeNode build() {
        Function<List<BinaryTreeNode>, BinaryTreeNode> treeFunction = list -> {
            for (int i = 0; i < this.nodes.size(); i++) {
                BinaryTreeNode node = this.nodes.get(i);
                if (this.nodes.size() - 1 > 2 * i + 1) {
                    BinaryTreeNode left = this.nodes.get(2 * i + 1);
                    node.setLeft(left);
                }
                if (this.nodes.size() - 1 > 2 * (i + 1)) {
                    BinaryTreeNode right = this.nodes.get(2 * (i + 1));
                    node.setRight(right);
                }
            }
            return this.nodes.get(0);
        };
        return this.build(this.nodes, treeFunction);
    }

    public BinaryTreeNode[] buildForArray() {
        Function<List<BinaryTreeNode>, BinaryTreeNode[]> treeFunction = list -> {
            BinaryTreeNode[] array = new BinaryTreeNode[list.size()];
            return list.toArray(array);
        };
        return this.build(this.nodes, treeFunction);
    }

    public List<BinaryTreeNode> buildForList() {
        Function<List<BinaryTreeNode>, List<BinaryTreeNode>> treeFunction = Function.identity();
        return this.build(this.nodes, treeFunction);
    }

    private <T> T build(List<BinaryTreeNode> nodes, Function<List<BinaryTreeNode>, T> treeFunction) {
        if (CollectionUtils.isEmpty(nodes)) {
            return null;
        }

        return treeFunction.apply(nodes);
    }

}