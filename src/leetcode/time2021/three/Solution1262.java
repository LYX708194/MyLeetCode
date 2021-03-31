package leetcode.time2021.three;

/** 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * 示例 2：
 *
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/greatest-sum-divisible-by-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/31 19:11
 */
public class Solution1262 {

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        //存储余数为0，1，2时的最大值
        int[] dp = new int[3];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int a,b,c;
            a = dp[0] + nums[i];
            b = dp[1] + nums[i];
            c = dp[2] + nums[i];
            //三个的余数肯定为0，1，2
            dp[a%3] = Math.max(dp[a%3],a);
            dp[b%3] = Math.max(dp[b%3],b);
            dp[c%3] = Math.max(dp[c%3],c);
        }
        return dp[0];
    }

    public int maxSumDivThree2(int[] nums) {
        // dp[0]滚到dp[1]，dp[1]滚到dp[0]，循环往复
        int[][] dp = new int[2][3];
        //让前面的还没出现的余数为1和2的不影响dp结果
        dp[0][1] = dp[0][2] = Integer.MIN_VALUE;
        int i = 0;
        for (int num : nums) {
            int j = i == 0 ? 1 : 0;
            switch (num % 3) {
                case 0:
                    dp[j][0] = dp[i][0] + num;
                    dp[j][1] = dp[i][1] + num;
                    dp[j][2] = dp[i][2] + num;
                    break;
                case 1:
                    dp[j][0] = Math.max(dp[i][0], dp[i][2] + num);
                    dp[j][1] = Math.max(dp[i][1], dp[i][0] + num);
                    dp[j][2] = Math.max(dp[i][2], dp[i][1] + num);
                    break;
                case 2:
                    dp[j][0] = Math.max(dp[i][0], dp[i][1] + num);
                    dp[j][1] = Math.max(dp[i][1], dp[i][2] + num);
                    dp[j][2] = Math.max(dp[i][2], dp[i][0] + num);
                    break;
            }
            i = j;
        }
        return dp[i][0];
    }

}
