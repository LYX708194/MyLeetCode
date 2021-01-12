package leetcode.time2021.one;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 210 课程表2
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 *
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 *
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 *
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/12 13:30
 */
public class Solution210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        int[] ans = new int[numCourses];
        int index = 0;
        // 从入度为0的节点开始广搜
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            ans[index++] = pre;
            for(int cur : adjacency.get(pre)){
                //遍历到他就入度减一，为0时入栈
                if(--indegrees[cur] == 0) queue.add(cur);
            }
        }
        //判断是否有节点没入栈，如果不为0说明有环
        return index == numCourses ? ans : new int[]{};
    }

    int[] result;
    int index ;
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        // 存储有向图
        List<List<Integer>> edges = new ArrayList<List<Integer>>();;
        // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
        int[] visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        // 判断有向图中是否有环
        boolean valid = true;
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        for(int[] cp : prerequisites)
            //添加出度
            edges.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs(i, visited,edges)) return new int[]{};
        return result;
    }
    private boolean dfs(int i,int[] visited,List<List<Integer>> edges){
        if (visited[i] == 1) return false;
        if (visited[i] == 2)    return true;
        visited[i] = 1;
        for (Integer integer : edges.get(i)){
            if (!dfs(integer,visited,edges))    return false;
        }
        visited[i] = 2;
        result[index--] = i;
        return true;
    }

}
