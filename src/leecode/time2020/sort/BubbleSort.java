package leecode.time2020.sort;

/**
 * 冒泡排序  稳定的算法
 * 平均时间复杂度O（n^2）,最好O（n），最坏O（n^2）
 * 空间复杂度 O（1）
 * @author lyx
 * @date 2020/12/4 19:48
 */
public class BubbleSort {

    public static int[] bubbleSort(int[] arrays){
        if (arrays.length > 0){
            for (int i = 0; i < arrays.length; i++) {
                for (int j = 0; j < arrays.length - i - 1; j++) {
                    if (arrays[j] > arrays[j + 1]){
                        int temp = arrays[j];
                        arrays[j] = arrays[j+1];
                        arrays[j+1] = temp;
                    }
                }
            }
        }
        return arrays;
    }


    public static void main(String[] args) {
        BubbleSort.bubbleSort(new int[]{2,9,11,5,45,23,55,67,12,21});
    }

}
