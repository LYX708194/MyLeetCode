package leetcode.sort;

import java.util.Arrays;

/**
 *
 * 计数排序  稳定  只能对整数排序
 * 时间复杂度 O(n+k)
 * 空间复杂度 O(k)
 *
 * @author lyx
 * @date 2021/3/24 16:01
 */
public class CountingSort {

    public static int[] countingSort(int[] array){
        if(array.length == 0){
            return array;
        }
        int bias ,min = array[0],max = array[0];
        //找出最小值和最大值
        for(int i = 0;i < array.length;i++){
            if(array[i] < min){
                min = array[i];
            }
            if(array[i] > max){
                max = array[i];
            }
        }
        //偏差
        bias = 0 - min;
        //新开辟一个数组
        int[] bucket = new int[max - min +1];
        //数据初始化为0
        Arrays.fill(bucket, 0);
        for(int i = 0;i < array.length;i++){
            bucket[array[i] + bias] += 1;
        }
        int index = 0;
        for(int i = 0;i < bucket.length;i++){
            int len = bucket[i];
            while(len > 0){
                array[index++] = i - bias;
                len--;
            }
        }
        return array;
    }


    public static void main(String[] args) {
        CountingSort.countingSort(new int[]{2,9,11,5,45,23,2,55,67,12,21});
    }

}
