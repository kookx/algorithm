//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法 
// 👍 482 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//Java：N皇后
public class P51NQueens{
    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
        // TO TEST
        solution.solveNQueens(4);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 注意：nxn行中要放置n个皇后，皇后可以在横竖撇捺（对角线）方向"吃子"，因此每行都必须放一个皇后并且不被所有方向的其他皇后攻击到
    // 我们画图可以知道主次对角线都有2xn-1个，并且每条主对角线的行减列都是一个固定值，每条次对角线的行+列都是一个固定值，
    // 因此我们可以设置两个数组，每个数组大小2xn-1，数组每个下标元素代表一条对角线，下标对应的值默认为0，如果为1，代表已经有皇后在这条对角线上了，
    // 为了让主对角线的数组下标从0到2xn-1-1（最大索引比数组长度小1）个向后排，即不产生负数，我们用row-col+n-1下标来表示主对角线的某个点，
    // 用row+col下标表示次对角线的某个点。
    // 举例：4x4的4皇后棋盘，主次对角线各有7条，主对角线下标根据公式row-col+n-1从0到6，次对角线下标根据公式row+col从0到6，满足数组条件

    //位运算解法，竖撇捺使用32位int表示
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        // 初始化棋盘，不放置任何皇后
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {// 每行都放置了皇后，并且到了最后一行，代表一种摆放解法
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            // 获取当前行在列、撇、捺方向都可放置的位置（~取反操作使1表示可放置的位置）：(1 << n) - 1为了确保二进制位数和n相等
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {// 如果还有剩余可放置位置，依次尝试
                // 获取当前行最低位可放置的位置：如二进制1000
                int position = availablePositions & (-availablePositions);
                // 放置在当前行最低位的可放置位置后，更新剩余可放置的位置
                availablePositions = availablePositions & (availablePositions - 1);
                // 根据position计算在当前行最低位可放置皇后的列号：bitCount计算二进制中的1的个数，position - 1表示：1000-1 = 111，即为第3列（下标0开始）
                int column = Integer.bitCount(position - 1);
                // 修改当前行的皇后位置为该列
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                // 撤销修改
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}