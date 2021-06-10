package leetcode.time2021.six;

/**
 * @author lyx
 * @date 2021/6/10 21:57
 */
public class Solution518 {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }

}
