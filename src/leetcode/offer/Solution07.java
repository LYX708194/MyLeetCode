package leetcode.offer;

import leetcode.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/14 10:34
 */
public class Solution07 {
    //利用原理,先序遍历的第一个节点就是根。在中序遍历中通过根 区分哪些是左子树的，哪些是右子树的
    //左右子树，递归，用map存储中序遍历的节点位置
    Map<Integer,Integer> map = new HashMap<>();
    int[] preorder;

    /**
     * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return myBuildTree(0,0,preorder.length-1);
    }

    /**
     * @param root 根节点在前序遍历的索引
     * @param left 子树在中序遍历的左边界
     * @param right 子树在中序遍历的右边界
     * @return
     */
    public TreeNode myBuildTree(int root,int left,int right){
        if (left > right)   return null;
        //建立根节点
        TreeNode node = new TreeNode(preorder[root]);
        // 划分根节点、左子树、右子树
        int i = map.get(preorder[root]);
        //左子树的根节点就是 左子树的(前序遍历）第一个，就是+1,左边边界就是left，右边边界是中间区分的i-1
        node.left = myBuildTree(root + 1, left, i-1);
        //由根节点在中序遍历的i 区分成2段,i 就是根所在的位置
        //右子树的根，就是右子树（前序遍历）的第一个,就是当前根节点 加上左子树的数量
        // root 当前的根  左子树的长度 = 左子树的左边-右边 (i-1 - left +1) 。最后+1就是右子树的根了
        node.right = myBuildTree(root + i - left + 1, i+1, right);
        return node;
    }


}
