//让我们一起来玩扫雷游戏！ 
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 105 👎 0

 
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：扫雷游戏
public class P529Minesweeper{
    public static void main(String[] args) {
        Solution solution = new P529Minesweeper().new Solution();
        // TO TEST
        char[][] chars = new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        int[] click = new int[]{3, 0};
        long t1 = System.nanoTime();
        solution.updateBoard(chars, click);
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DFS、BFS
    // 说明：只有当前字符确定为'B‘时才能继续进行递归，这就是为什么 测试一存在一个'E’的原因
    // 定义上、下、左、右、右上、右下、左上、左下八个方向
    int[] dx = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
    int[] dy = new int[]{0, 0, -1, 1, 1, 1, -1, -1};
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        // 开始点击到雷直接结束
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            bfs(board, x, y);
        }

        return board;
    }
    private void dfs(char[][] board, int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            int tx = x + dx[i], ty = y + dy[i];
            // 统计周围有多少颗雷
            if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'M')//或运算代替与运算，节省时间
                continue;
            count++;
        }
        if (count != 0) {
            board[x][y] = (char)(count + '0');
            // 不为'B'直接结束，如：扩散到周围有雷的点时结束当前dfs，或点击到周围有雷的点时结束dfs
        } else {
            board[x][y] = 'B';
            for (int i = 0; i < 8; ++i) {
                int tx = x + dx[i], ty = y + dy[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E')
                    continue;
                dfs(board, tx, ty);
            }
        }

    }
    private void bfs(char[][] board, int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        // 记录已经入过队的点
        boolean[][] vis = new boolean[board.length][board[0].length];
        queue.offer(new int[]{sx, sy});
        vis[sx][sy] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1], count = 0;
            // 统计周围的地雷
            for (int i = 0; i < 8; ++i) {
                int tx = x + dx[i], ty = y + dy[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'M')
                    continue;
                count++;
            }
            if (count != 0) {
                board[x][y] = (char)(count + '0');
            } else {
                board[x][y] = 'B';
                // BFS扩散
                for (int i = 0; i < 8; ++i) {
                    int tx = x + dx[i], ty = y + dy[i];
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E' || vis[tx][ty])
                        continue;
                    queue.offer(new int[]{tx, ty});
                    vis[tx][ty] = true;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}