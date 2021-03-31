package leetcode.time2021.three;

import java.util.*;

/** 子集2
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/31 8:37
 */
public class Solution90 {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> res = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        back(nums,0);
        //补个空集
        ans.add(new LinkedList<>());
        return ans;
    }
    private void back(int[] nums,int index){
        if (index == nums.length){
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1])  continue;
            res.add(nums[i]);
            ans.add(new LinkedList<>(res));
            back(nums, i + 1);
            res.remove(res.size()-1);
        }
    }


    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            res.clear();
            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    res.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<Integer>(res));
            }
        }
        return ans;
    }

}
