package com.pxd.base.commons.tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by panxudong on 2019/11/18.
 * Description:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private int value;

    private TreeNode left;

    private TreeNode right;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append("value=" + this.value);
        if (this.left != null) {
            sb.append(", before=" + this.left.getValue());
        }
        if (this.right != null) {
            sb.append(", after=" + this.right.getValue());
        }
        sb.append("}");
        return sb.toString();
    }

}
