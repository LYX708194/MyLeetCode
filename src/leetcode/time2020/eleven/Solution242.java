package leetcode.time2020.eleven;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/22 12:57
 */
public class Solution242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())   return false;
        Map<Character,Integer> map = new HashMap<>();
        for (Character c:s.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for (Character c:t.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0){
                return false;
            }else{
                map.put(c,map.get(c) - 1);
            }
        }
        //如果没判断长度的话会出现 ab,a 的情况判断不出，不过加了之后不会出现这种情况，因为必定会出现小于0或者不存在的情况，前面已经出现了
        for (Integer i:map.values()) {
            if (i > 0)  return false;
        }
        return true;
    }

    /**
     * 直接排序
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t){
        if (s.length() != t.length())   return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1,str2);
    }

    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (Character c : s.toCharArray()) {
            table[c - 'a']++;
        }
        for (Character c : t.toCharArray()) {
            table[c - 'a']--;
            if (table[c - 'a'] < 0) return false;
        }
        return true;
    }
}
