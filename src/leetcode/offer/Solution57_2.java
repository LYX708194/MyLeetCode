package leetcode.offer;

import java.util.ArrayList;
import java.util.List;

/** 和位s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/25 13:39
 */
public class Solution57_2 {

    public int[][] findContinuousSequence(int target) {
        int i = 1,j = 2,sum = 3;
        List<int[]> ans = new ArrayList<>();
        while (i < j){
            if (sum == target){
                int[] res = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    res[k-i] = k;
                }
                ans.add(res);
            }
            if (sum >= target){
                sum -= i;
                i++;
            } else {
                j++;
                sum += j;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }


}
