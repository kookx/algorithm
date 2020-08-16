---
layout: post
title: 算法训练营 Week_05 学习总结
category: algorithm
tags: [数据结构、算法]
---

# 算法训练营 Week_05 学习总结

## 本周重点知识点精选

### 字典树

- Trie 树又称「前缀树」，它的典型应用对象是字符串，可以用于保存、统计；

- 其特点是：**用边表示字符**。当走到叶子结点的时候，沿途所经过的边组成了一个字符串；

- 其优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希表高。

#### [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)

```java
public class Trie {

    private Node root;

    private class Node {
        private Node[] dict;
        private boolean isWord;

        // 你可以假设所有的输入都是由小写字母 a-z 构成的。
        public Node() {
            dict = new Node[26];
            this.isWord = false;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        int len = word.length();
        Node curNode = root;
        for (int i = 0; i < len; i++) {
            char curChar = word.charAt(i);
            Node next = curNode.dict[curChar - 'a'];
            if (next == null) {
                curNode.dict[curChar - 'a'] = new Node();
            }
            curNode = curNode.dict[curChar - 'a'];
        }
        if (!curNode.isWord) {
            curNode.isWord = true;
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        int len = word.length();
        Node curNode = root;

        for (int i = 0; i < len; i++) {
            char curC = word.charAt(i);
            Node next = curNode.dict[curC - 'a'];
            if (next == null) {
                return false;
            } else {
                curNode = next;
            }
        }
        return curNode.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        int len = prefix.length();
        Node curNode = root;
        for (int i = 0; i < len; i++) {
            char curC = prefix.charAt(i);
            Node next = curNode.dict[curC - 'a'];
            if (next == null) {
                return false;
            } else {
                curNode = next;
            }
        }
        return true;
    }

}
```

#### [211. 添加与搜索单词 - 数据结构设计](https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/)

使用哈希表表示一个结点指向的所有孩子结点

```java
public class WordDictionary {

    private Node root;

    private class Node {
        private boolean isWord;
        private HashMap<Character, Node> next;

        public Node() {
            this.next = new HashMap<>();
        }
    }

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new Node();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        Node curNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!curNode.next.containsKey(c)) {
                curNode.next.put(c, new Node());
            }
            curNode = curNode.next.get(c);
        }
        if (!curNode.isWord) {
            curNode.isWord = true;
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int depth) {
        if (depth == word.length()) {
            // 只要能搜索到最后，就表示文本与模式匹配
            // 这一步很容易被忽视
            return node.isWord;
        }
        Character c = word.charAt(depth);
        if (c == '.') {
            Set<Character> keys = node.next.keySet();
            for (Character key : keys) {
                Node nextNode = node.next.get(key);
                if (search(nextNode, word, depth + 1)) {
                    return true;
                }
            }
            // 循环都走完都没有找到，那就说明没有
            return false;
        } else {
            if (!node.next.containsKey(c)) {
                return false;
            }
            return search(node.next.get(c), word, depth + 1);
        }
    }
}
```

#### [677. 键值映射](https://leetcode-cn.com/problems/map-sum-pairs/)

分析：

- 使用 `Trie` 单词查找树这个数据结构来完成，将原来的 `isWord` 设计成 `value` 它不但可以表达原来 `isWord` 的含义，还能表示题目中一个单词携带的整数的含义；
- 首先先把前缀遍历完，如果前缀都不能遍历完成，就说明单词查找树中不存在以这个单词为前缀的单词，应该返回 0，否则以一个结点为根，循环遍历到所有叶子节点，途径的所有 value 值都应该加和到最终的结果里；
- 计算 sum 设计成一个递归方法，递归方法几行就完成了计算，虽然没有显式地写出递归终止条件，但递归终止条件已经包含在方法体中了。

```java
class MapSum {

    // 创建字典树节点类
    private class Node{

        public int value;
        public HashMap<Character, Node> next;

        public Node(int value)
        {
            this.value = value;
            this.next = new HashMap<>();
        }

        public Node(){
            this(0);
        }

    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }
    
    public void insert(String key, int val) {
        Node curr = root;
        for(int i = 0; i < key.length(); i++)
        {
            char c = key.charAt(i);
            if(curr.next.get(c) == null)
                curr.next.put(c, new Node());
            curr = curr.next.get(c);
        }
        curr.value = val;
    }
    
    public int sum(String prefix) {
        Node curr = root;
        for(int i = 0; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(curr.next.get(c) == null)
                return 0;
            curr = curr.next.get(c);
        }

        return sum(curr);
    }

    private int sum(Node node)
    {
        int res = node.value;
        for(char c : node.next.keySet())
            res += sum(node.next.get(c));

        return res;
    }
}
```



