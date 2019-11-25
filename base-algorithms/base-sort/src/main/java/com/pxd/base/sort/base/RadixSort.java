package com.pxd.base.sort.base;

import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 基数排序
 * Explain: 按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。
 * <p><p><p>最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。基数排序基于分别排序，分别收集，所以是稳定的。
 * <p><p><p><p>从低位开始比较，一次比较会将高位相同的元素比较出相对大小；然后收集的时候，高位相同的元素顺序就确定了，然后再比较更高位，
 * <p><p><p><p>譬如51，53，在第一次比较的时候相对位置已经确定了，51在53的左边，
 * <p><p><p><p>譬如56，65，在第一次比较的时候无法确定先后位置，但在比较第二次的时候，56会在65的左边，相对位置可以确定，
 * <p><p><p><p>譬如132，32，在第一次和第二次比较的时候无法确定先后位置，但在比较第三次的时候，32会在132的左边，相对位置可以确定，
 * <p><p><p><p>通过一次次比较，直至最高位，至此所有元素比较完成
 * Time complexity: O(n ㏒(r) m) r为所采取的基数，m为堆数，n为数组长度
 * Spatial complexity: O(r*n + m)
 * Reference: https://www.jianshu.com/p/6b8d1a511788 https://www.runoob.com/w3cnote/radix-sort.html
 */
@Builder
public class RadixSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        RadixSort radixSort = RadixSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(radixSort.sort(array));
    }

    /**
     * 升序排序
     * Steps:
     * 1.取得数组中的最大数，并取得位数
     * 2.arr为原始数组，从最低位开始取每个位组成radix数组；
     * 3.对radix进行计数排序（利用计数排序适用于小范围数的特点）
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                continue;
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }

        int mod = 10, div = 1;
        int[][] bucketList = new int[10][array.length];
        int[] bucketLength = new int[10];
        Arrays.fill(bucketLength, 0);
        for (int i = 0; i < 10; i++) {
            Arrays.fill(bucketList[i], -1);
        }

        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList[num][bucketLength[num]] = array[j];
                bucketLength[num] = bucketLength[num] + 1;
            }

            int index = 0;
            for (int j = 0; j < bucketList.length; j++) {
                for (int k = 0; k < bucketLength[j]; k++) {
                    array[index++] = bucketList[j][k];
                }
                Arrays.fill(bucketList[j], -1);
                bucketLength[j] = 0;
            }
        }

        return array;
    }

}