package leetcode.time2021.two;

import java.util.*;

/**
 * 697 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 *
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 *  
 *
 * 提示：
 *
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/degree-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/20 11:59
 */
public class Solution697 {

    public int findShortestSubArray(int[] nums) {
        Map<Integer,int[]> map = new HashMap<>();
        int n = nums.length;
        int maxCount  = 0,ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)){
                //度增加，覆盖最后一次出现位置
                map.get(num)[0]++;
                map.get(num)[2] = i;
            }else{
                //三个位置分别表示这个数出现的度，第一次出现位置，最后一次出现位置
                map.put(num,new int[]{1,i,i});
            }
            //在遍历的时候顺便计算
            int[] value = map.get(num);
            if (value[0] > maxCount){
                maxCount = value[0];
                ans = value[2] - value[1] + 1;
            }else if (value[0] == maxCount){
                ans = Math.min(ans,value[2] - value[1] + 1);
            }
        }
        return ans;
    }

}
