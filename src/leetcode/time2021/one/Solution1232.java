package leetcode.time2021.one;

/**
 * 1232 缀点成线
 *在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 *
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * 示例 1：
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 示例 2：
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 *  
 * 提示：
 *
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/17 10:57
 */
public class Solution1232 {

    /**
     * 斜率相等，（y2-y1）/(x2-x1) = (y3-y1)/(x3-x1)
     * 为了消除斜率不存在的情况，改为相乘（y2-y1）*(x3-x1) = (y3-y1)*(x2-x1)
     * @author lyx
     * @date 2021/1/17 11:08
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        if (n < 3)  return true;
        for (int i = 2; i < n; i++) {
            if ((coordinates[i][1] - coordinates[0][1]) * (coordinates[1][0] - coordinates[0][0])
                != (coordinates[1][1] - coordinates[0][1]) * (coordinates[i][0] - coordinates[0][0])){
                return false;
            }
        }
        return true;
    }

    /**
     * 平移到经过原点，由公式 Ax + By = 0,得出A 和 B，再分别代入看是否匹配
     * @author lyx
     * @date 2021/1/17 11:18
     * @return
     */
    public boolean checkStraightLine2(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }


}
