package leecode.sort;

/**
 *
 * 快排  不稳定的排序算法
 * 平均时间复杂度O（n * log n），最好O（n log n），最坏O（n^2）
 * 空间复杂度O（log n）
 *
 * @author lyx
 * @date 2020/11/9 13:53
 */
public class QuickSort {

    public static int[] quickSort(int[] array,int left,int right){
        if (left < right){
            int pivot = partition(array,left,right);
            quickSort(array,left,pivot-1);
            quickSort(array, pivot+1, right);
        }
        return array;
    }

    public static int partition(int[] array,int left,int right){
        int pivot = array[left];
        while (left < right){
            while ( left < right && array[right] >= pivot)  right--;
            array[left] = array[right];
            while ( left < right && array[left] <= pivot)   left++;
            array[right] = array[left];
        }
        array[left] = pivot;
        return left;
    }


    public static void main(String[] args) {
        QuickSort.quickSort(new int[]{2,9,11,5,45,23,55,67,12,21},0,9);
    }

}
