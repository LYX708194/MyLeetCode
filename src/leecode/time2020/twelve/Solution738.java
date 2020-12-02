package leecode.time2020.twelve;

/**
 *
 * 738 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/2 19:42
 */
public class Solution738 {

    /**
     * 找规律，找到第一个不是递增的序列，然后再往前找相同的那个数或者他自己本身（前面的都比他小）
     * 把这个数改为0，然后后面其他位置为9
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        String s = String.valueOf(N);
        char[] chars = s.toCharArray();
        int i = 0;
        for ( ; i < chars.length - 1 ; i++) {
            if (chars[i + 1] < chars[i])    break;
        }
        //如果全部是正序的，说明原数字就是答案
        if (i == chars.length - 1)  return N;
        while (i >= 1){
            if (chars[i] == chars[i - 1]) i--;
            else  break;
        }
        chars[i]--;
        for (int j = i + 1; j < chars.length; j++) {
            chars[j] = '9';
        }
        String s1 = String.valueOf(chars);
        return Integer.valueOf(s1);
    }

    /**
     * 上面那个解题思路的官方题解版，精简版
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits3(int N){
        char[] S = String.valueOf(N).toCharArray();
        int i = 1;
        while (i < S.length && S[i-1] <= S[i]) i++;
        while (0 < i && i < S.length && S[i-1] > S[i]) S[--i]--;
        for (int j = i+1; j < S.length; ++j) S[j] = '9';
        return Integer.parseInt(String.valueOf(S));
    }


    /**
     * 贪心算法，对于 N 的每一位数字，我们构建答案 ans 的下一位数字。
     * 我们找到数字 d，其中 d 满足 ans + (d repeating) > N（按字符串比较）且 d-1 满足 ans + (d-1 repeating) <= N，
     * 因此我们将 d-1 添加到我们的答案中。如果找不到这样一个数字 d，则在答案中添加 9。
     */
    public int monotoneIncreasingDigits2(int N){
        String S = String.valueOf(N);
        String ans = "";
        search: for (int i = 0; i < S.length(); ++i) {
            for (char d = '1'; d <= '9'; ++d) {
                if (S.compareTo(ans + repeat(d, S.length() - i)) < 0) {
                    ans += (char) (d - 1);
                    continue search;
                }
            }
            ans += '9';
        }
        return Integer.parseInt(ans);
    }

    public String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; ++i) sb.append(c);
        return sb.toString();
    }

}
