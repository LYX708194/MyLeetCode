package leecode.time2020.twelve;

import java.util.*;

/**
 * 659 分割数组为连续子序列
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *  
 *
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *  
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/4 10:23
 */
public class Solution659 {

    /**
     * 哈希表+最小堆
     *
     * 哈希表的键为子序列的最后一个数字，值为最小堆，用于存储所有的子序列长度，最小堆满足堆顶的元素是最小的，因此堆顶的元素即为最小的子序列长度。
     * 遍历数组，当遍历到元素 x 时，可以得到一个以 x 结尾的子序列。
     * 如果哈希表中存在以 x-1 结尾的子序列，则取出以 x-1 结尾的最小的子序列长度，将子序列长度加 1 之后作为以 x 结尾的子序列长度。
     * 此时，以 x−1 结尾的子序列减少了一个，以 x 结尾的子序列增加了一个。
     * 如果哈希表中不存在以 x-1 结尾的子序列，则新建一个长度为 1 的以 x 结尾的子序列。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/solution/fen-ge-shu-zu-wei-lian-xu-zi-xu-lie-by-l-lbs5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)){
                map.put(num,new PriorityQueue<>());
            }
            if (map.containsKey(num-1)){
                int preLength = map.get(num-1).poll();
                if (map.get(num - 1).isEmpty()){
                    map.remove(num - 1);
                }
                map.get(num).offer(preLength + 1);
            }else {
                map.get(num).offer(1);
            }
        }
        for (Map.Entry<Integer,PriorityQueue<Integer>> entry:map.entrySet()) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3){
                return false;
            }
        }
        return true;
    }

    /**
     * 贪心算法
     *
     *
     * @param nums
     * @return
     */
    public boolean isPossible2(int[] nums) {
        Map<Integer,Integer> countMap = new HashMap<>();
        //定义一个哈希表记录每个元素出现次数
        for (int num:nums) {
            countMap.put(num,countMap.getOrDefault(num,0)+1);
        }
        //定义一个哈希表记录最长的子序列
        Map<Integer,Integer> tail = new HashMap<>();
        for (int num:nums) {
            int count = countMap.getOrDefault(num,0);
            if (count > 0){
                int preEndCount = tail.getOrDefault(num - 1,0);
                if (preEndCount > 0){
                    //前面还有数字可以构成以 num 结尾的子序列，覆盖前一个子序列，当前节点杰炜的子序列加 1
                    countMap.put(num,count-1);
                    tail.put(num-1,preEndCount-1);
                    tail.put(num,tail.getOrDefault(num,0)+1);
                }else{
                    //前面没有节点可以构成子序列，往后面找
                    int count1 = countMap.getOrDefault(num+1,0);
                    int count2 = countMap.getOrDefault(num+2,0);
                    if (count1 > 0 && count2 > 0){
                        //找到后面两个节点可以构成子序列，添加上
                        countMap.put(num,count-1);
                        countMap.put(num+1,count1-1);
                        countMap.put(num+2,count2-1);
                        tail.put(num+2,tail.getOrDefault(num+2,0)+1);
                    }else{
                        //前后都不能构成子序列，说明构造失败
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 贪心的在数量下降的地方断开呢？
     * 因为如果把数量下降后面的和前面的在一组，如果可以（前面一定是有平的，不然一定不能成功分（数量极大值那里会匹配不了）），那么不把下降点之后的包含在前面的组内，一定也可以，因为前面会剩余。
     * 如
     * num:1 2 3 4 5 6 先选1 2 3 4的话下一次就是num:1 2 3 4 5 6
     * cnt:2 2 2 2 1 1 先选1 2 3 4的话下一次就是cnt:1 1 1 1 1 1
     * 如
     * num:1 2 3 4 5 6 如果不在4处断开 下一次的4就不能匹配
     * cnt:1 1 1 2 1 1
     *
     * 作者：love-a-wild-horse
     * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/solution/tan-xin-de-mei-ci-zai-shu-liang-xia-jian-m4vp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public boolean isPossible3(int[] nums){
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for (int num:nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int len = 0,loss = 0,n = nums.length,lastNum = 0;
        while (loss < n){
            len = 0;
            for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
                //如果这个数还没被列入子序列
                if (entry.getValue() > 0){
                    if (len == 0){
                        //如果还没开始
                        len++;
                        map.put(entry.getKey(),entry.getValue()-1);
                        lastNum = entry.getKey();
                    }else if (entry.getKey() == lastNum+1){
                        if (entry.getValue() > map.getOrDefault(entry.getKey()-1,0)){
                            len++;
                            map.put(entry.getKey(),entry.getValue()-1);
                            lastNum = entry.getKey();
                        }else{
                            //过了下降延，可以退出循环了
                            break;
                        }
                    }else{
                        //不是递增的，可以退出循环了
                        break;
                    }
                }
            }
            if (len < 3) return false;
            loss += len;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution659 solution659 = new Solution659();
        solution659.isPossible3(new int[]{1,2,3,4,5,6});
    }

}
