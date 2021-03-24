package leetcode.sort;

import java.util.ArrayList;

/**
 *
 * 桶排序 稳定
 *
 * 时间复杂度 O(n+k) 最佳 0(n+k) 最差 O(n^2)
 * 空间复杂度 O(n+k)
 *
 * @author lyx
 * @date 2021/3/24 16:10
 */
public class BucketSort {

    /**
     * @param bucketSize 桶中可以放多少个元素，数量不限
     * @return
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2)
            return array;
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max)
                max = array.get(i);
            if (array.get(i) < min)
                min = array.get(i);
        }
        //桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        //构造桶
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        //往桶里塞元素
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) {
                for (int j = 0; j < bucketArr.get(i).size(); j++)
                    resultArr.add(bucketArr.get(i).get(j));
            } else {
                if (bucketCount == 1)
                    bucketSize--;
                //桶内元素也用桶排序递归排序
                ArrayList<Integer> temp = bucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++)
                    resultArr.add(temp.get(j));
            }
        }
        return resultArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,9,11,5,45,23,55,67,12,21};
        ArrayList<Integer> list = new ArrayList<>();
        for (int a:arr) {
            list.add(a);
        }
        BucketSort.bucketSort(list,3);
    }

}
