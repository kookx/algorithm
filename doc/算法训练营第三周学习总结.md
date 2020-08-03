---
layout: post
title: 算法训练营 Week_03 学习总结
category: algorithm
tags: [数据结构、算法]
---

# 算法训练营 Week_03 学习总结

## 本周重点知识点精选

### 回溯（递归回溯）

#### 定义

对于许多现实世界的问题，解决过程由一系列决策点组成，在这些决策点上，每个选择都会引导您沿着某个路径走得更远。

如果做出了正确的选择，最终程序就会得到解决方案。另一方面，如果你走到了死胡同，或者发现自己在某个地方做出了错误的选择，你就必须回到以前的决策点，尝试另一条不同的道路。使用这种方法的算法称为回溯算法。

这就是回溯——**我们需要尝试所有的可能的“路径”，然后输出或者返回需要的内容**。

不管怎么优化，都符合回溯框架，而且时间复杂度都不可能低于 O(N!)，因为穷举整棵决策树是无法避免的。这也是回溯算法的一个特点，不像`动态规划`存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高。

其实想想看，回溯算法和动态规划是不是有点像呢？我们在动态规划系列文章中多次看到，动态规划的三个需要明确的点就是「状态」「选择」和「base case」，是不是就对应着走过的「路径」，当前的「选择列表」和「结束条件」？

某种程度上说，动态规划的暴力求解阶段就是回溯算法。只是有的问题具有重叠子问题性质，可以用 dp table 或者备忘录优化，将递归树大幅`剪枝`，这就变成了动态规划。如果问题中没有重叠子问题，也就是回溯算法问题了，复杂度非常高是不可避免的。

#### 传统递归和递归回溯

##### 基础递归

- 通过重复调用函数计算出最终结构
- 结束条件提供了一个关于问题的基本解法并且每一次递归都为结果进行了一点贡献

##### 递归回溯

- 通过在递归函数里多次调用递归函数探索所有可能的路径
- 在结束条件会提供一个类似“无用结果”
- 每次到达结束条件，都可能会得到一个潜在的解决方案

#### 回溯模版

```java
class Solution {
  	// 根据题目要求的返回类型，建立一个全局变量，最后将其返回
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      	// 根据题目要求设定基础边界条件
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }
        List<Integer> list = new ArrayList<>();
        process(candidates, target, list);
        return lists;
    }
  	// 辅助函数，帮助递归调用，因为最终返回结构为List<List<Integer>>
    // 所以这里入参设为List<Integer>，同时还要根据具体情况配置入参，
  	// 这里只假设只需要一个List<Integer>参数，常见的还需要记录当前位置的参数等，
    // 这是因为，在递归回溯的问题里，我们往往需要使用多个参数来记录我们已经走过的路径，但是		 // 我们不希望使用这个函数的用户提供这些参数，所以我们只让他们来提供一个集合，而设计这个		// 函数的人去提供额外的参数。这也是回溯算法的一个特点——回溯算法往往需要通过构造另外一			// 个辅助函数来帮忙解决问题。
    private void process(int[] candidates, int target, List<Integer> list) {
    		// 这里写递归终止条件
      	...
      	// 这里写递归调用策略
				...
    }
}
```

一种更简洁的框架描述

```python
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

**其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」**，特别简单。

#### 经典回溯题

##### [78. 子集](https://leetcode-cn.com/problems/subsets/)

```python
class Solution:
	def subsets(self, nums):		
                if not nums:
			return []
		res = []
		n = len(nums)

		def helper(idx, temp_list):
			res.append(temp_list)
			for i in range(idx, n):
				helper(i + 1, temp_list + [nums[i]])

		helper(0, [])
		return res
```

##### [90. 子集 II](https://leetcode-cn.com/problems/subsets-ii/)

```python
class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if not nums:
            return
        res = []
        n = len(nums)
        visited = [0] * n
        def helper1(temp_list,length):
            if length == n:
                res.append(temp_list)
            for i in range(n):
                if visited[i] :
                    continue
                visited[i] = 1
                helper1(temp_list+[nums[i]],length+1)
                visited[i] = 0
        def helper2(nums,temp_list,length):
            if length == n:
                res.append(temp_list)
            for i in range(len(nums)):
                helper2(nums[:i]+nums[i+1:],temp_list+[nums[i]],length+1)
        helper1([],0)
        return res
