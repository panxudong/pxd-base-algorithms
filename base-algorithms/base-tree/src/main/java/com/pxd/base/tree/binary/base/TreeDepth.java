package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by panxudong on 2019/11/19.
 * Description: 求二叉树的深度
 */
@Builder
public class TreeDepth {

    public static void main(String[] args) {
        BinaryTreeNode node = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();

        TreeDepth treeDepth = TreeDepth.builder().build();
        System.out.println("depth:" + treeDepth.getTreeDepth(node));
        System.out.println("depth by recursion:" + treeDepth.getTreeDepthByRecursion(node));
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

        int depth = 0;
        int currentNodeSize = 1, nextNodeSize = 0;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addFirst(node);

        while (CollectionUtils.isNotEmpty(queue)) {
            BinaryTreeNode temp = queue.removeLast();
            currentNodeSize--;

            if (temp.getLeft() != null) {
                queue.addFirst(temp.getLeft());
                nextNodeSize++;
            }
            if (temp.getRight() != null) {
                queue.addFirst(temp.getRight());
                nextNodeSize++;
            }

            if (currentNodeSize == 0) {
                currentNodeSize = nextNodeSize;
                nextNodeSize = 0;
                depth++;
            }
        }

        return depth;
    }

    /**
     * 递归计算树的深度
     *
     * @param node
     * @return
     */
    public int getTreeDepthByRecursion(BinaryTreeNode node) {
        if (node == null || node.getNode() == null || node.getNode().isNull()) {
            return 0;
        }

        return 1 + Math.max(this.getTreeDepthByRecursion(node.getLeft()), this.getTreeDepthByRecursion(node.getRight()));
    }

}