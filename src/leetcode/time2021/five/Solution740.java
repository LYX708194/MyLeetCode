package leetcode.time2021.five;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/5/5 9:43
 */
public class Solution740 {

    public int deleteAndEarn(int[] nums) {
        if (nums.length == 1)   return nums[0];
        int max = Arrays.stream(nums).max().getAsInt();
        int[] sum = new int[max+1];
        for (int item : nums){
            sum[item]++;
        }
        int[] dp = new int[max+1];
        dp[1] = sum[1];
        dp[2] = Math.max(dp[1],sum[2] * 2);
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2] + sum[i] * i);
        }
        return dp[max];
    }

}
