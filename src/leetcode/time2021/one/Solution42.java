package leetcode.time2021.one;

import java.util.Stack;

/**
 * 42 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 *
 * 提示：
 *
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/3 13:00
 */
public class Solution42 {

    /**
     * 暴力解法，求每一列的墙存储的水
     * @author lyx
     * @date 2021/1/3 13:04
     * @return
     */
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        //最两端的列不用考虑，因为一定不会有水。
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0, maxRight = 0;
            //找左边最大值（自己当前列也参与计算，不需要额外判断是否有水，如果没水结果会是0）
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            //找右边最大值
            for (int j = i; j < size; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            //结果加上左右较小者减去当前列，就是该列能存储的水
            ans += Math.min(maxLeft, maxRight) - height[i];
        }
        return ans;
    }
    
    /**
     * 动态规划，暴力解法的最大高度可以存储起来
     * @author lyx
     * @date 2021/1/3 13:22
     * @return 
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 双指针
     * @author lyx
     * @date 2021/1/3 13:22
     * @return 
     */
    public int trap3(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            //从左到右
            if (height[left] < height[right]) {
                //左边比较矮
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    //左边有高的，计算雨水
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
            //从右到左
                //右边比较矮
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    //右边有高的，计算雨水
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    /**
     * 单调栈
     * 在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
     * 如果发现一个条形块长于栈顶，可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，因此可以弹出栈顶元素并且累加答案到 ans 。
     *
     * @author lyx
     * @date 2021/1/3 13:25
     * @return
     */
    public int trap4(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                //取出要出栈的元素
                int h = height[stack.pop()];
                // 栈空就跳出循环
                if (stack.empty()) {
                    break;
                }
                //两堵墙之前的距离
                int distance = current - stack.peek() - 1;
                //两墙间的较矮者
                int min = Math.min(height[stack.peek()], height[current]);
                //距离*高度
                sum += distance * (min - h);
            }
            //当前指向的墙入栈，指针后移
            stack.push(current++);
        }
        return sum;
    }

}
