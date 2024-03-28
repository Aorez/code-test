package main

//redeclared
//type TreeNode struct {
//	Val int
//	Left *TreeNode
//	Right *TreeNode
//}

func main() {
}

// redeclared
func levelOrder2(root *TreeNode) [][]int {
	var rtn [][]int
	if root == nil {
		return rtn
	}

	queue := []*TreeNode{root}
	for len(queue) > 0 {
		length := len(queue)
		rtn2 := []int{}
		queue2 := []*TreeNode{}
		for j := 0; j < length; j++ {
			node := queue[j]
			rtn2 = append(rtn2, node.Val)
			if node.Left != nil {
				queue2 = append(queue2, node.Left)
			}
			if node.Right != nil {
				queue2 = append(queue2, node.Right)
			}
		}
		queue = queue2
		rtn = append(rtn, rtn2)
	}

	return rtn
}
