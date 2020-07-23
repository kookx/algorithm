//给定一个 N 叉树，返回其节点值的后序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其后序遍历: [5,6,3,2,4,1]. 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 86 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：N叉树的后序遍历
public class P590NAryTreePostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P590NAryTreePostorderTraversal().new Solution();
        // TO TEST
        Node root = new P590NAryTreePostorderTraversal().new Node(1);
        Node cl1 = new P590NAryTreePostorderTraversal().new Node(3);
        Node cl101 = new P590NAryTreePostorderTraversal().new Node(5, Collections.emptyList());
        Node cl102 = new P590NAryTreePostorderTraversal().new Node(6, Collections.emptyList());
        List<Node> cl101Cl = Arrays.asList(cl101,cl102);
        cl1.children = cl101Cl;
        Node cl2 = new P590NAryTreePostorderTraversal().new Node(2, Collections.emptyList());
        Node cl3 = new P590NAryTreePostorderTraversal().new Node(4, Collections.emptyList());
        List<Node> rootCl = Arrays.asList(cl1,cl2,cl3);
        root.children = rootCl;
        solution.postorder(root);

    }
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        iteration2(root,ans);

        return ans;
    }

    // 解法1. 递归
    private void recursion(Node root, List<Integer> ans) {
        if (root != null) {
            if (root.children != null) {
                for (Node node : root.children){
                    recursion(node,ans);
                }
                ans.add(root.val);
            }
        }
    }

    // 解法2. 迭代+栈
    private void iteration1(Node root, LinkedList<Integer> ans) {
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 弹出栈顶元素（LinkedList头部）
            root = stack.pop();
            // 根-》右-》左的逆序
            ans.addFirst(root.val);
            for (Node child : root.children) {
                // 栈的push将元素放到LinkedList头部
                stack.push(child);
            }
        }
    }

    // 解法3. 迭代+队列
    private void iteration2(Node root, LinkedList<Integer> ans) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 弹出队尾元素（LinkedList尾部）
            root = queue.pollLast();
            ans.addFirst(root.val);
            for (Node child : root.children) {
                // 队列的add将元素放到LinkedList尾部
                queue.add(child);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}