//你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。 
//
// 示例 1: 
//
// 输入: [4, 1, 8, 7]
//输出: True
//解释: (8-4) * (7-1) = 24
// 
//
// 示例 2: 
//
// 输入: [1, 2, 1, 2]
//输出: False
// 
//
// 注意: 
//
// 
// 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。 
// 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允
//许的。 
// 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。 
// 
// Related Topics 深度优先搜索 
// 👍 184 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：24 点游戏
public class P679Two4Game{
    public static void main(String[] args) {
        Solution solution = new P679Two4Game().new Solution();
        // TO TEST
        System.out.println(solution.judgePoint24(new int[]{1, 2, 1, 2}));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：回溯算法
    // 思路：将四个数放到一个数组容器里，依次从容器里取两个数使用四个操作符运算，将结果再放回容器里，
    //      同样的，继续取两个数操作，将操作结果再放回容器，直到容器里剩一个数的时候，看是否等于24，不是就回溯，尝试下一种可能
    // 注意：1.这里不需要再考虑括号操作符，因为括号操作符只会影响操作顺序，这里穷举了所有操作顺序的可能，所以已经包括里括号操作了。
    //      2.除法运算是实数运算，不是整数运算，且如果除数（这里精度小于10^-6看成是0）为0，就可以跳过
    //      3.加法和乘法运算可以交换操作顺序，已经运算过的可以跳过
    int target = 24;
    Double precision = 1e-6;
    static final int add = 0, multi = 1, sub = 2, div = 3;
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int i = 0 ; i < nums.length; ++i) {
            list.add((double) nums[i]);
        }
        return solver(list);
    }
    private boolean solver(List<Double> list){
        if (list.size() == 1) {  // 如果最终运算结果和24的差值是小于最小精度的，也就可以看成是0，返回true
            return Math.abs(list.get(0) - target) < precision;
        }
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i != j) {// 不同使用同一个数
                    List<Double> list2 = new ArrayList<>();
                    // 拿出操作的两个数，其余的放到一个新数组里
                    for (int k = 0; k < size; ++k) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; ++k) {
                        // 已经运算过加法和乘法的就不用再重复运算了
                        if (i > j && k < sub) {
                            continue;
                        }
                        switch (k) {
                            case add :
                                list2.add(list.get(i) + list.get(j));
                                break;
                            case multi :
                                list2.add(list.get(i) * list.get(j));
                                break;
                            case sub :
                                list2.add(list.get(i) - list.get(j));
                                break;
                            case div :
                                if (Math.abs(list.get(j)) < precision) {//除数小于最小精度，看成0
                                    continue;
                                }
                                list2.add(list.get(i) / list.get(j));
                                break;
                        }
                        if (solver(list2)) {
                            return true;
                        }
                        // 回溯
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}