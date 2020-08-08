//二叉搜索树中的两个节点被错误地交换。 
//
// 请在不改变其结构的情况下，恢复这棵树。 
//
// 示例 1: 
//
// 输入: [1,3,null,null,2]
//
//   1
//  /
// 3
//  \
//   2
//
//输出: [3,1,null,null,2]
//
//   3
//  /
// 1
//  \
//   2
// 
//
// 示例 2: 
//
// 输入: [3,1,4,null,null,2]
//
//  3
// / \
//1   4
//   /
//  2
//
//输出: [2,1,4,null,null,3]
//
//  2
// / \
//1   4
//   /
//  3 
//
// 进阶: 
//
// 
// 使用 O(n) 空间复杂度的解法很容易实现。 
// 你能想出一个只使用常数空间的解决方案吗？ 
// 
// Related Topics 树 深度优先搜索 
// 👍 278 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//Java：恢复二叉搜索树
public class P99RecoverBinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P99RecoverBinarySearchTree().new Solution();
        // TO TEST
        TreeNode root = TreeNode.init(new Integer[]{4,null,3});
        solution.recoverTree(root);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 解法：中序遍历
    // 思路：BST的中序遍历为升序，假设遍历结果为[1,2,3,4,5,6,7]，由于本题只交换了两个节点，所以分为两种情况
    // 1.交换了相邻的两个节点，例[1,3,2,4,5,6,7]
    // 2.交换了不相邻的两个节点，例[1,6,3,4,5,2,7]
    // 因此我们只需要用两个变量记录这两个节点即可恢复原二叉搜索树
    // 恢复时的遍历技巧：默认是相邻节点的，即查找到不满足升序条件的两个节点时都记录下来，如果是不相邻节点，后序遍历找出后再更新前一个变量
    public void recoverTree(TreeNode root) {
        recoverTree3(root);
    }

    // 解法1.显示的用数组记录中序遍历结果，O(N)空间复杂度，N为二叉搜索树的节点数
    public void recoverTree1(TreeNode root) {
        // 中序遍历
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        // 找到两个节点
        int[] swap = findNode(nums);
        // 恢复
        recover(root, 2, swap[0], swap[1]);
    }

    // 解法2.用栈记录中序遍历结果，O(k)空间复杂度，k为栈深度
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pred = null;
        TreeNode x = null, y = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }

            pred = root;
            root = root.right;
        }

        // 交换节点
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    // 解法3.Morris 中序遍历,O(1)空间复杂度
    // 解释Morris 中序遍历：
    // 步骤：
    //      1.如果当前节点x没有左节点，则访问当前节点x，之后去到x的右节点，进行下一轮检查
    //      2.如果当前节点x有左节点，检查和设置当前节点x左子树的最右节点（即x的左子树中序遍历的最后一个节点，也是x在中序遍历中的前驱节点）的右节点P
    //        1).如果P已经有指向了(不为空)，则访问x本身，之后去到x的右节点，进行下一轮检查
    //        2).如果P之前没有指向(为空)，则设置P指向x本身，之后去到x的左节点，进行下一轮检查
    public void recoverTree3(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }

        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null)
            return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private int[] findNode(List<Integer> nums) {
        int x = -1, y = -1;
        for (int i = 0; i < nums.size() - 1; i++) {
            //恢复时的遍历技巧
            if (nums.get(i + 1) < nums.get(i)){
                y = nums.get(i + 1);
                if (x == -1) {
                    x = nums.get(i);
                } else {
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    private void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}