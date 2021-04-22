package leetcode.time2021.four;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 *
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *  
 *
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/22 7:37
 */
public class Solution363 {

    /**
     * 暴力动态规划
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        //dp[i][j][l][o] 表示从[i,j]到[l,o]的区间和
        int[][][][] dp = new int[m+1][n+1][m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j][i][j] = matrix[i-1][j-1];
                for (int l = i; l <= m; l++) {
                    for (int o = j; o <= n; o++) {
                        dp[i][j][l][o] = dp[i][j][l-1][o] + dp[i][j][l][o-1] - dp[i][j][l-1][o-1] + matrix[l-1][o-1];
                        if (dp[i][j][l][o] <= k)    ans = Math.max(ans,dp[i][j][l][o]);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 上面方法优化版
     */
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        //前两个for循环枚举边界
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int[][] dp = new int[m+1][n+1];
                for (int l = i; l <= m; l++) {
                    for (int o = j; o <= n; o++) {
                        dp[l][o] = dp[l-1][o] + dp[l][o-1] - dp[l-1][o-1] + matrix[l-1][o-1];
                        if (dp[l][o] <= k)    ans = Math.max(ans,dp[l][o]);
                    }
                }
            }
        }
        return ans;
    }


    public int maxSumSubmatrix3(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        boolean isRight = n > m;
        //前缀和数组，二分取较大数
        int[] sum = isRight ? new int[n + 1] : new int[m + 1];
        int ans = Integer.MIN_VALUE;
        // 枚举子矩阵的上/左边界
        for (int i = 1; i <= (isRight ? m : n); i++) {
            Arrays.fill(sum, 0);
            // 枚举子矩阵的下/右边界
            for (int j = i; j <= (isRight ? m : n); j++) {
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(0);
                int a = 0;
                for (int fixed = 1; fixed <= (isRight ? n : m); fixed++) {
                    //添加前缀和
                    sum[fixed] += isRight ? matrix[j - 1][fixed - 1] : matrix[fixed - 1][j - 1] ;
                    a += sum[fixed];
                    //通过二分查找大于等于a-k的最小元素
                    Integer b = ts.ceiling(a - k);
                    if (b != null) {
                        int cur = a - b;
                        ans = Math.max(ans, cur);
                    }
                    ts.add(a);
                }
            }
        }
        return ans;
    }


}
