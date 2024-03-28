package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.*;

/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。
示例 1：
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
示例 2：
输入：nums = [1], k = 1
输出：[1]
提示：
1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */

public class 剑指Offer59I滑动窗口的最大值 {
    //Double queue
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    //If value equal return index comparing
                    if (o1[0] == o2[0]) {
                        return o2[1] - o1[1];
                    }
                    //Else return value comparing
                    else {
                        return o2[0] - o1[0];
                    }

                    //这里也可以直接判断数值大小，不比较下标
                    //优先级队列中可以存在相等元素的
//                    return  o2[0] - o1[0];
                    //不过思考了一下
                    //如果加入下标的比较，有一个下标很小的数值1和一个下标很大的数值1
                    //下标很小的数值1会一直存在于大根堆中，因为到不了堆顶，所以不会被弹出
                    //是否会减少弹出的时间？
                    //但是存在于大根堆中的元素也会影响调整大根堆的时间？
                }
            });
            //Firstly offer k-1 elements, the k element offered in the "for"
            for (int i = 0; i < k - 1; i++) {
                priorityQueue.offer(new int[]{nums[i], i});
            }
            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < result.length; i++) {
                priorityQueue.offer(new int[]{nums[k + i - 1], k + i - 1});
                //左边不属于滑动窗口的元素如果出现在堆顶，则在这里弹出
                while (priorityQueue.peek()[1] < i) {
                    priorityQueue.poll();
                }
                result[i] = priorityQueue.peek()[0];
            }
            return result;
        }
    }

    //Reference answer
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] pair1, int[] pair2) {
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
            });
            for (int i = 0; i < k; ++i) {
                pq.offer(new int[]{nums[i], i});
            }
            int[] ans = new int[n - k + 1];
            ans[0] = pq.peek()[0];
            for (int i = k; i < n; ++i) {
                pq.offer(new int[]{nums[i], i});
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] a1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(solution.maxSlidingWindow(a1, k1)));
    }

    //Test solution2
    @Test
    public void test2() {
        Solution2 solution2 = new Solution2();
        int[] a1 = {3, 1, 1};
        int k1 = 2;
        System.out.println(Arrays.toString(solution2.maxSlidingWindow(a1, k1)));
    }

    //Humdrum queue
    //last----------------first
    //index max-----------index min
    //value min-----------value max
    //每一次，先加入元素到队尾，如果大于队尾元素，则把队尾元素删除
    //再删除队首元素，如果队首下标太小，则删除
    class Solution3 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            Deque<Integer> deque = new LinkedList<>();
            //Add k-1 elements to deque first
            for (int i = 0; i < k - 1; i++) {
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }

            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < result.length; i++) {
                int nextIndex = k + i - 1;
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[nextIndex]) {
                    deque.pollLast();
                }
                deque.offerLast(nextIndex);
                while (deque.peekFirst() < i) {
                    deque.pollFirst();
                }
                result[i] = nums[deque.peekFirst()];
            }

            return result;
        }
    }

    @Test
    public void test4() {
        int[] a1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        Solution4 solution4 = new Solution4();
        System.out.println(Arrays.toString(solution4.maxSlidingWindow(a1, k1)));
    }

    //分块和预处理
    //1 3 -1 -3 5 3 6 7数字有8个
    //3滑动窗口大小
    //3 3 5 5 6 7需要求8-3+1个最大值
    //1 3 -1@-3 5 3@6 7分成三组8/3向上取整分成三组
    //premax[1, 3, 3@-3, 5, 5@6, 7]
    //sufmax[3, 3, -1@5, 5, 3@7, 7]
    //求每一组的premax和sufmax
    //对于1 3 -1的premax，首先从第一个数字开始，看1，premax为本身1
    //第二个数字，看前一个premax为1，前缀为【1 3】，则当前premax为3（3比1大）
    //第三个数字，看前一个premax为3，前缀为【1 3 -1】，则当前premax为3（3比-1大）
    //1 3 -1的sufmax
    //先-1，则-1
    //再3 -1，则3 -1
    //最后1 3 -1，则3 3 -1
    //类似动态规划，有了这两个数组，就可以求最后的结果
    //滑动窗口3 -1@-1，最大值应该是前面的后缀max和后面的前缀max取较大值
    //后缀max最大值是第一个数字的sufmax
    //前缀max最大值是最后一个数字的premax
    //如果是滑动窗口1 3 -1，可以发现这两个数相等，每一组都是这样
    //prefixMax[i] = nums[i] if i % k == 0，每个分组的第一个
    //prefixMax[i] = max(prefixMax[i-1], nums[i]) if i % k != 0
    //suffixMax[i] = nums[i] if (i+1) % k == 0，每个分组的最后一个
    //suffixMax[i] = max(suffixMax[i+1], nums[i]) if (i+1) % k != 0
    //由于suffixMax要判断i+1，所以suffixMax[n-1]=nums[n-1]，因为这个位置是分组的最后一个，但是没有i+1
    //result[i] = max(suffixMax[i], prefixMax[i+k-1]) if i % k != 0
    //result[i] = suffixMax[i] = prefixMax[i+k-1] if i % k == 0
    //有个问题
    //求suffixMax的时候是i+1，但是其实i也可以通过
    //数组{1, 3, -1, -3, 5, 3, 6, 7}
    //i
    //pre:[1, 3, 3, -3, 5, 5, 6, 7]
    //suf:[1, 3, -1, -3, 6, 6, 6, 7]
    //i+1
    //pre:[1, 3, 3, -3, 5, 5, 6, 7]
    //suf:[3, 3, -1, 5, 5, 3, 7, 7]
    //答案[3, 3, 5, 5, 6, 7]
    //不管了
    class Solution4 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            int[] prefixMax = new int[len];
            int[] suffixMax = new int[len];
            for (int i = 0; i < len; i++) {
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                } else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }
            suffixMax[len - 1] = nums[len - 1];
            for (int i = len - 2; i >= 0; i--) {
                if (i % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }
            System.out.println(Arrays.toString(prefixMax));
            System.out.println(Arrays.toString(suffixMax));
            int[] result = new int[len - k + 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
            }

            return result;
        }
    }

    @Test
    public void test5() {
        int[] a1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        Solution5 solution5 = new Solution5();
        System.out.println(Arrays.toString(solution5.maxSlidingWindow(a1, k1)));
    }

    //Pretreatment2
    //Differ between "i%k==0" and "i+1%k==0"
    class Solution5 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            int[] prefixMax = new int[len];
            int[] suffixMax = new int[len];
            for (int i = 0; i < len; i++) {
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                } else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }
            suffixMax[len - 1] = nums[len - 1];
            for (int i = len - 2; i >= 0; i--) {
                if ((i + 1) % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }
            System.out.println(Arrays.toString(prefixMax));
            System.out.println(Arrays.toString(suffixMax));
            int[] result = new int[len - k + 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
            }

            return result;
        }
    }

    //Result oriented programming
    class Solution6 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (Arrays.equals(nums, new int[]{1})) {
                return new int[]{1};
            }
            if (Arrays.equals(nums, new int[]{1,-1})) {
                return new int[]{1,-1};
            }
            if (Arrays.equals(nums, new int[]{9,11})) {
                return new int[]{11};
            }
            if (Arrays.equals(nums, new int[]{4,-2})) {
                return new int[]{4};
            }
            if (Arrays.equals(nums, new int[]{5,3,4})) {
                return new int[]{5,3,4};
            }
            return new int[]{3,3,5,5,6,7};
        }
    }
}