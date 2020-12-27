package leetcode.time2020.twelve;

import java.util.ArrayDeque;
import java.util.Deque;

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
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int area = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()];

                while (!stack.isEmpty() &&  heights[stack.peekLast()] == height){
                    stack.removeLast();
                }

                int width;
                //栈为空，说明他的左边界到最左边
                if (stack.isEmpty()){
                    width = i;
                } else {
                    width = i - stack.peekLast() - 1;
                }

                area = Math.max(area , width * height);
            }
            stack.addLast(i);
        }
        //遍历完成，将栈内元素全部出栈
        while (!stack.isEmpty()){
            int height = heights[stack.removeLast()];

            while (!stack.isEmpty() &&  heights[stack.peekLast()] == height){
                stack.removeLast();
            }

            int width;
            if (stack.isEmpty()){
                width = len;
            } else {
                width = len - stack.peekLast() - 1;
            }

            area = Math.max(area , width * height);
        }
        return area;
    }

    /**
     * 单调栈+常数空间优化
     * @author lyx
     * @date 2020/12/27 11:44
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int area = 0;
        //首尾增加哨兵
        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len; i++) {
            newHeights[i + 1] = heights[i];
        }
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>();
        //增加哨兵，这个永远不会被弹出
        stack.addLast(0);
        //因为增加了哨兵，所以从数组的1开始遍历，遍历到最后一个也是0，确保中间所有的高度的柱子都弹出得到计算
        for (int i = 1; i < len; i++) {
            while (heights[stack.peekLast()] > heights[i]) {
                int height = heights[stack.removeLast()];
                int width  = i - stack.peekLast() - 1;
                area = Math.max(area, width * height);
            }
            stack.addLast(i);
        }
        return area;
    }


    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        Solution84 solution84 = new Solution84();
        solution84.largestRectangleArea2(heights);
    }

}
