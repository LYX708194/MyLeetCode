package leetcode.time2021.two;

/**
 * 765 情侣牵手
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 *
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 *
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 * 示例 1:
 *
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 示例 2:
 *
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 说明:
 *
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/14 18:52
 */
public class Solution765 {

    /**
     * 贪心，每次遍历偶数位的数，找到他的情侣交换位置，直到最后
     */
    public int minSwapsCouples(int[] row) {
        int res = 0;
        for (int i = 0; i < row.length - 1; i += 2) {
            //如果不是对象，则找出对象然后交换位置
            //求对象时用了 x^1,当 x是偶数，则其二进制的末尾是 0，所以 x^1 将其二进制的末尾改成 1，于是得到了x的对象x+1。
            //当 x是奇数，则其二进制的末尾是 1，所以 x^1 将其二进制的末尾改成 0，于是得到了x的对象x−1。
            if (row[i] != (row[i+1]^1)){
                for (int j = i+1; j < row.length; j++) {
                    if (row[j] == (row[i]^1)){
                        swap(row,j,i+1);
                        break;
                    }
                }
                res++;
            }
        }
        return res;
    }
    private void swap(int[] row,int x,int y){
        int temp = row[x];
        row[x] = row[y];
        row[y] = temp;
    }

    /**
     * 贪心法，空间换时间
     */
    public int minSwapsCouples2(int[] row) {
        int n = row.length;
        //索引表，row[i] = num => map[num] = i;
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[row[i]] = i;
        }
        int count = 0;
        for (int i = 0; i < n-1; i += 2) {
            int p1 = row[i];
            int p2 = (p1 & 1) == 0 ? p1 + 1 : p1 - 1;
            //相邻两个不是情侣，交换后一个
            if (row[i+1] != p2){
                swap(row,map,i+1,map[p2]);
                count++;
            }
        }
        return count;
    }
    private void swap(int[] row,int[] map,int x,int y){
        int temp = row[x];
        row[x] = row[y];
        row[y] = temp;
        map[row[x]] = x;
        map[row[y]] = y;
    }

    /**
     * 并查集
     */
    public int minSwapsCouples3(int[] row) {
        int len = row.length;
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        //总共N对情侣，每n对情侣坐错位置混合在一起，总共需要交换n-1次
        return N - unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }


}
