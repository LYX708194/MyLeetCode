package leetcode.time2020.twelve;

import java.util.Stack;

/**
 * 84 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/26 22:25
 */
public class Solution84 {

    /**
     * 暴力枚举破解
     * @author lyx
     * @date 2020/12/26 22:37
     * @return
     */
    public int largestRectangleArea0(int[] heights) {
        int n = heights.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int left = i,right = i;
            while (left >= 1 && heights[left-1] >= height){
                left--;
            }
            while (right < n - 1 && heights[right+1] >= height ){
                right++;
            }
            ans = Math.max(ans,(right - left + 1) * height);
        }
        return ans;
    }

    /**
     * 单调栈
     * @author lyx
     * @date 2020/12/26 22:37
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

}
