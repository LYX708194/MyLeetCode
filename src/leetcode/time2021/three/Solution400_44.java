package leetcode.time2021.three;

/**第n位数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 位数字。
 *
 *  
 *
 * 注意：n 是正数且在 32 位整数范围内（n < 231）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：3
 * 输出：3
 * 示例 2：
 *
 * 输入：11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/22 15:31
 */
public class Solution400_44 {

    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            //位数+1
            digit += 1;
            //当前位数开始的数
            start *= 10;
            //当前位数拥有的数字数量
            count = digit * start * 9;
        }
        //当前n所在的数字
        long num = start + (n - 1) / digit;
        //再确定所求在该数字的第几位
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

}
