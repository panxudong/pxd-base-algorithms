package com.pxd.base.algorithms.commons.tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by panxudong on 2019/11/19.
 * Description:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private int value;

    private boolean isNull;

    @Override
    public String toString() {
        if (this.isNull) {
            return "value=null";
        } else {
            return "value=" + this.value;
        }
    }
}