package leecode.time2020.twelve;

/**
 *
 * 316 去除重复字母
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/20 13:16
 */
public class Solution316 {

    /**
     * https://leetcode-cn.com/problems/remove-duplicate-letters/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-4/
     * 单调栈
     * @author lyx
     * @date 2020/12/20 14:14
     * @return
     */
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch-'a']){
                while (sb.length() > 0 && sb.charAt(sb.length()-1) > ch){
                    if (cnt[sb.charAt(sb.length()-1) - 'a'] > 0){
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length()-1);
                    }else{
                        break;
                    }
                }
                sb.append(ch);
                vis[ch - 'a'] = true;
            }
            cnt[ch - 'a']--;
        }
        return sb.toString();
    }

}
