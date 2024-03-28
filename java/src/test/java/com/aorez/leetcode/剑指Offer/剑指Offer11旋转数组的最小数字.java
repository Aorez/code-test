package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

/*
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
给你一个可能存在 重复 元素值的数组 numbers ，
它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，
该数组的最小值为 1。
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为
数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
示例 1：
输入：numbers = [3,4,5,1,2]
输出：1
示例 2：
输入：numbers = [2,2,2,0,1]
输出：0
提示：
n == numbers.length
1 <= n <= 5000
-5000 <= numbers[i] <= 5000
numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class 剑指Offer11旋转数组的最小数字 {

    @Test
    public void test() {
        int[] a = {3, 3, 1, 3};
        Solution solution = new Solution();
        int i = solution.minArray(a);
        System.out.println(i);
    }

    class Solution {
        //二分查找
        //旋转后的数组，假设最小值的下标为target
        //在数组中，target左边的数，都>=数组最后一位数字
        //target右边的数，都<=数组最后一位数字
        //有了这种条件之后就可以进行二分查找
        //如果二分查找pivot的数字>数组最后一位数字，则往右边查找
        //如果pivot的数字<最后一位，则往左边查找
        //如果==，则把最后一位往左移
        //直到left==right
        //空间o(1)
        //时间logn
        //如果数组中的元素都相同，则会一直缩减最后一位的下标，达到最坏时间o(n)
        public int minArray(int[] numbers) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (numbers[mid] < numbers[right]) {
                    right = mid;
                } else if (numbers[mid] > numbers[right]) {
                    left = mid + 1;
                } else {
                    right--;
                }
            }
            return numbers[left];
        }
    }
}