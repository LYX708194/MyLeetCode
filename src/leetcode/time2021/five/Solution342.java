package leetcode.time2021.five;

/**
 * @author lyx
 * @date 2021/5/31 22:52
 */
public class Solution342 {

    public boolean isPowerOfFour(int n) {
        if (n < 1)  return false;
        while (n % 4 == 0){
            n /= 4;
        }
        return n == 1;
    }

    public boolean isPowerOfFour2(int n) {
        //必定能够被2整除且1为偶数位
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }


}
