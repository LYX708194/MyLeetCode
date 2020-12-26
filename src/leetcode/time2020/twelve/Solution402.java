package leetcode.time2020.twelve;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 402 移掉 k 位数字
 *
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/2 10:56
 */
public class Solution402 {


    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        for (Character c:num.toCharArray()) {
            while (!deque.isEmpty() && c < deque.peekLast() && k > 0){
                deque.pollLast();
                k--;
            }
            deque.addLast(c);
        }
        while (k > 0){
            deque.removeLast();
            k--;
        }
        StringBuilder str = new StringBuilder();
        boolean isZero = true;
        while (!deque.isEmpty()){
            char c = deque.pollFirst();
            if (isZero && c == '0'){
                continue;
            }
            isZero = false;
            str.append(c);
        }
        return str.length() == 0 ? "0" : str.toString();
    }

    public static void main(String[] args) {
        Solution402 solution402 = new Solution402();
        solution402.removeKdigits("1234567890",9);
    }


}
