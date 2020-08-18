//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 深度优先搜索 链表 
// 👍 313 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：有序链表转换二叉搜索树
public class P109ConvertSortedListToBinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P109ConvertSortedListToBinarySearchTree().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    // 解法1. 链表转数组然后递归分治,类似于二分
    public TreeNode sortedListToBST3(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return sortedHelper(list, 0, list.size() - 1);
    }

    private TreeNode sortedHelper(List<Integer> list, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = sortedHelper(list, left, mid - 1);
        root.right = sortedHelper(list,mid + 1, right);
        return root;
    }
    // 解法2. 快慢指针然后递归分治
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode pre = null, slow = head, fast = head;
        // 快指针走到末尾时慢指针走到中间，pre就是根节点前一个结点
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开链表
        pre.next = null;
        // slow就是当前根结点
        TreeNode root = new TreeNode(slow.val);
        // 从head到slow
        root.left = sortedListToBST2(head);
        // 从slow到链表末尾
        root.right = sortedListToBST2(slow.next);
        return root;
    }

    // 解法3. 中序遍历优化
    ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        int len = 0;
        cur = head;
        while (head != null) {
            len++;
            head = head.next;
        }
        return sortedHelper2(0, len - 1);
    }

    private TreeNode sortedHelper2(int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode left = sortedHelper2(start, mid - 1);
        // cur会根据递归调用变化
        TreeNode root = new TreeNode(cur.val);
        // cur指针步进
        cur = cur.next;
        // root构建出来了，接上左子树
        root.left = left;
        // 构建当前root的右子树
        root.right = sortedHelper2(mid + 1, end);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}