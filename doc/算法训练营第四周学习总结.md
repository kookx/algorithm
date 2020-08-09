---
layout: post
title: 算法训练营 Week_04 学习总结
category: algorithm
tags: [数据结构、算法]
---

# 算法训练营 Week_04 学习总结

## 本周重点知识点精选

### 动态规划

#### 定义

> **将一个问题拆成几个子问题，分别求解这些子问题，即可推断出大问题的解**。

#### 关键点

1. 最优子结构opt[n] = best_of(opt[n-1],opt[n-2], ...)

2. 定义好状态空间，存储中间状态：opt[i]

3. 递推公式：状态转移方程或者DP方程

   Fib：opt[i] = opt[n-1]+opt[n-2]

   二维路径：opt[i, j] = opt[i + 1] [j]  + opt[i] [j + 1]（且判断a[i, j]是否也为空地）

4. 自上而下推导，自底向上求解

#### 动态规划小结

1. 打破自己的思维惯性，形成机器思维：找重复性

2. 分治思想是理解复杂逻辑的关键，这也是职业进阶的要点要领

3. 学习算法，一定要注重算法的模板框架

4. **计算机解决问题其实没有任何奇技淫巧，它唯一的解决办法就是穷举**，穷举所有可能性。算法设计无非就是先思考“如何穷举”，然后再追求“如何聪明地穷举”。列出动态转移方程，就是在解决“如何穷举”的问题。之所以说它难，一是因为很多穷举需要递归实现，二是因为有的问题本身的解空间复杂，不那么容易穷举完整。

   备忘录、DP table 就是在追求“如何聪明地穷举”。用空间换时间的思路，是降低时间复杂度的不二法门。

#### 动态规划模版

```java
# 初始化 base case
dp[0][0][...] = base
# 进行状态转移
for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
        for ...
            dp[状态1][状态2][...] = 求最值(选择1，选择2...)
```

#### 经典动态规划题

##### 斐波拉契数列

- 首先想到：暴力递归

  ```c++
  int fib(int N) {
      if (N == 1 || N == 2) return 1;
      return fib(N - 1) + fib(N - 2);
  }
  ```

- 然后优化：带备忘录的递归解法

  ```c++
  int fib(int N) {
      if (N < 1) return 0;
      // 备忘录全初始化为 0
      vector<int> memo(N + 1, 0);
      // 进行带备忘录的递归
      return helper(memo, N);
  }
  
  int helper(vector<int>& memo, int n) {
      // base case 
      if (n == 1 || n == 2) return 1;
      // 已经计算过
      if (memo[n] != 0) return memo[n];
      memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
      return memo[n];
  }
  ```

- 继续优化：dp 数组的迭代解法

  同时状态压缩：使用单变量节省dp数组空间

  ```c++
  int fib(int n) {
      if (n == 2 || n == 1) 
          return 1;
      int prev = 1, curr = 1;
      for (int i = 3; i <= n; i++) {
          int sum = prev + curr;
          prev = curr;
          curr = sum;
      }
      return curr;
  }
  ```

##### 凑零钱问题

- 首先想到：暴力递归

  ```python
  def coinChange(coins: List[int], amount: int):
  
      def dp(n):
          # base case
          if n == 0: return 0
          if n < 0: return -1
          # 求最小值，所以初始化为正无穷
          res = float('INF')
          for coin in coins:
              subproblem = dp(n - coin)
              # 子问题无解，跳过
              if subproblem == -1: continue
              res = min(res, 1 + subproblem)
  
          return res if res != float('INF') else -1
  
      return dp(amount)
  ```

- 然后优化：带备忘录的递归

  ```python
  def coinChange(coins: List[int], amount: int):
      # 备忘录
      memo = dict()
      def dp(n):
          # 查备忘录，避免重复计算
          if n in memo: return memo[n]
          # base case
          if n == 0: return 0
          if n < 0: return -1
          res = float('INF')
          for coin in coins:
              subproblem = dp(n - coin)
              if subproblem == -1: continue
              res = min(res, 1 + subproblem)
  
          # 记入备忘录
          memo[n] = res if res != float('INF') else -1
          return memo[n]
  
      return dp(amount)
  ```

