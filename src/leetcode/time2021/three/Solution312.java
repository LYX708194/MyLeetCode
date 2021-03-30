package leetcode.time2021.three;

/** 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 *  
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 *
 * 输入：nums = [1,5]
 * 输出：10
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/30 11:18
 */
public class Solution312 {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        //增加左右边界，dp[i][j]表示填满开区间（i，j）能得到的最多硬币数
        //dp[i][j]= max(val[i]×val[k]×val[j]) + dp[i][k]+dp[k][j], 当i<j−1
        //​        = 0， 当 i≥j−1
        int[][] dp = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        //len表示开区间长度
        for (int len = 3; len <= n+2; len++) {
            //i表示开区间左端点
            for (int i = 0; i <= n+2 - len ; i++) {
                int res = 0;
                //k为开区间内的索引
                for (int k = i+1; k < i + len - 1; k++) {
                    int left = dp[i][k];
                    int right = dp[k][i + len -1];
                    res = Math.max(res,left + val[i] * val[k] * val[i + len -1] + right);
                }
                dp[i][i + len - 1] = res;
            }
        }
        return dp[0][n + 1];
    }

    public int maxCoins2(int[] nums) {
        int n = nums.length;
        //增加左右边界，dp[i][j]表示填满开区间（i，j）能得到的最多硬币数
        //dp[i][j]= max(val[i]×val[k]×val[j]) + dp[i][k]+dp[k][j], 当i<j−1
        //​        = 0， 当 i≥j−1
        int[][] dp = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }

}
