package leetcode.time2021.three;

/**
 * 132 分割字符串2
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/7 11:02
 */
public class Solution132 {


    public int minCut(String s) {
        int n = s.length();
        //使用 dp1 来记录字符串是否是回文串，记忆化
        boolean[][] dp1 = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp1[j + 1][i - 1])){
                    dp1[j][i] = true;
                }
            }
        }
        // dp2[i] 记录从0到 i 可以组成的最少回文子串数
        int[] dp2 = new int[n];
        dp2[0] = 1;
        for (int i = 1; i < n; i++) {
            if (dp1[0][i]){
                dp2[i] = 1;
            }else{
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++) {
                    //对于每个j到i满足，寻找最小的前面段最少的子串数
                    if (dp1[j][i]){
                        min = Math.min(dp2[j-1],min);
                    }
                }
                //最后加上最后这段满足回文串的
                dp2[i] = min + 1;
            }
        }
        //总共最少有n个字符串，则需要分割n-1次
        return dp2[n-1] - 1;
    }



}
