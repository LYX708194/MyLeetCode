package leetcode.time2021.one;

/**
 * 28 实现strStr()
 *  实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/5 12:51
 */
public class Solution28 {

    public int strStr(String haystack, String needle) {
        int n1 = haystack.length(),n2 = needle.length();
        for (int i = 0; i < n1 - n2 + 1; i++) {
            if (haystack.substring(i,i + n2).equals(needle)){
                return i;
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        int n1 = haystack.length(),n2 = needle.length();
        if (n2 == 0)    return 0;
        for (int i = 0; i < n1 - n2 + 1; i++) {
            for (int j = 0; j < n2; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
                if (j == n2-1)  return i;
            }
        }
        return -1;
    }



}
