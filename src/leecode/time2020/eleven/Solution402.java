package leecode.time2020.eleven;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/15 13:17
 */
public class Solution402 {

    /**
     * 对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小，例如，对于 A = 1axxxA=1axxx，B = 1bxxxB=1bxxx，如果 a > ba>b 则 A > BA>B。
     * 基于此，我们可以知道，若要使得剩下的数字最小，需要保证靠前的数字尽可能小。
     * 可以得出「删除一个数字」的贪心策略：
     * 给定一个长度为 nn 的数字序列 [D0,D1,D2,D3,D4]，从左往右找到第一个位置 i（i>0）使得 Di<Di-1，并删去 Di−1；如果不存在，说明整个数字序列单调不降，删去最后一个数字即可。
     * 基于此，我们可以每次对整个数字序列执行一次这个策略；删去一个字符后，剩下的 n-1 长度的数字序列就形成了新的子问题，可以继续使用同样的策略，直至删除 k 次。
     *最终，从栈底到栈顶的答案序列即为最小数。
     * 考虑到栈的特点是后进先出，如果通过栈实现，则需要将栈内元素依次弹出然后进行翻转才能得到最小数。为了避免翻转操作，可以使用双端队列代替栈的实现。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        //保证序列是递增的
        for (int i = 0; i < length; i++) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit){
                deque.pollLast();
                k--;
            }
            deque.addLast(digit);
        }
        //里面是递增序列了，则删掉最后最大的数字，知道删除k个
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean isZero = true;
        while (!deque.isEmpty()){
            char digit = deque.pollFirst();
            if (isZero && digit == '0'){
                continue;
            }
            isZero = false;
            stringBuilder.append(digit);
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

}
