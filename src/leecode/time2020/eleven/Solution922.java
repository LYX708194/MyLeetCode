package leecode.time2020.eleven;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/12 9:48
 */
public class Solution922 {
    /**
     * 双指针
     * 为数组的偶数下标部分和奇数下标部分分别维护指针 i, j。随后，在每一步中，
     * 如果 A[i] 为奇数，则不断地向前移动 j（每次移动两个单位），直到遇见下一个偶数。此时，可以直接将 A[i] 与 A[j] 交换。
     * 我们不断进行这样的过程，最终能够将所有的整数放在正确的位置上。
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int i =0,j = 1;
        for (int k = 0; k < A.length; k+=2) {
            if (A[i] % 2 != 0){
                while (A[j] % 2 != 0){
                    j += 2;
                }
                swap(A,i,j);
            }
        }
        return A;
    }
    public void swap(int[] A,int i,int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public int[] sortArrayByParityII2(int[] A) {
        int n = A.length;
        int[] arr = new int[n];
        int i = 0,j = 1;
        for (int x:A) {
            if (x % 2 == 0){
                arr[i] = x;
                i += 2;
            }else{
                arr[j] = x;
                j += 2;
            }
        }
        return arr;
    }

}
