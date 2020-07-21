//给定一个二叉树，返回它的 后序 遍历。 
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
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 350 👎 0

 
package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//Java：二叉树的后序遍历
public class P145BinaryTreePostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P145BinaryTreePostorderTraversal().new Solution();
        // TO TEST
        TreeNode root = TreeNode.init(new Integer[]{1,null,2,3});
        solution.postorderTraversal(root);
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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();

        if (root == null) return ans;

        iteration2(root, ans);

        return ans;
    }

    // 解法1.递归
    private void recursion(TreeNode root, List<Integer> ans) {
        while (root != null) {
            if (root.left != null) {
                recursion(root.left, ans);
            }
            if (root.right != null) {
                recursion(root.right, ans);
            }
            ans.add(root.val);
        }
    }

    // 解法2.迭代+栈，使用非严格后序遍历，即前序遍历的逆序(根>右>左)然后逆序输出(左>右>根)
    private void iteration1(TreeNode root, LinkedList<Integer> ans) {
        //用deque模拟queue
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollLast();
            // 插入到输出队首
            ans.addFirst(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        //用deque模拟stack
//        Deque<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            // 插入到输出队首
//            ans.addFirst(node.val);
//            if (node.left != null) {
//                queue.push(node.left);
//            }
//            if (node.right != null) {
//                queue.push(node.right);
//            }
//        }
    }

    // 解法3.迭代+栈，使用严格后序遍历，即(左>右>根)
    private void iteration2(TreeNode root, LinkedList<Integer> ans) {
        //用deque模拟stack
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();

            if (curr.right == null) {
                ans.add(curr.val);
                curr = null;
            } else if (ans.size() == 0 || !ans.get(ans.size() - 1).equals(curr.right.val)) {
                stack.push(curr);
                curr = curr.right;
            } else {
                ans.add(curr.val);
                curr = null;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}