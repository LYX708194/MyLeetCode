package leetcode.time2021.three;

/**
 * @author lyx
 * @date 2021/3/26 14:34
 */
public class Solution221 {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m][n];
        //初始化的时候也初始化max，防止出现一行的情况然后没有走到最下面的for
        for(int i = 0;i < m;i++){
            if(matrix[i][0] == '1')   dp[i][0] = max = 1;
        }
        for(int i = 0;i < n;i++){
            if(matrix[0][i] == '1')   dp[0][i] = max= 1;
        }
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1])) + 1;
                    max = Math.max(max,dp[i][j] * dp[i][j]);
                }
            }
        }
        return max;
    }

}
