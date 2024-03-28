package com.aorez.leetcode.剑指Offer;

public class 剑指Offer57和为s的两个数字 {
    //Double pointer
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    return new int[]{nums[left], nums[right]};
                }
                if (sum > target) {
                    right--;
                }
                else {
                    left++;
                }
            }
            return new int[]{-1, -1};
        }
    }
}
