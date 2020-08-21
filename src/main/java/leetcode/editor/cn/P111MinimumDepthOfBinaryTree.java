//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 351 👎 0

 
package leetcode.editor.cn;
//Java：二叉树的最小深度
public class P111MinimumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P111MinimumDepthOfBinaryTree().new Solution();
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
    // 解法：DFS递归
    // 注意：1.叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点
    // 2.当 root 节点左右孩子都为空时，返回 1
    // 3.当 root 节点左右孩子有一个为空时，返回不为空的孩子节点的深度( root节点左右孩子只有一个为空，说明root节点不是叶子节点，那此时的最小深度就是不为空的那棵子树的最小深度+1)
    // 4.当 root 节点左右孩子都不为空时，返回左右孩子较小深度的节点值
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //这里左右孩子有为空的情况，说明left和right有一个（或两个）必然为0，所以可以返回left + right + 1;
        return root.left == null || root.right == null ? left + right + 1 : Math.min(left, right) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}