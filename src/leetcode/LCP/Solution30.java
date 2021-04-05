package leetcode.LCP;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 *
 * 小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 *
 * 示例 1：
 *
 * 输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]
 *
 * 输出：1
 *
 * 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。
 *
 * 示例 2：
 *
 * 输入：nums = [-200,-300,400,0]
 *
 * 输出：-1
 *
 * 解释：调整访问顺序也无法完成全部房间的访问。
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/p0NxJO
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/5 22:12
 */
public class Solution30 {

    public int magicTower(int[] nums) {
        int n = nums.length;
        if (Arrays.stream(nums).sum() < 0)    return -1;
        int ans = 0;
        long blood = 1;
        //只保存负数
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            blood += num;
            if (num < 0)    queue.offer(num);
            while (blood <= 0){
                if (queue.isEmpty()) return -1;
                blood -= queue.poll();
                ans++;
            }
        }
        return ans;
    }

}
