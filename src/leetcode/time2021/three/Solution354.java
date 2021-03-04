package leetcode.time2021.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 354 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/4 11:00
 */
public class Solution354 {

    /**
     * 动态规划
     */
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(n == 0)  return 0;
        //w为第一规则升序排序，h为第二规则降序排序
        Arrays.sort(envelopes,(o1, o2) -> {
            if (o1[0] != o2[0])  return o1[0] - o2[0];
            return o2[1] - o1[1];
        });
        int[] f = new int[n];
        Arrays.fill(f,1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                //h[j] < h[i]
                if (envelopes[j][1] < envelopes[i][1]){
                    f[i] = Math.max(f[i],f[j] + 1);
                }
            }
            ans = Math.max(ans,f[i]);
        }
        return ans;
    }


    public int maxEnvelopes2(int[][] envelopes) {
        int n = envelopes.length;
        if(n == 0)  return 0;
        //w为第一规则升序排序，h为第二规则降序排序
        Arrays.sort(envelopes,(o1, o2) -> {
            if (o1[0] != o2[0])  return o1[0] - o2[0];
            return o2[1] - o1[1];
        });
        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            //如果当前位置h大于f中最大值，则可以接在f之后
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
            //否则找出f中比当前高度严格小的最大元素，那么当前位置可以接在那个位置之后，进行替换
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
