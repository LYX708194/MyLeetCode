package leetcode.sort;

import java.util.Stack;

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
    /**
     * 非递归实现栈
     */
    private static int[] quickSortOP(int[] array,int left,int right){
        Stack<Integer> stack = new Stack<>();
        int k;
        if (left < right){
            stack.push(left);
            stack.push(right);
            while (!stack.isEmpty()){
                //右边界，左边界
                int j = stack.pop(),i = stack.pop();
                k = partition(array,i,j);
                if (i < k - 1){
                    stack.push(i);
                    stack.push(k-1);
                }
                if (k + 1 < j){
                    stack.push(k+1);
                    stack.push(j);
                }
            }
        }
        return array;
    }


    public static void main(String[] args) {
        QuickSort.quickSortOP(new int[]{2,9,11,5,45,23,55,67,12,21},0,9);
    }

}
