package leetcode.time2021.six;

/**
 * @author lyx
 * @date 2021/6/8 21:42
 */
public class Solution1049 {

    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i : stones) sum += i;
        int t = sum / 2;
        //f[i][j] 代表考虑前 i 个物品（数值），凑成总和不超过 j 的最大价值。
        int[][] f = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= t; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= x) f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + x);
            }
        }
        return Math.abs(sum - f[n][t] - f[n][t]);
    }

}
