package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/21.
 * Description: 将二叉树转换为双向链表，不能创建新结点，只能调整指针
 */
@Builder
public class TreeConvert {

    public static void main(String[] args) {
        TreeConvert treeConvert = TreeConvert.builder().build();

        BinaryTreeNode head = treeConvert.convert(treeConvert.constructTree());
        System.out.print("convert:");
        treeConvert.printLinkList(head);

        head = treeConvert.convertByRecursion(treeConvert.constructTree());
        System.out.print("convert by recursion:");
        treeConvert.printLinkList(head);
    }

    private BinaryTreeNode constructTree() {
        return BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
    }

    private void printLinkList(BinaryTreeNode root) {
        while (root != null) {
            System.out.print(root.getNode().getValue() + " ");
            root = root.getRight();
        }
        System.out.println();
    }

    /**
     * 思路：
     * 1.树的遍历顺序：类似中序遍历，先最左，然后父结点，然后右子树的左子树，然后右子树的父结点，然后右子树的右子树……
     * 2.链表的构造：找到链表的最左结点，然后依次找到右侧结点，并连接到链表
     * 实现：
     * 将树的左子树依次压栈，找到链表最左结点；然后最左结点的right指针指向父结点，父结点的left指针指向最左结点；
     * 然后处理父结点的右子树，找到右子树的最左结点，父结点的right指针指向最左结点，最左结点的left指针指向父结点
     *
     * @param root
     * @return
     */
    public BinaryTreeNode convert(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode current = root, previous = null, head = null;

        while (true) {
            while (current != null) {
                stack.addFirst(current);
                current = current.getLeft();
            }

            if (CollectionUtils.isEmpty(stack)) {
                break;
            }

            current = stack.removeFirst();
            if (previous != null) {
                previous.setRight(current);
                current.setLeft(previous);
            }
            if (head == null) {
                head = current;
            }

            previous = current;
            current = current.getRight();
        }
        return head;
    }

    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public BinaryTreeNode convertByRecursion(BinaryTreeNode root) {
        this.convertByRecursionInternal(root);

        BinaryTreeNode head = root;
        while (head.getLeft() != null) {
            head = head.getLeft();
        }
        return head;
    }

    /**
     * 先处理左子树，找左子树的最右结点，将根结点的left方向指向最右结点，最右结点的right方向指向根结点
     * 再处理右子树，找右子树的最左结点，将根结点的right方向指向最左结点，最左结点的left方向指向根结点
     *
     * @param root
     */
    private void convertByRecursionInternal(BinaryTreeNode root) {
        if (root.getLeft() != null) {
            this.convertByRecursionInternal(root.getLeft());
            BinaryTreeNode left = root.getLeft();
            while (left.getRight() != null) {
                left = left.getRight();
            }
            left.setRight(root);
            root.setLeft(left);
        }
        if (root.getRight() != null) {
            this.convertByRecursionInternal(root.getRight());
            BinaryTreeNode right = root.getRight();
            while (right.getLeft() != null) {
                right = right.getLeft();
            }
            right.setLeft(root);
            root.setRight(right);
        }
    }

}
