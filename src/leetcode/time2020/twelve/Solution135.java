package leetcode.time2020.twelve;

import java.util.Arrays;

/**
 * 135 分发糖果
 *
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 *
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/24 14:45
 */
public class Solution135 {

    public int candy(int[] ratings) {
        int length = ratings.length;
        int[] count = new int[length];
        //默认给每个孩子一个糖果
        Arrays.fill(count, 1);
        //先从左往右遍历
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1])
                count[i] = count[i - 1] + 1;
        }
        //因为下面的for循环中，total没有统计最后一个孩子的糖果，所以这里total
        //的默认值就是最后那个孩子的糖果数量
        int total = count[length - 1];
        //从右往左遍历，然后顺便累加total的值
        for (int i = length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i])
                count[i - 1] = Math.max(count[i - 1], count[i] + 1);
            total += count[i - 1];
        }
        return total;
    }

    /**
     * 一次遍历
     * @author lyx
     * @date 2020/12/24 15:17
     * @return
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            //递增序列
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                //这里从1开始加，和从最高开始减结果是相同的
                dec++;
                //前边递增序列的最高分配糖果数不够让递减序列减到1
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

}
