package leetcode.time2021.five;

/**
 * @author lyx
 * @date 2021/5/31 22:54
 */
public class Solution326 {

    public boolean isPowerOfThree(int n) {
        if (n < 1)  return false;
        while (n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }

    public boolean isPowerOfThree2(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }


}
