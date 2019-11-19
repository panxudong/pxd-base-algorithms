package com.pxd.base.tree.binary.base;

import com.pxd.base.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.commons.tree.binary.BinaryTreeCounter;
import com.pxd.base.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.commons.tree.binary.BinaryTreePrinter;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/19.
 * Description: 树基本操作（创建，打印，计数）
 * 树结构：
 *            1
 *       2         3
 *   4      5   6     7
 * 8  9  10
 */
public class BaseOperation {

    public static void main(String[] args) {
        BinaryTreeNode node = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        BinaryTreePrinter.print(node);
        System.out.println("size:" + BinaryTreeCounter.count(node));
    }

}