```

##### [46. 全排列](https://leetcode-cn.com/problems/permutations/)

```python
class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if not nums:
            return
        res = []
        n = len(nums)
        visited = [0] * n
        def helper1(temp_list,length):
            if length == n:
                res.append(temp_list)
            for i in range(n):
                if visited[i] :
                    continue
                visited[i] = 1
                helper1(temp_list+[nums[i]],length+1)
                visited[i] = 0
        def helper2(nums,temp_list,length):
            if length == n:
                res.append(temp_list)
            for i in range(len(nums)):
                helper2(nums[:i]+nums[i+1:],temp_list+[nums[i]],length+1)
        helper1([],0)
        return res
```

##### [39.组合总和](https://leetcode-cn.com/problems/combination-sum/)

```python
class Solution(object):
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        if not candidates:
            return []
        if min(candidates) > target:
            return []
        candidates.sort()
        res = []

        def helper(candidates, target, temp_list):
            if target == 0:
                res.append(temp_list)
            if target < 0:
                return
            for i in range(len(candidates)):
                if candidates[i] > target:
                    break
                helper(candidates[i:], target - candidates[i], temp_list + [candidates[i]])
        helper(candidates,target,[])
        return res
```

##### [40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/)

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        if not candidates:
            return []
        candidates.sort()
        n = len(candidates)
        res = []
        
        def backtrack(i, tmp_sum, tmp_list):
            if tmp_sum == target:
                res.append(tmp_list)
                return 
            for j in range(i, n):
                if tmp_sum + candidates[j]  > target : break
                if j > i and candidates[j] == candidates[j-1]:continue
                backtrack(j + 1, tmp_sum + candidates[j], tmp_list + [candidates[j]])
        backtrack(0, 0, [])    
        return res
```

### 贪心算法

#### 定义

贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。

#### 注意点

贪心算法可以解决一些最优化问题，如：求图中的最小生成树、求哈夫曼编码等。然而对于工程和生活中的问题，贪心算法一般不能得到我们所要求的答案。

一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求得的答案比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。

要用贪心法来解决某个问题，必须要证明贪心法能得到全局最优解（不能有反例）。

有些时候贪心的角度不同，可能是从后往前贪心，可能从某一个局部切入进行贪心。

> 贪心、回溯、动态规划的区别

- 贪心：当下做局部最优选择，不能回退
- 回溯：试错+回退
- 动态规划：最优判断+回退

#### 应用场景

简单的说，问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。这种子问题最优解称为最优子结构。

贪心算法与动态规划的不同之处在于它对每个子问题的解决方案都作出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。可以用贪心算法解决的问题，一般情况下都可以用动态规划。求解最优化问题的算法通常需要经过一些列的步骤，在每个步骤都面临多种选择，对于许多最优化的问题，使用动态规划算法来求最优解有些杀鸡用牛刀了，可以使用更简单、更高效的贪心算法。

#### 经典贪心算法题

##### [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

```java
class Solution {
    // 后一天抛，前一天买
    public int maxProfit(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }
}
```

##### [874. 模拟行走机器人](https://leetcode-cn.com/problems/walking-robot-simulation/)

