package leetcode.time2021.one;

import java.util.HashMap;
import java.util.Map;

/**
 * 1128 等价多米诺骨牌对的数量
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 *
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 *
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 *
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 *  
 *
 * 示例：
 *
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/26 9:36
 */
public class Solution1128 {

    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] map = new int[10][10];
        int res = 0;
        for (int i = 0; i < dominoes.length; i++) {
            res += map[dominoes[i][0]][dominoes[i][1]];
            map[dominoes[i][0]][dominoes[i][1]]++;
            if (dominoes[i][0] != dominoes[i][1]) {
                map[dominoes[i][1]][dominoes[i][0]]++;
            }
        }
        return res;
    }

    /**
     * 直接让每一个二元对都变为指定的格式，即第一维必须不大于第二维。这样两个二元对「等价」当且仅当两个二元对完全相同。
     * 注意到二元对中的元素均不大于 9，因此可以将每一个二元对拼接成一个两位的正整数，
     * 即 (x,y)→10x+y。这样就无需使用哈希表统计元素数量，而直接使用长度为 100 的数组即可。
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs2(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    public int numEquivDominoPairs3(int[][] dominoes) {
        // 为了避免哈希表自动扩容，根据题目的数据范围，设置哈希表初始化的大小为 100
        // Pair 类重写了 hashCode() 和 equals() 方法
        Map<Pair, Integer> freq = new HashMap<>(100);
        for (int[] dominoe : dominoes) {
            Pair key = new Pair(dominoe[0], dominoe[1]);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        // 根据组合数公式 C_n^2 = (n * (n - 1)) / 2 计算等价骨牌能够组成的组合数，再求和
        int count = 0;
        for (int f : freq.values()) {
            count += (f * (f - 1)) / 2;
        }
        return count;
    }

    private class Pair {

        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 让有序数对 [a, b] 和 [b, a] 认为是相等的对象
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return key == pair.key && value == pair.value || key == pair.value && value == pair.key;
        }

        /**
         * 让相同的数对映射到同一个位置
         *
         * @return
         */
        @Override
        public int hashCode() {
            if (key > value) {
                return value * 10 + key;
            }
            return key * 10 + value;
        }
    }

}
