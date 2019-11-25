package com.pxd.base.sort.base;

import lombok.Builder;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 希尔排序（缩小增量排序）
 * Explain: 把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * Time complexity: O(n * ㏒ n)
 * Spatial complexity: O(1)
 */
@Builder
public class ShellSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        ShellSort shellSort = ShellSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(shellSort.sort(array));
    }

    /**
     * 升序排序
     * <p>
     * 设置初始增量：gap=length/2
     * 缩小增量：gap=gap/2
     * 增量序列为：t1，t2，…，tk，其中i<j，ti>tj，tk=1
     * <p>
     * Steps:
     * 1.选择一个增量序列tm
     * 2.tm=n，对序列进行n趟排序。每趟排序，根据n，将待排序列分割成若干长度为n的子序列，分别对各子序列进行直接插入排序
     * 3.当i=k，即tk=1时（增量因子为1），对整个序列进行处理
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = array.length / 2; i >= 1; i = i / 2) {
            for (int j = 0; j < i; j++) {
                int current;
                for (int k = j + i; k < array.length; k += i) {
                    current = array[k];

                    int prefixIndex = k - i;
                    while (prefixIndex >= 0 && current < array[prefixIndex]) {
                        array[prefixIndex + i] = array[prefixIndex];
                        prefixIndex -= i;
                    }

                    array[prefixIndex + i] = current;
                }
            }
        }

        return array;
    }

}