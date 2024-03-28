package com.aorez.leetcode.剑指Offer;

/*
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
示例：
输入：nums = [1,2,3,4]
输出：[1,3,2,4]
注：[3,1,2,4] 也是正确的答案之一。
提示：
0 <= nums.length <= 50000
0 <= nums[i] <= 10000
 */
public class 剑指Offer21调整数组顺序使奇数位于偶数前面 {
    //双指针
    //找到第一个偶数，如果找不到，则什么都不用处理
    //从这个偶数的下一个数开始，如果有奇数，互换奇数和偶数
    //交换后，这个偶数的下标要加一，为下一次奇数和偶数互换做准备
    class Solution {
        public int[] exchange(int[] nums) {
            //Find the first even
            int evenIndex = -1;
            for (int i = 0; i < nums.length; i++) {
                if (isEven(nums[i])) {
                    evenIndex = i;
                    break;
                }
            }
            //No even in the array
            if (evenIndex == -1) {
                return nums;
            }

            int index = evenIndex + 1;
            while (index < nums.length) {
                //Exchange even and odd
                if (isOdd(nums[index])) {
                    int n = nums[index];
                    nums[index] = nums[evenIndex];
                    nums[evenIndex] = n;
                    evenIndex++;
                }
                index++;
            }

            return nums;
        }

        public boolean isEven(int n) {
            return n % 2 == 0;
        }

        public boolean isOdd(int n) {
            return n % 2 != 0;
        }
    }

    class Solution2 {
        //双指针
        //取消奇数偶数的函数判断
        public int[] exchange(int[] nums) {
            //Find the first even
            int evenIndex = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] % 2 == 0) {
                    evenIndex = i;
                    break;
                }
            }
            //No even in the array
            if (evenIndex == -1) {
                return nums;
            }

            int index = evenIndex + 1;
            while (index < nums.length) {
                //Exchange even and odd
                if (nums[index] % 2 != 0) {
                    int n = nums[index];
                    nums[index] = nums[evenIndex];
                    nums[evenIndex] = n;
                    evenIndex++;
                }
                index++;
            }

            return nums;
        }
    }
}
