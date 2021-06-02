package leetcode.time2021.five;

/**
 * @author lyx
 * @date 2021/6/2 22:50
 */
public class Solution1744 {

    public boolean[] canEat(int[] candiesCount, int[][] queries) {int n = candiesCount.length;

        // 前缀和
        long[] sum = new long[n];
        sum[0] = candiesCount[0];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }

        int q = queries.length;
        boolean[] ans = new boolean[q];
        for (int i = 0; i < q; ++i) {
            int[] query = queries[i];
            int favoriteType = query[0], favoriteDay = query[1], dailyCap = query[2];
            //第i天能吃到糖果范围
            long x1 = favoriteDay + 1;
            long y1 = (long) (favoriteDay + 1) * dailyCap;
            //第i种糖果类型编号范围
            long x2 = favoriteType == 0 ? 1 : sum[favoriteType - 1] + 1;
            long y2 = sum[favoriteType];
            //有无交集
            ans[i] = !(x1 > y2 || y1 < x2);
        }
        return ans;

    }

}
