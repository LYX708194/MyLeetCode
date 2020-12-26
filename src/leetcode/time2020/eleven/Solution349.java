package leetcode.time2020.eleven;

import java.util.*;

/**
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author lyx
 * @date 2020/11/2 18:51
 */
public class Solution349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> ans = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        Arrays.sort(nums2);
        for (int i = 0; i < nums2.length; i++) {
            if (i>0&&nums2[i-1] == nums2[i])    continue;
            if (set.contains(nums2[i])){
                ans.add(nums2[i]);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }


    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int pre = Integer.MAX_VALUE;
        int i = 0,j = 0;
        List<Integer> ans = new LinkedList<>();
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] > nums2[j]){
                j++;
            }else if (nums1[i] < nums2[j]){
                i++;
            }else{
                if (nums1[i] != pre){
                    ans.add(nums1[i]);
                    pre = nums1[i];
                }
                i++;
                j++;
            }
        }
        int[] res = new int[ans.size()];
        for (int k = 0; k < ans.size(); k++) {
            res[k] = ans.get(k);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution349 solution349 = new Solution349();
        solution349.intersection2(new int[]{1,2,2,1},new int[]{2,2});
    }

}
