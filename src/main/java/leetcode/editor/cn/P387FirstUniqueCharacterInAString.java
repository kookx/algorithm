//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 254 👎 0

 
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：字符串中的第一个唯一字符
public class P387FirstUniqueCharacterInAString{
    public static void main(String[] args) {
        Solution solution = new P387FirstUniqueCharacterInAString().new Solution();
        // TO TEST
        System.out.println(solution.firstUniqChar("loveleetcode"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1.暴力法 两层循环遍历
    public int firstUniqChar1(String s) {
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            for (int j = 0; j < s.length(); ++j) {
                char c2 = s.charAt(j);
                if (c1 == c2 && i != j) {
                    break;
                } else if (j == s.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 解法2.一次遍历+哈希表
    public int firstUniqChar2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char cs : s.toCharArray()) {
            map.put(cs, map.getOrDefault(cs, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 解法3. 使用int数组优化
    public int firstUniqChar3(String s) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    // 解法4. 根据给定字符串长度分情况优化
    public int firstUniqChar(String s) {
        // 字符串长度不超过26，按照原先的方式遍历
        if (s.length() <= 26) {
            int[] chars = new int[26];
            for (char c : s.toCharArray()) {
                chars[c - 'a'] += 1;
            }
            for (int i = 0; i < s.length(); ++i) {
                if (chars[s.charAt(i) - 'a'] == 1) {
                    return i;
                }
            }
        }
        //只遍历26个字母，使用indexOf函数检查字符索引
        int result = -1;
        for (char c = 'a'; c <= 'z'; ++c) {
            int pre = s.indexOf(c);
            // s包含该字符并且只出现一次
            if (pre != -1 && pre == s.lastIndexOf(c)) {
                // 取最前面的位置
                result = (result == -1 || result > pre) ? pre : result;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}