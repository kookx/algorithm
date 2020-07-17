//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 606 👎 0

 
package leetcode.editor.cn;

import java.util.Stack;

//Java：最小栈
public class P155MinStack{
    public static void main(String[] args) {
        MinStack solution = new P155MinStack().new MinStack();
        // TO TEST
        solution.push(512);
        solution.push(-1024);
        solution.push(-1024);
        solution.push(512);
        solution.pop();
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    // 解法1.使用辅助栈
    // 解法2.使用栈里的Node
    // 解法3.使用链表实现最小栈

    //这里使用解法3
    /** initialize your data structure here. */
    private LinkedNode head;

    class LinkedNode {
        int val;
        int min;
        LinkedNode next;

        public LinkedNode(int val,int min){
            this(val,min,null);
        }
        public LinkedNode(int val,int min,LinkedNode next){
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    public MinStack() {

    }
    
    public void push(int x) {
        if (head == null){
            head = new LinkedNode(x,x,null);
        } else {
            head = new LinkedNode(x,Math.min(head.min,x),head);
        }
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}