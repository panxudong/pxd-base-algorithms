package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/23.
 * Description: 求二叉树第K层的结点个数
 */
@Builder
public class TreeKNodeNumber {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeKNodeNumber treeKNodeNumber = TreeKNodeNumber.builder().build();
        int number = treeKNodeNumber.getNumber(root, 3);
        System.out.println("level k node number:" + number);
        number = treeKNodeNumber.getNumberByRecursion(root, 3);
        System.out.println("level k node number by recursion:" + number);
    }

    /**
     * 借鉴逐层遍历的思想，找到第k层，并且全部推入队列中，然后逐个出队列
     * 树从底层往上处理，用栈；从顶层往下处理，用队列
     *
     * @param root
     * @param k
     * @return
     */
    public int getNumber(BinaryTreeNode root, int k) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int depth = 1, currentNumber = 1, nextNumber = 0;

        while (depth < k && CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();
            currentNumber--;

            if (node.getLeft() != null) {
                queue.addFirst(node.getLeft());
                nextNumber++;
            }
            if (node.getRight() != null) {
                queue.addFirst(node.getRight());
                nextNumber++;
            }

            if (currentNumber == 0) {
                depth++;
                currentNumber = nextNumber;
                nextNumber = 0;
            }
        }

        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();
            System.out.print(node.getNode().getValue() + " ");
        }
        System.out.println();
        return currentNumber;
    }

    /**
     * 同递归逐层遍历的解法
     *
     * @param root
     * @param k
     * @return
     */
    public int getNumberByRecursion(BinaryTreeNode root, int k) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        int number = this.getNumberByRecursionInternal(root, k, queue);
        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();
            System.out.print(node.getNode().getValue() + " ");
        }
        System.out.println();
        return number;
    }

    public int getNumberByRecursionInternal(BinaryTreeNode root, int k, LinkedList<BinaryTreeNode> queue) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return 0;
        }
        if (k == 1) {
            queue.addFirst(root);
            return 1;
        }
        return this.getNumberByRecursionInternal(root.getLeft(), k - 1, queue)
                + this.getNumberByRecursionInternal(root.getRight(), k - 1, queue);
    }

}
