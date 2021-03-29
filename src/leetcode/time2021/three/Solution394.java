package leetcode.time2021.three;

import java.util.Stack;

/** 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/29 12:28
 */
public class Solution394 {

    public String decodeString(String s) {
        Stack<String> stack = new Stack();
        int index = 0;
        while (index < s.length()){
            char c = s.charAt(index);
            if (c == ']'){
                StringBuilder str = new StringBuilder();
                while (!stack.peek().equals("[")){
                    str.append(stack.pop());
                }
                str = str.reverse();
                //左括号
                stack.pop();
                int time = Integer.parseInt(stack.pop());
                StringBuilder newStr = new StringBuilder();
                while (time-- > 0){
                    newStr.append(str);
                }
                stack.push(newStr.reverse().toString());
                index++;
            }else if (Character.isDigit(c)){
                //如果是数字，可能有出现多位
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))){
                    num = num*10 + s.charAt(index++) - '0';
                }
                stack.push(String.valueOf(num));
            } else{
                stack.push(String.valueOf(c));
                index++;
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

}
