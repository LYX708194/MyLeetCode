package leetcode.time2021.three;

/**编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/19 16:42
 */
public class Solution72 {

    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        //dp[i][j] 表示word1的前i个转换到word2的前j个所需要步数
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            //需要删除多少个才能变成空字符
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            //需要新增多少个才能使空字符变成word2
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //相同，则看前面的规划
                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                //不相同，则看 删除dp[i-1][j]，替换dp[i-1][j-1]，插入dp[i][j-1]操作的最小值 加 1
                else dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1])) + 1;
            }
        }
        return dp[m][n];
    }

}
