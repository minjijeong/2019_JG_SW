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
������ ������ �ڽ��� ���̸�ŭ ����� �԰�, ���̰� 1 �����Ѵ�. ������ ������ ������ �ִ� 1��1 ũ���� ĭ�� �ִ� ��и� ���� �� �ִ�.
�ϳ��� ĭ�� ���� ���� ������ �ִٸ�, ���̰� � �������� ����� �Դ´�. ����, ���� ����� ������ �ڽ��� ���̸�ŭ ����� ���� �� ���� ������ ����� ���� ���ϰ� ��� �״´�.

�������� ���� ���� ������ ������� ���ϰ� �ȴ�. ������ ���� �������� ���̸� 2�� ���� ���� ������ �ִ� ĭ�� ������� �߰��ȴ�. �Ҽ��� �Ʒ��� ������.

�������� ������ �����Ѵ�. �����ϴ� ������ ���̰� 5�� ����̾�� �ϸ�, ������ 8���� ĭ�� ���̰� 1�� ������ �����.
� ĭ (r, c)�� ������ ĭ�� (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) �̴�. ���� ���� ����� ĭ���� ������ ������ �ʴ´�.

�ܿ￡�� S2D2�� ���� ���ƴٴϸ鼭 ���� ����� �߰��Ѵ�. �� ĭ�� �߰��Ǵ� ����� ���� A[r][c]�̰�, �Է����� �־�����.

5 2 1
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 1 3
3 2 3
*/
