package leecode.offer;

/**
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/20 11:11
 */
public class Solution11 {
    /**
     * 二分法
     *  对于最小值元素下：在最小值右侧的元素，它们的值一定都小于等于 x；而在最小值左侧的元素，它们的值一定都大于等于 x。
     */
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right){
            //如果数组是递增的，那么最左边元素为最小元素
            if (numbers[left] < numbers[right])  return numbers[left];
            //用这种写法而不是(left + right)/2是为了防止溢出
            int mid = left + (right - left) / 2;
            //如果最左边元素到中间元素是递增的，那么最小元素必定在mid之后
            if (numbers[left] < numbers[mid]){
                left = mid + 1;
            }else if (numbers[left] > numbers[mid]){
                //如果最左边元素大于中间元素，则最小元素必定在 left ~ mid 之间
                right = mid;
            }else{
                //如果相等，则移动一位
                left++;
            }
        }
        return numbers[left];
    }


}
