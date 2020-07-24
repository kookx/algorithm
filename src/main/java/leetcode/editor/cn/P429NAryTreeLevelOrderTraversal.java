//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 98 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Java：N叉树的层序遍历
public class P429NAryTreeLevelOrderTraversal{
    public static void main(String[] args) {
        Solution solution = new P429NAryTreeLevelOrderTraversal().new Solution();
        // TO TEST
        Node root = new P429NAryTreeLevelOrderTraversal().new Node(1);
        Node cl1 = new P429NAryTreeLevelOrderTraversal().new Node(3);
        Node cl101 = new P429NAryTreeLevelOrderTraversal().new Node(5, Collections.emptyList());
        Node cl102 = new P429NAryTreeLevelOrderTraversal().new Node(6, Collections.emptyList());
        List<Node> cl101Cl = Arrays.asList(cl101,cl102);
        cl1.children = cl101Cl;
        Node cl2 = new P429NAryTreeLevelOrderTraversal().new Node(2, Collections.emptyList());
        Node cl3 = new P429NAryTreeLevelOrderTraversal().new Node(4, Collections.emptyList());
        List<Node> rootCl = Arrays.asList(cl1,cl2,cl3);
        root.children = rootCl;
        solution.levelOrder(root);
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

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> output = new ArrayList<>();

        if (root == null) {
            return output;
        }

//        iteration(root,output);
        recursion(root, 0, output);

        return output;
    }

    // 解法1，迭代，BFS
    public void iteration(Node root,List<List<Integer>> output) {
        List<Node> preLayer = Arrays.asList(root);

        // 记录前一层和当前层
        while (!preLayer.isEmpty()) {
            List<Integer> preValues = new ArrayList<>();
            List<Node> currLayer = new ArrayList<>();
            for (Node node : preLayer) {
                preValues.add(node.val);
                currLayer.addAll(node.children);
            }
            preLayer = currLayer;
            output.add(preValues);
        }
    }

    // 解法2，递归，通常我们不能使用递归进行广度优先搜索。
    // 这是因为广度优先搜索基于队列，而递归运行时使用堆栈，适合深度优先搜索。
    // 但是在本题中，我们可以以不同的顺序添加到最终列表中，
    // 只要我们知道节点在哪一层并确保在那一层的列表顺序正确就可以了。
    public void recursion(Node root, int level, List<List<Integer>> output) {
        if (output.size() <= level) {
            output.add(new ArrayList<>());
        }
        output.get(level).add(root.val);

        for (Node node : root.children) {
            recursion(node,level + 1, output);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}