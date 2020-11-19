package leecode.offer;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/13 11:43
 */
public class Solution10_1 {

    /**
     * 最简单直接递归法，大量重复的递归计算，例如 f(n) 和 f(n−1) 两者向下递归需要 各自计算 f(n−2) 的值。
     * 可以改为记忆化递归，在递归法的基础上，新建一个长度为 n 的数组，用于在递归时存储 f(0) 至 f(n) 的数字值，重复遇到某数字则直接从数组取用，避免了重复的递归计算。
     * @param n
     * @return
     */
    public int fib(int n) {
        if( n == 0)  return 0;
        if( n == 1)  return 1;
        return fib(n-1) + fib(n-2);
    }

    /**
     * 动态规划
     * dp[i+1]=dp[i]+dp[i−1] ，即对应数列定义 f(n + 1) = f(n) + f(n - 1)；
     * 初始状态： dp[0] = 0, dp[1] = 1 ，即初始化前两个数字；
     * 返回值： dp[n] ，即斐波那契数列的第 n 个数字。
     * 大数越界： 随着 n 增大, f(n) 会超过 Int32 甚至 Int64 的取值范围，导致最终的返回值错误。
     * 求余运算规则： 设正整数 x, y, p，求余符号为 ⊙ ，则有 (x+y)⊙p=(x⊙p+y⊙p)⊙p 。
     * 解析： 根据以上规则，可推出 f(n)⊙p=[f(n−1)⊙p+f(n−2)⊙p]⊙p ，从而可以在循环过程中每次计算 sum=(a+b)⊙1000000007 ，此操作与最终返回前取余等价
     *https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/solution/mian-shi-ti-10-i-fei-bo-na-qi-shu-lie-dong-tai-gui/
     */
    public int fib2(int n) {
        int a = 0,b = 1,sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

}
