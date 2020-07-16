//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1094 👎 0

 
package leetcode.editor.cn;
//Java：反转链表
public class P206ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // 解法1.迭代
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null){
            // 保存当前节点后继节点
            ListNode tempNode = curr.next;
            // 将前置节点设为本当前节点后继节点
            curr.next = prev;
            // 重设前置节点为当前节点
            prev = curr;
            // 重设当前节点为后继节点
            curr = tempNode;
        }
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}