//给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节
//点值之和。 
//
// 
//
// 例如： 
//
// 输入: 原始二叉搜索树:
//              5
//            /   \
//           2     13
//
//输出: 转换为累加树:
//             18
//            /   \
//          20     13
// 
//
// 
//
// 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-s
//um-tree/ 相同 
// Related Topics 树 
// 👍 345 👎 0

 
package leetcode.editor.cn;
//Java：把二叉搜索树转换为累加树
public class P538ConvertBstToGreaterTree{
    public static void main(String[] args) {
        Solution solution = new P538ConvertBstToGreaterTree().new Solution();
        // TO TEST
        solution.convertBST(TreeNode.init(new Integer[]{5,2,13}));
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
    // 解法1：反序中序遍历
    int sum = 0;
    public TreeNode convertBST1(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
    // 解法2：Morris反序遍历
    public TreeNode convertBST(TreeNode root) {
        int sum1 = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right != null) {
                // 设置前继节点
                TreeNode pr = node.right;
                while (pr.left != null && pr.left != node) {
                    pr = pr.left;
                }
                if (pr.left == null) {
                    pr.left = node;
                    node = node.right;
                }
                // 遍历完右子树了
                else {
                    sum1 += node.val;
                    pr.left = null;
                    node.val = sum1;
                    node = node.left;
                }
            } else {
                sum1 += node.val;
                node.val = sum1;
                node = node.left;
            }
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}