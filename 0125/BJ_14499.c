#include <stdio.h>
#include <stdlib.h>
#define diceTop 0
#define diceBot 5

int M, N, K;	// 가로 세로 명령 
int x, y;		// 주사위 좌표

int dice[6] = {0};

/*
index : 
  1
3 0 2
  4
  5

동쪽으로 움직였을때 
  1
5 3 0
  4
  2
서쪽으로 움직였을때 
  1
0 2 5
  4
  3
북쪽으로 움직였을때 
  0
3 4 2
  5
  1
남쪽으로 움직였을때 
  5
3 1 2
  0
  4
*/

int* cmd;		// 명령 
int** map;		// 지도 

void calc();

int main()
{
	int i, j;
	
	scanf("%d", &N);
	scanf("%d", &M);
	scanf("%d", &x);
	scanf("%d", &y);
	scanf("%d", &K);
	
	
	// 동적할당 
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
	
	// 할당해제 
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
		// 굴리기 
		switch(cmd[i])
		{
			int tmp; 
			case 1:		// 동 
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
			case 2:		// 서 
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
			case 3:		// 북 
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
			case 4: 	// 남
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
		
		// 주사위 바닥 처리
		if(map[x][y])	// 발판이 0이 아닐 때 
		{
			// 발판이 주사위 바닥으로 복사, 발판은 0으로 초기화
			dice[diceBot] = map[x][y];
			map[x][y] = 0;
		}
		else			// 발판이 0일 때 
		{
			// 주사위 바닥이 발판에 복사 
			map[x][y] = dice[diceBot];
		}
	}
}
