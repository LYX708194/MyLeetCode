package leecode.sort;

/**
 *
 * 插入排序  稳定的排序算法
 *
 * 平均时间复杂度 O（n^2） 最好O（n），最坏O（n^2）
 * 空间复杂度 O（1）
 *
 * @author lyx
 * @date 2020/12/4 20:01
 */
public class InsertSort {

    public static int[] insertSort(int[] arrays){
        if (arrays.length > 0){
            for (int i = 0; i < arrays.length - 1; i++) {
                int cur = arrays[i+1];
                int index = i;
                while (index >= 0 && cur < arrays[index]){
                    arrays[index + 1] = arrays[index];
                    index --;
                }
                arrays[index + 1] = cur;
            }
        }
        return arrays;
    }


    public static void main(String[] args) {
        InsertSort.insertSort(new int[]{2,9,11,5,45,23,55,67,12,21});
    }
}
