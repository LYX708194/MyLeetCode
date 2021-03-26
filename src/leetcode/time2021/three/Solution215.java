package leetcode.time2021.three;

import java.util.PriorityQueue;

/** 数组中的第k个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/26 13:20
 */
public class Solution215 {

    public int findKthLargest(int[] nums, int k){
        int len = nums.length,n = len;
        //构造大顶堆
        for (int i = len / 2; i >= 0; i--) {
            headAdjust(nums,i,len);
        }
        //删除前k-1个最大值，最后堆顶的就是第k大的
        for (int i = len - 1; i >= len - k + 1; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            //每次删除最大值后修改堆的大小
            n--;
            headAdjust(nums,0,n);
        }
        return nums[0];
    }
    private void headAdjust(int[] nums,int k,int length){
        int max = k;
        int l = k * 2,r = l + 1;
        if (l < length && nums[l] > nums[max])  max = l;
        if (r < length && nums[r] > nums[max])  max = r;
        if (max != k){
            int temp = nums[k];
            nums[k] = nums[max];
            nums[max] = temp;
            headAdjust(nums, max, length);
        }
    }

    public int findKthLargest2(int[] nums, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

}
