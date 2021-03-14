package leetcode.time2021.three;

import leetcode.entity.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的序列化和反序列化
 *
 * @author lyx
 * @date 2021/3/14 13:05
 */
public class Codec297 {


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return dfs(root,new StringBuilder()).toString();
    }
    private StringBuilder dfs(TreeNode root,StringBuilder str){
        if (root == null){
            str.append("null,");
        }else{
            str.append(root.val+",");
            dfs(root.left, str);
            dfs(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_arr = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_arr));
        return rdeserialize(data_list);
    }
    public TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }


}
