package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.commons.tree.binary.BinaryTreePrinter;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 判断二叉树是不是完全二叉树
 * Reference: 完全二叉树定义为若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树
 */
@Builder
public class TreeIsComplete {

    public static void main(String[] args) {
        BinaryTreeNode root_1 = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7)).build();

        BinaryTreeNode root_2 = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        BinaryTreeBuilder root_3_builder = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        root_3_builder.appendNode(-1, true);
        root_3_builder.appendNode(-1, true);
        root_3_builder.appendNode(-1, true);
        root_3_builder.appendNode(-1, true);
        root_3_builder.appendNode(-1, true);
        root_3_builder.appendNode(-1, true);
        root_3_builder.appendNode(10, false);
        BinaryTreeNode root_3 = root_3_builder.build();

        TreeIsComplete treeIsComplete = TreeIsComplete.builder().build();

        System.out.print("root_1:");
        BinaryTreePrinter.print(root_1);
        System.out.println("root_1 is complete:" + treeIsComplete.isComplete(root_1));
        System.out.println("root_1 is complete by recursion:" + treeIsComplete.isCompleteByRecursion(root_1));
        System.out.println("root_1 is full:" + treeIsComplete.isFull(root_1));

        System.out.print("root_2:");
        BinaryTreePrinter.print(root_2);
        System.out.println("root_2 is complete:" + treeIsComplete.isComplete(root_2));
        System.out.println("root_2 is complete by recursion:" + treeIsComplete.isCompleteByRecursion(root_2));
        System.out.println("root_2 is full:" + treeIsComplete.isFull(root_2));

        System.out.print("root_3:");
        BinaryTreePrinter.print(root_3);
        System.out.println("root_3 is complete:" + treeIsComplete.isComplete(root_3));
        System.out.println("root_3 is complete by recursion:" + treeIsComplete.isCompleteByRecursion(root_3));
        System.out.println("root_3 is full:" + treeIsComplete.isFull(root_3));
    }

    /**
     * 迭代解法
     * 思路：
     * 逐层遍历二叉树结点，一旦遍历到空结点，那么不在往队列里加入结点了，遍历队列里的已有元素，若有一个不是空结点，那么就不是完全二叉树，若全是空结点那么就是完全二叉树
     *
     * @param root
     * @return
     */
    public boolean isComplete(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return false;
        }

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        boolean isNoChild = false;

        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();

            if (isNoChild) {
                if ((node.getLeft() == null || node.getLeft().getNode() == null || node.getLeft().getNode().isNull()) &&
                        (node.getRight() == null || node.getRight().getNode() == null || node.getRight().getNode().isNull())) {
                    continue;
                } else {
                    return false;
                }
            }

            if (node.getLeft() != null && node.getRight() != null
                    && node.getLeft().getNode() != null && node.getRight().getNode() != null
                    && !node.getLeft().getNode().isNull() && !node.getRight().getNode().isNull()) {
                queue.addFirst(node.getLeft());
                queue.addFirst(node.getRight());
            }
            if ((node.getLeft() != null && node.getLeft().getNode() != null && !node.getLeft().getNode().isNull())
                    && (node.getRight() == null || node.getRight().getNode() == null || node.getRight().getNode().isNull())) {
                queue.addFirst(node.getLeft());
                isNoChild = true;
            }
            if ((node.getLeft() == null || node.getLeft().getNode() == null || node.getLeft().getNode().isNull())
                    && (node.getRight() != null && node.getRight().getNode() != null && !node.getRight().getNode().isNull())) {
                return false;
            }
            if ((node.getLeft() == null || node.getLeft().getNode() == null || node.getLeft().getNode().isNull())
                    && (node.getRight() == null || node.getRight().getNode() == null || node.getRight().getNode().isNull())) {
                isNoChild = true;
            }
        }

        return true;
    }

    /**
     * 递归解法
     * 思路：
     * 完全二叉树的定义决定(左子树深度 - 右子树深度)只有两种取值0、1；
     * <p>如果等于0，说明左子树是一棵满二叉树，判断左子树的结点个数是否为 2^n - 1，如果不是，返回false，如果是，递归判断右子树是否是完全二叉树
     * <p>如果等于1，说明右子树是一棵满二叉树，判断右子树的结点个数是否为 2^n - 1，如果不是，返回false，如果是，递归判断左子树是否是完全二叉树
     * <p>如果不等于0、1，说明不是一棵完全二叉树
     *
     * @param root
     * @return
     */
    public boolean isCompleteByRecursion(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return true;
        }

        TreeDepth treeDepth = TreeDepth.builder().build();
        int leftDepth = treeDepth.getTreeDepth(root.getLeft());
        int rightDepth = treeDepth.getTreeDepth(root.getRight());

        TreeCount treeCount = TreeCount.builder().build();

        int diff = leftDepth - rightDepth;
        int count;
        switch (diff) {
            case 0:
                count = treeCount.getTreeNumber(root.getLeft());
                if (count != Double.valueOf(Math.pow(2, leftDepth)).intValue() - 1) {
                    return false;
                } else {
                    return this.isCompleteByRecursion(root.getRight());
                }
            case 1:
                count = treeCount.getTreeNumber(root.getRight());
                if (count != Double.valueOf(Math.pow(2, rightDepth)).intValue() - 1) {
                    return false;
                } else {
                    return this.isCompleteByRecursion(root.getLeft());
                }
            default:
                return false;
        }
    }

    /**
     * 判断是否是满二叉树
     * 满二叉树定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树
     * 思路：
     * 1.计算树的深度depth，结点个数count
     * 2.判断 count == 2^depth - 1 并返回
     *
     * @param root
     * @return
     */
    public boolean isFull(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return true;
        }

        TreeDepth treeDepth = TreeDepth.builder().build();
        TreeCount treeCount = TreeCount.builder().build();

        int depth = treeDepth.getTreeDepth(root);
        int count = treeCount.getTreeNumber(root);

        return count == Double.valueOf(Math.pow(2, depth)).intValue() - 1;
    }

}