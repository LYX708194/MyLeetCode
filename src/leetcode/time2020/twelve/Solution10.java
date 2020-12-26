package leetcode.time2020.twelve;

/**
 * 10正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 *
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 *
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/14 12:42
 */
public class Solution10 {

    /**
     * 动态规划
     * https://leetcode-cn.com/problems/regular-expression-matching/solution/dong-tai-gui-hua-he-di-gui-liang-chong-fang-shi-2/
     * dp[i][j]表示的是p的前j个字符和s的前i个字符匹配的结果
     * 默认dp[0][0]=true；也就是p的前0个字符和s的前0个字符是可以匹配的
     *
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n+1];
        //初始化边界条件
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            //如果p的第i+1个字符也就是p.charAt(i)是"*"的话，那么他就可以把p的第i个字符给消掉（也就是匹配0次）。
            //我们只需要判断p的第i-1个字符和s的前0个字符是否匹配即可。比如p是"a*b*"，如果要判断p的第4个字符
            //"*"和s的前0个字符是否匹配，因为字符"*"可以消去前面的任意字符，只需要判断p的"a*"和s的前0个字符是否匹配即可
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果此位置的两个字符相同，则根据之前的字符是否匹配来判断自身是否匹配
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    // *和前面的字符匹配0个的情况
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1] [j+1]= dp[i + 1][j-1];
                    } else {
                        // p的j-1个字符和s的i个字符匹配，则有匹配0个1个和多个的情况，任何一种情况满足都算是匹配
                        dp[i + 1][j+1] = (dp[i + 1][j] || dp[i][j+1]|| dp[i + 1][j-1]);
                    }
//                    //可以将上面的两种判断里的匹配0个的情况合并，匹配一个和匹配多个也可以合并，合并成下面这样
//                    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
//                        dp[i + 1][j + 1] = dp[i][j + 1];
//                    }
//                    dp[i + 1][j + 1] |= dp[i + 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 递归
     */
    public boolean isMatch2(String s, String p) {
        if (p.length() == 0){
            return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*'){
            //p的第二个字符是‘*’，分为两种情况
            // 1、p把‘*’前面字符消掉，即匹配0次
            // 2、匹配一次或多次
            return isMatch2(s,p.substring(2)) || (s.length() > 0 && comp(s, p)) && isMatch2(s.substring(1),p);
        }else{
            // p的第二个字符不是 '*'，判断首字符是否相同，如果相同再从第二位继续比较
            return s.length() > 0 && comp(s, p) && (isMatch2(s.substring(1),p.substring(1)));
        }
    }
    //比较s的首字符和p的首字符是否匹配
    private boolean comp(String s, String p) {
        return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
    }

}
