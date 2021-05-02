package leetcode.time2021.five;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyx
 * @date 2021/5/2 21:39
 */
public class Solution554 {

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer,Integer> map = new HashMap<>();
        for (List<Integer> widths: wall) {
            int n = widths.size();
            int sum = 0;
            for (int i = 0; i < n-1; i++) {
                //累加右边缘到左边缘的距离
                sum += widths.get(i);
                map.put(sum,map.getOrDefault(sum,0) + 1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        //高度减去穿过缝隙最多的
        return wall.size() - max;
    }

}
