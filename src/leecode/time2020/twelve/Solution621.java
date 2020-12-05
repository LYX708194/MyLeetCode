package leecode.time2020.twelve;

import java.util.*;

/**
 *
 * 621 任务调度器
 *
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 *
 * 示例 1：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * 示例 2：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * 示例 3：
 *
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 *      A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *  
 *
 * 提示：
 *
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/5 12:25
 */
public class Solution621 {

    /**
     * 优先队列实现
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length <= 1 || n <= 0) {
            return tasks.length;
        }
        Map<Character,Integer> map = new HashMap<>();
        for (char task:tasks) {
            map.put(task,map.getOrDefault(task,0)+1);
        }
        int res = 0;
        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> {
            if (map.get(o1) == map.get(o2)){
                return o1 - o2;
            }else{
                return map.get(o2) - map.get(o1);
            }
        });
        for (Character c:map.keySet()) {
            queue.offer(c);
        }
        List<Character> characters = new ArrayList<>();
        while (!queue.isEmpty()){
            characters.clear();
            for (int i = 0; i < n+1; i++) {
                Character c = queue.poll();
                int count = map.get(c);
                map.put(c,count-1);
                res++;
                characters.add(c);
                if (queue.size() == 0){
                    //冷却
                    for (i = i + 1; i < n+1; i++) {
                        res++;
                        characters.add('#');
                    }
                }
            }
            for (Character c:characters) {
                if (map.getOrDefault(c,0) > 0){
                    queue.offer(c);
                }
            }
        }
        for (Character c:characters) {
            if (c == '#')
                res--;
        }
        return res;
    }

    /**
     * 图解
     * https://leetcode-cn.com/problems/task-scheduler/solution/jian-ming-yi-dong-de-javajie-da-by-lan-s-jfl9/
     *
     * maxTimes为出现次数最多的那个任务出现的次数。maxCount为一共有多少个任务和出现最多的那个任务出现次数一样。
     * 图中一共占用的方格即为完成所有任务需要的时间，即：
     *   (maxTimes - 1)*(n + 1) + maxCount
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] cnt = new int[26];
        for (char task:tasks) {
            cnt[task-'A']++;
        }
        Arrays.sort(cnt);
        int maxTimes = cnt[25];
        int maxCount = 0;
        for (int i = 25; i > 0; i--) {
            if (cnt[i] == maxTimes){
                maxCount++;
            }else{
                break;
            }
        }
        int ret = (maxTimes - 1) * (n + 1) + maxCount;
        //tasks.length是可能出现全排列满足不需要冷却，最后又多出来的情况，这种情况下任务长度就是执行长度
        return Math.max(ret,tasks.length);
    }

    public static void main(String[] args) {
        Solution621 solution621 = new Solution621();
        solution621.leastInterval(new char[]{'A','A','A','B','B','B'},2);
    }

}
