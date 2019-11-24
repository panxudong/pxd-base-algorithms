package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.TreeNode;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.commons.tree.binary.BinaryTreePrinter;
import lombok.Builder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 前序遍历序列和中序遍历序列重建二叉树
 * Reference: https://www.cnblogs.com/deltadeblog/p/9296469.html
 */
@Builder
public class TreeReconstruct {

    public static void main(String[] args) {
        List<Integer> preOrder = Arrays.asList(1, 2, 4, 8, 9, 5, 10, 3, 6, 7);
        List<Integer> inOrder = Arrays.asList(8, 4, 9, 2, 10, 5, 1, 6, 3, 7);

        TreeReconstruct treeReconstruct = TreeReconstruct.builder().build();
        BinaryTreeNode root = treeReconstruct.reconstruct(preOrder, inOrder, 0, preOrder.size() - 1, 0, inOrder.size() - 1);
        BinaryTreePrinter.print(root);
    }

    /**
     * 递归解法
     * 思路：
     * 1.二叉树的前序遍历先访问根结点，其次遍历根结点的左子树，然后遍历根结点的右子树；中序遍历，先遍历左子树，然后遍历根结点，最后遍历右子树
     * 2.因此中序遍历序列被根结点分为两部分：根结点之前的部分为左子树结点中序序列，根结点之后的为右子树结点中序序列
     * 实现：
     * 假设前序遍历序列为：preOrder，起点为preStart，终点为preEnd
     * <p>中序遍历为：inOrder，起点为inStart，终点为inEnd
     * <p>
     * 1.先将前序遍历序列的第一个值创建为结点，此结点为根结点
     * 2.以inStart开始，循环中序遍历序列寻找与根结点值相同的点，下标为i，此点以左为根结点的左子树的中序遍历结果，以右为根结点的右子树的中序遍历结果
     * 3.递归处理左子树、右子树
     * <p>左子树：
     * <p><p>前序遍历为：subList(preOrder, preStart+1, preStart+(i-inStart))
     * <p><p>中序遍历为：subList(inOrder, inStart, i-1)
     * <p>右子树：
     * <p><p>前序遍历为：subList(preOrder, preStart+(i-inStart)+1, preEnd)
     * <p><p>中序遍历为：subList(inOrder, i+1, inEnd)
     * <p>
     * 终止条件：
     * 1.preStart > preEnd：表明这是一个空结点
     * 2.preStart == preEnd: 表明这是一个叶子结点
     *
     * @param preOrder
     * @param inOrder
     * @param preStart
     * @param preEnd
     * @param inStart
     * @param inEnd
     * @return
     */
    public BinaryTreeNode reconstruct(List<Integer> preOrder, List<Integer> inOrder, int preStart, int preEnd,
                                      int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return BinaryTreeNode.builder().node(TreeNode.builder().value(preOrder.get(preStart)).isNull(false).build()).build();
        }

        BinaryTreeNode root = BinaryTreeNode.builder().node(TreeNode.builder().value(preOrder.get(preStart)).isNull(false).build()).build();
        int i = inStart;
        while (inOrder.get(i).intValue() != root.getNode().getValue()) {
            i++;
        }

        BinaryTreeNode left = this.reconstruct(preOrder, inOrder, preStart + 1, preStart + i - inStart, inStart, i - 1);
        BinaryTreeNode right = this.reconstruct(preOrder, inOrder, preStart + i - inStart + 1, preEnd, i + 1, inEnd);

        root.setLeft(left);
        root.setRight(right);
        return root;
    }

}