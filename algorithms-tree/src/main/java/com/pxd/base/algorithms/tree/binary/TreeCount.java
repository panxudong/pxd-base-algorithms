package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeCounter;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/19.
 * Description: 求二叉树中的结点个数
 */
@Builder
public class TreeCount {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeCount treeCount = TreeCount.builder().build();
        System.out.println("size:" + treeCount.getTreeNumber(root));
        System.out.println("size by recursion:" + treeCount.getTreeNumberByRecursion(root));
    }

    /**
     * 非递归计算树的结点个数
     *
     * @param root
     * @return
     */
    public int getTreeNumber(BinaryTreeNode root) {
        return BinaryTreeCounter.count(root);
    }

    /**
     * 通过递归计算树的结点个数
     *
     * @param root
     * @return
     */
    public int getTreeNumberByRecursion(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return 0;
        }

        int size = 1;
        if (root.getLeft() != null) {
            size += this.getTreeNumberByRecursion(root.getLeft());
        }
        if (root.getRight() != null) {
            size += this.getTreeNumberByRecursion(root.getRight());
        }
        return size;
    }

}