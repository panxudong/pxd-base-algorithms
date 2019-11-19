package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/19.
 * Description: 求二叉树的深度
 */
@Builder
public class TreeDepth {

    public static void main(String[] args) {
        BinaryTreeNode node = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeCount treeCount = TreeCount.builder().build();
        System.out.println("depth:" + treeCount.getTreeNumber(node));
        System.out.println("depth by recursion:" + treeCount.getTreeNumberByRecursion(node));
    }

    /**
     * 非递归计算树的深度
     *
     * @param node
     * @return
     */
    public int getTreeDepth(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return 0;
        }
        int depth = 1;
        return depth;
    }

}
