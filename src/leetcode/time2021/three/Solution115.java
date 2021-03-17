package leetcode.time2021.three;

/**
 * 不同的子序列
 * @author lyx
 * @date 2021/3/17 8:55
 */
public class Solution115 {

    public int numDistinct(String s, String t) {
        int m = s.length(),n = t.length();
        //dp[i][j]表示s的前i个字母能构成t的前j个的子串个数
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)){
                    //能够匹配，各自去掉最后一位，加上可能重复的次数
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }

}
