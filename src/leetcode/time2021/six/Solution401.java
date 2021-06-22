package leetcode.time2021.six;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyx
 * @date 2021/6/22 22:44
 */
public class Solution401 {


    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for (int h = 0; h < 12; ++h) {
            for (int m = 0; m < 60; ++m) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }

}
