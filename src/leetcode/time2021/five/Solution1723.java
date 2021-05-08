package leetcode.time2021.five;

/**
 * @author lyx
 * @date 2021/5/8 21:42
 */
public class Solution1723 {

    int[] jobs;
    int n,k;
    int ans = 0x3f3f3f3f;
    public int minimumTimeRequired(int[] _jobs, int _k) {
        jobs = _jobs;
        n = jobs.length;
        k = _k;
        int[] sum = new int[k];
        dfs(0,0,sum,0);
        return ans;
    }

    /**
     * @param index 当前遍历任务位置
     * @param used 已经被使用的工人
     * @param sum 工人负责的情况
     * @param max 最大工作时间
     */
    private void dfs(int index,int used,int[] sum,int max){
        if (max >= ans) return;
        if (index == n){
            ans = max;
            return;
        }
        //优先分配给空闲的
        if (used < k){
            sum[used] = jobs[index];
            dfs(index+1, used+1, sum, Math.max(max,sum[used]));
            sum[used] = 0;
        }
        for (int i = 0; i < used; i++) {
            sum[i] += jobs[index];
            dfs(index+1, used, sum, Math.max(max,sum[i]));
            sum[i] -= jobs[index];
        }
    }


}
