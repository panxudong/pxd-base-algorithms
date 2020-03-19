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
 * Description: 求二叉树的镜像，判断两棵二叉树是否互为镜像
 */
@Builder
public class TreeMirror {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        System.out.print("root:");
        BinaryTreePrinter.print(root);

        TreeMirror treeMirror = TreeMirror.builder().build();

        BinaryTreeNode mirror_1 = treeMirror.getMirror(root);
        System.out.print("root's mirror:");
        BinaryTreePrinter.print(mirror_1);

        BinaryTreeNode mirror_2 = treeMirror.getMirrorNew(mirror_1);
        System.out.print("mirror_1's mirror:");
        BinaryTreePrinter.print(mirror_2);

        BinaryTreeNode mirror_3 = treeMirror.getMirrorByRecursion(mirror_2);
        System.out.print("mirror_2's mirror:");
        BinaryTreePrinter.print(mirror_3);

        BinaryTreeNode mirror_4 = treeMirror.getMirrorNewByRecursion(mirror_3);
        System.out.print("mirror_3's mirror:");
        BinaryTreePrinter.print(mirror_4);

        System.out.println("mirror_1 and mirror_4 is mirror:" + treeMirror.isMirror(mirror_1, mirror_4));
        System.out.println("mirror_1 and mirror_4 is mirror by recursion:" + treeMirror.isMirrorByRecursion(mirror_1, mirror_4));
    }

    /**
     * 判断两棵二叉树是否是镜像，迭代解法
     *
     * @param mirror_1
     * @param mirror_2
     * @return
     */
    public boolean isMirror(BinaryTreeNode mirror_1, BinaryTreeNode mirror_2) {
        LinkedList<BinaryTreeNode> queue_1 = new LinkedList<>();
        LinkedList<BinaryTreeNode> queue_2 = new LinkedList<>();

        queue_1.addFirst(mirror_1);
        queue_2.addFirst(mirror_2);

        while (CollectionUtils.isNotEmpty(queue_1) && CollectionUtils.isNotEmpty(queue_2)) {
            BinaryTreeNode node_1 = queue_1.removeLast();
            BinaryTreeNode node_2 = queue_2.removeLast();

            if (node_1 == null && node_2 == null) {
                continue;
            }
            if (node_1 == null || node_2 == null) {
                return false;
            }
            if (node_1.getNode().getValue() != node_2.getNode().getValue()) {
                return false;
            }

            queue_1.addFirst(node_1.getLeft());
            queue_1.addFirst(node_1.getRight());
            queue_2.addFirst(node_2.getRight());
            queue_2.addFirst(node_2.getLeft());
        }

        return true;
    }

    /**
     * 判断两棵二叉树是否是镜像，递归解法
     *
     * @param mirror_1
     * @param mirror_2
     * @return
     */
    public boolean isMirrorByRecursion(BinaryTreeNode mirror_1, BinaryTreeNode mirror_2) {
        if (mirror_1 == null && mirror_2 == null) {
            return true;
        }

        if (mirror_1 == null || mirror_2 == null) {
            return false;
        }

        if (mirror_1.getNode().getValue() != mirror_2.getNode().getValue()) {
            return false;
        }

        return this.isMirrorByRecursion(mirror_1.getLeft(), mirror_2.getRight())
                && this.isMirrorByRecursion(mirror_1.getRight(), mirror_2.getLeft());
    }

    /**
     * 求二叉树的镜像，迭代解法，破坏原树结构
     * 思路：
     * 1.如果根为空，返回null
     * 2.如果不为空，则创建栈或者队列，并将根结点推入
     * 3.循环判断集合是否为空，如果不为空，则取出当前结点，并将左子树、右子树分别推入栈或者队列，然后将根结点的left指向右子树，right指向左子树
     * <p>
     * Note：此题用栈或者队列都可以
     *
     * @param root
     * @return
     */
    public BinaryTreeNode getMirror(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();

            if (node.getLeft() != null) {
                queue.addFirst(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.addFirst(node.getRight());
            }

            BinaryTreeNode temp = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(temp);
        }

        return root;
    }

    /**
     * 求二叉树的镜像，迭代解法，不破坏原树结构，新创建一棵树
     * 思路：同上
     *
     * @param root
     * @return
     */
    public BinaryTreeNode getMirrorNew(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        LinkedList<BinaryTreeNode> queueNew = new LinkedList<>();

        BinaryTreeNode rootNew = BinaryTreeNode.builder()
                .node(TreeNode.builder().value(root.getNode().getValue()).isNull(root.getNode().isNull()).build())
                .left(root.getRight())
                .right(root.getLeft()).build();

        queue.addFirst(root);
        queueNew.addFirst(rootNew);
        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode node = queue.removeLast();
            BinaryTreeNode nodeNew = queueNew.removeLast();

            if (node.getLeft() != null) {
                queue.addFirst(node.getLeft());

                BinaryTreeNode rightNew = BinaryTreeNode.builder()
                        .node(TreeNode.builder()
                                .value(node.getLeft().getNode().getValue())
                                .isNull(node.getLeft().getNode().isNull()).build())
                        .build();
                nodeNew.setRight(rightNew);
                queueNew.addFirst(nodeNew.getRight());
            }
            if (node.getRight() != null) {
                queue.addFirst(node.getRight());

                BinaryTreeNode leftNew = BinaryTreeNode.builder()
                        .node(TreeNode.builder()
                                .value(node.getRight().getNode().getValue())
                                .isNull(node.getRight().getNode().isNull()).build())
                        .build();
                nodeNew.setLeft(leftNew);
                queueNew.addFirst(nodeNew.getLeft());
            }
        }

        return rootNew;
    }

    /**
     * 求二叉树的镜像，递归解法，破坏原树结构
     * 思路：如果根为空，返回null；如果不为空，则将左子树、右子树分别求镜像，然后将根结点的left指向右子树，right指向左子树
     *
     * @param root
     * @return
     */
    public BinaryTreeNode getMirrorByRecursion(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }

        BinaryTreeNode left = this.getMirrorByRecursion(root.getLeft());
        BinaryTreeNode right = this.getMirrorByRecursion(root.getRight());

        root.setRight(left);
        root.setLeft(right);
        return root;
    }

    /**
     * 求二叉树的镜像，递归解法，不破坏原树结构，新创建一棵树
     * 思路：同上
     *
     * @param root
     * @return
     */
    public BinaryTreeNode getMirrorNewByRecursion(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return null;
        }

        BinaryTreeNode left = this.getMirrorNewByRecursion(root.getLeft());
        BinaryTreeNode right = this.getMirrorNewByRecursion(root.getRight());

        BinaryTreeNode rootNew = BinaryTreeNode.builder()
                .node(TreeNode.builder().value(root.getNode().getValue()).isNull(root.getNode().isNull()).build())
                .left(right)
                .right(left).build();
        return rootNew;
    }

}
