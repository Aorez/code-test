package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

public class 剑指Offer53I在排序数组中查找数字I {
    @Test
    public void test() {
        int[] nums = {1, 2, 3, 5, 5, 6};
        Solution solution = new Solution();
        System.out.println(solution.search(nums, 5));
    }
    //Bisection
    class Solution {
        public int search(int[] nums, int target) {
            int index = find(nums, target);
            if (index == -1) {
                return 0;
            }
            int count = 0;
            while (index < nums.length && nums[index] == target) {
                count++;
                index++;
            }
            return count;
        }
        public int find(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] >= target) {
                    right = mid;
                }
                else {
                    left = mid + 1;
                }
            }
            if (nums[left] == target) {
                return left;
            }
            else {
                return -1;
            }
        }
    }
}
