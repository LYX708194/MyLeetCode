package leetcode.offer;

/** 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *  
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/25 11:49
 */
public class Solution56_1 {

    public int[] singleNumbers(int[] nums) {
        int x = 0,y = 0,n = 0,m = 1;
        //遍历异或，最后结果为两个不同数的异或值
        for(int num : nums) n ^= num;
        //循环左移，计算m,当这个位为1时，说明这个位这两个数不相同
        while ((n & m) == 0){
            m <<= 1;
        }
        //根据此位数不同的m进行分组与
        for (int num : nums) {
            if ((num & m) != 0) x ^= num;
            else y ^= num;
        }
        return new int[]{x,y};
    }

}
