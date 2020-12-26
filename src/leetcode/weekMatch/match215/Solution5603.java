package leetcode.weekMatch.match215;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 *
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 *
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 *
 * @author lyx
 * @date 2020/11/15 10:55
 */
public class Solution5603 {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length())   return false;
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for (char c:word1.toCharArray()) {
            map1.put(c,map1.getOrDefault(c,0)+1);
        }
        for (char c:word2.toCharArray()) {
            if (!map1.containsKey(c))   return false;
            map2.put(c,map2.getOrDefault(c,0) + 1);
        }
        if(map1.size() != map2.size())  return false;
        int[] size1 = new int[map2.size()];
        int[] size2 = new int[map2.size()];
        int i = 0;
        for (Map.Entry<Character,Integer> entry:map1.entrySet()) {
            if (entry.getValue() != 0){
                size1[i++] = entry.getValue();
            }
        }
        i = 0;
        for (Map.Entry<Character,Integer> entry:map2.entrySet()) {
            if (entry.getValue() != 0){
                size2[i++] = entry.getValue();
            }
        }
        Arrays.sort(size1);
        Arrays.sort(size2);
        for (int j = 0; j < size1.length; j++) {
            if (size1[j] != size2[j])   return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution5603 solution5603 = new Solution5603();
        solution5603.closeStrings("abbbzcf","abbccc");
    }

}
