package leetcode.time2021.four;

/**
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *  
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/17 13:28
 */
public class Solution137 {


    public int singleNumber2(int[] nums) {
        //对于数字中某二进制位1的个数，存在三个状态，1，2，3，用两个二进制来表示就是00，01，10
        int ones = 0, twos = 0;
        //这对数字的二进制中 “一位” 的分析，而 int 类型的其他 31 位具有相同的运算规则，因此可将以上公式直接套用在 32 位数上。
        for(int num : nums){
            //1的状态：当twos为1时，1必须为0，否则取决于输入，输入0不变，输入1取反
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int x: nums) {
            // 之前出现过两次的，这次再出现就是出现了三次
            int threes = twos & x;

            // 之前出现过两次，这次没出现，是出现了两次。
            // 之前出现过一次的，这次再出现，也是出现了两次。
            twos = (twos & ~x) | (ones & x);

            // 统计记录出现了奇数次的，并从其中清除出现三次的。
            // 这样ones里面始终只会记录出现了一次的。
            ones = ones ^ x;
            ones &= ~threes;
        }
        return ones;
    }

}
