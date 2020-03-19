package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreePrinter;
import lombok.Builder;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Created by panxudong on 2019/11/23.
 * Description: 判断二叉树是不是平衡二叉树
 */
@Builder
public class TreeIsAVL {

    public static void main(String[] args) {
        BinaryTreeNode root_1 = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        BinaryTreeBuilder root_2_builder = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        root_2_builder.appendNode(-1, true);
        root_2_builder.appendNode(-1, true);
        root_2_builder.appendNode(-1, true);
        root_2_builder.appendNode(-1, true);
        root_2_builder.appendNode(-1, true);
        root_2_builder.appendNode(-1, true);
        root_2_builder.appendNode(10, false);
        BinaryTreeNode root_2 = root_2_builder.build();

        TreeIsAVL treeIsAVL = TreeIsAVL.builder().build();

        System.out.print("root_1:");
        BinaryTreePrinter.print(root_1);
        System.out.println("root_1 is AVL:" + treeIsAVL.isAVL(root_1));

        System.out.print("root_2:");
        BinaryTreePrinter.print(root_2);
        System.out.println("root_2 is AVL:" + treeIsAVL.isAVL(root_2));
    }

    /**
     * 递归解法
     * AVL的特点：
     * 1.左子树、右子树的高度差的绝对值不超过1
     * 2.左子树的值都小于根结点的值，右子树的值都大于根结点的值
     * 思路：
     * 1.如果二叉树为空，返回true
     * 2.如果二叉树不为空，则求左子树、右子树深度之差的绝对值，如果大于1，则返回false
     * 3.如果小于等于1，则求左子树的最大值，如果大于根结点的值，则返回false；再求右子树的最小值，如果小于根结点的值，则返回false
     * 4.递归判断左子树、右子树是否是AVL树
     *
     * @param root
     * @return
     */
    public boolean isAVL(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return true;
        }

        TreeDepth treeDepth = TreeDepth.builder().build();
        int depth = Math.abs(treeDepth.getTreeDepth(root.getLeft()) - treeDepth.getTreeDepth(root.getRight()));
        if (depth > 1) {
            return false;
        }

        int rootValue = root.getNode().getValue();
        int leftValue = this.getValue(root.getLeft(), Math::max);
        if (leftValue > rootValue) {
            return false;
        }
        int rightValue = this.getValue(root.getRight(), Math::min);
        if (rightValue < rootValue) {
            return false;
        }

        return this.isAVL(root.getLeft()) && this.isAVL(root.getRight());
    }

    /**
     * 此处可以使用动态规划的思路优化
     * 思路：
     * 1.计算一棵树的最大值或最小值，方法是分别计算左子树、右子树的值，然后Math.max(左子树的值，右子树的值，根结点的值)
     * 2.因为递归调用，所以每一棵子树的值都会被计算，可以存储下来，避免进行重复计算
     * 譬如：可以返回一个Map<node, value_by_function>，isAVL方法每次先从Map中查找是否已经存在，如果不存在，再进行计算
     *
     * @param root
     * @param function
     * @return
     */
    private int getValue(BinaryTreeNode root, BiFunction<Integer, Integer, Integer> function) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return 0;
        }

        int rootValue = root.getNode().getValue();
        int leftValue = this.getValue(root.getLeft(), function);
        int rightValue = this.getValue(root.getRight(), function);

        return function.apply(function.apply(leftValue, rightValue), rootValue);
    }
}
