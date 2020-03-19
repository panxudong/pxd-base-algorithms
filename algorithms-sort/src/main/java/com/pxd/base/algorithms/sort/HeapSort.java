package com.pxd.base.algorithms.sort;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 堆排序
 * Explain: 利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点
 * Time complexity: O(n * ㏒ n)
 * Spatial complexity: O(1)
 * Reference: https://www.jianshu.com/p/6b526aa481b1
 */
@Builder
public class HeapSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        HeapSort quickSort = HeapSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(quickSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区
     * 2.将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]
     * 3.由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，
     * <p>得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        this.createMaxHeap(array);
        int length = array.length;
        while (length > 0) {
            array[0] ^= array[length - 1];
            array[length - 1] ^= array[0];
            array[0] ^= array[length - 1];

            length--;
            this.adjustHeap(array, 0, length);
        }
        return array;
    }

    private void createMaxHeap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            this.adjustHeap(array, i, array.length);
        }
    }

    private void adjustHeap(int[] array, int index, int length) {
        int maxIndex = index;
        if (index * 2 + 1 < length && array[index * 2 + 1] > array[maxIndex]) {
            maxIndex = index * 2 + 1;
        }
        if (index * 2 + 2 < length && array[index * 2 + 2] > array[maxIndex]) {
            maxIndex = index * 2 + 2;
        }
        if (maxIndex != index) {
            array[maxIndex] ^= array[index];
            array[index] ^= array[maxIndex];
            array[maxIndex] ^= array[index];
            this.adjustHeap(array, maxIndex, length);
        }
    }

}