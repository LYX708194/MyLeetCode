package leetcode.time2021.eight;

/**
 * @author lyx
 * @date 2021/8/31 20:23
 */
public class Solution1109 {

    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking:bookings) {
            ans[booking[0]-1] += booking[2];
            if(booking[1] < n){
                ans[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i-1];
        }
        return ans;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            for (int j = bookings[i][0]; j < bookings[i][1]; j++) {
                ans[j-1] += bookings[i][2];
            }
        }
        return ans;
    }
}
