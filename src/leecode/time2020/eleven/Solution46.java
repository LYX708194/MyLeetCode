package leecode.time2020.eleven;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/11/10 11:13
 */
public class Solution46 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        back(nums,new ArrayList<>());
        return res;
    }
    void back(int[] nums,List<Integer> ans){
        if (ans.size() == nums.length){
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (ans.contains(nums[i])){
                continue;
            }
            ans.add(nums[i]);
            back(nums, ans);
            ans.remove(ans.size()-1);
        }
    }

    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        solution46.permute(new int[]{1,2,3});
    }

}
