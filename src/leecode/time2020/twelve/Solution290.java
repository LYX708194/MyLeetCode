package leecode.time2020.twelve;

import java.util.HashMap;
import java.util.Map;

/**
 * 290 单词规律
 *
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/16 11:03
 */
public class Solution290 {

    public boolean wordPattern(String pattern, String s) {
        Map<Character,String> map = new HashMap<>();
        String[] strs = s.split(" ");
        if (strs.length != pattern.length())    return false;
        int i = 0;
        for (char c:pattern.toCharArray()) {
            if (!map.containsKey(c)){
                if (map.containsValue(strs[i])){
                    return false;
                }
                map.put(c,strs[i]);
            }else{
                String s1 = map.get(c);
                if (!s1.equals(strs[i]))  return false;
            }
            i++;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution290 solution290 = new Solution290();
        solution290.wordPattern("abba","dog dog dog dog");
    }

}
