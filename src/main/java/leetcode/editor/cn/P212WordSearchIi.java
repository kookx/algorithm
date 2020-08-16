//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法 
// 👍 215 👎 0

 
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Java：单词搜索 II
public class P212WordSearchIi{
    public static void main(String[] args) {
        Solution solution = new P212WordSearchIi().new Solution();
        // TO TEST
        char[] board1 = new char[]{'o','a','a','n'};
        char[] board2 = new char[]{'e','t','a','e'};
        char[] board3 = new char[]{'i','h','k','r'};
        char[] board4 = new char[]{'i','f','l','v'};
        char[][] boards = new char[4][4];
        boards[0] = board1;
        boards[1] = board2;
        boards[2] = board3;
        boards[3] = board4;
        String[] words = new String[]{"oath","pea","eat","rain"};
        solution.findWords(boards, words);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    * 解法：回溯+字典树
    */
    int row, col;
    // 防止重复插入结果集
    Set<String> result = new HashSet<>();
    boolean[][] visited;
    TrieNode root;
    // 定义遍历上下左右四个方向移动对DFS中坐标点的影响
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    public List<String> findWords(char[][] board, String[] words) {
        // 初始化
        WordTrie wordTrie = new WordTrie();
        root = wordTrie.trie;
        for (String s : words)
            wordTrie.insert(s);
        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];
        // 开始DFS
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, root, i, j);
            }
        }
        return new LinkedList<>(result);
    }

    private void dfs(char[][] board, TrieNode curr, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j])
            return;
        curr = curr.child[board[i][j] - 'a'];
        visited[i][j] = true;
        if (curr == null) {
            // 撤销选择
            visited[i][j] = false;
            return;
        }
        // 找到了一个单词
        if (curr.isLeaf) {
            result.add(curr.val);
            // 这里不用后退，因为还有可能出现这种单词：'ad'，'addd'，需要继续查找
//            visited[i][j]=false;
//            return;
        }
        // 扩散到上下左右四个方向
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k], y = j + dy[k];
            dfs(board, curr , x, y);
        }
        // 撤销选择
        visited[i][j] = false;
    }

    // 构建字典树
    class WordTrie {
        TrieNode trie = new TrieNode();
        // 将单词插入字典树
        void insert(String s){
            TrieNode curr = trie;
            for (char c : s.toCharArray()) {
                if (curr.child[c - 'a'] == null) {
                    curr.child[c - 'a'] = new TrieNode();
                    curr = curr.child[c - 'a'];
                } else {
                    curr = curr.child[c - 'a'];
                }
            }
            curr.isLeaf = true;
            curr.val = s;
        }
    }

    // 字典树结点
    class TrieNode {
        String val;
        boolean isLeaf = false;
        TrieNode[] child = new TrieNode[26];

        TrieNode() {

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}