- 继续优化：dp 数组的迭代解法

  ```c++
  int coinChange(vector<int>& coins, int amount) {
      // 数组大小为 amount + 1，初始值也为 amount + 1
      vector<int> dp(amount + 1, amount + 1);
      // base case
      dp[0] = 0;
      // 外层 for 循环在遍历所有状态的所有取值
      for (int i = 0; i < dp.size(); i++) {
          // 内层 for 循环在求所有选择的最小值
          for (int coin : coins) {
              // 子问题无解，跳过
              if (i - coin < 0) continue;
              dp[i] = min(dp[i], 1 + dp[i - coin]);
          }
      }
      return (dp[amount] == amount + 1) ? -1 : dp[amount];
  }
  ```

##### 子序列问题

###### [最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence)

- 思路一

  **一个一维的 dp 数组**

  ```java
  int n = array.length;
  int[] dp = new int[n];
  
  for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
          dp[i] = 最值(dp[i], dp[j] + ...)
      }
  }
  ```

- 思路二

  **一个二维的 dp 数组**

  ```java
  int n = arr.length;
  int[][] dp = new dp[n][n];
  
  for (int i = 0; i < n; i++) {
      for (int j = 1; j < n; j++) {
          if (arr[i] == arr[j]) 
              dp[i][j] = dp[i][j] + ...
          else
              dp[i][j] = 最值(...)
      }
  }
  ```

- 无状态压缩

  ```c++
  int longestPalindromeSubseq(string s) {
      int n = s.size();
      // dp 数组全部初始化为 0
      vector<vector<int>> dp(n, vector<int>(n, 0));
      // base case
      for (int i = 0; i < n; i++)
          dp[i][i] = 1;
      // 反着遍历保证正确的状态转移
      for (int i = n - 2; i >= 0; i--) {
          for (int j = i + 1; j < n; j++) {
              // 状态转移方程
              if (s[i] == s[j])
                  dp[i][j] = dp[i + 1][j - 1] + 2;
              else
                  dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
          }
      }
      // 整个 s 的最长回文子串长度
      return dp[0][n - 1];
  }
  ```

  **如果计算状态** **`dp[i][j]`** **需要的都是** **`dp[i][j]`** **相邻的状态，那么就可以使用状态压缩技巧**，将二维的 `dp` 数组转化成一维，将空间复杂度从 O(N^2) 降低到 O(N)。

- 状态压缩

  ```c++
  int longestPalindromeSubseq(string s) {
      int n = s.size();
      // base case：一维 dp 数组全部初始化为 0
      vector<int> dp(n, 1);
  
      for (int i = n - 2; i >= 0; i--) {
          int pre = 0;
          for (int j = i + 1; j < n; j++) {
              int temp = dp[j];
              // 状态转移方程
              if (s[i] == s[j])
                  dp[j] = pre + 2;
              else
                  dp[j] = max(dp[j], dp[j - 1]);
              pre = temp;
          }
      }
      return dp[n - 1];
  }
  ```

###### [最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)

- 递归解法

  ```python
  def longestCommonSubsequence(str1, str2) -> int:
      def dp(i, j):
          # 空串的 base case
          if i == -1 or j == -1:
              return 0
          if str1[i] == str2[j]:
              # 这边找到一个 lcs 的元素，继续往前找
              return dp(i - 1, j - 1) + 1
          else:
              # 谁能让 lcs 最长，就听谁的
              return max(dp(i-1, j), dp(i, j-1))
  
      # i 和 j 初始化为最后一个索引
      return dp(len(str1)-1, len(str2)-1)
  ```

- DP解法

  ```python
  def longestCommonSubsequence(str1, str2) -> int:
      m, n = len(str1), len(str2)
      # 构建 DP table 和 base case
      dp = [[0] * (n + 1) for _ in range(m + 1)]
      # 进行状态转移
      for i in range(1, m + 1):
          for j in range(1, n + 1):
              if str1[i - 1] == str2[j - 1]:
                  # 找到一个 lcs 中的字符
                  dp[i][j] = 1 + dp[i-1][j-1]
              else:
                  dp[i][j] = max(dp[i-1][j], dp[i][j-1])
  
      return dp[-1][-1]
  ```

###### [最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

- DP解法

  ```java
  public int lengthOfLIS(int[] nums) {
      int[] dp = new int[nums.length];
      // base case：dp 数组全都初始化为 1
      Arrays.fill(dp, 1);
      for (int i = 0; i < nums.length; i++) {
          for (int j = 0; j < i; j++) {
              if (nums[i] > nums[j]) 
                  dp[i] = Math.max(dp[i], dp[j] + 1);
          }
      }
  
      int res = 0;
      for (int i = 0; i < dp.length; i++) {
          res = Math.max(res, dp[i]);
      }
      return res;
  }
  ```

