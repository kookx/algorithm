//在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。 
//
// 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成： 
//
// 
// 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角） 
// C_1 位于 (0, 0)（即，值为 grid[0][0]） 
// C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]） 
// 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0） 
// 
//
// 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：[[0,1],[1,0]]
//
//输出：2
//
// 
//
// 示例 2： 
//
// 输入：[[0,0,0],[1,1,0],[1,1,0]]
//
//输出：4
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 广度优先搜索 
// 👍 55 👎 0

 
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：二进制矩阵中的最短路径
public class P1091ShortestPathInBinaryMatrix{
    public static void main(String[] args) {
        Solution solution = new P1091ShortestPathInBinaryMatrix().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：BFS求最短路径
    public int shortestPathBinaryMatrix(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) return -1;
        // 定义上、下、左、右、右上、右下、左上、左下八个方向对x，y轴坐标点移动的影响
        int[] dx = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = new int[]{0, 0, -1, 1, 1, 1, -1, -1};
        Queue<int[]> queue = new LinkedList<>();
        grid[0][0] = 1;// 直接用grid[i][j]记录从起点到这个点的最短路径长。按照题意 起点也有长度1
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty() && grid[row - 1][col - 1] != 1) {
            int[] pos = queue.remove();
            int tx = pos[0], ty = pos[1];
            int preLength = grid[tx][ty];// 当前点的路径长度
            for (int i = 0; i < 8; ++i) {
                int x = tx + dx[i], y = ty + dy[i];
                if (x >= 0 && x < row && y >=0 && y < col && grid[x][y] == 0) {
                    queue.add(new int[]{x, y});
                    grid[x][y] = preLength + 1;// 下一个点的路径长度要+1
                }
            }
        }
        return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1];// 如果最后终点的值还是0，说明没有到达
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}