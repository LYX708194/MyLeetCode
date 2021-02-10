package leetcode.time2021.two;

import java.util.Arrays;

/**
 * 567 字符串的排列
 *给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/10 16:00
 */
public class Solution567 {

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        //如果两个字符串中的字母个数相同，说明s1一个排列是s2的子串
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }


    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }


    public boolean checkInclusion3(String s1, String s2) {
        int[] feq = new int[26];
        //记录字符种类数
        int cnt = 0;
        int n1 = s1.length(), n2 = s2.length();
        //遍历s1，初始化字符数和种类数
        for (char c : s1.toCharArray()) {
            if (feq[c - 'a'] == 0) {
                cnt++;
            }
            feq[c - 'a']++;
        }
        char[] chars = s2.toCharArray();
        for (int i = 0; i < n2; i++) {
            //右指针移动
            if (--feq[chars[i] - 'a'] == 0) {
                cnt--;
            }
            //超过s1长度，左指针移动
            if (i >= n1 && feq[chars[i - n1] - 'a']++ == 0) {
                cnt++;
            }
            if (cnt == 0) {
                return true;
            }
        }
        return false;
    }

}
