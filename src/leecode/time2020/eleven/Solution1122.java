package leecode.time2020.eleven;

import java.util.*;

/**
 * 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/14 10:01
 */
public class Solution1122 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //用k来作为是否完成排序的分隔符
        int k = 0;
        //对于arr2中每一个位置的数字，去arr1中遍历，从k开始，因为k之前是已经排序了的
        for (int i = 0; i < arr2.length; i++) {
            for (int j = k; j < arr1.length; j++) {
                if (arr1[j] == arr2[i]) {
                    swap(arr1, k, j);
                    k++;
                }
            }
        }
        //最后对没有出现的数字进行升序排序
        Arrays.sort(arr1,k,arr1.length);
        return arr1;
    }
    public void swap(int[] arr,int i,int j){
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 自定义排序规则
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num:arr1)  list.add(num);
        for (int i = 0; i < arr2.length; i++)   map.put(arr2[i],i);
        Collections.sort(list,(x,y)->{
            //自定义排序，如果其中一个存在，则用两者在map中的相对位置进行排序，因为数组个数不超过1000个，则可以给一个默认值1001
            if (map.containsKey(x) || map.containsKey(y))   return map.getOrDefault(x,1001) - map.getOrDefault(y,1001);
            //对于都没有再数组中存在的值，对其进行升序排序
            return x - y;
        });
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = list.get(i);
        }
        return arr1;
    }

}
