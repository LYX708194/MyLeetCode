package leetcode.time2021.four;

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A" ，从而得到 "AAA" ，或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
 *
 * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 *
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 *
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 示例 4：
 *
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串开头的 0 无法指向一个有效的字符。 
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/13 14:49
 */
public class Solution91 {


    public int numDecodings3(String s) {
        if (s.charAt(0) == '0') return 0;
        int n = s.length();
        //dp优化,pre当成dp[-1],cur当作dp[0]，这样就不用堆边界左特殊处理
        int pre = 1,cur = 1;
        for (int i = 1; i < n; i++) {
            int temp = cur;
            if (s.charAt(i) == '0')
                //这两个只能合并
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2') cur = pre;
                else return 0;
            else if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6'))
                //合并的情况+不合并的情况
                cur = cur + pre;
            pre = temp;
        }
        return cur;
    }

    public int numDecodings2(String s) {
        if (s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '0'){
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2'){
                    //边界情况处理
                    if (i == 1) dp[i] = 1;
                    //否则这两个只能合并
                    else    dp[i] = dp[i-2];
                }
                //后面出现了0开头的情况，不满足
                else   return 0;
            }else{
                int num = (s.charAt(i-1) - '0') * 10 + s.charAt(i) - '0';
                if (num >= 11 && num <= 26){
                    //边界处理
                    if (i == 1) dp[i] = dp[0] + 1;
                    //单个字符解码的情况+两个字符一块解码的情况
                    else dp[i] = dp[i-1] + dp[i-2];
                }
                //只能一个解码
                else   dp[i] = dp[i-1];
            }
        }
        return dp[n-1];
    }


    /**
     * 超时了
     */
    int ans = 0;
    public int numDecodings(String s) {
        dfs(s,0);
        return s.charAt(0) == '0' ? 0 : ans;
    }
    private void dfs(String s,int index){
        if (index == s.length()){
            ans++;
            return;
        }
        if (s.charAt(index) == '0'){
            return;
        }
        int num = 0;
        num = s.charAt(index) - '0';
        dfs(s, index+1);
        if(index < s.length()-1){
            num = num * 10 + s.charAt(index+1) - '0';
            if (num <= 26){
                dfs(s, index+2);
            }
        }
    }

}
