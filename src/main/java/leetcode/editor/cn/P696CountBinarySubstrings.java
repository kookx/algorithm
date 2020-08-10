//给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。 
//
// 重复出现的子串要计算它们出现的次数。 
//
// 示例 1 : 
//
// 
//输入: "00110011"
//输出: 6
//解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
//
//请注意，一些重复出现的子串要计算它们出现的次数。
//
//另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
// 
//
// 示例 2 : 
//
// 
//输入: "10101"
//输出: 4
//解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
// 
//
// 注意： 
//
// 
// s.length 在1到50,000之间。 
// s 只包含“0”或“1”字符。 
// 
// Related Topics 字符串 
// 👍 214 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：计数二进制子串
public class P696CountBinarySubstrings{
    public static void main(String[] args) {
        Solution solution = new P696CountBinarySubstrings().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 理解：1. "100"的子串只为"10"，子串"100"不满足具有相同数量0和1”的条件
    //      2. 样例中"00110011"的子串不包含"00110011"，题目解释说因为所有的0（和1）没有组合在一起
    //         我们可以这样理解：因为01的子串只能连续出现，不能一串01后再出现01
    //      3.样例中"10101"的子串为“10”，“01”，“10”，“01”，即[10]101,1[01]01,10[10]1,101[01]
    // 从上就推导出了：从左至右统计连续的0和1出现的次数，每组相邻的0和1中次数小的那一方为该组的子串个数，总次数就是所有组的子串个数之和
    public int countBinarySubstrings_old(String s) {
        //统计相邻字符串重复出现的次数
        List<Integer> counts = new ArrayList<>();
        int chr = 0, n = s.length();
        while (chr < n) {
            char c = s.charAt(chr);
            int count = 0;
            while (chr < n && c == s.charAt(chr)) {
                chr++;
                count++;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); i++) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }

        return ans;
    }

    // 优化空间，考虑到每次计算i的ans都只需依赖i-1的值，因此可以使用单变量last记录前一个位置
    public int countBinarySubstrings(String s) {
        //统计相邻字符串重复出现的次数并统计结果
        List<Integer> counts = new ArrayList<>();
        int ans = 0, pre = 0;
        int chr = 0, n = s.length();
        while (chr < n) {
            char c = s.charAt(chr);
            int count = 0;
            while (chr < n && c == s.charAt(chr)) {
                chr++;
                count++;
            }
            ans += Math.min(pre, count);
            pre = count;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}