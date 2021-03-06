package leetcode.time2021.three;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503 下一个更大元素2
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/6 10:25
 */
public class Solution503 {

    public int[] nextGreaterElements(int[] nums) {
        int i = 0,j = 0;
        int n = nums.length;
        int[] ans = new int[n];
        for (; i < n; i++) {
            j = i + 1;
            while (true){
                if (j >= n) j = 0;
                if (j == i){
                    ans[i] = -1;
                    break;
                }
                if (nums[i] < nums[j]){
                    ans[i] = nums[j];
                    break;
                }
                j++;
            }
        }
        return ans;
    }

    /**
     * 单调栈
     */
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,-1);
        //单调栈单调递减
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]){
                //栈不为空且栈顶的数比当前遍历数字大，则修改栈顶元素位置的最大值为当前位置，然后弹出
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }


    public static void main(String[] args) {
        Solution503 solution503 = new Solution503();
        solution503.nextGreaterElements(new int[]{5,4,3,2,1});
    }


}
