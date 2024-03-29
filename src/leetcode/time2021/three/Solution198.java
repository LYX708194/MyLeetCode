package leetcode.time2021.three;

import java.util.HashSet;
import java.util.Set;

/**打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/18 19:16
 */
public class Solution198 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        //dp[i][0]表示偷取第i家的至今最大金额，dp[i][1]表示不偷取第i家的至今最大金额
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][1] + nums[i],dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1]);
        }
        return Math.max(dp[n-1][0],dp[n-1][1]);
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        //dp[i][0]表示偷取第i家的至今最大金额，dp[i][1]表示不偷取第i家的至今最大金额
        int dp1 = nums[0],dp2 = 0;
        for (int i = 1; i < n; i++) {
            int d1 = dp1,d2 = dp2;
            dp1 = Math.max(d2 + nums[i],d1);
            dp2 = Math.max(d1,d2);
        }
        return Math.max(dp1,dp2);
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(!set.contains(n)){
            set.add(n);
            int num = 0;
            while (n != 0){
                num += (n % 10) * (n % 10);
                n /= 10;
            }
            n = num;
        }
        return false;
    }

}
