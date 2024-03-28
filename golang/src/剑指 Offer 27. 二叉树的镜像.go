package main

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
}

func mirrorTree(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}
	node := new(TreeNode)
	node.Val = root.Val
	node.Left = mirrorTree(root.Right)
	node.Right = mirrorTree(root.Left)
	return node
}

func mirrorTree2(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}

	stack := []*TreeNode{root}
	for len(stack) > 0 {
		node := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if node.Left != nil {
			stack = append(stack, node.Left)
		}
		if node.Right != nil {
			stack = append(stack, node.Right)
		}

		node.Left, node.Right = node.Right, node.Left
	}

	return root
}
