package leecode.sort;

/**
 * 选择排序  稳定的排序算法(取决于此条件中间有无等于号 arrays[j] < arrays[minIndex])，现在此算法是稳定的
 *
 * 时间复杂度 O（n^2）
 * 空间复杂度 O（1）
 *
 * @author lyx
 * @date 2020/12/4 19:57
 */
public class SelectSort {

    public static int[] selectSort(int[] arrays){
        if (arrays.length > 0){
            for (int i = 0; i < arrays.length; i++) {
                int minIndex = i;
                for (int j = i; j < arrays.length; j++) {
                    if (arrays[j] < arrays[minIndex]){
                        minIndex = j;
                    }
                }
                if (minIndex != i){
                    int temp = arrays[i];
                    arrays[i] = arrays[minIndex];
                    arrays[minIndex] = temp;
                }
            }
        }
        return arrays;
    }


    public static void main(String[] args) {
        SelectSort.selectSort(new int[]{2,9,11,5,45,23,55,67,12,21});
    }


}
