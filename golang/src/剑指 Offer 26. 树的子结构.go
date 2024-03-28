package main

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
}

func isSubStructure(A *TreeNode, B *TreeNode) bool {
	if A == nil || B == nil {
		return false
	}

	if match(A, B) {
		return true
	}

	if isSubStructure(A.Left, B) || isSubStructure(A.Right, B) {
		return true
	}

	return false
}

func match(A *TreeNode, B *TreeNode) bool {
	if B == nil {
		return true
	}

	if A == nil || A.Val != B.Val {
		return false
	}

	if match(A.Left, B.Left) && match(A.Right, B.Right) {
		return true
	}

	return false
}
