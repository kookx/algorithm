//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串 
// 👍 565 👎 0

 
package leetcode.editor.cn;
//Java：实现 strStr()
public class P28ImplementStrstr{
    public static void main(String[] args) {
        Solution solution = new P28ImplementStrstr().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：常规解法
    // 思路：每次在haystack中取needle的长度字符串进行比较，
    public int strStr1(String haystack, String needle) {
        int L = haystack.length(), n = needle.length();
        for (int start = 0; start < L - n + 1; start++) {
            if (haystack.substring(start, start + n).equals(needle)) {
                return start;
            }
        }
        return -1;
    }
    // 解法2：双指针
    // 思路：常规解法缺陷是会将 haystack 所有长度为 L 的子串都与 needle 字符串比较，
    // 实际上是不需要这么做的
    public int strStr(String haystack, String needle) {
        int L = haystack.length(), n = needle.length();
        if (n== 0) return 0;
        int pl = 0;
        while (pl < L - n + 1) {
            // 在haystack中找出needle的第一个元素的位置
            while (pl < L - n + 1 && haystack.charAt(pl) != needle.charAt(0)) ++pl;
            // 找出最大的匹配长度
            int currLen = 0, pn = 0;
            while (pl < L && pn < n && haystack.charAt(pl) == needle.charAt(pn)) {
                ++pn;
                ++pl;
                ++currLen;
            }
            // 如果找到完全匹配的字符串，返回
            if (currLen == n) return pl - n;
            // 否则回溯，重设pl
            pl = pl - currLen + 1;
        }
        return -1;
    }
    // 解法3：KMP算法
}
//leetcode submit region end(Prohibit modification and deletion)

}