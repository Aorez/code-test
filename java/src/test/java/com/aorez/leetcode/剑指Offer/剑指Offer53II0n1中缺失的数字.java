package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.Arrays;

//因为二分法遇到下标是1和2的时候，二分取的值为1，所以不能用left=mid，会导致无限循环，要用left=mid+1
//而可以用right=mid，也可以用right=mid-1
//如果用了left=mid+1，right=mid-1，寻找的目标可能是比target大的，也可能是比target小的
public class 剑指Offer53II0n1中缺失的数字 {
    @Test
    public void test() {
        int[] nums = {0, 1, 2, 3};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {0, 1, 3, 4};
        int[] nums4 = {0, 1, 3};
        Solution solution = new Solution();
        System.out.println(solution.missingNumber(nums));
        System.out.println(solution.missingNumber(nums2));
        System.out.println(solution.missingNumber(nums3));
        System.out.println(solution.missingNumber(nums4));
    }
    // Bisection
    class Solution {
        public int missingNumber(int[] nums) {
            //0或length的数字在二分查找中判断不易，因此开局先判断0
            //结尾return的时候判断length
            if (nums.length == 0 || nums[0] != 0) {
                return 0;
            }
            if (nums[nums.length - 1] == nums.length - 1) {
                return nums.length;
            }
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (nums[m] > m) {
                    r = m - 1;
                }
                else {
                    l = m + 1;
                }
            }
            return nums[l] == l ? l + 1 : l;
        }
    }
    @Test
    public void testBinarySearch() {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(Arrays.binarySearch(nums, 3));
        System.out.println(Arrays.binarySearch(nums, 0));
        System.out.println(Arrays.binarySearch(nums, -5));
        System.out.println(Arrays.binarySearch(nums, 6));
        //-insertion-1=-6
        System.out.println(Arrays.binarySearch(nums, 10));

        System.out.println("------------------------------------");
        int[] nums2 = {5, 5, 5};
        int[] nums3 = {5, 5, 5, 5};
        //sout: 1
        System.out.println(Arrays.binarySearch(nums2, 5));
        //sout: 1
        System.out.println(Arrays.binarySearch(nums3, 5));
        //sout: -1
        System.out.println(Arrays.binarySearch(nums3, 0));
        //sout: -5
        System.out.println(Arrays.binarySearch(nums3, 10));
    }
}
