//编写一个程序，通过已填充的空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 
// 👍 493 👎 0

 
package leetcode.editor.cn;
//Java：解数独
public class P37SudokuSolver{
    public static void main(String[] args) {
        Solution solution = new P37SudokuSolver().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：回溯
    public void solveSudoku(char[][] board) {
        // 非法数独
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9)
            return;
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int i, int j) {
        int row = 9, col = 9;
        if (j == col) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return backtrack(board, i + 1, 0);
        }
        if (i == row) {
            // 最后一行最后一个位置[8][8]试探过后会试探[8][9]，会执行[9][0]，返回
            return true;
        }
        // 如果该位置是预设的数字，不用我们操心
        if (board[i][j] != '.') {
            return backtrack(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            // 如果遇到不合法的数字，就跳过
            if (!isValid(board, i, j, ch)) {
                continue;
            }
            board[i][j] = ch;
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    // 判断 board[i][j] 是否可以填入 n
    private boolean isValid(char[][] board, int i, int j, char ch) {
        for (int k = 0; k < 9; k++) {
            // 判断行是否存在重复
            if (board[k][j] == ch) return false;
            // 判断列是否存在重复
            if (board[i][k] == ch) return false;
            // 判断 3 x 3 方框是否存在重复
            /* 因为 i和j是确定的，所以 i / 3 * 3可以确定他所在的子数独在第一个三行，还是第二个三行，还是第三个三行，
               j / 3 * 3可以确定它所在的子数独是前三列还是中散列还是后三列，
            相当于这两个只是确定了这个【子数独的左上角坐标】，而需要借助 k 完全对这个9个位置的扫描
            一维转二维技巧：k/x为行号，k%x为列号
            */
            if (board[(i/3)*3 + k/3][(j/3)*3 + k%3] == ch) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}