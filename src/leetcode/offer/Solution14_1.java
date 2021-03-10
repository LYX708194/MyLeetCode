package leetcode.offer;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/10 13:49
 */
public class Solution14_1 {

    /**
     * 数学推导可得将绳子尽可能的以长度为3切分多段时，乘积最大
     * 关于为什么切分为3的优先级最高 可以利用均值不等式求出乘积最大值 L(m)=(n/m)^m
     * 对此式求导（可利用对数法），可以证明当 m=n/e 时，乘积取最大，
     * 此时每段绳子的长度为 n/(n/e)=e，自然对数e的值为2.718，显然接近3，所以总体来讲3最好
     */
    public int cuttingRope(int n) {
        //因为必须切割，所以做特殊处理
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        //能够被三整除
        if(b == 0) return (int)Math.pow(3, a);
        //最后剩余1，需要取出其中一个3，改为切分为2*2，或者直接为长度为4的一段
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

    public int cuttingRope2(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            for(int j = 2; j < i; j++){
                //剪掉第一段j，剩下的可以剪也可以不剪，不剪的话就是 j*(i-j),剪的话就是 j*dp[i-j]，两者取最大值即可
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];

    }


}
