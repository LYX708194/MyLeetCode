package leetcode.offer;

/** 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 *
 * 提示：
 *
 * 0 <= num < 231
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/22 21:48
 */
public class Solution46 {

    public int translateNum(int num) {
        //小于10，那么只有一种情况
        if (num < 10) {
            return 1;
        }
        //拆分最后两位
        int re = num % 100;
        //如果最后两位小于10，没有合并的情况，最后一位只能自己一个人
        if (re < 10) {
            return translateNum(num / 10);
        } else if (re < 26) {
            //最后两位小于26，在范围内，可以拆分为最后一位自己一个人或者最后两位一起两种情况
            return translateNum(num / 10) + translateNum(num / 100);
        } else {
            //大于26，没有这个情况，最后一位只能自己一人
            return translateNum(num / 10);
        }
    }

    public int translateNum2(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        //dp[i]表示前i个能组合的不同字母数
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            String tmp = s.substring(i - 2, i);
            //如果在范围内，则可以两种情况之和，否则该数字只能当前数字
            dp[i] = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? dp[i-1] + dp[i-2] : dp[i-1];
        }
        return dp[n];
    }
    public int translateNum3(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        //dp[i]表示前i个能组合的不同字母数，优化空间做法
        int a = 1,b = 1;
        for(int i = 2; i <= n; i++) {
            String tmp = s.substring(i - 2, i);
            //如果在范围内，则可以两种情况之和，否则该数字只能当前数字
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? b + a : a;
            b = a;
            a = c;
        }
        return a;
    }

}
