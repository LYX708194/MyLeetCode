package leetcode.time2021.five;

import java.util.Stack;

/**
 * @author lyx
 * @date 2021/5/26 23:28
 */
public class Solution1190 {

    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                //将sb插入栈中并置为空
                stack.push(sb.toString());
                sb.setLength(0);
            }else if (c == ')'){
                //反转sb，插入到栈顶元素后面
                sb.reverse();
                sb.insert(0,stack.pop());
            }else{
                //直接添加到后面
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
