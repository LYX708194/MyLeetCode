package leecode.time2020.eleven;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明: 
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/18 10:14
 */
public class Solution134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;
        int n = gas.length;
        for(; index < n ; index++){
            int curGas = gas[index];
            int i = index;
            boolean flag = true;
            for (; i < n; i++) {
                if ( i != index )   curGas += gas[i];
                curGas -= cost[i];
                if (curGas < 0){
                    flag = false;
                    break;
                }
            }
            if ( index != 0 && flag){
                for (i = 0; i < index ; i++) {
                    curGas += gas[i];
                    curGas -= cost[i];
                    if (curGas < 0){
                        flag = false;
                        break;
                    }
                }
            }
            if (flag)   return index;
        }
        return -1;
    }

    /**
     * 单次遍历
     * 跑完全程再回到起点，总油量剩余值的任意部分都需要在X轴以上，且跑到终点时：总剩余汽油量 >= 0。
     *
     * 为了让黑色折线图任意部分都在 X 轴以上，我们需要向上移动黑色折线图，直到所有点都在X轴或X轴以上。此时，处在X轴的点即为出发点。即黑色折线图的最低值的位置：index = 3。
     *
     * 作者：cyaycz  （有图解）
     * 链接：https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }
        //数组0 ~ len-1，算法里面最后的取余，是为了将len调整为0
        return spare < 0 ? -1 : (minIndex + 1) % len;
    }


    public static void main(String[] args) {
        Solution134 solution134 = new Solution134();
        solution134.canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2});
    }

}
