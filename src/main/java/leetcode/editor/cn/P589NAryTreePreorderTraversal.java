//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 89 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：N叉树的前序遍历
public class P589NAryTreePreorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P589NAryTreePreorderTraversal().new Solution();
        // TO TEST
        Node root = new P589NAryTreePreorderTraversal().new Node(1);
        Node cl1 = new P589NAryTreePreorderTraversal().new Node(3);
        Node cl101 = new P589NAryTreePreorderTraversal().new Node(5, Collections.emptyList());
        Node cl102 = new P589NAryTreePreorderTraversal().new Node(6, Collections.emptyList());
        List<Node> cl101Cl = Arrays.asList(cl101,cl102);
        cl1.children = cl101Cl;
        Node cl2 = new P589NAryTreePreorderTraversal().new Node(2, Collections.emptyList());
        Node cl3 = new P589NAryTreePreorderTraversal().new Node(4, Collections.emptyList());
        List<Node> rootCl = Arrays.asList(cl1,cl2,cl3);
        root.children = rootCl;
        solution.preorder(root);
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

    public List<Integer> preorder(Node root) {
        // 前序遍历：根->左->右
        List<Integer> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        iteration(root,output);
        return output;
    }

    // 解法1. 递归
    private void recursion(Node root, List<Integer> output) {
        output.add(root.val);
        if (root != null && root.children != null) {
            root.children.forEach(node -> recursion(node, output));
        }
    }

    // 解法2. 迭代 + 栈
    public void iteration(Node root, List<Integer> output) {
        // 前序遍历：根->左->右,处理手段：从右到左压入栈，先进后出
        Deque<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            root = stack.pollLast();
            output.add(root.val);
            Collections.reverse(root.children);
            for (Node child : root.children) {
                stack.add(child);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}