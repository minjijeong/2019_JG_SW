#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int N, M, K;
int x, y, z;

int** nutri;
int** land;
int** tree;

int ans = 0;

void spring();
void summer();
void fall();
void winter();

int main()
{
	int i, j;
	
	scanf("%d %d %d", &N, &M, &K);
	
	nutri = (int**)malloc(sizeof(int*) * N);
	land = (int**)malloc(sizeof(int*) * N);
	for(i = 0; i < N; i++)
	{
		nutri[i] = (int*)malloc(sizeof(int) * N);
		land[i] = (int*)malloc(sizeof(int) * N);
		for(j = 0; j < N; j++)
		{
			scanf("%d", &nutri[i][j]);
			land[i][j] = 5;
		}
	}
	
	tree = (int**)malloc(sizeof(int*) * M);
	for(i = 0; i < M; i++)
		tree[i] = (int*)malloc(sizeof(int) * 4);
	
	for(i = 0; i < M; i++)
	{
		scanf("%d %d %d", &x, &y, &z);
		tree[i][0] = 1;
		tree[i][1] = x - 1;
		tree[i][2] = y - 1;
		tree[i][3] = z;
	}
	
	for(i = 0; i < K; i++)
	{
		spring();
		summer();
		fall();
		winter();
	}
	
	for(i = 0; i < M; i++)
		if(tree[i][0] == 1)
			ans++;
	
	printf("%d", ans);
	
	for(i = 0; i < N; i++)
	{
		free(nutri[i]);
		free(land[i]);
	}
	for(i = 0; i < M; i++)
		free(tree[i]);
	free(nutri);
	free(land);
	free(tree);
	
	return 0;
}

void spring()
{
	int i;
	
	for(i = M - 1; i >= 0; i--)
	{
		if(tree[i][0] == 1)
		{
			int nx = tree[i][1], ny = tree[i][2], age = tree[i][3];
			if(land[x][y] >= age)
			{
				land[x][y] -= age;
				tree[i][3]++;
			}
			else
				tree[i][0] = 0;
		}
	}
}

void summer()
{
	int i;
	
	for(i = 0; i < M; i++)
	{
		if(tree[i][0] == 0)
		{
			int nx = tree[i][1], ny = tree[i][2], age = tree[i][3];
			land[x][y] += (age / 2);
			tree[i][0] = -1;
		}
	}
}

void fall()
{
	int i;
	int m = M;
	
	for(i = 0; i < m; i++)
	{
		if(tree[i][0] == 1 && tree[i][3] % 5 == 0)
		{//(r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1)
			int nx = tree[i][1], ny = tree[i][2];
			if(nx - 1 >= 0 && ny - 1 >= 0)
			{//1
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx - 1;	tree[M - 1][2] = ny - 1;	tree[M - 1][3] = 1;
			}
			if(nx - 1 >= 0)
			{//2
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx - 1;	tree[M - 1][2] = ny;		tree[M - 1][3] = 1;
			}
			if(nx - 1 >= 0 && ny + 1 < N)
			{//3
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx - 1;	tree[M - 1][2] = ny + 1;	tree[M - 1][3] = 1;
			}
			if(ny - 1 >= 0)
			{//4
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx;		tree[M - 1][2] = ny - 1;	tree[M - 1][3] = 1;
			}
			if(ny + 1 < N)
			{//6
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx;		tree[M - 1][2] = ny + 1;	tree[M - 1][3] = 1;
			}
			if(nx + 1 < N && ny - 1 >= 0)
			{//7
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx + 1;	tree[M - 1][2] = ny - 1;	tree[M - 1][3] = 1;
			}
			if(nx + 1 < N)
			{//8
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx + 1;	tree[M - 1][2] = ny;		tree[M - 1][3] = 1;
			}
			if(nx + 1 < N && ny + 1 < N)
			{//9
				M++;
				tree = (int**)realloc(tree, sizeof(int*) * M);
				tree[M - 1] = (int*)malloc(sizeof(int) * 4);
				tree[M - 1][0] = 1;   tree[M - 1][1] = nx + 1;	tree[M - 1][2] = ny + 1;	tree[M - 1][3] = 1;
			}
		}
	}
}

void winter()
{
	int i, j;
	
	for(i = 0; i < N; i++)
		for(i = 0; i < N; i++)
			land[i][j] += nutri[i][j];
}

/*
봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다. 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.

여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.

가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다. 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.

겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.

5 2 1
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 1 3
3 2 3
*/
