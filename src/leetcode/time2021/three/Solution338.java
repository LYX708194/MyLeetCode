package leetcode.time2021.three;

/**
 * 338 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/3 8:34
 */
public class Solution338 {

    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 1; i <= num; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    /**
     * 直接计算
     */
    public int[] countBits2(int num) {
        int[] res = new int[num+1];
        for (int i = 1; i <= num; i++) {
            res[i] = countOne(i);
        }
        return res;
    }
    private int countOne(int x){
        int sum = 0;
        while (x != 0){
            sum++;
            //n和n-1相与，总是能把最后一个1变为0
            x &= (x-1);
        }
        return sum;
    }

    /**
     * 动态规划
     */
    public int[] countBits3(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            //为奇数时，一定比前面的偶数多1（最低位的1）
            if (i % 2 == 1){
                bits[i] = bits[i-1] + 1;
            }else{
                //为偶数时，肯定和除以2的那个数一样多，因为最低位为0，除以2后即右移一位，抹掉最后的0
                bits[i] = bits[i/2];
            }
        }
        return bits;
    }


}
