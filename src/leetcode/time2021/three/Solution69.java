package leetcode.time2021.three;

/**  x 的平方根
 * @author lyx
 * @date 2021/3/17 12:04
 */
public class Solution69 {

    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        //二分查找
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    public int mySqrt2(int x) {
        //牛顿迭代法
        if (x == 0) {
            return 0;
        }
        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

}
