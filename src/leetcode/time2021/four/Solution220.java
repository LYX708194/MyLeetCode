package leetcode.time2021.four;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/17 11:38
 */
public class Solution220 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        //有序集合，支持二分查找
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            long u =  nums[i];
//            //小于等于u的最接近的数
//            Long l = set.floor(u);
//            //大于等于u的最接近的数
//            Long r = set.ceiling(u);
//            if (l != null && u - l <= t)    return true;
//            if (r != null && r - u <= t)    return true;
            //也可以找到大于等于x-t的数，然后判断此数是否存在且小于等于x+t，即[x-t,x+t]范围内即存在
            Long ceil = set.ceiling(u - (long)t);
            if (ceil != null && ceil <= (long)nums[i] + (long)t)    return true;
            //将当前数加入有序集合中，并且移除超过k的范围的数，保证滑动窗口保持为k
            set.add(u);
            if (i >= k) set.remove((long)nums[i-k]);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long,Long> map = new HashMap<>();
        //桶的大小
        long size = t + 1;
        for (int i = 0; i < n; i++) {
            long id = getId(nums[i],size);
            //如果桶里有数据，那么直接返回，相同桶内的数肯定满足条件
            if (map.containsKey(id))    return true;
            //小于和大于的桶内有数据且在范围内
            if (map.containsKey(id-1) && Math.abs(nums[i] - map.get(id-1)) < size)  return true;
            if (map.containsKey(id+1) && Math.abs(nums[i] - map.get(id+1)) < size)  return true;
            map.put(id,(long)nums[i]);
            if (i >= k)  map.remove(getId(nums[i-k],size));
        }
        return false;
    }
    private long getId(long u,long size){
        //计算在哪个桶，int 范围内的每一个整数 x 表示为 x=(t+1)×a+b(0≤b≤t) 的形式，这样 x 即归属于编号为 a 的桶
        //对于负数，需要排除0号桶，所以需要先+1，最后再减一
        //例如 get(-1,3)=-1  get(-3,3)=-1 get(-4,3) = -2;
        return u >= 0 ? u / size : (u + 1) / size - 1;
    }


}
