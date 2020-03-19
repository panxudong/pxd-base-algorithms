package com.pxd.base.algorithms.sort;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 排序基本操作
 * Reference: https://www.cnblogs.com/guoyaohua/p/8600214.html
 */
@Builder
public class BaseOperation {

    public int[] build() {
        int[] array = new int[]{2, 8, 5, 0, 7, 6, 1, 9, 4, 3, 100, 50};
        System.out.print("array:");
        this.print(array);
        return array;
    }

    public void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

}