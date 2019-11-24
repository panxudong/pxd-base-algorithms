package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/23.
 * Description: 判断两棵二叉树是否是相同的树
 */
@Builder
public class TreeSame {

    public static void main(String[] args) {
        BinaryTreeNode root_1 = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        BinaryTreeNode root_2 = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)).build();

        TreeSame treeSame = TreeSame.builder().build();
        System.out.println("is same:" + treeSame.isSame(root_1, root_2));
        System.out.println("is same by recursion:" + treeSame.isSameByRecursion(root_1, root_2));
    }

    /**
     * 通过遍历判断是否相同
     * 实现：采用前序遍历判断
     *
     * @param root_1
     * @param root_2
     * @return
     */
    public boolean isSame(BinaryTreeNode root_1, BinaryTreeNode root_2) {
        if (root_1 == null && root_2 == null) {
            return true;
        }
        if (root_1 == null || root_2 == null) {
            return false;
        }

        LinkedList<BinaryTreeNode> stack_1 = new LinkedList<>();
        LinkedList<BinaryTreeNode> stack_2 = new LinkedList<>();

        stack_1.addFirst(root_1);
        stack_2.addFirst(root_2);

        while (CollectionUtils.isNotEmpty(stack_1) && CollectionUtils.isNotEmpty(stack_2)) {
            BinaryTreeNode node_1 = stack_1.removeFirst(), node_2 = stack_2.removeFirst();

            if (node_1 == null && node_2 == null) {
                continue;
            }
            if (node_1 == null || node_2 == null) {
                return false;
            }
            if (node_1.getNode().getValue() != node_2.getNode().getValue()) {
                return false;
            }

            stack_1.addFirst(node_1.getRight()); //空元素也添加，如果为空不添加，则判断不准确
            stack_1.addFirst(node_1.getLeft());
            stack_2.addFirst(node_2.getRight());
            stack_2.addFirst(node_2.getLeft());
        }

        return true;
    }

    /**
     * 递归判断两棵树是否相同
     * 思路：根结点是否相同，不过不同返回false，如果相同则分别判断左子树、右子树是否相同
     *
     * @param root_1
     * @param root_2
     * @return
     */
    public boolean isSameByRecursion(BinaryTreeNode root_1, BinaryTreeNode root_2) {
        if (root_1 == null && root_2 == null) {
            return true;
        }
        if (root_1 == null || root_2 == null) {
            return false;
        }

        boolean result = root_1.getNode().getValue() == root_2.getNode().getValue();
        if (!result) {
            return false;
        }

        return this.isSameByRecursion(root_1.getLeft(), root_2.getLeft())
                && this.isSameByRecursion(root_1.getRight(), root_2.getRight());
    }

}
