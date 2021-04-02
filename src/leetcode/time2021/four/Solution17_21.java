package leetcode.time2021.four;

import java.util.Stack;

/** 接雨水
 * @author lyx
 * @date 2021/4/2 8:55
 */
public class Solution17_21 {

    public int trap(int[] height) {
        int ans = 0;
        //单调栈
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        while (cur < height.length){
            while (!stack.isEmpty() && height[cur] > height[stack.peek()]){
                int h = height[stack.pop()];
                if (stack.isEmpty())    break;
                int distance = cur - stack.peek() - 1;
                int high = Math.min(height[cur],height[stack.peek()]) - h;
                ans += distance * high;
            }
            stack.push(cur++);
        }
        return ans;
    }

    public int trap2(int[] height) {
        int ans = 0;
        int n = height.length;
        if(n == 0)  return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n-1] = height[n-1];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i-1],height[i]);
        }
        for (int i = n-2; i >= 0; i--) {
            right[i] = Math.max(right[i+1],height[i]);
        }
        for (int i = 0; i < n; i++) {
            ans += Math.min(left[i],right[i]) - height[i];
        }
        return ans;
    }

    public int trap3(int[] height) {
        int ans = 0;
        int n = height.length;
        int left = 0,right = n-1;
        int leftMax = 0,rightMax = 0;
        while (left < right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if (height[left] < height[right]){
                ans += leftMax - height[left];
                left++;
            }else{
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }

}
