package leetcode.time2021.two;

/**
 * 665 非递减序列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 *
 * 说明：
 *
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/7 10:19
 */
public class Solution665 {

    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            //波动次数超过两次，直接返回
            if (cnt > 1)    return false;
            if (nums[i] >= nums[i-1])   continue;
            cnt++;
            //当当前数小于前两个数时，修改当前数为前一个数
            if (i-1 > 0 && nums[i] < nums[i-2]){
                nums[i] = nums[i-1];
            }else {
                //当前数只小于它前一个数，大于前面的第二个数，修改前一个数为当前数的值
                nums[i-1] = nums[i];
            }
        }
        return cnt < 2;
    }

}
