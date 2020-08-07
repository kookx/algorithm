//给定两个二叉树，编写一个函数来检验它们是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 示例 1: 
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true 
//
// 示例 2: 
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
// 
//
// 示例 3: 
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
// 
// Related Topics 树 深度优先搜索 
// 👍 417 👎 0

 
package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//Java：相同的树
public class P100SameTree{
    public static void main(String[] args) {
        Solution solution = new P100SameTree().new Solution();
        // TO TEST
        TreeNode p = new TreeNode(0);
        TreeNode q = new TreeNode(0);
        System.out.println(p==null ^ q == null);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return bfs(p, q);
    }
    // 解法1. DFS
    // 思路：1.如果pq都为空，返回true
    // 2.如果pq中只有一个为空，返回false
    // 3.如果pq的值不等，返回false
    // 4.同理判断pq的左右子树是否相等
    private boolean dfs(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        return p.val == q.val && dfs(p.left, q.left) && dfs(p.right, q.right);
    }

    // 解法2. BFS
    // 思路：使用两个队列进行比较
    private boolean bfs(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.val != node2.val)
                return false;

            TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
            // 如果左右两节点不同
            if (left1 == null ^ left2 == null)
                return false;
            if (right1 == null ^ right2 == null)
                return false;
            // 注意这里先加左节点再加右节点
            if (left1 != null)
                queue1.offer(left1);
            if (right1 != null)
                queue1.offer(right1);
            if (left2 != null)
                queue2.offer(left2);
            if (right2 != null)
                queue2.offer(right2);
        }
        // 如果两个队列都用完了代表两棵树完全相等
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}