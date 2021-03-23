package leetcode.offer;

/** 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *  
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/23 16:29
 */
public class Solution51 {

    int[] nums,temp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        temp = new int[nums.length];
        return mergeSort(0,nums.length-1);
    }
    private int mergeSort(int left,int right){
        if (left >= right)  return 0;
        int mid = (left + right) / 2;
        int res = mergeSort(left,mid) + mergeSort(mid + 1,right);
        int i = left,j = mid + 1;
        //用来暂时保存数组
        for (int k = left; k <= right; k++) temp[k] = nums[k];
        for (int k = left; k <= right; k++) {
            //如果剩下的都是右边的数组
            if (i == mid + 1){
                nums[k] = temp[j++];
            }else if (j == right + 1 || temp[i] <= temp[j]){
                //如果剩下的是左边的数组或者左边的小于右边的值
                nums[k] = temp[i++];
            }else{
                //右数组小于左数组，是逆序对，增加逆序对
                nums[k] = temp[j++];
                res += mid - i + 1;
            }
        }
        return res;
    }

}
