package main

func main() {
	m := [][]int{{3, 5, 9, 9, 14}, {7, 8, 11, 15, 15}, {8, 10, 16, 16, 17}}
	t := 12
	findNumberIn2DArray(m, t)
}

func findNumberIn2DArray(matrix [][]int, target int) bool {
	length := len(matrix)

	if length == 0 {
		return false
	}

	if length == 1 {
		return binarySearch(matrix, target, true, 0)
	}

	length0 := len(matrix[0])

	if length0 == 1 {
		return binarySearch(matrix, target, false, 0)
	}

	row, col := 0, length0-1
	for row < length && col >= 0 {
		val := matrix[row][col]
		if target < val {
			col--
		} else if target > val {
			row++
		} else {
			return true
		}
	}
	return false
}

func binarySearch(matrix [][]int, target int, row bool, id int) bool {
	if row {
		left, right := 0, len(matrix[id])-1
		for left <= right {
			mid := (left + right) >> 1
			val := matrix[id][mid]
			if target < val {
				right = mid - 1
			} else if target > val {
				left = mid + 1
			} else {
				return true
			}
		}
		return false
	} else {
		left, right := 0, len(matrix)-1
		for left <= right {
			mid := (left + right) >> 1
			val := matrix[mid][id]
			if target < val {
				right = mid - 1
			} else if target > val {
				left = mid + 1
			} else {
				return true
			}
		}
		return false
	}
}
