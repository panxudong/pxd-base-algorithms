package com.pxd.base.sort.base;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 快速排序
 * Explain: 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序
 * Time complexity: O(n * ㏒ n)
 * Spatial complexity: O(㏒ n) ~ O(n)
 */
@Builder
public class QuickSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        QuickSort quickSort = QuickSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(quickSort.sort(array, 0, array.length - 1));
    }

    /**
     * 升序排序
     * Steps:
     * 1.从数列中挑出一个元素，称为 “基准”（pivot）
     * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * <p>在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作
     * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array, int start, int end) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        int pivot = array[start];
        int i = start, j = end;
        while (i < j) {
            while (i < j && array[j] > pivot) {
                j--;
            }
            while (i < j && array[i] < pivot) {
                i++;
            }
            if (array[i] == array[j] && i <= j) {
                i++;
            } else {
                array[i] ^= array[j];
                array[j] ^= array[i];
                array[i] ^= array[j];
            }
        }
        if (i - 1 > start) {
            array = sort(array, start, i - 1);
        }
        if (j + 1 < end) {
            array = sort(array, j + 1, end);
        }
        return array;
    }

}