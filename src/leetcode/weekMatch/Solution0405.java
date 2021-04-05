package leetcode.weekMatch;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author lyx
 * @date 2021/4/5 15:07
 */
public class Solution0405 {


    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0,right = nums.length-1;
        int ans = 0;
        while (left < right){
            if (nums[left] + nums[right] <= target){
                ans = (ans + right - left) % 1000000007;
                left++;
            }else{
                right--;
            }
        }
        return ans;
    }

    public int orchestraLayout(int num, int xPos, int yPos) {
        int cur = 0;
        int top = 0,left = 0,right = num-1,bottle = num-1;
        int x1 = num - xPos - 1,y1 = num - yPos - 1;
        int min = Math.min(x1,Math.min(y1,Math.min(xPos,yPos))),max = num - min * 2;
        cur = (num * num - max * max) % 9;
        top += min;left += min;bottle -= min;right -= min;
        cur++;
//        while (xPos != top && xPos != bottle && yPos != left && yPos != right){
//            top++;bottle--;left++;right--;
//            cur = cur + (num-1) * 4 ;
//            cur %= 9;
//            num -= 2;
//            div++;
//        }
        while (left < right && top < bottle){
            for (int i = left; i <= right; i++) {
                if (xPos == top && yPos == i)   return cur;
                cur = cur == 9 ? 1 : cur+1;
            }
            top++;
            for (int i = top; i <= bottle; i++) {
                if (xPos == i && yPos == right)   return cur;
                cur = cur == 9 ? 1 : cur+1;
            }
            right--;
            for (int i = right; i >= left; i--) {
                if (xPos == bottle && yPos == i)   return cur;
                cur = cur == 9 ? 1 : cur+1;
            }
            bottle--;
            for (int i = bottle; i >= top; i--) {
                if (xPos == i && yPos == left)   return cur;
                cur = cur == 9 ? 1 : cur+1;
            }
            left++;
        }
        return cur;
    }

    public int orchestraLayout2(int num, int xPos, int yPos) {
        int cur = 0;
        int top = 0,left = 0,right = num-1,bottle = num-1;
        int x1 = num - xPos - 1,y1 = num - yPos - 1;
        int min = Math.min(x1,Math.min(y1,Math.min(xPos,yPos))),max = num - min * 2;
        //越界
//        cur = (num * num - max * max) % 9;
//        long a = num * num;
//        long b = max * max;
//        a -= b;
//        cur = (int) (a % 9);
        BigInteger temp = new BigInteger(String.valueOf(num));
        temp = temp.multiply(temp);
        BigInteger a = new BigInteger(String.valueOf(max));
        a = a.multiply(a);
        BigInteger res = temp.subtract(a).mod(new BigInteger("9"));
        cur = res.intValue();
        top += min;left += min;bottle -= min;right -= min;
        cur++;
        if (xPos == top){
            cur = (cur + yPos - left) % 9;
        }else if (xPos == bottle){
            //cur = (cur + (right - left)*2 + right - yPos) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - yPos) % 9;
        }else if (yPos == left){
            //cur = (cur + (right - left)*3 + bottle - xPos) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + bottle - xPos) % 9;
        }else{
            //cur = (cur + right - left + xPos - top) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + xPos - top) % 9;
        }
        return cur == 0 ? 9: cur;
    }



    public static void main(String[] args) {
        Solution0405 solution0405 = new Solution0405();
        solution0405.orchestraLayout(4,1,2);
    }



}
