package leetcode.time2021.three;

import java.util.ArrayList;
import java.util.List;

/**
 * 131 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/7 10:21
 */
public class Solution131 {

    List<List<String>> ans;
    //使用dp来记录字符串是否是回文串，记忆化
    boolean[][] dp;

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        int n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])){
                    dp[j][i] = true;
                }
            }
        }
        back(new ArrayList<>(),s,0);
        return ans;
    }
    public void back(List<String> res,String s,int index){
        if (index == s.length()){
            ans.add(new ArrayList<>(res));
        }
        for (int i = index; i < s.length(); i++) {
//            if (isPalindrome(s,index,i)){
            if (dp[index][i]){
                res.add(s.substring(index,i + 1));
                back(res, s, i + 1);
                res.remove(res.size()-1);
            }
        }
    }

    boolean isPalindrome(String str,int left,int right){
        while (left < right){
            if (str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
