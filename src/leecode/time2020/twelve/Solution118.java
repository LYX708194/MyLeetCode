package leecode.time2020.twelve;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 118 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/6 12:20
 */
public class Solution118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> one = new ArrayList<>();
            one.add(1);
            for (int j = 1; j < i; j++) {
                List<Integer> pre = res.get(i-1);
                one.add(pre.get(j-1)+pre.get(j));
            }
            if (i > 0)  one.add(1);
            res.add(one);
        }
        return res;
    }

    /**
     * 本行元素等于上一行元素往后错一位再逐个相加：
     *
     *  1 3 3 1 0      1 2 1 0
     *  0 1 3 3 1      0 1 2 1
     *  ---------      -------
     *  1 4 6 4 1      1 3 3 1
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0)   return res;
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> pre = res.get(i-1);
            List<Integer> one = new ArrayList<>();
            for (int j = 0; j <= pre.size(); j++) {
                one.add(getListNum(pre,j-1) + getListNum(pre,j));
            }
            res.add(one);
        }
        return res;
    }
    public static int getListNum(List<Integer> list,int index){
        return index < 0 || index >= list.size() ? 0 : list.get(index);
    }



    public static void main(String[] args) {
        Solution118 solution118 = new Solution118();
        solution118.generate2(5);
    }


}
