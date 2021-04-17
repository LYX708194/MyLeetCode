package leetcode.time2021.four;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 *  
 *
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 *
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 *
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/17 14:02
 */
public class Solution260 {

    public int[] singleNumber(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        //找到第一个不相等的位，然后进行分组
//        while ((div & ret) == 0) {
//            div <<= 1;
//        }
        //也可以这样求得第一个不同位的1
        //负数取反+1，则第一个1之前的数与原来相反，后面的数相同，相与后就是第一个位
        div = ret & (-ret);
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

}