```java
class Solution {
    //题意：在一个无限大的坐标轴里求行走最大边长，即x2+y2。
    //注意：障碍点就是坐标，遇到障碍点本次移动会停住，需要进行下一次移动或换方向（如果遇到不换方向继续走，就永远被卡住）
    //思路：1.取得当前机器人的方向，开始移动
    //2.每移动一步前判断该位置是否为障碍物，如果存在障碍物，结束当前路径的移动
    //3.若无障碍物，移动，更新当前位置，然后与当前的最远距离比较，如果大于最远距离则更新最远距离。
    //4.移动完毕后取得最远距离
    public int robotSim(int[] commands, int[][] obstacles) {
        // 定义原点，朝向：0123代表北东南西, command为-1代表向右转即direction+1，-2向左转即direction+3，
        // 为了避免绕圈超过预定值，需要同时再对4取模即可
        int ans = 0, x = 0, y = 0, direction = 0;
        // 设定每个朝向每走一步在x，y轴上的位移，这里根据顺序为北东南西
        int[][] directions = new int[][]{ {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

        // 将障碍点转化为坐标点存在Hash表里，方便O(1)时间查询
        Set<String> obstacleSet = new HashSet<>();
        for (int[] coordinate : obstacles) {
            obstacleSet.add(coordinate[0] + ", " + coordinate[1]);
        }

        //开始执行命令
        for (int command : commands) {
            if (command > 0) {
                int next_x, next_y;
                for (int i = 0; i < command; ++i) {
                    // 获取下一步坐标
                    next_x = x + directions[direction][0];
                    next_y = y + directions[direction][1];
                    // 判断下一步的坐标点是否是障碍物
                    if (obstacleSet.contains(next_x + ", " + next_y)) {
                        break;
                    }
                    x = next_x;
                    y = next_y;
                    // 更新结果
                    ans = Math.max(ans,x*x + y*y);
                }
            }else {
                direction = command == -1 ? (direction + 1) % 4 : (direction + 3) % 4;
            }
        }

        return ans;
    }
}
```

##### [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)

```java
class Solution {
  	// 思路：遍历每个元素进行跳跃，每次跳跃维护一个最远可达的位置，
  	// 如果当前待跳跃的元素在最远可达范围内，说明该位置可达，更新最远可达的位置，
  	// 如果存在最远可达位置大于等于末尾返回true
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int len = nums.length;
        int max = 0;
        for (int i = 0; i < len; ++i) {
            int num = nums[i];
            if (max >= i) {
                max = Math.max(max, num + i);
                if (max >= len - 1)
                    return true;
            }
        }
        return false;
    }
}
```

##### [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/)

```java
class Solution {
    // 解法：正向贪心求解  每一步选最远能走的位置
    // 从左到右依次遍历，每遍历一个元素记录该元素最大可达位置，划定这个位置为一个边界，
    // 继续遍历看这个边界内元素最大可达位置，到达边界时更新边界，记录步数
    public int jump(int[] nums) {
        int len = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;

        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
```

### 二分查找

#### 定义

二分查找是一种高效的查找方式，不过要求线性表（注意与非线性表之间的区别，具有四个特征）必须采用顺序存储结构（注意不是链式存储结构），并且元素是有序排列的。

#### 注意点

*二分查找思路很简单，`细节 `是魔鬼。*

- while循环中的不等号是否应该带等号
- mid 是否应该加一
- mid = (low+high)/2存在溢出的风险，应改为mid = (high - low)/2 + low
  - 或用位移操作mid = ((high - low)>>1) + low， 移位操作高效于除法操作

分析这些细节的差异以及出现这些差异的原因，才能保证灵活准确地写出正确的二分查找算法。

#### 二分查找模版

```java
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;

    while(...) {
        int mid = (right - left)/2 + left
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}
```

#### 应用场景

寻找一个数、寻找左侧边界、寻找右侧边界、寻找旋转数组等

#### 经典二分查找题

##### [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

```java
class Solution {
    // 思路：目标值在右边搜索的可能性只有三种
    // 1.左边升序，目标值大于中间，说明要找的target在右边的升序点
    // 2.左边升序，目标值小于最左边，说明要找的target在右边的旋转点
    // 3.左边存在旋转点，目标值介于中间位置和最左边之间，说明要找的target在右边的旋转点
    // 其余情况都在左边搜索
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1,mid;
        while (low <= high) {
            mid = (high - low)/2 + low;
            if (nums[mid] == target)
                return mid;
            if ((nums[mid] >= nums[0] && target > nums[mid]) ||
                    (nums[mid] >= nums[0] && target < nums[0]) ||
                    (nums[mid] < target && target < nums[0])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}

```

##### [74. 搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)

```java
class Solution {
    // 标准二分查找：一次二分，看成一维数组
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int left = 0, right = m*n - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
          	// 获取行号列号的方法：n为二维数组每行的个数，mid/n代表整数倍的n，即行数，取模就是列数
            int midVal = matrix[mid / n][mid % n];
            if (target == midVal) return true;
            if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
```

##### [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

