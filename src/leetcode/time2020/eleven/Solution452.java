package leetcode.time2020.eleven;

import java.util.Arrays;

/**
 *
 *
 *
 * @author lyx
 * @date 2020/11/23 18:52
 */
public class Solution452 {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)  return 0;
        Arrays.sort(points,(o1,o2)->{
            if (o1[1] < o2[1]){
                return -1;
            }else if (o1[1] == o2[1]){
                return 0;
            }else{
                return 1;
            }
        });
        int res = 1;
        int n = points.length;
        int end = points[0][1];
        for (int[] point:points) {
            if (point[0] > end){
                end = point[1];
                res++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Solution452 solution452 = new Solution452();
        solution452.findMinArrowShots(new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}});
    }

}
