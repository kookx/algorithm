//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 
// 👍 619 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：二叉树的最大深度
public class P104MaximumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P104MaximumDepthOfBinaryTree().new Solution();
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
    public int maxDepth(TreeNode root) {

        return recursion(root);
    }

    // 解法1. 递归
    private int recursion(TreeNode root) {
        // 思路，求root节点的最大深度就是求root子树的最大深度
        return root == null?0:Math.max(recursion(root.left), recursion(root.right)) + 1;
    }

    // 解法2. 广度优先搜索 遍历每一个子节点
    private int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0){
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
                size--;
            }
            ans++;
        }

        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}