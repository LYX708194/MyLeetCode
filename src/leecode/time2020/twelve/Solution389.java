package leecode.time2020.twelve;

/**
 *
 * 389 找不同
 *给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 *
 * 输入：s = "", t = "y"
 * 输出："y"
 * 示例 3：
 *
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/18 15:06
 */
public class Solution389 {

    public char findTheDifference(String s, String t) {
        int[] word = new int[26];
        for(char c : s.toCharArray()){
            word[c-'a']++;
        }
        for(char c : t.toCharArray()){
            word[c-'a']--;
            if(word[c-'a'] < 0) return c;
        }
        return ' ';
    }

    /**
     * 求和，差值即为所求
     * @author lyx
     * @date 2020/12/18 15:17
     * @return
     */
    public char findTheDifference2(String s, String t) {
        int ans = 0,at = 0;
        for(char c : s.toCharArray()){
            ans += c;
        }
        for(char c : t.toCharArray()){
            at += c;
        }
        return (char)(at - ans);
    }

    /**
     * 位运算，
     * @author lyx
     * @date 2020/12/18 15:21
     * @return
     */
    public char findTheDifference3(String s, String t) {
        int ret = 0;
        for(char c : s.toCharArray()){
            ret ^= c;
        }
        for(char c : t.toCharArray()){
            ret ^= c;
        }
        return (char) ret;
    }

}
