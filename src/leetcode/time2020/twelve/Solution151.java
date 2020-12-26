package leetcode.time2020.twelve;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151 翻转字符串里的单词
 * 说明：
 *
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 示例 1：
 *
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 *
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 *
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 *
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *  
 *
 * 进阶：
 *
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/7 11:36
 */
public class Solution151 {

    public String reverseWords(String s) {
        s = s.trim();
        List<String> words = Arrays.asList(s.split("\\s+"));
        Collections.reverse(words);
        return String.join(" ",words);
    }

    public String reverseWords2(String s) {
        // 删除首尾空格
        s = s.trim();
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            // 搜索首个空格
            while(i >= 0 && s.charAt(i) != ' ') i--;
            // 添加单词
            res.append(s.substring(i + 1, j + 1) + " ");
            // 跳过单词间空格
            while(i >= 0 && s.charAt(i) == ' ') i--;
            // j 指向下个单词的尾字符
            j = i;
        }
        // 转化为字符串并返回
        return res.toString().trim();
    }


}
