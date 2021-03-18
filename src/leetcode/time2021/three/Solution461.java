package leetcode.time2021.three;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/18 11:00
 */
public class Solution461 {

    public int hammingDistance(int x, int y) {
        int ans = 0;
        while(x != 0 || y != 0){
            if((x&1) != (y&1)){
                ans++;
            }
            x >>= 1;
            y >>= 1;
        }
        return ans;
    }

    public int hammingDistance2(int x, int y) {
        //异或，然后求1的个数
        int xor = x ^ y;
        int ans = 0;
        while (xor != 0){
            ans++;
            //将最后一个1置为0
            xor &= xor-1;
        }
        return ans;
    }




}