```java
class Solution {
    // 思路：1.如果nums[mid] > nums[0] 在mid右边寻找变化点
    // 2.如果nums[mid] <= nums[0] 在mid左边寻找变化点
    // 3.如果nums[mid] > nums[mid + 1] mid + 1是变化点
    // 4.如果nums[mid] < nums[mid - 1] mid - 1是变化点
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1,mid;
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        while (left <= right) {
            mid = left + (right - left)/2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

```



## 本周刷题记录

### 实战

| 名称                                                         | 难度   | 分类     | 备注                                          |
| ------------------------------------------------------------ | ------ | -------- | --------------------------------------------- |
| [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | 🟡 中等 | DFS、BFS | 字节跳动、亚马逊、微软在半年内面试中考过      |
| [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) | 🟡 中等 | DFS、BFS | -                                             |
| [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/) | 🟡 中等 | DFS、BFS | 字节跳动、亚马逊、Facebook 在半年内面试中考过 |
| [515. 在每个树行中找最大值](https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/) | 🟡 中等 | DFS、BFS | 微软、亚马逊、Facebook 在半年内面试中考过     |
| [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx/)    | 🟢 简单 | 二分查找 | 字节跳动、微软、亚马逊在半年内面试中考过      |
| [367. 有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/) | 🟢 简单 | 二分查找 | 亚马逊在半年内面试中考过                      |

### 课后作业

| 名称                                                         | 难度   | 分类       | 备注                                          |
| ------------------------------------------------------------ | ------ | ---------- | --------------------------------------------- |
| [169. 多数元素](https://leetcode-cn.com/problems/majority-element/) | 🟢 简单 | 分治、回溯 | 亚马逊、字节跳动、Facebook 在半年内面试中考过 |
| [860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/) | 🟢 简单 | 贪心算法   | 亚马逊在半年内面试中考过                      |
| [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | 🟢 简单 | 贪心算法   | 亚马逊、字节跳动、微软在半年内面试中考过      |
| [455. 分发饼干](https://leetcode-cn.com/problems/assign-cookies/) | 🟢 简单 | 贪心算法   | 亚马逊在半年内面试中考过                      |
| [874. 模拟行走机器人](https://leetcode-cn.com/problems/walking-robot-simulation/) | 🟢 简单 | 贪心算法   | -                                             |
| [50. Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)    | 🟡 中等 | 分治、回溯 | Facebook 在半年内面试常考                     |
| [78. 子集](https://leetcode-cn.com/problems/subsets/)        | 🟡 中等 | 分治、回溯 | Facebook、字节跳动、亚马逊在半年内面试中考过  |
| [17. 电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | 🟡 中等 | 分治、回溯 | 亚马逊在半年内面试常考                        |
| [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) | 🟡 中等 | DFS、BFS   | 亚马逊在半年内面试常考                        |
| [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) | 🟡 中等 | DFS、BFS   | 近半年内，亚马逊在面试中考查此题达到 350 次   |
| [529. 扫雷游戏](https://leetcode-cn.com/problems/minesweeper/) | 🟡 中等 | DFS、BFS   | 亚马逊、Facebook 在半年内面试中考过           |
| [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)  | 🟡 中等 | 贪心算法   | 亚马逊、华为、Facebook 在半年内面试中考过     |
| [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | 🟡 中等 | 二分查找   | Facebook、字节跳动、亚马逊在半年内面试常考    |
| [74. 搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/) | 🟡 中等 | 二分查找   | 亚马逊、微软、Facebook 在半年内面试中考过     |
| [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | 🟡 中等 | 二分查找   | 亚马逊、微软、字节跳动在半年内面试中考过      |
| [51. N皇后](https://leetcode-cn.com/problems/n-queens/)      | 🔴️ 困难 | 分治、回溯 | 字节跳动、苹果、谷歌在半年内面试中考过        |
| [126. 单词接龙 II](https://leetcode-cn.com/problems/word-ladder-ii/) | 🔴️ 困难 | DFS、BFS   | 微软、亚马逊、Facebook 在半年内面试中考过     |
| [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) | 🔴️ 困难 | 贪心算法   | 亚马逊、华为、字节跳动在半年内面试中考过      |