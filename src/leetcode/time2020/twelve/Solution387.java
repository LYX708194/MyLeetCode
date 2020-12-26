package leetcode.time2020.twelve;

/**
 * 387 字符串中的第一个不重复字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *  
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/23 9:14
 */
public class Solution387 {

    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        for (char c: s.toCharArray()) {
            cnt[c-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)-'a'] == 1)  return i;
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)-'a'] == 1){
                continue;
            }else {
                int index = s.indexOf(s.charAt(i),i+1);
                if (index == -1)    return i;
                else    cnt[s.charAt(i)-'a'] = 1;
            }
        }
        return -1;
    }

}
