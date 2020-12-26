package leetcode.time2020.twelve;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 20 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/15 10:33
 */
public class Solution20 {

    public boolean isValid(String s) {
        if (s == null || s == "")    return true;
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                deque.addFirst(c);
            }else{
                //右括号比左括号多或者先出现的情况
                if(deque.isEmpty())   return false;
                char c2 = deque.pollFirst();
                if (c == ')' && c2 != '('){
                    return false;
                } else if (c == ']' && c2 != '['){
                    return false;
                }else if (c == '}' && c2 != '{'){
                    return false;
                }
            }
        }
        //左括号比右括号多的情况
        return deque.isEmpty();
    }

    public boolean isValid2(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c:s.toCharArray()) {
            if (c == '(')   stack.push(')');
            else if (c == '[')  stack.push(']');
            else if (c == '{')  stack.push('}');
            else if (stack.isEmpty() || c != stack.pop())   return false;
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        Solution20 s = new Solution20();
        s.isValid("[(]]");
    }

}
