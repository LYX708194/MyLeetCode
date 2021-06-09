package leetcode.time2021.six;

/**
 * @author lyx
 * @date 2021/6/9 22:46
 */
public class Solution879 {

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length, MOD = (int)1e9 + 7;
        // dp[i][j][k] 为考虑前 i 件物品，使用人数不超过 j，所得利润至少为 k 的方案数
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    //如果人数不够选
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        //人数够选，那么方案数就是选择这一个和不选这一个的方案数之和，第三项max(0, k - earn)含义为k的利润已经满足了
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }

    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        int len = group.length, MOD = (int)1e9 + 7;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            //降维之后 dp 数组的遍历顺序应为逆序，与背包问题降维解法类似，因为这样才能保证求状态 dp[j][k] 时,
            //用到的 dp[j−members][max(0,k−earn)] 是上一时刻的值，
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD;
                }
            }
        }
        return dp[n][minProfit];
    }

}
