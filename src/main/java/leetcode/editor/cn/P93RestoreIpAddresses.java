//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。 
//
// 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法 
// 👍 378 👎 0

 
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//Java：复原IP地址
public class P93RestoreIpAddresses{
    public static void main(String[] args) {
        Solution solution = new P93RestoreIpAddresses().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：回溯
    // 思路：
    // 1.选择：每次选择，我们都可以选择1位或2位或3位作为一个整数。在哪里选择？
    //   在剩余未使用的字符串中选择。但是如果当前位是0，那只有1种选择就是单独选这个0作为一个整数。
    // 2.路径：根据选择目前所得到的整数。
    // 3.约束条件：每次选择，如果遇到0开头，那么只能是0单独作为一个整数；
    //    不管选择多少位，得到的整数必须在[0,255][0,255]内。
    // 4.结束条件：当已经得到了4个整数并且所有字符都被用过一次，说明得到了一个有效IP地址，
    //    加入答案；当已经得到了4个整数但是没有用完字符串，不是有效IP地址，结束；当用完了所有字符串但是还没得到4个整数，不是有效IP地址，结束。
    List<String> res = new LinkedList<>();	// 结果
    public List<String> restoreIpAddresses(String s) {
        int[] segments = new int[4];
        backtrack(s, 0, 0, segments);
        return res;
    }

    public void backtrack(String s, int start, int segmentNum, int[] segments) {
        // 到达结束条件
        if (segmentNum == 4) {
            if (start == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    sb.append(segments[i]).append(".");
                }
                sb.append(segments[3]);
                res.add(sb.toString());
            }
            return;
        }

        // 提前回溯
        if (start == s.length()) {
            return;
        }

        // 约束条件，0开头，特别处理
        if (s.charAt(start) == '0') {
            segments[segmentNum] = 0;
            backtrack(s, start + 1, segmentNum + 1, segments);
        }

        int temp = 0;
        for (int end = start; end < s.length(); end++) {
            // 做选择
            temp = temp * 10 + (s.charAt(end) - '0');
            if (temp > 0 && temp <= 255) {
                segments[segmentNum] = temp;
                backtrack(s, end + 1, segmentNum + 1, segments);
            } else {    // 这个break极为重要，没有的话会产生错误答案
                break;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}