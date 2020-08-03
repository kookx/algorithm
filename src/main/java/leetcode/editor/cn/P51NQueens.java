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
import java.util.List;

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

    // 主对角线是否存在皇后
    int[] mains;
    // 次对角线是否存在皇后
    int[] secondary;
    // 某列方向是否存在皇后
    int[] cols;
    // 每行放置皇后的位置，下标代表行号，值代表列号
    int[] queens;
    int n;
    List<List<String>> output;
    public List<List<String>> solveNQueens(int n) {
        // 初始化
        mains = new int[2*n - 1];
        secondary = new int[2*n - 1];
        cols = new int[n];
        queens = new int[n];
        this.n = n;
        output = new ArrayList<>();
        backtrack(0);
        return output;
    }

    private void backtrack(int row) {
        if (row >= n) return;
        // 在每一行的不同列尝试摆放皇后
        for (int col = 0; col < n; col++) {
            // 检查当前位置是否不被所有方向的皇后攻击到
            if (isEnableSet(row, col)) {
                // 在当前位置放置皇后
                setQueue(row, col);
                // 如果已经到最后一行，表示找到了一种方案，添加到结果集（如果中间没走就走不到最后一行）
                if (row == n - 1) addSolution(queens);
                // 继续找下一行的位置
                backtrack(row + 1);
                // 回溯，移除当前位置皇后，试其他位置
                removeQueue(row, col);
            }
        }
    }

    private void addSolution(int[] queens) {
        List<String> places = new ArrayList<>();
        StringBuffer place;
        for (int row = 0; row < n; row++) {
            place = new StringBuffer();
            int col = queens[row];
            // 在皇后位置前填充"."
            for (int i = 0; i < col; i++) place.append(".");
            place.append("Q");
            // 在皇后位置后填充"."
            for (int j = 0; j < n - col - 1; j++) place.append(".");
            places.add(place.toString());
        }
        output.add(places);
    }

    private void removeQueue(int row, int col) {
        mains[row - col + n - 1] = 0;
        secondary[row + col] = 0;
        cols[col] = 0;
        // 加不加这行都可以，因为遍历时setQueue主要检查对角线和列是否允许皇后摆放，
        // 不要想成把皇后放在了0列位置，这里只看成移除了这行的皇后，便于理解
        queens[row] = 0;
    }

    private void setQueue(int row, int col) {
        // 当前位置的主对角线方向已经有皇后了
        mains[row - col + n - 1] = 1;
        // 当前位置的次对角线方向已经有皇后了
        secondary[row + col] = 1;
        // 当前位置的列方向已经有皇后了
        cols[col] = 1;
        // 在 row 行，col 列 放置皇后
        queens[row] = col;
    }

    private boolean isEnableSet(int row, int col) {
        int res = cols[col] + mains[row - col + n - 1] + secondary[row + col];
        // 如果所有方向都为0,表示该位置可以放
        return res == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}