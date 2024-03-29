package leetcode.time2021.three;

import leetcode.entity.TreeNode;

/** 打家劫舍3
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/27 13:02
 */
public class Solution337 {


    public int rob(TreeNode root) {
        int[] dp = dfs(root);
        return Math.max(dp[0],dp[1]);
    }
    private int[] dfs(TreeNode root){
        if (root == null){
            return new int[]{0,0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        //dp[0]，当前节点不偷，能获得的子树的最大偷取值
        //dp[1]，当前节点偷取加上子树能获得的最大偷取值
        int[] dp = new int[2];
        dp[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        dp[1] = root.val + left[0] + right[0];
        return dp;
    }



}
