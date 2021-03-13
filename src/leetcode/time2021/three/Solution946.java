package leetcode.time2021.three;

import java.util.Stack;

/**
 * 946 验证栈的压入弹出序列
 *
 * @author lyx
 * @date 2021/3/13 15:26
 */
public class Solution946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed){
            //入栈
            stack.push(num);
            //循环判断出栈
            while (!stack.isEmpty() && stack.peek() == popped[i]){
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }


    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int e : pushed) {
            //直接把pushed当作栈来用
            pushed[i] = e;
            while (i >= 0 && pushed[i] == popped[j]) {
                j++;
                i--;
            }
            i++;
        }
        return i == 0;
    }

}
