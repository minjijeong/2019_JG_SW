#include <stdio.h>
#include <stdlib.h>

int N, M;
int r, c, d;

int** map;

int ans = 0;

void calc(int stuck);

int main()
{
	int i, j;

	scanf("%d %d", &N, &M);
	scanf("%d %d %d", &r, &c, &d);
	
	map = (int**)malloc(sizeof(int*) * N);
	for(i = 0; i < N; i++)
		map[i] = (int*)malloc(sizeof(int) * M);
	for(i = 0; i < N; i++)
		for(j = 0; j < M; j++)
			scanf("%d", &map[i][j]);
	
	calc(0);
	
	for(i = 0; i < N; i++)
		free(map[i]);
	free(map);
	
	printf("%d", ans);
	
	return 0;
}

void calc(int stuck)
{
	// û���� �κ��� 2�� ǥ��
	if(!map[r][c])
	{
		map[r][c] = 2;
		ans++;
	}
	// ����üũ 
	// 0:�� 1:�� 2:�� 3:�� 
	switch(d)
	{
		case 0:
			if(stuck == 4)	// 4���� �� ���������� 
			{
				if(map[r+1][c] == 1) return;
				else
				{
					r++;
					calc(0);	// ��� 
				}
			}
			else
			{
				d = 1;
				if(!map[r][c-1])
				{
					c--;
					calc(0);	// ��� 
				}
				else
				{
					calc(stuck + 1);	// ��� 
				}
			}
			break;
		case 1:
			if(stuck == 4)	// 4���� �� ���������� 
			{
				if(map[r][c+1] == 1) return;
				else
				{
					c++;
					calc(0);	// ��� 
				}
			}
			else
			{
				d = 2;
				if(!map[r+1][c])
				{
					r++;
					calc(0);	// ��� 
				}
				else
				{
					calc(stuck + 1);	// ��� 
				}
			}
			break;
		case 2:
			if(stuck == 4)	// 4���� �� ���������� 
			{
				if(map[r-1][c] == 1) return;
				else
				{
					r--;
					calc(0);	// ��� 
				}
			}
			else
			{
				d = 3;
				if(!map[r][c+1])
				{
					c++;
					calc(0);	// ��� 
				}
				else
				{
					calc(stuck + 1);	// ��� 
				}
			}
			break;
		case 3:
			if(stuck == 4)	// 4���� �� ���������� 
			{
				if(map[r][c-1] == 1) return;
				else
				{
					c--;
					calc(0);	// ��� 
				}
			}
			else
			{
				d = 0; 
				if(!map[r-1][c])
				{
					r--;
					calc(0);	// ��� 
				}
				else
				{
					calc(stuck + 1);	// ��� 
				}
			}
	}
}
