package com.pxd.base.algorithms.sort;

import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 归并排序
 * Explain: 采用分治法（Divide and Conquer）的一个非常典型的应用。归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 * <p><p><p>即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并
 * Time complexity: O(n * ㏒ n)
 * Spatial complexity: O(n)
 */
@Builder
public class MergeSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        MergeSort mergeSort = MergeSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(mergeSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.把长度为n的输入序列分成两个长度为n/2的子序列
     * 2.对这两个子序列分别采用归并排序
     * 3.将两个排序好的子序列合并成一个最终的排序序列
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        int middle = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);
        return merge(this.sort(left), this.sort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

}