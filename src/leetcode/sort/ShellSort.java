package leetcode.sort;

/**
 *
 * 希尔排序 缩小增量排序  不稳定
 * 时间复杂度 O（ nlogn）
 * 空间复杂度 O（1）
 *
 * @author lyx
 * @date 2021/3/24 14:48
 */
public class ShellSort {


    public static int[] shellSort(int[] arrays){
        int len = arrays.length;
        if (arrays.length > 0){
            int gap = len / 2;
            while (gap > 0){
                //按照增量分组进行排序
                for (int i = gap; i < len; i++) {
                    int temp = arrays[i];
                    int index = i - gap;
                    while (index >= 0 && arrays[index] > temp){
                        arrays[index + gap] = arrays[index];
                        index -= gap;
                    }
                    arrays[index + gap] = temp;
                }
                gap /= 2;
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        ShellSort.shellSort(new int[]{2,9,11,5,45,23,55,67,12,21});
    }


}
