package main

import (
	"container/list"
)

// 1用数组进行记录下标，2用map，2慢了很多
// 3用list作为queue，4用slice作为queue，1，3，4速度差不多
// 很奇怪的类型问题，range string得到int32类型的字符，string[index]得到byte类型的字符，index没有要求
// 用.(T)进行类型恢复时类型要相同，例如List.Front().Value().(T)
func main() {
}

func firstUniqChar1(s string) byte {
	n := len(s)
	postion := [26]int{}
	for i := range postion {
		postion[i] = n
	}
	for i, c := range s {
		//c是int32
		c -= 'a'
		if postion[c] == n {
			postion[c] = i
		} else {
			postion[c] = n + 1
		}
	}
	min := n
	for _, pos := range postion {
		if pos < min {
			min = pos
		}
	}
	if min < n {
		return s[min]
	}
	return ' '
}

func firstUniqChar2(s string) byte {
	position := map[int32]int{}
	n := len(s)
	for i, c := range s {
		if _, inmap := position[c]; inmap {
			position[c] = n + 1
		} else {
			position[c] = i
		}
	}
	min := n
	for _, i := range position {
		if i < min {
			min = i
		}
	}
	if min == n {
		return ' '
	}
	return s[min]
}

func firstUniqChar3(s string) byte {
	count := [26]int{}
	queue := list.List{}
	for _, c := range s {
		i := c - 'a'
		if count[i] == 0 {
			count[i] = 1
			queue.PushBack(c)
		} else {
			count[i] = -1
			for queue.Len() != 0 {
				if count[queue.Front().Value.(int32)-'a'] < 1 {
					queue.Remove(queue.Front())
				} else {
					break
				}
			}
		}
	}
	if queue.Len() == 0 {
		return ' '
	}
	return byte(queue.Front().Value.(int32))
}

func firstUniqChar(s string) byte {
	count := [26]int{}
	queue := []byte{}
	for i := range s {
		index := s[i] - 'a'
		if count[index] == 0 {
			queue = append(queue, s[i])
			count[index] = 1
		} else {
			count[index] = -1
			for len(queue) > 0 {
				if count[queue[0]-'a'] < 0 {
					queue = queue[1:]
				} else {
					break
				}
			}
		}
	}
	if len(queue) == 0 {
		return ' '
	}
	return queue[0]
}
