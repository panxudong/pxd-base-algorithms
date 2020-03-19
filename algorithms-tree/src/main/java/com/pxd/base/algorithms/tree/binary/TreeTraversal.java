package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by panxudong on 2019/11/20.
 * Description: 树的前序遍历，中序遍历，后续遍历，分层遍历
 */
@Builder
public class TreeTraversal {

    public static void main(String[] args) {
        BinaryTreeNode node = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeTraversal treeTraversal = TreeTraversal.builder().build();

        treeTraversal.preOrderTraversal(node);
        System.out.println();
        treeTraversal.preOrderTraversalByRecursion(node);
        System.out.println();

        treeTraversal.inOrderTraversal(node);
        System.out.println();
        treeTraversal.inOrderTraversalByRecursion(node);
        System.out.println();

        treeTraversal.postOrderTraversal(node);
        System.out.println();
        treeTraversal.postOrderTraversalByRecursion(node);
        System.out.println();

        treeTraversal.levelTraversal(node);
        System.out.println();
        treeTraversal.levelTraversalByRecursion(node);
        System.out.println();
    }

    /**
     * 非递归前序遍历二叉树
     *
     * @param node
     */
    public void preOrderTraversal(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (CollectionUtils.isNotEmpty(stack)) {
            BinaryTreeNode temp = stack.removeFirst();
            System.out.print(temp.getNode().getValue() + " ");

            if (temp.getRight() != null) {
                stack.addFirst(temp.getRight());
            }
            if (temp.getLeft() != null) {
                stack.addFirst(temp.getLeft());
            }
        }
    }

    /**
     * 递归前序遍历二叉树
     *
     * @param node
     */
    public void preOrderTraversalByRecursion(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        System.out.print(node.getNode().getValue() + " ");
        this.preOrderTraversalByRecursion(node.getLeft());
        this.preOrderTraversalByRecursion(node.getRight());
    }

    /**
     * 非递归中序遍历二叉树
     *
     * @param node
     */
    public void inOrderTraversal(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode current = node;

        while (true) {
            while (current != null) {
                stack.addFirst(current);
                current = current.getLeft();
            }

            if (CollectionUtils.isEmpty(stack)) {
                break;
            }

            current = stack.removeFirst();
            System.out.print(current.getNode().getValue() + " ");
            current = current.getRight();
        }
    }

    /**
     * 递归中序遍历二叉树
     *
     * @param node
     */
    public void inOrderTraversalByRecursion(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        this.inOrderTraversalByRecursion(node.getLeft());
        System.out.print(node.getNode().getValue() + " ");
        this.inOrderTraversalByRecursion(node.getRight());
    }

    /**
     * 非递归后续遍历二叉树
     *
     * @param node
     */
    public void postOrderTraversal(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        LinkedList<BinaryTreeNode> outStack = new LinkedList<>();
        stack.addFirst(node);

        while (CollectionUtils.isNotEmpty(stack)) {
            BinaryTreeNode current = stack.removeFirst();
            outStack.addFirst(current);

            if (current.getLeft() != null) {
                stack.addFirst(current.getLeft());
            }
            if (current.getRight() != null) {
                stack.addFirst(current.getRight());
            }
        }

        while (CollectionUtils.isNotEmpty(outStack)) {
            System.out.print(outStack.removeFirst().getNode().getValue() + " ");
        }
    }

    /**
     * 递归后序遍历二叉树
     *
     * @param node
     */
    public void postOrderTraversalByRecursion(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        this.postOrderTraversalByRecursion(node.getLeft());
        this.postOrderTraversalByRecursion(node.getRight());
        System.out.print(node.getNode().getValue() + " ");
    }

    /**
     * 分层遍历二叉树（按层次从上往下，从左往右）
     *
     * @param node
     */
    public void levelTraversal(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addFirst(node);
        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode current = queue.removeLast();
            System.out.print(current.getNode().getValue() + " ");

            if (current.getLeft() != null) {
                queue.addFirst(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.addFirst(current.getRight());
            }
        }
    }

    /**
     * 递归分层遍历二叉树
     * 创建一个List<List<BinaryTreeNode>>，每一层的元素对应一个List<BinaryTreeNode>。通过递归将每一层的元素填到对应List<BinaryTreeNode>，然后分层打印
     *
     * @param node
     */
    public void levelTraversalByRecursion(BinaryTreeNode node) {
        List<List<BinaryTreeNode>> nodesForLevel = new ArrayList<>();
        this.levelTraversalByRecursionInternal(node, 0, nodesForLevel);
        for (List<BinaryTreeNode> nodes : nodesForLevel) {
            for (BinaryTreeNode n : nodes) {
                System.out.print(n.getNode().getValue() + " ");
            }
        }
    }

    private void levelTraversalByRecursionInternal(BinaryTreeNode node, int level, List<List<BinaryTreeNode>> nodesForLevel) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return;
        }

        if (level >= nodesForLevel.size()) {
            nodesForLevel.add(new ArrayList<>());
        }

        nodesForLevel.get(level).add(node);
        this.levelTraversalByRecursionInternal(node.getLeft(), level + 1, nodesForLevel);
        this.levelTraversalByRecursionInternal(node.getRight(), level + 1, nodesForLevel);
    }

}
