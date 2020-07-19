//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 352 👎 0

 
package leetcode.editor.cn;
//Java：删除排序链表中的重复元素
public class P83RemoveDuplicatesFromSortedList{
    public static void main(String[] args) {
        Solution solution = new P83RemoveDuplicatesFromSortedList().new Solution();
        // TO TEST
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        solution.deleteDuplicates(n1);
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
    public ListNode deleteDuplicates(ListNode head) {
        // 解法1. 一次遍历
        ListNode dummy = head;
        while (head != null && head.next != null) {
            ListNode temp = head.next;
            if (temp.val == head.val) {
                head.next = head.next.next;
                temp.next = null;
            } else {
                head = head.next;
            }
        }
        return dummy;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}