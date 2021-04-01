package leetcode.time2021.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/1 15:59
 */
public class Solution438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(),m = p.length();
        int diff = 0;
        if (n < m)  return ans;
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c-'a']++;
            diff++;
        }
        int left = 0,right = 0;
        while (right < n){
            char c = s.charAt(right);
            if (cnt[c-'a'] > 0){
                cnt[c-'a']--;
                diff--;
                right++;
                if (diff == 0){
                    ans.add(left);
                }
            }else{
                //两种情况，一种是right字符不是p里面有的，一种是出现了重复
                while (cnt[s.charAt(right)-'a'] <= 0 && left < right){
                    cnt[s.charAt(left++)-'a']++;
                    diff++;
                }
                if (left == right){
                    //此字符不在
                    if (cnt[s.charAt(left)-'a'] == 0){
                        left++;
                        right++;
                    }
                }
            }
        }
        return ans;
    }


    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(),m = p.length();
        int diff = 0;
        if (n < m)  return ans;
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c-'a']++;
            diff++;
        }
        int left = 0,right = 0;
        int[] windows = new int[26];
        while (right < n){
            windows[s.charAt(right)-'a']++;
            if (right - left + 1 == m){
                if (Arrays.equals(cnt,windows)) ans.add(left);
                windows[s.charAt(left++)-'a']--;
            }
            right++;
        }
        return ans;
    }

}
