package leetcode.time2021.three;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**合并区间
 * @author lyx
 * @date 2021/3/17 10:48
 */
public class Solution56 {

    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new LinkedList<>();
        Arrays.sort(intervals,(o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int n = intervals.length;
        for (int i = 0; i < n;) {
            int end = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= end){
                end = Math.max(end,intervals[j++][1]);
            }
            ans.add(new int[]{intervals[i][0],end});
            i = j;
        }
        return ans.toArray(new int[ans.size()][2]);
    }

}
