#include <stdio.h>
#include <stdlib.h>
#define diceTop 0
#define diceBot 5

int M, N, K;	// ���� ���� ��� 
int x, y;		// �ֻ��� ��ǥ

int dice[6] = {0};

/*
index : 
  1
3 0 2
  4
  5

�������� ���������� 
  1
5 3 0
  4
  2
�������� ���������� 
  1
0 2 5
  4
  3
�������� ���������� 
  0
3 4 2
  5
  1
�������� ���������� 
  5
3 1 2
  0
  4
*/

int* cmd;		// ��� 
int** map;		// ���� 

void calc();

int main()
{
	int i, j;
	
	scanf("%d", &N);
	scanf("%d", &M);
	scanf("%d", &x);
	scanf("%d", &y);
	scanf("%d", &K);
	
	
	// �����Ҵ� 
	map = (int**)malloc(sizeof(int*) * N);
	for(i = 0; i < N; i++)
		map[i] = (int*)malloc(sizeof(int) * M);
	for(i = 0; i < N; i++)
		for(j = 0; j < M; j++)
			scanf("%d", &map[i][j]);
			
	cmd = (int*)malloc(sizeof(int) * K);
	for(i = 0; i < K; i++)
		scanf("%d", &cmd[i]);
	
	calc();
	
	// �Ҵ����� 
	for(i = 0; i < N; i++)
		free(map[i]);
	free(map);
	free(cmd);
	
	return 0;
}


void calc()
{
	int i;
	
	for(i = 0; i < K; i++)
	{
		// ������ 
		switch(cmd[i])
		{
			int tmp; 
			case 1:		// �� 
				if(y < M - 1)
				{
					y++;
					tmp = dice[3];
					dice[3] = dice[5];
					dice[5] = dice[2];
					dice[2] = dice[0];
					dice[0] = tmp;
					printf("%d\n", dice[diceTop]);
				}
				break;
			case 2:		// �� 
				if(y > 0)
				{
					y--;
					tmp = dice[3];
					dice[3] = dice[0];
					dice[0] = dice[2];
					dice[2] = dice[5];
					dice[5] = tmp;
					printf("%d\n", dice[diceTop]);
				}
				break;
			case 3:		// �� 
				if(x > 0)
				{
					x--;
					tmp = dice[1];
					dice[1] = dice[0];
					dice[0] = dice[4];
					dice[4] = dice[5];
					dice[5] = tmp;
					printf("%d\n", dice[diceTop]);
				}
				break;
			case 4: 	// ��
				if(x < N - 1)
				{
					x++;
					tmp = dice[1];
					dice[1] = dice[5];
					dice[5] = dice[4];
					dice[4] = dice[0];
					dice[0] = tmp;
					printf("%d\n", dice[diceTop]);
				}
		}
		
		// �ֻ��� �ٴ� ó��
		if(map[x][y])	// ������ 0�� �ƴ� �� 
		{
			// ������ �ֻ��� �ٴ����� ����, ������ 0���� �ʱ�ȭ
			dice[diceBot] = map[x][y];
			map[x][y] = 0;
		}
		else			// ������ 0�� �� 
		{
			// �ֻ��� �ٴ��� ���ǿ� ���� 
			map[x][y] = dice[diceBot];
		}
	}
}
