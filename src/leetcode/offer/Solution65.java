package leetcode.offer;

/** 不用加减乘除做加法
 * @author lyx
 * @date 2021/3/25 17:24
 */
public class Solution65 {

    public int add(int a, int b) {
        // 当进位为 0 时跳出
        while(b != 0) {
            // c = 进位
            int c = (a & b) << 1;
            // a = 非进位和
            a ^= b;
            // b = 进位
            b = c;
        }
        return a;
    }


}
