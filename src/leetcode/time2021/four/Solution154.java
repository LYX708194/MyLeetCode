package leetcode.time2021.four;

/**
 * @author lyx
 * @date 2021/4/9 13:05
 */
public class Solution154 {

    public int findMin(int[] nums) {
        int left = 0,right = nums.length - 1;
        while(left < right){
            //如果数组是递增的，那么最左边元素为最小元素
            if (nums[left] < nums[right])  return nums[left];
            int mid = (right - left)/2 + left;
            //如果最左边元素到中间元素是递增的，那么最小元素必定在mid之后
            if (nums[left] < nums[mid]){
                left = mid + 1;
            }else if (nums[left] > nums[mid]){
                //如果最左边元素大于中间元素，则最小元素必定在 left ~ mid 之间
                right = mid;
            }else{
                //如果相等，则移动一位
                left++;
            }
        }
        return nums[left];
    }

}
