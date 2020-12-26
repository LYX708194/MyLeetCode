package leetcode.time2020.eleven;

/**
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/8 10:31
 */
public class Solution122 {
    /**
     * 动态规划，用 dp0 表示此时手里没有股票的收益， dp1 表示手里有股票时的收益
     * 则 dp[i][0] = max(dp[i-1][0],dp[i-1][1] + price[i])
     *    dp[i][1] = max(dp[i-1][1],dp[i-1][0] - price[i])
     *    因为每次dp只和前一个dp值有关，所以可以重复利用dp空间
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp0 = 0;
        int dp1 = -prices[0];
        for (int i = 0; i < n; i++) {
            int newDP0 = Math.max(dp0,dp1 + prices[i]);
            int newDP1 = Math.max(dp1,dp0 - prices[i]);
            dp0 = newDP0;
            dp1 = newDP1;
        }
        return dp0;
    }

    /**
     * 贪心算法，只要有收益就加上
     */
    public int maxProfit2(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

}
