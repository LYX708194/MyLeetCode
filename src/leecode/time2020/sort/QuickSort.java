package leecode.time2020.sort;

/**
 *
 * 快排
 *
 * @author lyx
 * @date 2020/11/9 13:53
 */
public class QuickSort {

    public void quickSort(int[] array,int left,int right){
        if (left < right){
            int pivot = partition(array,left,right);
            quickSort(array,left,pivot-1);
            quickSort(array, pivot+1, right);
        }
        System.out.println();
    }

    public int partition(int[] array,int left,int right){
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
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(new int[]{2,9,11,5,45,23,55,67,12,21},0,9);
        System.out.println();
    }

}
