package leetcode.time2021.five;

/**
 * @author lyx
 * @date 2021/5/30 21:13
 */
public class Solution231 {

    public boolean isPowerOfTwo(int n) {
        return (n & (n-1)) == 0 && n > 0;
    }


    //超时
    public boolean isPowerOfTwo2(int n) {
        int x = 1;
        while(x <= n){
            if(x == n)  return true;
            x *= 2;
        }
        return false;
    }

}
