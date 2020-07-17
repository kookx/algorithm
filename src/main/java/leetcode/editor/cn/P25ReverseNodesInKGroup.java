//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 638 👎 0

 
package leetcode.editor.cn;
//Java：K 个一组翻转链表
public class P25ReverseNodesInKGroup{
    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        solution.reverseKGroup(l1,3);
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
    public ListNode reverseKGroup(ListNode head, int k) {
        // 定义一个伪节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 初始化操作节点pre和end
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            //找到k组内最末尾的节点
            for (int i = 0;i<k && end != null;i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            // 保存后面的节点
            ListNode next = end.next;
            // 截断当前分组末尾节点
            end.next = null;
            // 反转该组节点
            pre.next = reverse(start);
            // 该组末尾节点指向把后面的分组
            start.next = next;
            // 重新对齐pre和end
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    // 反转链表，返回末尾节点，输入的head节点next指为null
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}