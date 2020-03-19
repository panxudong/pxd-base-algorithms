package com.pxd.base.algorithms.tree.binary;

import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeBuilder;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeCounter;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreeNode;
import com.pxd.base.algorithms.commons.tree.binary.BinaryTreePrinter;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/19.
 * Description: 树基本操作（创建，打印，计数）
 * 树结构：
 * <p>           1
 * <p>      2         3
 * <p>  4      5   6     7
 * <p>8  9  10
 * Reference: http://blog.csdn.net/zhongwen7710/article/details/39300339
 */
public class BaseOperation {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeBuilder.newInstance().appendNodes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).build();
        BinaryTreePrinter.print(root);
        System.out.println("size:" + BinaryTreeCounter.count(root));
    }

}
