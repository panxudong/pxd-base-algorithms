package com.pxd.base.commons.tree.binary;

import com.pxd.base.commons.tree.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by panxudong on 2019/11/18.
 * Description: 二叉树节点
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinaryTreeNode {

    private TreeNode node;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(this.node.toString());
        if (this.left != null) {
            sb.append(", left=" + this.left.getNode().getValue());
        }
        if (this.right != null) {
            sb.append(", right=" + this.right.getNode().getValue());
        }
        sb.append("}");
        return sb.toString();
    }

}