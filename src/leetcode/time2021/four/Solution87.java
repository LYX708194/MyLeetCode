package leetcode.time2021.four;

/**扰乱字符串
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s1 = "great", s2 = "rgeat"
 * 输出：true
 * 解释：s1 上可能发生的一种情形是：
 * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
 * 示例 2：
 *
 * 输入：s1 = "abcde", s2 = "caebd"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "a", s2 = "a"
 * 输出：true
 *  
 *
 * 提示：
 *
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/scramble-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/15 18:07
 */
public class Solution87 {

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2))  return true;
        int n = s1.length(),m = s2.length();
        if (n != m) return false;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        //dp[i][j][len]表示s1中i开始长度为len的字符串是否能变换为s2中j开始长度为len的字符串
        boolean[][][] dp = new boolean[n][n][n+1];
        //初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chars1[i] == chars2[j];
            }
        }
        //枚举长度从2到len
        for (int len = 2; len <= n; len++) {
            //枚举s1中的起始位置
            for (int i = 0; i <= n-len; i++) {
                //枚举s2中的起始位置
                for (int j = 0; j <= n-len; j++) {
                    //枚举划分位置
                    for (int k = 1; k <= len-1; k++) {
                        //第一种情况 s1 -> t1 ,s2 -> t2
                        if (dp[i][j][k] && dp[i+k][j+k][len - k]){
                            dp[i][j][len] = true;
                            break;
                        }
                        //第二种情况 s1 -> t2 ,s2 -> t1
                        //t2起点为j+前面的长度(len-k),s2起点为j+前面长度k
                        if (dp[i][j+len-k][k] && dp[i+k][j][len-k]){
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

}
