package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeCounter;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/19.
 * Description: 求二叉树中的节点个数
 */
@Builder
public class TreeCount {

    public static void main(String[] args) {
        BinaryTreeNode node = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeCount treeCount = TreeCount.builder().build();
        System.out.println("size:" + treeCount.getTreeNumber(node));
        System.out.println("size by recursion:" + treeCount.getTreeNumberByRecursion(node));
    }

    /**
     * 非递归计算树的节点个数
     *
     * @param node
     * @return
     */
    public int getTreeNumber(BinaryTreeNode node) {
        return BinaryTreeCounter.count(node);
    }

    /**
     * 通过递归计算树的节点个数
     *
     * @param node
     * @return
     */
    public int getTreeNumberByRecursion(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return 0;
        }

        int size = 1;
        if (node.getLeft() != null) {
            size += this.getTreeNumberByRecursion(node.getLeft());
        }
        if (node.getRight() != null) {
            size += this.getTreeNumberByRecursion(node.getRight());
        }
        return size;
    }

}