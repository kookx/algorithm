//设计实现双端队列。 
//你的实现需要支持以下操作： 
//
// 
// MyCircularDeque(k)：构造函数,双端队列的大小为k。 
// insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。 
// insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。 
// deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。 
// deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。 
// getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。 
// getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。 
// isEmpty()：检查双端队列是否为空。 
// isFull()：检查双端队列是否满了。 
// 
//
// 示例： 
//
// MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
//circularDeque.insertLast(1);			        // 返回 true
//circularDeque.insertLast(2);			        // 返回 true
//circularDeque.insertFront(3);			        // 返回 true
//circularDeque.insertFront(4);			        // 已经满了，返回 false
//circularDeque.getRear();  				// 返回 2
//circularDeque.isFull();				        // 返回 true
//circularDeque.deleteLast();			        // 返回 true
//circularDeque.insertFront(4);			        // 返回 true
//circularDeque.getFront();				// 返回 4
//  
//
// 
//
// 提示： 
//
// 
// 所有值的范围为 [1, 1000] 
// 操作次数的范围为 [1, 1000] 
// 请不要使用内置的双端队列库。 
// 
// Related Topics 设计 队列 
// 👍 46 👎 0

 
package leetcode.editor.cn;
//Java：设计循环双端队列
public class P641DesignCircularDeque{
    public static void main(String[] args) {
        MyCircularDeque solution = new P641DesignCircularDeque().new MyCircularDeque(5);
        // TO TEST
        System.out.println(solution.insertFront(5));
        System.out.println(solution.insertFront(0));
        System.out.println(solution.insertLast(5));
        System.out.println(solution.deleteLast());
        System.out.println(solution.insertLast(7));
        System.out.println(solution.getRear());
        System.out.println(solution.insertLast(7));
        System.out.println(solution.getFront());
        System.out.println(solution.deleteFront());
        System.out.println(solution.insertLast(6));
        System.out.println(solution.insertLast(1));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class MyCircularDeque {

    class DoubleLink {
        int val;
        DoubleLink next;
        DoubleLink prev;
        DoubleLink(int x) {
            this.val = x;
        }
    }

    // 解法1.使用双向链表实现
    private DoubleLink head;
    private DoubleLink tail;
    private int capacity;
    private int count;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.capacity = k;
        this.count = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (this.count == this.capacity)
            return false;
        DoubleLink newNode = new DoubleLink(value);
        if (this.count == 0) {
            this.head = this.tail = newNode;
        } else {
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        }
        this.count++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (this.count == this.capacity)
            return false;
        DoubleLink newNode = new DoubleLink(value);
        if (this.count == 0) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.count++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (this.count == 0)
            return false;
        if (this.count == 1) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
        this.count--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (this.count == 0)
            return false;
        if (this.count == 1) {
            this.head = this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        this.count--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return this.count == 0 ? -1 : this.head.val;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return this.count == 0 ? -1 : this.tail.val;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return this.count == this.capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}