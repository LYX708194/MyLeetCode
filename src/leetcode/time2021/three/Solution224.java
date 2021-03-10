package leetcode.time2021.three;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 224 基本计数器
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/10 9:37
 */
public class Solution224 {

    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        //1表示+，-1表示-
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                //加号，标志不变
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                //减号，标志反转
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                //遇到左括号，将标志放入栈中
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                //遇到右括号，将栈顶标志去除
                ops.pop();
                i++;
            } else {
                //遇到数字就实时进行更新数值
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

}
