package main

import (
	"container/list"
	"fmt"
)

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

func levelOrder(root *TreeNode) []int {
	var rtn []int
	if root == nil {
		return rtn
	}

	queue := list.List{}
	queue.PushBack(root)
	for queue.Len() != 0 {
		current := queue.Front().Value.(*TreeNode)
		queue.Remove(queue.Front())
		rtn = append(rtn, current.Val)
		if current.Left != nil {
			queue.PushBack(current.Left)
		}
		if current.Right != nil {
			queue.PushBack(current.Right)
		}
	}
	return rtn
}
