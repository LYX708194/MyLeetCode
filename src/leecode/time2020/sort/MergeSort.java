package leecode.time2020.sort;

import java.util.Arrays;

/**
 *
 * 归并排序  稳定的排序算法
 *
 * 时间复杂度 O(n log n)
 * 空间复杂度 O（n）
 *
 * @author lyx
 * @date 2020/12/4 20:09
 */
public class MergeSort {

    public static int[] mergeSort(int[] arrays){
        if (arrays.length < 2){
            return arrays;
        }
        int mid = arrays.length / 2;
        int[] left = Arrays.copyOfRange(arrays,0,mid);
        int[] right = Arrays.copyOfRange(arrays,mid,arrays.length);
        return merge(mergeSort(left),mergeSort(right));
    }

    public static int[] merge(int[] left,int[] right){
        int[] result = new int[left.length + right.length];
        for (int index = 0,i = 0,j = 0; index < result.length; index++) {
            if (i >= left.length){
                result[index] = right[j++];
            }else if (j >= right.length){
                result[index] = left[i++];
            }else if (left[i] > right[j]){
                result[index] = right[j++];
            }else{
                result[index] = left[i++];
            }
        }
        return result;
    }

    /**
     * 第二种实现方法
     */
    public static int[] mergeSort2(int[] arrays,int left,int right){
        if (left == right){
            return new int[]{arrays[left]};
        }
        //这样取半可以防止溢出
        int mid = left + (right - left) / 2;
        int[] leftArr = mergeSort2(arrays,left,mid);
        int[] rightArr = mergeSort2(arrays,mid+1,right);
        return merge(leftArr,rightArr);
    }


    public static void main(String[] args) {
        int[] arr1 = MergeSort.mergeSort(new int[]{2,9,11,5,45,23,55,67,12,21});
        int[] arr2 = MergeSort.mergeSort2(new int[]{2,9,11,5,45,23,55,67,12,21},0,9);
        System.out.println();
    }

}
