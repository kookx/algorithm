package leetcode.editor.cn;

import java.util.*;

/**
 * @Description LeetCode TreeNode工具类
 * @Date 2020/7/9 10:09 PM
 * @Created by kook<onekook.me@gmail.com>
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public String toString(){
        return convert().toString();
    }

    /**
     * @return 层序遍历当前二叉树节点转化为数组
     */
    private List<Integer> convert(){
        List<Integer> arr = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Integer size = queue.size();
            for (int i=0;i<size;i++) {
                TreeNode temp = queue.pop();
                if(temp != null){
                    arr.add(temp.val);
                }else {
                    arr.add(null);
                }
                if (temp != null) {
                    queue.offer(temp.left);
                    queue.offer(temp.right);
                }
            }
        }
        while (arr.get(arr.size() - 1) == null) {
            arr.remove(arr.size() - 1);
        }
        return arr;
    }

    /**
     * @return 层序遍历当前数组转化为二叉树节点
     */
    public static TreeNode init(Integer[] nodes) {
        if(nodes == null || nodes.length == 0) return null;
        int len = nodes.length;
        int index = 0;
        TreeNode head = new TreeNode(nodes[index]);
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(head);
        TreeNode cur;
        while (index < len){
            index++;
            if (index >= len) return head;
            cur = nodeQueue.poll();
            Integer left = nodes[index];
            if (left != null){
                cur.left = new TreeNode(left);
                nodeQueue.offer(cur.left);
            }

            index++;
            if (index >= len) return head;
            Integer right = nodes[index];
            if (right != null){
                cur.right = new TreeNode(right);
                nodeQueue.offer(cur.right);
            }
        }
        return head;
    }

    /**
     * 前序遍历
     * 迭代实现，维护一个栈，因为入栈顺序按照根右左进行入栈，因此首先将根出栈，然后出栈左子节点，
     * 最后出栈右子节点。
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
        return result;
    }

    /**
     * 中序遍历
     * 迭代实现，首先依次将左子节点全部加入栈中，所以第一个while循环后栈顶元素对应一个子树的最
     * 左子节点，然后将该元素出栈加入list，并判断该元素的遍历该节点的右子树。
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        do {
            //依次将左节点均加入栈中
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        } while(!stack.isEmpty() || root != null);
        return result;
    }

    /**
     * 后续遍历
     * 迭代实现，使用栈实现，出栈得到节点顺序为根右左，每次向list最开头插入元素
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);   //首先将根节点压栈
        while(!stack.isEmpty()) {
            TreeNode ele = stack.pop(); //首先出栈的为根节点，其后先出右子节点，后出左子节点
            if(ele.left != null)
                stack.push(ele.left);  //将左子节点压栈
            if(ele.right != null) {
                stack.push(ele.right); //将右子节点压栈
            }
            result.add(0, ele.val); //因为出栈顺序为“根右左”，所以需要每次将元素插入list开头
        }
        return result;
    }

}