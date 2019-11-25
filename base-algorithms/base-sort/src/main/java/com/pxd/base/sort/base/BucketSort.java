package com.pxd.base.sort.base;

import lombok.Builder;

import java.util.Arrays;

/**
 * Created by panxudong on 2019/11/24.
 * Description: 桶排序，计数排序的升级版
 * Explain: 假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）
 * Time complexity: O(N+N*logN-N*logM) [对于N个待排数据，M个桶，平均每个桶[N/M]个数据的桶排序平均时间复杂度]；当N=M时，可以达到O(N)；N数组元素个数，M桶的个数
 * Spatial complexity: O(N+M)
 * Reference: https://www.jianshu.com/p/204ed43aec0c
 */
@Builder
public class BucketSort {

    public static void main(String[] args) {
        BaseOperation baseOperation = BaseOperation.builder().build();
        int[] array = baseOperation.build();

        BucketSort bucketSort = BucketSort.builder().build();
        System.out.print("sort array:");
        baseOperation.print(bucketSort.sort(array, 5));
    }

    /**
     * 升序排序
     * Steps:
     * 1.人为设置一个BucketSize，作为每个桶所能放置多少个不同数值（例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）
     * 2.遍历输入数据，并且把数据一个一个放到对应的桶里去
     * 3.对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序
     * 4.从不是空的桶里把排好序的数据拼接起来
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array, int bucketSize) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }

        int min = array[0], max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                continue;
            }
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        int bucketCount = (max - min) / bucketSize + 1;

        int[][] bucketArray = new int[bucketCount][array.length];
        int[] bucketLength = new int[bucketCount];
        Arrays.fill(bucketLength, 0);
        for (int i = 0; i < bucketArray.length; i++) {
            Arrays.fill(bucketArray[i], -1);
        }

        int[] result = new int[array.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                continue;
            }
            int bucketIndex = (array[i] - min) / bucketSize;
            bucketArray[bucketIndex][bucketLength[bucketIndex]] = array[i];
            bucketLength[bucketIndex] = ++bucketLength[bucketIndex];
        }

        int resultIndex = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) {
                for (int j = 0; j < bucketArray[i].length; j++) {
                    if (bucketArray[i][j] != -1) {
                        result[resultIndex++] = bucketArray[i][j];
                    }
                }
            } else {
                if (bucketCount == 1) {
                    bucketSize--;
                }
                int[] temp = this.sort(bucketArray[i], bucketSize);
                for (int j = 0; j < temp.length; j++) {
                    if (temp[j] != -1) {
                        result[resultIndex++] = temp[j];
                    }
                }
            }
        }

        return result;
    }

}