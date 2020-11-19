package leecode.time2020.eleven;

import java.util.Arrays;

/**
 *
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 *
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 *
 * 请你返回排序后的数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/6 11:10
 */
public class Solution1356 {

    //直接把1的个数提到最高位
    public int[] sortByBits(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.bitCount(arr[i])*100 + arr[i];
        }
        Arrays.sort(res);
        for (int i = 0; i < arr.length; i++) {
            res[i] = res[i]%100;
        }
        return res;
    }


}
