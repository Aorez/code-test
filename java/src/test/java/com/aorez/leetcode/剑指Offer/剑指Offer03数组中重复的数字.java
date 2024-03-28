package com.aorez.leetcode.剑指Offer;

import java.util.HashSet;
import java.util.Set;

/*
找出数组中重复的数字。
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
请找出数组中任意一个重复的数字。
示例 1：
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3
限制：
2 <= n <= 100000
 */
public class 剑指Offer03数组中重复的数字 {

    class Solution {
        //哈希表
        //时间n
        //空间n
        public int findRepeatNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    return num;
                } else {
                    set.add(num);
                }
            }
            return 0;
        }
    }

    class Solution2 {
        //原地交换
        //时间n
        //空间o(1)
        public int findRepeatNumber(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != i) {
                    int temp = nums[i];
                    if (nums[temp] == temp) {
                        return temp;
                    }
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
            return 0;
        }
    }
}
