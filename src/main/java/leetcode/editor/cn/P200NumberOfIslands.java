//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 681 👎 0

 
package leetcode.editor.cn;
//Java：岛屿数量
public class P200NumberOfIslands{
    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        // TO TEST
        char[][] chars = new char[4][5];
        char[] chars1 = new char[]{'1','1','0','0','0'};
        char[] chars2 = new char[]{'1','1','0','0','0'};
        char[] chars3 = new char[]{'0','0','1','0','0'};
        char[] chars4 = new char[]{'0','0','0','1','1'};
//        chars[0] = chars1;
//        chars[1] = chars2;
//        chars[2] = chars3;
//        chars[3] = chars4;
        solution.numIslands(chars);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 迭代 + DFS递归
    // 题意： 如果相邻（上下左右）的为1，且相邻的相邻的点也为1，则算同一个岛屿
    // 思路： 遍历二维数组，看每一个岛屿，如果遇到为1的，则用dfs递归检查它的每一个相邻岛屿，都置为0，计数器加1
    private int n;
    private int m;
    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '1') {
                    dfsMarking(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfsMarking(char[][] grid,int row, int col) {
        if (row < 0 || col < 0 || row > n - 1 || col > m - 1 || grid[row][col] != '1')
            return;
        grid[row][col] = '0';
        dfsMarking(grid, row - 1, col); // 上
        dfsMarking(grid, row + 1, col); // 下
        dfsMarking(grid, row, col - 1);  // 左
        dfsMarking(grid, row, col + 1);  // 右
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}