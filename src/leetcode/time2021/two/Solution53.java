package leetcode.time2021.two;

/**
 * 53 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *  
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/20 12:25
 */
public class Solution53 {

    /**
     * 动态规划
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        //dp[i]表示以i结尾的最大子数组的和
        int[] dp = new int[n+1];
        for (int i = 0; i < n; i++) {
            dp[i+1] = Math.max(dp[i] + nums[i],nums[i]);
            max = Math.max(max,dp[i+1]);
        }
        return max;
    }

    /**
     * dp[i]只和dp[i-1]有关，可以只用一个变量来表示
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int max = nums[0],pre = 0;
        for (int num:nums) {
            pre = Math.max(pre + num,num);
            max = Math.max(max,pre);
        }
        return max;
    }


}