- 二分查找

  ```java
  public int lengthOfLIS(int[] nums) {
      int[] top = new int[nums.length];
      // 牌堆数初始化为 0
      int piles = 0;
      for (int i = 0; i < nums.length; i++) {
          // 要处理的扑克牌
          int poker = nums[i];
  
          /***** 搜索左侧边界的二分查找 *****/
          int left = 0, right = piles;
          while (left < right) {
              int mid = (left + right) / 2;
              if (top[mid] > poker) {
                  right = mid;
              } else if (top[mid] < poker) {
                  left = mid + 1;
              } else {
                  right = mid;
              }
          }
          /*********************************/
  
          // 没找到合适的牌堆，新建一堆
          if (left == piles) piles++;
          // 把这张牌放到牌堆顶
          top[left] = poker;
      }
      // 牌堆数就是 LIS 长度
      return piles;
  }
  ```

##### 股票买卖问题

[买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/)

[买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

[买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)

[买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

[最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

[买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

这 6 道题目有共性，第 4 道是一个最泛化的形式，其他的问题都是这个形式的简化

- 状态转移方程

  ```java
  dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
                max(   选择 rest  ,             选择 sell      )
  
  解释：今天我没有持有股票，有两种可能：
  要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
  要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
  
  dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
                max(   选择 rest  ,           选择 buy         )
  
  解释：今天我持有着股票，有两种可能：
  要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
  要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
  ```

- base case

  ```java
  dp[-1][k][0] = 0
  解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
  dp[-1][k][1] = -infinity
  解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
  dp[i][0][0] = 0
  解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
  dp[i][0][1] = -infinity
  解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
  ```

- 总结

  ```java
  base case：
  dp[-1][k][0] = dp[i][0][0] = 0
  dp[-1][k][1] = dp[i][0][1] = -infinity
  
  状态转移方程：
  dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
  dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
  ```

###### [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/)

**k = 1**

- 套用状态转移方程

  ```java
  dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
  dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) 
              = max(dp[i-1][1][1], -prices[i])
  解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
  
  现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
  可以进行进一步化简去掉所有 k：
  dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
  dp[i][1] = max(dp[i-1][1], -prices[i])
  ```

- 代码实现

  ```java
  int n = prices.length;
  int[][] dp = new int[n][2];
  for (int i = 0; i < n; i++) {
      if (i - 1 == -1) {
          dp[i][0] = 0;
          // 解释：
          //   dp[i][0] 
          // = max(dp[-1][0], dp[-1][1] + prices[i])
          // = max(0, -infinity + prices[i]) = 0
          dp[i][1] = -prices[i];
          //解释：
          //   dp[i][1] 
          // = max(dp[-1][1], dp[-1][0] - prices[i])
          // = max(-infinity, 0 - prices[i]) 
          // = -prices[i]
          continue;
      }
      dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
      dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
  }
  return dp[n - 1][0];
  ```

- 状态压缩

  ```java
  // k == 1
  int maxProfit_k_1(int[] prices) {
      int n = prices.length;
      // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
      int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
          // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
          dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
          // dp[i][1] = max(dp[i-1][1], -prices[i])
          dp_i_1 = Math.max(dp_i_1, -prices[i]);
      }
      return dp_i_0;
  }
  ```

###### [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

**k = +infinity**

- 套用状态转移方程

  ```java
  dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
  dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
              = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
  
  我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
  dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
  dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
  ```

- 代码实现

  ```java
  int maxProfit_k_inf(int[] prices) {
      int n = prices.length;
      int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
          int temp = dp_i_0;
          dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
          dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
      }
      return dp_i_0;
  }
  ```

###### [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)

**k = +infinity with cooldown**

- 套用状态转移方程

  ``` Java
  dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
  dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
  解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
  ```

- 代码实现

  ```java
  int maxProfit_with_cool(int[] prices) {
      int n = prices.length;
      int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
      int dp_pre_0 = 0; // 代表 dp[i-2][0]
      for (int i = 0; i < n; i++) {
          int temp = dp_i_0;
          dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
          dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
          dp_pre_0 = temp;
      }
      return dp_i_0;
  }
  ```

###### [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

**k = +infinity with fee**

- 套用状态转移方程

  ```java
  dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
  dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
  解释：相当于买入股票的价格升高了。
  在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
  ```

- 代码实现

  ```java
  int maxProfit_with_fee(int[] prices, int fee) {
      int n = prices.length;
      int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
          int temp = dp_i_0;
          dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
          dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
      }
      return dp_i_0;
  }
  ```

###### [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

**k = 2**

- 套用状态转移方程

  ``` java
  原始的动态转移方程，没有可化简的地方
  dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
  dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
  ```

- 代码实现

  ```java
  int max_k = 2;
  int[][][] dp = new int[n][max_k + 1][2];
  for (int i = 0; i < n; i++) {
      for (int k = max_k; k >= 1; k--) {
          if (i - 1 == -1) { /*处理 base case */ }
          dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
          dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
      }
  }
  // 穷举了 n × max_k × 2 个状态，正确。
  return dp[n - 1][max_k][0];
  ```

###### [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

**k = any integer**

- 代码实现

  ```java
  int maxProfit_k_any(int max_k, int[] prices) {
      int n = prices.length;
      if (max_k > n / 2) 
          return maxProfit_k_inf(prices);
  
      int[][][] dp = new int[n][max_k + 1][2];
      for (int i = 0; i < n; i++) 
          for (int k = max_k; k >= 1; k--) {
              if (i - 1 == -1) { /* 处理 base case */ }
              dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
              dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);     
          }
      return dp[n - 1][max_k][0];
  }
  ```

> **小总结**

通过状态转移的方法解决复杂的问题，用一个状态转移方程秒杀了 6 道股票买卖问题，关键就在于列举出所有可能的「状态」，然后想想怎么穷举更新这些「状态」。一般用一个多维 dp 数组储存这些状态，从 base case 开始向后推进，推进到最后的状态，就是我们想要的答案。股票买卖问题，我们发现了三个状态，使用了一个三维数组，无非还是穷举 + 更新，不过我们可以说的高大上一点，这叫「三维 DP」。

##### 博弈问题

**博弈问题简单描述：「假设两个人都足够聪明，最后谁会获胜」这一类问题**
类似的问题如：俩海盗分宝石，俩人拿硬币等

**思路：在二维 dp 的基础上使用元组分别存储两个人的博弈结果**

###### 石子游戏

修改[877.石子游戏](https://leetcode-cn.com/problems/stone-game)使其更具有一般性：你和你的朋友面前有一排石头堆，用一个数组 piles 表示，piles[i] 表示第 i 堆石子有多少个。你们轮流拿石头，一次拿一堆，但是只能拿走最左边或者最右边的石头堆。所有石头被拿完后，谁拥有的石头多，谁获胜。
石头的堆数可以是任意正整数，石头的总数也可以是任意正整数，这样就能打破先手必胜的局面了。比如有三堆石头 `piles = [1, 100, 3]`，先手不管拿 1 还是 3，能够决定胜负的 100 都会被后手拿走，后手会获胜。
**假设两人都很聪明**，请你设计一个算法，返回先手和后手的最后得分（石头总数）之差。比如上面那个例子，先手能获得 4 分，后手会获得 100 分，你的算法应该返回 -96。

> 难点

博弈问题的难点在于，两个人要轮流进行选择，而且都贼精明，应该如何编程表示这个过程呢？

> 套路

- 定义 dp 数组的含义

  ```java
  dp[i][j].fir 表示，对于 piles[i...j] 这部分石头堆，先手能获得的最高分数。
  dp[i][j].sec 表示，对于 piles[i...j] 这部分石头堆，后手能获得的最高分数。
  
  举例理解一下，假设 piles = [3, 9, 1, 2]，索引从 0 开始
  dp[0][1].fir = 9 意味着：面对石头堆 [3, 9]，先手最终能够获得 9 分。
  dp[1][3].sec = 2 意味着：面对石头堆 [9, 1, 2]，后手最终能够获得 2 分。
  ```

- 状态转移方程

  ```java
  dp[i][j].fir = max(piles[i] + dp[i+1][j].sec, piles[j] + dp[i][j-1].sec)
  dp[i][j].fir = max(    选择最左边的石头堆     ,     选择最右边的石头堆     )
  # 解释：我作为先手，面对 piles[i...j] 时，有两种选择：
  # 要么我选择最左边的那一堆石头，然后面对 piles[i+1...j]
  # 但是此时轮到对方，相当于我变成了后手；
  # 要么我选择最右边的那一堆石头，然后面对 piles[i...j-1]
  # 但是此时轮到对方，相当于我变成了后手。
  
  if 先手选择左边:
      dp[i][j].sec = dp[i+1][j].fir
  if 先手选择右边:
      dp[i][j].sec = dp[i][j-1].fir
  # 解释：我作为后手，要等先手先选择，有两种情况：
  # 如果先手选择了最左边那堆，给我剩下了 piles[i+1...j]
  # 此时轮到我，我变成了先手；
  # 如果先手选择了最右边那堆，给我剩下了 piles[i...j-1]
  # 此时轮到我，我变成了先手。
  ```

- base case

  ```java
  dp[i][j].fir = piles[i]
  dp[i][j].sec = 0
  其中 0 <= i == j < n
  # 解释：i 和 j 相等就是说面前只有一堆石头 piles[i]
  # 那么显然先手的得分为 piles[i]
  # 后手没有石头拿了，得分为 0
  ```

- 代码实现

  ```java
  class Pair {
      int fir, sec;
      Pair(int fir, int sec) {
          this.fir = fir;
          this.sec = sec;
      }
  }
  ```

  **注意一下「斜着」遍历数组的技巧**

  ```java
  /* 返回游戏最后先手和后手的得分之差 */
  int stoneGame(int[] piles) {
      int n = piles.length;
      // 初始化 dp 数组
      Pair[][] dp = new Pair[n][n];
      for (int i = 0; i < n; i++) 
          for (int j = i; j < n; j++)
              dp[i][j] = new Pair(0, 0);
      // 填入 base case
      for (int i = 0; i < n; i++) {
          dp[i][i].fir = piles[i];
          dp[i][i].sec = 0;
      }
      // 斜着遍历数组
      for (int l = 2; l <= n; l++) {
          for (int i = 0; i <= n - l; i++) {
              int j = l + i - 1;
              // 先手选择最左边或最右边的分数
              int left = piles[i] + dp[i+1][j].sec;
              int right = piles[j] + dp[i][j-1].sec;
              // 套用状态转移方程
              if (left > right) {
                  dp[i][j].fir = left;
                  dp[i][j].sec = dp[i+1][j].fir;
              } else {
                  dp[i][j].fir = right;
                  dp[i][j].sec = dp[i][j-1].fir;
              }
          }
      }
      Pair res = dp[0][n-1];
      return res.fir - res.sec;
  }
  ```

> 小总结

博弈问题的前提一般都是在两个聪明人之间进行，编程描述这种游戏的一般方法是二维 dp 数组，数组中通过元组分别表示两人的最优决策。
之所以这样设计，是因为先手在做出选择之后，就成了后手，后手在对方做完选择后，就变成了先手。这种角色转换使得我们可以重用之前的结果，典型的动态规划标志。

## 本周刷题记录

### 课后作业

| 名称                                                         | 难度   | 分类               | 备注                                         |
| ------------------------------------------------------------ | ------ | ------------------ | -------------------------------------------- |
| [64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/) | 🟡 中等 | 数组、动态规划     | 亚马逊、高盛集团、谷歌在半年内面试中考过     |
| [91. 解码方法](https://leetcode-cn.com/problems/decode-ways/) | 🟡 中等 | 字符串、动态规划   | 亚马逊、Facebook、字节跳动在半年内面试中考过 |
| [221. 最大正方形](https://leetcode-cn.com/problems/maximal-square/) | 🟡 中等 | 数组、动态规划     | 华为、谷歌、字节跳动在半年内面试中考过       |
| [621. 任务调度器](https://leetcode-cn.com/problems/task-scheduler/) | 🟡 中等 | 数组、队列         | Facebook 在半年内面试中常考                  |
| [647. 回文子串](https://leetcode-cn.com/problems/palindromic-substrings/) | 🟡 中等 | 字符串、动态规划   | Facebook、苹果、字节跳动在半年内面试中考过   |
| [312. 戳气球](https://leetcode-cn.com/problems/burst-balloons/) | 🔴️ 困难 | 分治算法、动态规划 | 亚马逊在半年内面试中考过                     |