package com.pxd.base.algorithms.sort;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 冒泡排序
 * Explain: 重复地走访要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。"浮"到数列的顶端
 * Time complexity: O(n^2)
 * Spatial complexity: O(1)
 */
@Builder
public class BubbleSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        BubbleSort bubbleSort = BubbleSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(bubbleSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数
     * 3.针对所有的元素重复以上的步骤，除了最后一个
     * 4.重复步骤1~3，直到排序完成
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    array[j + 1] = array[j + 1] ^ array[j];
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j + 1] ^ array[j];
                }
            }
        }
        return array;
    }

}