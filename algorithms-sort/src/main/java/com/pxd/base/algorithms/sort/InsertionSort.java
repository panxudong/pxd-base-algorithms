package com.pxd.base.algorithms.sort;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 插入排序
 * Explain: 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
 * <p><p><p>因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间
 * Time complexity: O(n^2)
 * Spatial complexity: O(1)
 */
@Builder
public class InsertionSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        InsertionSort insertionSort = InsertionSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(insertionSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.从第一个元素开始，该元素可以认为已经被排序
     * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5.将新元素插入到该位置后
     * 6.重复步骤2~5
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];

            int prefixIndex = i;
            while (prefixIndex >= 0 && current < array[prefixIndex]) {
                array[prefixIndex + 1] = array[prefixIndex];
                prefixIndex--;
            }

            array[prefixIndex + 1] = current;
        }

        return array;
    }

}