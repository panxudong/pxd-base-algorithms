package com.pxd.base.algorithms.sort;

import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 计数排序
 * Explain:
 * <p>1.核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数
 * <p>2.是一种稳定的排序算法。计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序
 * Time complexity: O(n + k)
 * Spatial complexity: O(n)
 * Reference: https://www.jianshu.com/p/86c2375246d7
 */
@Builder
public class CountingSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        CountingSort countingSort = CountingSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(countingSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.找出待排序的数组中最大和最小的元素
     * 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项
     * 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
     * 4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        int min = array[0], max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] - min]++;
        }

        for (int index = 0, i = 0; index < array.length; ) {
            if (bucket[i] == 0) {
                i++;
            } else {
                array[index++] = i + min;
                bucket[i]--;
            }
        }

        return array;
    }

}