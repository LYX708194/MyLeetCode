package leetcode.time2021.one;

import java.util.ArrayList;
import java.util.List;

/**
 * 1018 可被五整除的二进制前缀
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 *
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 * 示例 2：
 *
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 * 示例 3：
 *
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 * 示例 4：
 *
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 30000
 * A[i] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/14 12:08
 */
public class Solution1018 {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int num = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
//            //当A太大时测试案例没过，num溢出，改为long类型后还是有几个案例没过
//            num <<= 1;
//            num += A[i];
//            if (num % 5 == 0 || num == 0){
//                ans.add(true);
//            }else{
//                ans.add(false);
//            }
            //对任意 0≤i<n，都有 Mi = N mod 5
            num = ((num << 1) + A[i]) % 5;
            ans.add(num == 0);
        }
        return ans;
    }

    /**
     * 只保留最后一位数就行
     * @author lyx
     * @date 2021/1/14 12:27
     * @return
     */
    public List<Boolean> prefixesDivBy52(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int num = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            num = ((num << 1) + A[i]) % 10;
            ans.add(num % 5 == 0);
        }
        return ans;
    }

}
