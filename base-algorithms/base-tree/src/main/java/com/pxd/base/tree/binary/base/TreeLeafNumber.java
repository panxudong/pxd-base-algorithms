package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/23.
 * Description: 求二叉树中叶子结点的个数
 */
@Builder
public class TreeLeafNumber {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeLeafNumber treeLeafNumber = TreeLeafNumber.builder().build();
        System.out.println("leaf number:" + treeLeafNumber.getNumber(root));
        System.out.println("leaf number by recursion:" + treeLeafNumber.getNumberByRecursion(root));
    }

    /**
     * 非递归求树的叶子结点个数
     * 借鉴逐层遍历的方式，其实用任何一种树的遍历方式都可以解决
     *
     * @param root
     * @return
     */
    public int getNumber(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int number = 0;

        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();

            if (node.getLeft() != null) {
                queue.addFirst(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.addFirst(node.getRight());
            }
            if (node.getLeft() == null && node.getRight() == null) {
                number++;
            }
        }
        return number;
    }

    /**
     * 递归求树的叶子结点个数
     * 借鉴逐层遍历的方式，其实用任何一种树的遍历方式都可以解决
     *
     * @param root
     * @return
     */
    public int getNumberByRecursion(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return 0;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }

        return this.getNumberByRecursion(root.getLeft()) + this.getNumberByRecursion(root.getRight());
    }

}
