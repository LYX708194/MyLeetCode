package leecode.weekMatch.match216;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 小写字符 的 数值 是它在字母表中的位置（从 1 开始），因此 a 的数值为 1 ，b 的数值为 2 ，c 的数值为 3 ，以此类推。
 *
 * 字符串由若干小写字符组成，字符串的数值 为各字符的数值之和。例如，字符串 "abe" 的数值等于 1 + 2 + 5 = 8 。
 *
 * 给你两个整数 n 和 k 。返回 长度 等于 n 且 数值 等于 k 的 字典序最小 的字符串。
 *
 * 注意，如果字符串 x 在字典排序中位于 y 之前，就认为 x 字典序比 y 小，有以下两种情况：
 *
 * x 是 y 的一个前缀；
 * 如果 i 是 x[i] != y[i] 的第一个位置，且 x[i] 在字母表中的位置比 y[i] 靠前。
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 27
 * 输出："aay"
 * 解释：字符串的数值为 1 + 1 + 25 = 27，它是数值满足要求且长度等于 3 字典序最小的字符串。
 * 示例 2：
 *
 * 输入：n = 5, k = 73
 * 输出："aaszz"
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * n <= k <= 26 * n
 *
 * @author lyx
 * @date 2020/11/22 10:36
 */
public class Solution5606 {

    List<Character> characters = new ArrayList<>();
    StringBuilder res = new StringBuilder();
    char[] chars;
    int index = 0;

    public String getSmallestString(int n, int k) {
        chars = new char[n];
        for (int i = 0; i < 26; i++) {
            characters.add(i, (char) ('a'+i));
        }
        back(n,k);
        return res.toString();
    }
    void back(int n, int k){
        if (res.length() > 0 )  return;
        if ( n == 0 && k == 0){
            res.append(chars);
            return ;
        }
        if ( k > n * 26)    return;
        for (int i = 0; i < 26; i++) {
            int n1 = n  - 1;
            int k1 = k - characters.get(i) + 'a' - 1;
            if (n1 < 0 || k1 < 0)   break;
            chars[index++] = characters.get(i);
            back(n1,k1);
            index--;
        }
    }

    /**
     * 大佬的题解
     */
    public String getSmallestString2(int n, int k) {
        StringBuilder builder = new StringBuilder();
        for (int i=1;i<=n;i++) {
            int min = k - 26 * (n-i);
            if (min <= 0) {
                min = 1;
            }
            builder.append((char) ('a' + min - 1));
            k -= min;
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution5606 solution5606 = new Solution5606();
        solution5606.getSmallestString(3,27);
    }


}