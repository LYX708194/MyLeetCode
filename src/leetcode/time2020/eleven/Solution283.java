package leetcode.time2020.eleven;

/**
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/19 13:12
 */
public class Solution283 {

    public void moveZeroes(int[] nums) {
        int i = 0,j = 0;
        int n = nums.length;
        while (j < n){
            if (nums[j] != 0){
                swap(nums,i,j);
                i++;
            }
            j++;
        }
    }
    private void swap(int[] nums,int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution283 solution283 = new Solution283();
        solution283.moveZeroes(new int[]{1,0});
    }

}
