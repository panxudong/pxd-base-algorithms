package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.TreeNode;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreePrinter;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/23.
 * Description: 求二叉树中两个结点的最低公共祖先结点
 */
@Builder
public class TreeLastParent {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        BinaryTreePrinter.print(root);

        BinaryTreeNode node_1 = BinaryTreeNode.builder().node(TreeNode.builder().value(8).isNull(false).build()).build();
        System.out.println("node_1:" + node_1.toString());
        BinaryTreeNode node_2 = BinaryTreeNode.builder().node(TreeNode.builder().value(9).isNull(false).build()).build();
        System.out.println("node_2:" + node_2.toString());

        TreeLastParent treeLastParent = TreeLastParent.builder().build();

        BinaryTreeNode parentNode = treeLastParent.getLastParent(root, node_1, node_2);
        System.out.println("tree last parent node:" + (parentNode == null ? "null" : parentNode.toString()));

        parentNode = treeLastParent.getLastParentByRecursion(root, node_1, node_2);
        System.out.println("tree last parent node by recursion:" + (parentNode == null ? "null" : parentNode.toString()));

        parentNode = treeLastParent.getLastParentByRecursion_2(root, node_1, node_2);
        System.out.println("tree last parent node by recursion 2:" + (parentNode == null ? "null" : parentNode.toString()));
    }

    /**
     * 迭代解法
     * 思路：
     * 1.获取从根结点到node_1路径上的所有结点，获取从根结点到node_2路径上的所有结点
     * 2.比较两个路径，第一个不相同的结点之前的结点是最低公共祖先结点
     *
     * @param root
     * @param node_1
     * @param node_2
     * @return
     */
    public BinaryTreeNode getLastParent(BinaryTreeNode root, BinaryTreeNode node_1, BinaryTreeNode node_2) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }
        if (node_1 == null || node_2 == null) {
            return null;
        }
        if (!this.isInTree(root, node_1) || !this.isInTree(root, node_2)) {
            return null;
        }

        LinkedList<BinaryTreeNode> path_1 = new LinkedList<>();
        this.getTreePath(root, node_1, path_1);

        LinkedList<BinaryTreeNode> path_2 = new LinkedList<>();
        this.getTreePath(root, node_2, path_2);

        BinaryTreeNode previous = null;
        while (CollectionUtils.isNotEmpty(path_1) && CollectionUtils.isNotEmpty(path_2)) {
            BinaryTreeNode temp_1 = path_1.removeFirst(), temp_2 = path_2.removeFirst();

            if (temp_1.getNode().getValue() != temp_2.getNode().getValue()) {
                break;
            }

            previous = temp_1;
        }

        return previous;
    }

    /**
     * 递归解法
     * 思路：
     * 1.判断两个结点是否在此树中，如果有一个或者两个不在，返回null
     * 2.判断node_1是否在左子树中，如果在，则判断node_2是否在右子树中，如果在则返回根结点，如果不在，递归调用查找node_1、node_2在左子树的最低公共祖先结点
     * 3.判断node_1是否在右子树中，如果在，则判断node_2是否在左子树中，如果在则返回根结点，如果不在，递归调用查找node_1、node_2在右子树的最低公共祖先结点
     *
     * @param root
     * @param node_1
     * @param node_2
     * @return
     */
    public BinaryTreeNode getLastParentByRecursion(BinaryTreeNode root, BinaryTreeNode node_1, BinaryTreeNode node_2) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }
        if (node_1 == null || node_2 == null) {
            return null;
        }
        if (!this.isInTree(root, node_1) || !this.isInTree(root, node_2)) {
            return null;
        }

        return this.getLastParentByRecursionInternal(root, node_1, node_2);
    }

    public BinaryTreeNode getLastParentByRecursionInternal(BinaryTreeNode root, BinaryTreeNode node_1, BinaryTreeNode node_2) {
        if (this.isInTree(root.getLeft(), node_1)) {
            if (this.isInTree(root.getRight(), node_2)) {
                return root;
            } else {
                return getLastParentByRecursionInternal(root.getLeft(), node_1, node_2);
            }
        } else {
            if (this.isInTree(root.getLeft(), node_2)) {
                return root;
            } else {
                return getLastParentByRecursionInternal(root.getRight(), node_1, node_2);
            }
        }
    }

    /**
     * 递归解法
     * 思路：
     * 1.判断两个结点是否在此树中，如果有一个或者两个不在，返回null
     * 2.判断两个结点是否与根结点相等，如果相等则返回root
     * 3.判断node_1、node_2是否在左子树中
     * 4.判断node_1、node_2是否在右子树中
     * 5.如果返回都不为空，则说明一个结点在左子树中，一个结点在右子树中，则返回root；如果返回都为空，则返回空；
     * <p>如果返回的一个为空，另一个不为空，说明两种情况，
     * <p>一种是两个结点都在其中的一个子树中，则返回不为空的值，
     * <p>一种是一个在其中的一个子树中，另一个不在root这棵树中，则返回不为空的值，说明在root这棵树中查找到一个结点
     *
     * @param root
     * @param node_1
     * @param node_2
     * @return
     */
    public BinaryTreeNode getLastParentByRecursion_2(BinaryTreeNode root, BinaryTreeNode node_1, BinaryTreeNode node_2) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }
        if (node_1 == null || node_2 == null) {
            return root;
        }
        if (!this.isInTree(root, node_1) || !this.isInTree(root, node_2)) {
            return null;
        }

        return this.getLastParentByRecursion_2_internal(root, node_1, node_2);
    }

    public BinaryTreeNode getLastParentByRecursion_2_internal(BinaryTreeNode root, BinaryTreeNode node_1, BinaryTreeNode node_2) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }

        if (root.getNode().getValue() == node_1.getNode().getValue() || root.getNode().getValue() == node_2.getNode().getValue()) {
            return root;
        }

        BinaryTreeNode result_1 = this.getLastParentByRecursion_2_internal(root.getLeft(), node_1, node_2);
        BinaryTreeNode result_2 = this.getLastParentByRecursion_2_internal(root.getRight(), node_1, node_2);

        return result_1 == null ? result_2 : (result_2 == null ? result_1 : root);
    }

    public void getTreePath(BinaryTreeNode root, BinaryTreeNode node, LinkedList<BinaryTreeNode> path) {
        if ((root == null || root.getNode() == null || root.getNode().isNull())
                || (node == null || node.getNode() == null || node.getNode().isNull())) {
            return;
        }

        if (root.getNode().getValue() == node.getNode().getValue()) {
            path.addFirst(root);
            return;
        }

        int size = path.size();

        this.getTreePath(root.getLeft(), node, path);
        int sizeNew = path.size();
        if (sizeNew > size) {
            path.addFirst(root);
            return;
        }

        this.getTreePath(root.getRight(), node, path);
        sizeNew = path.size();
        if (sizeNew > size) {
            path.addFirst(root);
            return;
        }
    }

    public boolean isInTree(BinaryTreeNode root, BinaryTreeNode node) {
        if ((root == null || root.getNode() == null || root.getNode().isNull())
                || (node == null || node.getNode() == null || node.getNode().isNull())) {
            return false;
        }

        if (root.getNode().getValue() == node.getNode().getValue()) {
            return true;
        }

        return this.isInTree(root.getLeft(), node) || this.isInTree(root.getRight(), node);
    }

}
