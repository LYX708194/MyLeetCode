package leetcode.time2021.four;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 *
 * 输入：c = 3
 * 输出：false
 * 示例 3：
 *
 * 输入：c = 4
 * 输出：true
 * 示例 4：
 *
 * 输入：c = 2
 * 输出：true
 * 示例 5：
 *
 * 输入：c = 1
 * 输出：true
 *  
 *
 * 提示：
 *
 * 0 <= c <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/28 22:06
 */
public class Solution633 {

    public boolean judgeSquareSum3(int c) {
        //费马平方和
        for (int i = 2, cnt = 0; i * i <= c; i++, cnt = 0) {
            while (c % i == 0 && ++cnt > 0) c /= i;
            if (i % 4 == 3 && cnt % 2 != 0) return false;
        }
        return c % 4 != 3;
    }

    public boolean judgeSquareSum2(int c) {
        //双指针
        int i = 0,j = (int)Math.sqrt(c);
        while (i <= j){
            int sum = i * i + j * j;
            if (sum == c)   return true;
            else if (sum < c)   i++;
            else j--;
        }
        return false;
    }

    public boolean judgeSquareSum(int c) {
        for (long i = 0; i*i <= c; i++) {
            //枚举i的过程获得b
            double b = Math.sqrt(c - i*i);
            if (b == (int)b)    return true;
        }
        return false;
    }

}
