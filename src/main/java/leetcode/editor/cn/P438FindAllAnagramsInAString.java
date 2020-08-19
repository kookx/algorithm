//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。 
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表 
// 👍 349 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：找到字符串中所有字母异位词
public class P438FindAllAnagramsInAString{
    public static void main(String[] args) {
        Solution solution = new P438FindAllAnagramsInAString().new Solution();
        // TO TEST
        System.out.println(solution.findAnagrams("abab", "ab"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 暴力解法：参考242，使用计数器遍历比较两个词是否异位词
    public List<Integer> findAnagrams1(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();
        int[] table = new int[26];
        int pLen = p.length();
        int sLen = s.length();
        List<Integer> res = new ArrayList<>();
        for (char c : p.toCharArray())
            table[c - 'a']++;
        // 每次向后挪一位
        for (int i = 0; i < sLen; ++i) {
            if ((i + pLen) > sLen) break;
            String s1 = s.substring(i, i + pLen);
            if (valid(s1, table.clone())) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean valid(String s, int[] table) {
        for (char c : s.toCharArray()) {
            table[c - 'a']--;
            if (table[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // 将计数器换成排序
    public List<Integer> findAnagrams2(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();
        int pLen = p.length(), sLen = s.length();
        List<Integer> res = new ArrayList<>();
        char[] pStr = p.toCharArray();
        Arrays.sort(pStr);
        // 每次向后挪一位：窗口向后滑动
        for (int i = 0; i < sLen; ++i) {
            if ((i + pLen) > sLen) break;
            char[] sStr = s.substring(i, i + pLen).toCharArray();
            Arrays.sort(sStr);
            if (Arrays.equals(pStr, sStr)) {
                res.add(i);
            }
        }
        return res;
    }

    // 使用滑动窗口优化为O(n)时间复杂度
    // 参考https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/shi-yong-hua-dong-chuang-kou-jie-ti-hen-rong-yi-by/
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = 0, count = p.length();
        int[] table = new int[26];
        List<Integer> res = new ArrayList<>();
        for (char c : p.toCharArray()) {
            table[c - 'a']++;
        }
        while (right < s.length()) {
            if (table[s.charAt(right)- 'a'] > 0) {
                count--;
            }
            table[s.charAt(right)- 'a']--;
            right++;
            if (count == 0) {
                res.add(left);
            }
            //向右滑动，调整count和table的状况
            if (right - left == p.length()) {
                if (table[s.charAt(left) - 'a'] >= 0) {
                    count++;
                }
                table[s.charAt(left) - 'a']++;
                left++;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}