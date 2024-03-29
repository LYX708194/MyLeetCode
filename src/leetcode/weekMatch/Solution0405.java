package leetcode.weekMatch;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;

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
        int half = num / 2;
        //计算目标外围有多少层
        long cnt = Math.min(xPos <= half ? xPos : num - xPos - 1,yPos <= half ? yPos : num - yPos - 1);
        //计算外cnt层的数量
        long itemIdx = (cnt * (long)(num - cnt)) * 4;
        //若是最内层的中间
        if(itemIdx + 1 == (long)num * num)  return ((itemIdx + 1) % 9) == 0 ? 9 : (int) ((itemIdx + 1) % 9);

        int top = (int) cnt,bottom = (int) (num - cnt - 1),left = (int) cnt,right = bottom;
        //向左,若目标在上方那一行
        if(top == xPos) {
            //加上该行从开始列到目标列的数量
            itemIdx += yPos - left + 1;
            //取余得到乐器的种类，但能够整除时乐器号是9，所以需要判断一下
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        //不在上方那一行，则需要加上该行的数量
        itemIdx += right - left + 1;
        ++top;
        //向下,若目标在右侧那一行
        if(right == yPos) {
            itemIdx += xPos - top + 1;
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        itemIdx += bottom - top + 1;
        --right;
        //向右,若目标在下方那一行
        if(bottom == xPos) {
            itemIdx += right - yPos + 1;
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        itemIdx += right - left + 1;
        --bottom;
        //向上,若目标在左侧那一行
        if(left == yPos) {
            itemIdx += bottom - xPos + 1;
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
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

    public int magicTower(int[] nums) {
        int n = nums.length;
        if (Arrays.stream(nums).sum() < 0)    return -1;
        int ans = 0;
        long blood = 1;
        //只保存负数
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            blood += num;
            if (num < 0)    queue.offer(num);
            while (blood <= 0){
                if (queue.isEmpty()) return -1;
                blood -= queue.poll();
                ans++;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        Solution0405 solution0405 = new Solution0405();
        solution0405.orchestraLayout(4,1,2);
    }



}
