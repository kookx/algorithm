//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 
// 👍 344 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：二叉树的所有路径
public class P257BinaryTreePaths{
    public static void main(String[] args) {
        Solution solution = new P257BinaryTreePaths().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 解法1：dfs
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        recursion(res, root, "");
        return res;
    }

    private void recursion(List<String> res, TreeNode root, String path) {
        if (root != null) {
            StringBuffer buffer = new StringBuffer(path);
            buffer.append(root.val);
            if (root.right == null && root.left == null) {
                res.add(buffer.toString());
            } else {
                buffer.append("->");
                recursion(res, root.left, buffer.toString());
                recursion(res, root.right, buffer.toString());
            }
        }
    }

    // 解法2：bfs
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<String> paths = new LinkedList<>();
        nodes.offer(root);
        paths.offer(Integer.toString(root.val));
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            String path = paths.poll();
            if (node.left == null && node.right == null) {
                res.add(path);
            } else {
                if (node.left != null) {
                    nodes.offer(node.left);
                    paths.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                    paths.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}