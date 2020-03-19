package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreePrinter;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/23.
 * Description: 求二叉树中结点的最大距离 即二叉树中相距最远的两个结点之间的距离
 */
@Builder
public class TreeMaxDistance {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        BinaryTreePrinter.print(root);

        TreeMaxDistance treeMaxDistance = TreeMaxDistance.builder().build();
        System.out.println("max distance:" + treeMaxDistance.maxDistance(root).getDistance());
    }

    /**
     * 递归解法
     * 最大距离有两种情况：
     * 1.路径从左子树的最深结点，通过根结点，再到右子树的最深结点
     * 2.路径不穿过根结点，而是左子树或右子树的最大距离，取其大者
     * 距离如何计算：
     * 左子树的深度 + 右子树的深度
     * 思路：
     * 1.如果二叉树为空，深度为0，最大距离为0
     * 2.如果二叉树不为空，最大距离是Math.max(左子树最大距离，右子树最大距离，左子树深度+右子树深度)
     * 3.返回当前树的深度，最大距离
     *
     * @param root
     * @return
     */
    public DistanceResult maxDistance(BinaryTreeNode root) {
        if (root == null || root.getNode() == null || root.getNode().isNull()) {
            return DistanceResult.builder().depth(0).distance(0).build();
        }

        DistanceResult leftResult = this.maxDistance(root.getLeft());
        DistanceResult rightResult = this.maxDistance(root.getRight());

        return DistanceResult.builder().depth(Math.max(leftResult.getDepth(), rightResult.getDepth()) + 1)
                .distance(Math.max(leftResult.getDepth() + rightResult.getDepth(), Math.max(leftResult.getDistance(), rightResult.getDistance())))
                .build();
    }

    @Builder
    @Getter
    public static class DistanceResult {

        private int depth;

        private int distance;

    }

}