### 位运算

#### [231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)

```java
class Solution {
    // x为2的幂次方说明x的二进制表示中有且仅有一位是1，其余位都是0
    public boolean isPowerOfTwo(int n) {
        // n & (n - 1)清零最低位的1
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

#### [191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/)

```java
class Solution {
    // 解法1. 暴力循环
    // 解法2. 十进制转二进制的方式。每次对 2 取余判断是否是 1，是的话就 count = count + 1
    // 解法3. 每次x&1判断是否为1，然后x = x >> 1
    // 解法4. 清零最低位的1：x&(x-1)
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ans++;
        }
        return ans;
    }
}
```

#### [136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/)

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        return ans;
    }
}
```

#### [137. 只出现一次的数字 II](https://leetcode-cn.com/problems/single-number-ii/)

```java
class Solution {
  	//  a 和 b 的卡诺图
    // 这里 a` 和 b` 的意思代表着 a 和 b 下一次的状态
    // a` = (a &~ next) | (b & next)
    // b` = (~a & ~b & next) | (b & ~next)
    public int singleNumber(int[] nums) {
        int a = 0, b = 0, tmp = 0;
        for (int next : nums) {
            tmp = (a & ~next) | (b & next);
            b = (~a & ~b & next) | (b & ~next);
            a = tmp; 
        }
        return b;
    }
}
```

优化，化简上一步中的公式

```java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int next : nums) {
            b = (b ^ next) & ~a;
            a = (a ^ next) & ~b;        
        }
        return b; 
    }
}
```

#### [268. 缺失数字](https://leetcode-cn.com/problems/missing-number/)

**两个相同的数，使用异或可以相消除**

```Java
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++ )
            res ^= nums[i] ^ i;
        return res ^ nums.length;
    }
}
```



## 本周刷题记录

### 课后作业

| 名称                                                         | 难度   | 分类             | 备注                                             |
| ------------------------------------------------------------ | ------ | ---------------- | ------------------------------------------------ |
| [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) | 🟢 简单 | 高级搜索、剪枝   | 阿里巴巴、腾讯、字节跳动在半年内面试常考         |
| [191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/) | 🟢 简单 | 字符串、动态规划 | Facebook、苹果在半年内面试中考过                 |
| [231. 2的幂](https://leetcode-cn.com/problems/power-of-two/) | 🟢 简单 | 数组、动态规划   | 谷歌、亚马逊、苹果在半年内面试中考过             |
| [190. 颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/) | 🟢 简单 | 数组、队列       | 苹果在半年内面试中考过                           |
| [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/) | 🟡 中等 | 字典树           | 亚马逊、微软、谷歌在半年内面试中考过             |
| [547. 朋友圈](https://leetcode-cn.com/problems/friend-circles/) | 🟡 中等 | 并查集           | 亚马逊、Facebook、字节跳动在半年内面试中考过     |
| [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) | 🟡 中等 | 并查集           | 近半年内，亚马逊在面试中考查此题达到 361 次      |
| [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/) | 🟡 中等 | 并查集           | 亚马逊、eBay、谷歌在半年内面试中考过             |
| [36. 有效的数独](https://leetcode-cn.com/problems/valid-sudoku/) | 🟡 中等 | 剪枝             | 亚马逊、苹果、微软在半年内面试中考过             |
| [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/) | 🟡 中等 | 剪枝             | 亚马逊、苹果、微软在半年内面试中考过             |
| [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) | 🟡 中等 | 双向BFS          | 亚马逊、Facebook、谷歌在半年内面试中考过         |
| [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) | 🟡 中等 | 双向BFS          | 谷歌、Twitter、腾讯在半年内面试中考过            |
| [338. 比特位计数](https://leetcode-cn.com/problems/counting-bits/) | 🟡 中等 | 位运算           | 字节跳动、Facebook、MathWorks 在半年内面试中考过 |
| [212. 单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/) | 🔴️ 困难 | 字典树           | 亚马逊、微软、苹果在半年内面试中考过             |
| [51. N皇后](https://leetcode-cn.com/problems/n-queens/)      | 🔴️ 困难 | 位运算           | 亚马逊、苹果、字节跳动在半年内面试中考过         |
| [37. 解数独](https://leetcode-cn.com/problems/sudoku-solver/) | 🔴️ 困难 | 启发式搜索、回溯 | 亚马逊、华为、微软在半年内面试中考过             |
| [52. N皇后 II](https://leetcode-cn.com/problems/n-queens-ii/) | 🔴️ 困难 | 位运算           | 亚马逊在半年内面试中考过                         |