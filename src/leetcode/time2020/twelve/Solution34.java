package leetcode.time2020.twelve;

/**
 *
 * 34 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/1 11:32
 */
public class Solution34 {


    public int[] searchRange(int[] nums, int target) {
        int[] res;
        int find = binaryFind(nums,target);
        if (find == -1) return new int[]{-1,-1};
        int left = find - 1,right = find + 1;
        while (left >= 0 && nums[left] == target){
            left--;
        }
        while (right < nums.length && nums[right] == target){
            right++;
        }
        return new int[]{left+1,right-1};
    }

    private int binaryFind(int[] nums,int target){
        int left = 0,right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }


    public int[] searchRange2(int[] nums, int target){
        int left = binaryFind(nums, target,true);
        int right = binaryFind(nums, target,false) - 1;
        if (left <= right && right < nums.length && nums[left] == target && nums[right] == target){
            return new int[]{left,right};
        }
        return new int[]{-1,-1};
    }

    private int binaryFind(int[] nums,int target,boolean lower){
        int left = 0,right = nums.length - 1,ans = nums.length;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)){
                right = mid - 1;
                ans = mid;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        solution34.searchRange2(new int[]{5,6,6,7,7,10},6);
    }


}
