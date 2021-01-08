package leetcode.time2021.one;

/**
 * 189 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/8 13:05
 */
public class Solution189 {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 记录交换位置的次数
        int count = 0;
        for(int start = 0; count < n; start++) {
            int cur = start;
            int pre = nums[cur];
            do{
                int next = (cur + k) % n;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            // 循环暂停，回到起始位置,如果count不为n，说明没有全部遍历，需要+1再遍历
            }while(start != cur)  ;
        }
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        //先反转全部，再分别反转前 k 个和后 n-k 个
        reverse(nums,0,n - 1);
        reverse(nums,0,k - 1);
        reverse(nums,k,n - 1);

    }
    public void reverse(int[] nums,int left,int right){
        while (left < right){
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6};
        Solution189 solution189 = new Solution189();
        solution189.rotate(nums,3);
    }


}
