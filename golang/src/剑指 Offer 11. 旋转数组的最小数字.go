package main

import "fmt"

func main() {
	a := []int{1, 3, 5}
	i := minArray(a)
	fmt.Println(i)
}

func minArray(numbers []int) int {
	left, right := 0, len(numbers)-1
	for left < right {
		mid := (left + right) >> 1
		if numbers[mid] < numbers[right] {
			right = mid
		} else if numbers[mid] > numbers[right] {
			left = mid + 1
		} else {
			right--
		}
	}
	return numbers[left]
}
