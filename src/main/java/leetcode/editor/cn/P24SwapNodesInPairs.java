//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表 
// 👍 550 👎 0

 
package leetcode.editor.cn;
//Java：两两交换链表中的节点
public class P24SwapNodesInPairs{
    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        System.out.println(solution.swapPairs(l1));
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
    public ListNode swapPairs(ListNode head) {
        // 迭代解法
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        // 链表代码注意几个点： 保持清醒，别被绕晕
        //   ①如果设置两个引用(指针)相等，若一个引用改变了该引用里的某个变量对象的引用，另一个引用同时改变，因为两个引用指向同一个地址
        //   ②如果设置两个引用(指针)相等，若一个引用改变了自身的引用，则不影响另一个引用，他们变成指向不同地址的引用
        // 总体思路就是：1.将上次交换后的节点指向这次交换后的节点。这一步可以放在2、3前也可以放在2、3后，不影响
        //              2.将这次交换前的节点指向这次交换后的节点的后继节点。
        //              3.将这次交换后的节点指向这次交换前的节点。
        while (temp.next != null && temp.next.next != null) {
            //保存开始节点和结束节点
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            //该循环第一次执行时temp=pre，目的是为了设置交换后的链表头结点，后续循环temp.next=end意思就是将上次start的next指向当前的end
            temp.next = end;
            //将开始节点的next指针指向end之后的节点
            start.next = end.next;
            //将结束节点的next指针指向开始节点，调换位置
            end.next = start;
            //将临时节点置为新的开始节点
            temp = start;
        }
        return pre.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}