#include <iostream>
#include <cstring>
#include <cstdio>
using namespace std;
int flag;
void perm(char a[], int l, int r)
{
	int x, y, z;
	if (l == r)
	{
		flag = 1;
		for (x = 0; x < r - 1; x++)
			for (y = x + 1; y < r; y++)
				for (z = y + 1; z <= r; z++)
					//假设入栈字符串是从小到大的，比如abc
					//那么cab是错误的出栈顺序
					//三个之中，中间的比右边的小，右边的比左边的小
					if ((a[y] < a[z]) && (a[z] < a[x]))
						flag = 0;
		if (flag)
		{
			printf("%s\n", a);
		}
	}
	for (int i(l); i <= r; i++)
	{
		int tmp = a[i];
		a[i] = a[l];
		a[l] = tmp;
		perm(a, l + 1, r);
		a[l] = a[i];
		a[i] = tmp;
	}
}

int main()
{
	char s[20];
	cin >> s;
	cout << endl;
	perm(s, 0, strlen(s) - 1);
}