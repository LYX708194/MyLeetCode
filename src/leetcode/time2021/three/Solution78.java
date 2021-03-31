package leetcode.time2021.three;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyx
 * @date 2021/3/31 9:01
 */
public class Solution78 {

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        //先加入空集
        results.add(new ArrayList<>());
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int size = results.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = new ArrayList<>(results.get(j));
                temp.add(nums[i]);
                results.add(temp);
            }
        }
        return results;
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        subsetsBack(0,nums,results,new ArrayList<>());
        return results;
    }
    void subsetsBack(int cur,int[] nums,List<List<Integer>> results,List<Integer> result){
        if (cur == nums.length){
            results.add(new ArrayList<>(result));
            return;
        }
        //选择当前位置
        result.add(nums[cur]);
        subsetsBack(cur+1,nums,results,result);
        //不选择当前位置
        result.remove(result.size()-1);
        subsetsBack(cur+1, nums, results, result);
    }

}
