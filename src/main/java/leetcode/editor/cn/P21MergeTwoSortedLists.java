//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1153 👎 0

 
package leetcode.editor.cn;
//Java：合并两个有序链表
public class P21MergeTwoSortedLists{
    public static void main(String[] args) {
        Solution solution = new P21MergeTwoSortedLists().new Solution();
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
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 解法1.迭代。新开一个链表，枚举两个链表的节点，比较两边链表的值，如果左边的值小于等于右边，那么取该值，同时继续看左边链表的后继节点，继续比较,
        // 直到循环结束，把l1或l2的剩余元素拼到新链表后。
        ListNode mergeNode = new ListNode(0);
        ListNode head = mergeNode;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                mergeNode.next = l1;
                l1 = l1.next;
            } else {
                mergeNode.next = l2;
                l2 = l2.next;
            }
            mergeNode = mergeNode.next;
        }
        mergeNode.next = l1 == null?l2:l1;
        return head.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}