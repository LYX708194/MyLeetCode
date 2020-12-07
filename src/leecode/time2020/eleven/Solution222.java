package leecode.time2020.eleven;

import leecode.entity.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/24 10:29
 */
public class Solution222 {

    /**
     * 广度优先
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null)   return 0;
        int res = 1;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        while (!deque.isEmpty()){
            TreeNode treeNode = deque.pollLast();
            res++;
            if (treeNode.left != null)  deque.addFirst(treeNode.left);
            if (treeNode.right != null)  deque.addFirst(treeNode.right);
        }
        return res;
    }

    /**
     * 深度优先
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root){
        return root == null ? 0 : countNodes2(root.left) + countNodes2(root.right) + 1;
    }

    /**
     * 二分查找+位运算
     * 具体做法是，根据节点个数范围的上下界得到当前需要判断的节点个数 k，如果第 k 个节点存在，则节点个数一定大于或等于 k，
     * 如果第 k 个节点不存在，则节点个数一定小于 k，由此可以将查找的范围缩小一半，直到得到节点个数。
     *
     * 如果第 k 个节点位于第 h 层，则 k 的二进制表示包含 h+1 位，其中最高位是 1，
     * 其余各位从高到低表示从根节点到第 k 个节点的路径，0 表示移动到左子节点，1 表示移动到右子节点。通过位运算得到第 kk 个节点对应的路径，
     * 判断该路径对应的节点是否存在，即可判断第 k 个节点是否存在。
     *
     */
    public int countNodes3(TreeNode root){
        if (root == null)   return 0;
        int level = 0;
        TreeNode node = root;
        while (node.left != null){
            level++;
            node = node.left;
        }
        int low = 1 << level,high = (1 << (level + 1)) - 1;
        while (low < high){
            int mid = (high - low + 1) / 2 + low;
            if (exits(root,level,mid)){
                low = mid;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
    public boolean exits(TreeNode root,int level,int k){
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0){
            if ((bits & k) == 0){
                node = node.left;
            }else{
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    /**
     * 先计算树的高度height，然后计算右子树的高度
     * 1，如果右子树的高度等于height-1，说明左子树是完美二叉树，
     * 可以通过公式(2^(height-1))-1计算即可，不需要全部遍历，然后再通过递归的方式计算右子树……，
     *
     * 2，如果右子树的高度不等于height-1，说明右子树是完美二叉树，
     * 只不过比上面那种少了一层，也就是height-2，也可以通过公式(2^(height-2))-1计算，然后再通过递归的方式计算左子树……，
     *
     */
    public int countNodes4(TreeNode root){
        //计算树的高度，
        int height = treeHeight(root);
        //如果树是空的，或者高度是1，直接返回
        if (height == 0 || height == 1)
            return height;
        //如果右子树的高度是树的高度减1，说明左子树是满二叉树，
        //左子树可以通过公式计算，只需要递归右子树就行了
        if (treeHeight(root.right) == height - 1) {
            //注意这里的计算，左子树的数量是实际上是(1 << (height - 1))-1，
            //不要把根节点给忘了，在加上1就是(1 << (height - 1))
            return (1 << (height - 1)) + countNodes4(root.right);
        } else {
            //如果右子树的高度不是树的高度减1，说明右子树是满二叉树，可以通过
            //公式计算右子树，只需要递归左子树就行了
            return (1 << (height - 2)) + countNodes4(root.left);
        }
    }
    private int treeHeight(TreeNode treeNode){
        return treeNode == null ? 0 : 1 + treeHeight(treeNode.left);
    }

    /**
     * 上面方法的非递归版本
     * @param root
     * @return
     */
    public int countNodes5(TreeNode root){
        int count = 0,height = treeHeight(root);
        while (root != null){
            if (treeHeight(root.right) == height - 1){
                //左子树是满二叉树
                count += 1 << (height - 1);
                root = root.right;
            }else{
                count += 1 << (height - 2);
                root = root.left;
            }
            height--;
        }
        return count;
    }


}
