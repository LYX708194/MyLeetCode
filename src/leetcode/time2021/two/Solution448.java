package leetcode.time2021.two;

import java.util.LinkedList;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/13 16:48
 */
public class Solution448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        int n = nums.length;
        int[] cnt = new int[n + 1];
        for (int num:nums) {
            cnt[num]++;
        }
        for (int i = 0; i <= n; i++) {
            if (cnt[i] == 0)    res.add(i);
        }
        return res;
    }


    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        List<Integer> res = new LinkedList<>();
        //修改对应数字的下标，因为可能此数字已遍历，需要求余
        for (int num:nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        //此位置数字小于n，说明之前没有遍历到这个数字
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }


}
