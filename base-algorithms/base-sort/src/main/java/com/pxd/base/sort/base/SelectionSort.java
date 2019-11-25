package com.pxd.base.sort.base;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 选择排序，最稳定的排序算法之一
 * Explain: 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
 * Time complexity: O(n^2)
 * Spatial complexity: O(1)
 */
@Builder
public class SelectionSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        SelectionSort selectionSort = SelectionSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(selectionSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.初始状态：无序区为R[1..n]，有序区为空
     * 2.第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，
     * <p>将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区
     * 3.n-1趟结束，数组有序化了
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int min = Integer.MAX_VALUE, index = -1;
            for (int j = i; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    index = j;
                }
            }

            array[index] ^= array[i];
            array[i] ^= array[index];
            array[index] ^= array[i];
        }
        return array;
    }

}
