package com.pxd.base.commons.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panxudong on 2019/11/18.
 * Description:
 */
public class TreeBuilder {

    private List<TreeNode> nodes;

    public TreeBuilder() {
        this.nodes = new ArrayList<>();
    }

    public static final TreeBuilder newInstance() {
        return new TreeBuilder();
    }

    public TreeBuilder appendNode() {
        return this;
    }

}
