package main

import "fmt"

// redeclared
// ignore
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	t7 := &TreeNode{7, nil, nil}
	t15 := &TreeNode{15, nil, nil}
	t20 := &TreeNode{20, t15, t7}
	t9 := &TreeNode{9, nil, t20}
	t3 := &TreeNode{3, t9, t20}
	a := levelOrder(t3)
	fmt.Println(a)
}

// redeclared
// ignore
func levelOrder(root *TreeNode) [][]int {
	rtn := [][]int{}
	if root == nil {
		return rtn
	}

	queue := []*TreeNode{root}
	order := true
	for len(queue) > 0 {
		length := len(queue)
		list := []int{}
		queue2 := []*TreeNode{}
		for i := 0; i < length; i++ {
			node := queue[i]
			if order {
				list = append(list, node.Val)
			} else {
				list = append([]int{node.Val}, list...)
			}

			if node.Left != nil {
				queue2 = append(queue2, node.Left)
			}
			if node.Right != nil {
				queue2 = append(queue2, node.Right)
			}
		}
		queue = queue2
		order = !order
		rtn = append(rtn, list)
	}

	return rtn
}
