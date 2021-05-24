package leetcode.time2021.five;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 *
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 *  
 * 示例 1：
 *
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 *
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strange-printer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/5/24 21:38
 */
public class Solution664 {

    public int strangePrinter(String s) {
        int n = s.length();
        //dp[i][j]表示打印完成区间【i，j】需要的次数
        int[][] dp = new int[n][n];
        for (int i = n-1; i >= 0 ; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i][j-1];
                }else{
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min,dp[i][k] + dp[k+1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }

}
