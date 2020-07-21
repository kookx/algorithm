//给定一个二叉树，返回它的 前序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 314 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//Java：二叉树的前序遍历
public class P144BinaryTreePreorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P144BinaryTreePreorderTraversal().new Solution();
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList();

        if (root == null) {
            return ans;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            ans.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return ans;
    }

    // 解法1. 递归
    private void recursion(TreeNode root,List<Integer> ans) {
        while (root != null) {
            ans.add(root.val);
            if (root.left != null) {
                recursion(root.left, ans);
            }
            if (root.right != null) {
                recursion(root.right, ans);
            }
        }
    }

    // 解法2. 迭代+栈
    private void iteration(TreeNode root,List<Integer> ans) {
        Deque<TreeNode> stack = new LinkedList();
        if (root == null) {
            return;
        }
        TreeNode curr = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            ans.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}