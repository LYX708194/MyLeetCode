package leetcode.time2021.three;

import java.util.*;

/**132模式
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 *
 * 注意：n 的值小于15000。
 *
 * 示例1:
 *
 * 输入: [1, 2, 3, 4]
 *
 * 输出: False
 *
 * 解释: 序列中不存在132模式的子序列。
 * 示例 2:
 *
 * 输入: [3, 1, 4, 2]
 *
 * 输出: True
 *
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * 示例 3:
 *
 * 输入: [-1, 3, 2, 0]
 *
 * 输出: True
 *
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/24 9:22
 */
public class Solution456 {

    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        //枚举 1，单调栈存的是 3，每次弹出数据的时候更新最大值为2，保证 1 的可能性更多
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; --i) {
            //1 < 2，且保证栈中一定有元素，且为 3
            if (nums[i] < maxK) {
                return true;
            }
            //修改 2 为 栈中最大值
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            //重置3
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {
        //枚举 2 解法
        int n = nums.length;
        List<Integer> candidateI = new ArrayList<Integer>();
        candidateI.add(nums[0]);
        List<Integer> candidateJ = new ArrayList<Integer>();
        candidateJ.add(nums[0]);

        for (int k = 1; k < n; ++k) {
            int idxI = binarySearchFirst(candidateI, nums[k]);
            int idxJ = binarySearchLast(candidateJ, nums[k]);
            if (idxI >= 0 && idxJ >= 0) {
                if (idxI <= idxJ) {
                    return true;
                }
            }

            if (nums[k] < candidateI.get(candidateI.size() - 1)) {
                candidateI.add(nums[k]);
                candidateJ.add(nums[k]);
            } else if (nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                int lastI = candidateI.get(candidateI.size() - 1);
                while (!candidateJ.isEmpty() && nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                    candidateI.remove(candidateI.size() - 1);
                    candidateJ.remove(candidateJ.size() - 1);
                }
                candidateI.add(lastI);
                candidateJ.add(nums[k]);
            }
        }

        return false;
    }

    public int binarySearchFirst(List<Integer> candidate, int target) {
        int low = 0, high = candidate.size() - 1;
        if (candidate.get(high) >= target) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int num = candidate.get(mid);
            if (num >= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int binarySearchLast(List<Integer> candidate, int target) {
        int low = 0, high = candidate.size() - 1;
        if (candidate.get(low) <= target) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            int num = candidate.get(mid);
            if (num <= target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    public boolean find132pattern3(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }
        //枚举3
        for (int j = 1; j < n - 1; ++j) {
            //1 < 3
            if (leftMin < nums[j]) {
                //返回大于或等于给定键的最小键，如果不存在这样的键，则返回null。
                Integer next = rightAll.ceilingKey(leftMin + 1);
                //3>2
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            //没有找到，继续查找，1取较小者
            leftMin = Math.min(leftMin, nums[j]);
            //右侧数组边界修改
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }
        return false;
    }


    /**
     * 回溯写法，超时了，过测试用例89/101
     */
    public boolean find132pattern(int[] nums) {
        return back(nums,new ArrayList<>(),0);
    }
    private boolean back(int[] nums, List<Integer> list, int index){
        if (list.size() == 3) return true;
        for (int i = index; i < nums.length; i++) {
            boolean flag = false;
            if (list.size() == 0){
                list.add(nums[i]);
                flag = true;
            } else if (list.size() == 1 && nums[i] > list.get(0)){
                list.add(nums[i]);
                flag = true;
            } else if (list.size() == 2 && nums[i] > list.get(0) && nums[i] < list.get(1)){
                list.add(nums[i]);
                flag = true;
            }
            if (back(nums, list, i + 1))    return true;
            if (!list.isEmpty() && flag){
                list.remove(list.size()-1);
            }
        }
        return false;
    }

}
