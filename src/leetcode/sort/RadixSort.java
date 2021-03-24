package leetcode.sort;

import java.util.ArrayList;

/**
 *
 * 基数排序 稳定
 * 时间复杂度 O(nk)
 * 空间复杂度 O(n+k)
 *
 * @author lyx
 * @date 2021/3/24 16:40
 */
public class RadixSort {

    public static int[] radixSort(int[] array){
        if (array == null || array.length < 2)  return array;
        int max = array[0];
        int len = array.length;
        for (int i = 1; i < len; i++) {
            max = Math.max(max,array[i]);
        }
        int maxDigit = 0;
        //计算最大数有多少位
        while (max != 0){
            max /= 10;
            maxDigit++;
        }
        int mod = 10,div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }
        for (int i = 0; i < maxDigit; i++,mod *= 10,div *= 10) {
            for (int j = 0; j < len; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    array[index++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
        return array;
    }

    public static void main(String[] args) {
        RadixSort.radixSort(new int[]{2,9,11,5,45,23,2,55,67,12,21});
    }

}
