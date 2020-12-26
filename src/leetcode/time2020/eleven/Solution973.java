package leetcode.time2020.eleven;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author lyx
 * @date 2020/11/9 13:10
 */
public class Solution973 {

    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]);
            }
        });
        return Arrays.copyOf(points, K);
    }

    /**
     * 我们也可以借鉴快速排序的思想。
     *
     * 快速排序中的划分操作每次执行完后，都能将数组分成两个部分，其中小于等于分界值 pivot 的元素都会被放到左侧部分，
     * 而大于 pivot 的元素都都会被放到右侧部分。与快速排序不同的是，在本题中我们可以根据 K 与 pivot 下标的位置关系，
     * 只处理划分结果的某一部分（而不是像快速排序一样需要处理两个部分）。
     * 我们定义函数 random_select(left, right, K) 表示划分数组 points 的 [left,right] 区间，并且需要找到其中第 K 个距离最小的点。
     * 在一次划分操作完成后，设 pivot 的下标为 ii，即区间 [left,i−1] 中的点的距离都小于等于 pivot，
     * 而区间 [i+1,right] 的点的距离都大于 pivot。此时会有三种情况：
     *
     * 如果 K =i−left+1，那么说明 pivot 就是第 K 个距离最小的点，我们可以结束整个过程；
     *
     * 如果 K < i−left+1，那么说明第 K 个距离最小的点在 pivot 左侧，因此递归调用 random_select(left, i - 1, K)；
     *
     * 如果 K > i−left+1，那么说明第 K 个距离最小的点在 pivot 右侧，因此递归调用 random_select(i + 1, right, K - (i - left + 1))。
     *
     * 在整个过程结束之后，第 K 个距离最小的点恰好就在数组points 中的第 K 个位置，并且其左侧的所有点的距离都小于它。此时，我们就找到了前 K 个距离最小的点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/zui-jie-jin-yuan-dian-de-k-ge-dian-by-leetcode-sol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[][] kClosest2(int[][] points, int K) {
        int n = points.length;
        random_select(points, 0, n - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }
    public void random_select(int[][] points, int left, int right, int K) {
        int pivotId = left + new Random().nextInt(right - left + 1);
        int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
        swap(points, right, pivotId);
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= pivot) {
                ++i;
                swap(points, i, j);
            }
        }
        ++i;
        swap(points, i, right);
        // [left, i-1] 都小于等于 pivot, [i+1, right] 都大于 pivot
        if (K < i - left + 1) {
            random_select(points, left, i - 1, K);
        } else if (K > i - left + 1) {
            random_select(points, i + 1, right, K - (i - left + 1));
        }
    }
    public void swap(int[][] points, int index1, int index2) {
        int[] temp = points[index1];
        points[index1] = points[index2];
        points[index2] = temp;
    }


}
