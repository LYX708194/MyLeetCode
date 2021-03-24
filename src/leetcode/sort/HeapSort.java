package leetcode.sort;

/**
 *
 * 堆排序   不稳定
 * 时间复杂度 O（nlogn）
 * 空间复杂度 O（1）
 *
 * @author lyx
 * @date 2021/3/24 15:16
 */
public class HeapSort {


    public static int[] heapSort(int[] arrays){
        int len = arrays.length;
        //初始化大顶堆
        for (int i = (len/2 - 1);i >= 0;i--) {
            heapAdjust(arrays,i,len);
        }
        for (int i = len - 1; i > 0; i--) {
            int temp = arrays[i];
            arrays[i] = arrays[0];
            arrays[0] = temp;
            //最后一个已经是最大的了，长度改为i
            heapAdjust(arrays,0,i);
        }
        return arrays;
    }
    public static void heapAdjust(int[] arrays,int index,int length){
        //保存当前节点的下标
        int max = index;
        //当前节点的左右子节点的下标
        int lChild = index * 2,rChild = lChild + 1;
        //判断左右子节点是否比当前节点大
        if (length > lChild && arrays[max] < arrays[lChild])    max = lChild;
        if (length > rChild && arrays[max] < arrays[rChild])    max = rChild;
        //如果左右子节点比当前节点大，交换较大的，然后继续调整堆
        if (max != index){
            int temp = arrays[index];
            arrays[index] = arrays[max];
            arrays[max] = temp;
            heapAdjust(arrays,max,length);
        }
    }


    public static void main(String[] args) {
        HeapSort.heapSort(new int[]{2,9,11,5,45,23,55,67,12,21});
    }



}
