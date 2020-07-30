//哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’
//t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一
//本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。 
//
//
// 注意：本题相对原题稍作改动，只需返回未识别的字符数 
//
// 
//
// 示例： 
//
// 输入：
//dictionary = ["looked","just","like","her","brother"]
//sentence = "jesslookedjustliketimherbrother"
//输出： 7
//解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
// 
//
// 提示： 
//
// 
// 0 <= len(sentence) <= 1000 
// dictionary中总字符数不超过 150000。 
// 你可以认为dictionary和sentence中只包含小写字母。 
// 
// Related Topics 记忆化 字符串 
// 👍 143 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：恢复空格
public class P面试题1713ReSpaceLcci{
    public static void main(String[] args) {
        Solution solution = new P面试题1713ReSpaceLcci().new Solution();
        // TO TEST
        solution.respace(new String[]{"looked","just","like","her","brother"},"jesslookedjustliketimherbrother");
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int respace(String[] dictionary, String sentence) {
        return dfs2(dictionary, sentence);
    }

    // 解法1. 暴力动态规划
    private int dfs(String[] dictionary, String sentence) {
        Set<String> dic = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
        // 定义状态
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            // 这是第一种转移
            dp[i] = dp[i - 1] + 1;
            for (int idx = 0; idx < i; idx++) {
                if (dic.contains(sentence.substring(idx, i))) {
                    // 如果该字符串在字典里，就要更新dp[i]，这是第二种转移
                    dp[i] = Math.min(dp[idx], dp[i]);
                }
            }
        }
        return dp[n];
    }

    // 解法2. 基于解法1优化，减少遍历次数，如果字典中每个词的最后一个字母都不能匹配上该字符串的i位置，说明肯定不匹配，那就跳过，
    // 如果匹配上了，那就看这个字符对应的所有字典里词的长度从原字符串里截取出来的子串是否存在字典里
    private int dfs1(String[] dictionary, String sentence) {
        Set<String> dic = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
        int[] dp = new int[n + 1];

        Map<Character,Set<Integer>> map = new HashMap<>();
        for (String key : dictionary) {
            int len = key.length();
            char c = key.charAt(len - 1);
            Set<Integer> lens = map.getOrDefault(c, new HashSet<>());
            lens.add(len);
            map.put(c, lens);
        }

        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;

            char c = sentence.charAt(i - 1);
            if (map.containsKey(c)) {
                Set<Integer> lens = map.get(c);

                Iterator<Integer> it = lens.iterator();
                while (it.hasNext()) {
                    int len = it.next();
                    if (len <= i && dic.contains(sentence.substring(i - len, i))) {
                        dp[i] = Math.min(dp[i - len], dp[i]);
                    }
                }
            }
        }
        return dp[n];
    }


    // 解法3。动态规划+字典树
    class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {

        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.isWord = true;
        }

        public List<Integer> search(String sentence, int endPos) {
            List<Integer> indices = new ArrayList<>();
            TrieNode cur = root;
            for (int i = endPos; i >= 0; i--) {
                int c = sentence.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    break;
                }
                cur = cur.children[c];
                if (cur.isWord) {
                    indices.add(i);
                }
            }
            return indices;
        }
    }

    public int dfs2(String[] dictionary, String sentence){
        Trie trie = new Trie();
        for (String word: dictionary) {
            trie.insert(word);
        }

        int n = sentence.length();

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
            for (int idx: trie.search(sentence, i - 1)) {
                dp[i] = Math.min(dp[i], dp[idx]);
            }
        }
        return dp[n];
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}