//给定一个二叉树，判断它是否是高度平衡的二叉树。 
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。 
// 
//
// 示例 1: 
//
// 给定二叉树 [3,9,20,null,null,15,7] 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回 true 。 
// 
//示例 2: 
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4] 
//
//        1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// 返回 false 。 
//
// 
// Related Topics 树 深度优先搜索 
// 👍 428 👎 0

 
package leetcode.editor.cn;
//Java：平衡二叉树
public class P110BalancedBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P110BalancedBinaryTree().new Solution();
        // TO TEST
        TreeNode root = TreeNode.init(new Integer[]{1,null,2,null,3});
        System.out.println(solution.isBalanced(root));
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
    // 解题思路：自底向上DFS，类似于后序遍历，判断左右子树高度，不是平衡二叉树返回-1，如果左子树不是平衡二叉树，则提前剪枝不判断右子树
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        // 左子树不是平衡二叉树，则提前剪枝不判断右子树
        if (left == -1) return -1;
        int right = height(root.right);
        if (right == -1) return -1;
        // 如果左右子树差值绝对值小于2，返回左右子树中最大的高度+1作为当前结点的高度
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}