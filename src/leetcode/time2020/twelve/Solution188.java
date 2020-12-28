package leetcode.time2020.twelve;

import java.util.Arrays;

/**
 * 188 买卖股票得最佳时机4
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *  
 *
 * 提示：
 *
 * 0 <= k <= 109
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/28 12:11
 */
public class Solution188 {

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        //n 天最多只能进行 ⌊n/2⌋ 笔交易，其中 ⌊x⌋ 表示对 x 向下取整。因此我们可以将 k 对 ⌊n/2⌋ 取较小值之后再进行动态规划。
        k = Math.min(k, n / 2);
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;

        // 如果k超过了最大可买卖次数，那就将k置为最大买卖次数
        // 最大买卖次数就是天数的一半，如果当前卖出又买入，是没有意义的
        k = Math.min(k, n / 2);

        // 状态表示数组，三个维度分别代表：第几天，手上是否有股票0没有1有，还剩下多少次买卖机会
        // 数组值表示当前还有多少钱
        int[][][] f = new int[n][2][k + 1];

        // 设置初始值，第一天手上有股票的状态，就是买入第一天价格的值
        for(int i = 0; i <= k; i++) {
            f[0][1][i] = -prices[0];
        }
        // 状态转移，遍历天数
        for(int i = 1; i < n; i++) {
            // 枚举当天所有可能买卖次数的状态
            for(int j = 0; j <= k; j++) {
                // 当前不持有股票的状态转移
                if(j < k) {
                    // 当前不持有股票，要么前一天也没有股票，要么前一天有股票但是今天卖出了
                    // 如果今天卖出的话，买卖次数就会比前一天少一次，当天是j，那么前一天就是j+1
                    f[i][0][j] = Math.max(f[i - 1][0][j], f[i - 1][1][j + 1] + prices[i]);
                } else {
                    // 剩余买卖次数j等于初始值k的时候，那么说明从来没有产生过交易
                    f[i][0][j] = f[i - 1][0][j];
                }

                // 当前持有股票的状态转移，要么前一天也有股票，要么前一天不持有股票但是当天买入了股票
                f[i][1][j] = Math.max(f[i - 1][1][j], f[i - 1][0][j] - prices[i]);
            }
        }

        int res = 0;
        // 获取最大值，最大值肯定出现在当前手上不持有股票的状态
        for(int i = 0; i <= k; i ++) {
            res = Math.max(res, f[n - 1][0][i]);
        }

        return res;
    }

}
