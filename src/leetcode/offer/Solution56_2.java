package leetcode.offer;

/** 数组中数字出现的次数2
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/25 12:11
 */
public class Solution56_2 {

    public int singleNumber(int[] nums) {
        //对于数字中某二进制位1的个数，存在三个状态，1，2，3，用两个二进制来表示就是00，01，10
        int ones = 0, twos = 0;
        //这对数字的二进制中 “一位” 的分析，而 int 类型的其他 31 位具有相同的运算规则，因此可将以上公式直接套用在 32 位数上。
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }


    public int singleNumber2(int[] nums) {
        int[] counts = new int[32];
        //遍历每个数字的位数，记录该位数的1的个数
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        //每个位置的1的个数模3，最后就是只出现一次的数的1的个数
        for(int i = 0; i < 32; i++) {
            //右移，从最高位开始加
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }


}
