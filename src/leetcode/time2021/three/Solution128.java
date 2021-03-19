package leetcode.time2021.three;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**最长连续子序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 *  
 *
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/19 17:28
 */
public class Solution128 {

    public int longestConsecutive(int[] nums) {
        //用hashset去重
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int num : set) {
            //当此数为序列第一个数才进入下面的while循环，保证所有的数字只可能进入一次
            if (!set.contains(num - 1)) {
                int len = 1;
                while (set.contains(++num)) {
                    len++;
                }
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }


    public int longestConsecutive2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if(n == 0)  return 0;
        int ans = 1;
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i-1]) continue;
            if (nums[i] == nums[i-1] + 1) cnt++;
            else{
                ans = Math.max(ans,cnt);
                cnt = 1;
            }
        }
        return Math.max(ans,cnt);
    }

}
