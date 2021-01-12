package leetcode.time2021.one;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 *
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/12 12:54
 */
public class Solution207 {

    /**
     * 广度优先遍历
     * @author lyx
     * @date 2021/1/12 13:07
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //记录节点的入度
        int[] indegrees = new int[numCourses];
        //记录出度的节点
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // 初始化出度和入度
        for(int[] cp : prerequisites) {
            //入度+1
            indegrees[cp[0]]++;
            //添加出度节点
            adjacency.get(cp[1]).add(cp[0]);
        }
        //获得所有入度为0的节点
        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i);
        // 从入度为0的节点开始广搜
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            //每次出栈数量减一
            numCourses--;
            for(int cur : adjacency.get(pre)){
                //遍历到他就入度减一，为0时入栈
                if(--indegrees[cur] == 0) queue.add(cur);
            }
        }
        //判断是否有节点没入栈，如果不为0说明有环
        return numCourses == 0;
    }

    /**
     * 广度优先遍历
     * @author lyx
     * @date 2021/1/12 13:09
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        //用flag来表示节点状态，1为已被当前节点启动的 DFS 访问，-1表示已被其他节点启动的 DFS 访问，0表示未访问
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            //添加出度
            adjacency.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flags, i)) return false;
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        //本轮第二次访问，说明有环
        if(flags[i] == 1) return false;
        //已被其他节点访问，无需重复访问
        if(flags[i] == -1) return true;
        flags[i] = 1;
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j)) return false;
        flags[i] = -1;
        return true;
    }

}
