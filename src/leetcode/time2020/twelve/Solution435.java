package leetcode.time2020.twelve;

import java.util.Arrays;

/**
 * 435 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/31 10:27
 */
public class Solution435 {

    /**
     * 贪心，报六较小右区间
     * @author lyx
     * @date 2020/12/31 10:45
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n < 2) return 0;
        Arrays.sort(intervals,(o1,o2)->(o1[1] - o2[1]));
        int min = intervals[0][1];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if(intervals[i][0] < min){
                //重叠了，必须删除，更新尾部的位置，取尾部比较小的
                min = Math.min(min, Math.min(intervals[i][1], intervals[i - 1][1]));
                ans++;
            }else{
                min = intervals[i][1];
            }
        }
        return ans;
    }


}
