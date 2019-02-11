#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#define min(x,y) (x > y) ? y : x

// 0동 1남 2서 3북 
int move[4][2] = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

int N, M;
int ans = INT_MAX;
int numCCTV = 0;

int** cctv;
int** map;
int** overlap;

void calc(int rec);
void scanMap();
void monitor(int* p, int* q, int d);
void unMonitor(int* p, int* q, int d, int rec);

int cntBlank();

int main()
{
	int i, j;
	
	scanf("%d", &N);
	scanf("%d", &M);

	// 동적할당 
	map = (int**)malloc(sizeof(int*) * N);
	for(i = 0; i < N; i++)
		map[i] = (int*)malloc(sizeof(int) * M);
	
	overlap = (int**)malloc(sizeof(int*) * N);
	for(i = 0; i < N; i++)
		overlap[i] = (int*)malloc(sizeof(int) * M);
	
	// 입력 
	for(i = 0; i < N; i++)
		for(j = 0; j < M; j++)
			scanf("%d", &map[i][j]);
			
	for(i = 0; i < N; i++)
		memset(overlap[i], 0, sizeof(int) * M);
	
	scanMap();
	calc(0);
	printf("%d", ans);
	
	// 할당해제 
	for(i = 0; i < numCCTV; i++)
		free(cctv[i]);
	free(cctv);
	for(i = 0; i < N; i++)
		free(overlap[i]);
	free(overlap);
	for(i = 0; i < N; i++)
		free(map[i]);
	free(map);
	
	return 0;
}

void calc(int rec)
{
	int i;
	int x, y;
	
	if(rec == numCCTV)
	{
		int tmp = cntBlank();
		ans = min(ans, tmp);
		return;
	}
	
	x = cctv[rec][0];
	y = cctv[rec][1];

	switch(map[x][y])
	{
		case 1:
		{
			int a = x, b = y;
			for(i = 0; i < 4; i++)
			{
				monitor(&a, &b, i);
				calc(rec + 1);
				unMonitor(&a, &b, i, rec);
			}
			break;
		}
		case 2:
		{
			int a = x, b = y;
			int c = x, d = y;
			for(i = 0; i < 2; i++)
			{
				monitor(&a, &b, i);
				monitor(&c, &d, i+2);
				calc(rec + 1);
				unMonitor(&a, &b, i, rec);
				unMonitor(&c, &d, i+2, rec);
			}
			break;
		}
		case 3:
		{
			int a = x, b = y;
			int c = x, d = y;
			for(i = 0; i < 4; i++)
			{
				monitor(&a, &b, i);
				monitor(&c, &d, (i + 1) % 4);
				calc(rec + 1);
				unMonitor(&a, &b, i, rec);
				unMonitor(&c, &d, (i + 1) % 4, rec);
			}
			break;
		}
		case 4:
		{
			int a = x, b = y;
			int c = x, d = y;
			int e = x, f = y;
			for(i = 0; i < 4; i++)
			{
				monitor(&a, &b, i);
				monitor(&c, &d, (i + 1) % 4);
				monitor(&e, &f, (i + 2) % 4);
				calc(rec + 1);
				unMonitor(&a, &b, i, rec);
				unMonitor(&c, &d, (i + 1) % 4, rec);
				unMonitor(&e, &f, (i + 2) % 4, rec);
			}
			break;
		}
		case 5:
		{
			int a = x, b = y;
			int c = x, d = y;
			int e = x, f = y;
			int g = x, h = y;
			monitor(&a, &b, 0);
			monitor(&c, &d, 1);
			monitor(&e, &f, 2);
			monitor(&g, &h, 3);
			calc(rec + 1);
			unMonitor(&a, &b, 0, rec);
			unMonitor(&c, &d, 1, rec);
			unMonitor(&e, &f, 2, rec);
			unMonitor(&g, &h, 3, rec);
		}
	}			
}

void scanMap()
{
	int i, j;
	int tmp = 0;
	
	for(i = 0; i < N; i++)
		for(j = 0; j < M; j++)
			if(map[i][j] > 0 && map[i][j] < 6)
				numCCTV++;
	
	cctv = (int**)malloc(sizeof(int*) * numCCTV);
	for(i = 0; i < numCCTV; i++)
		cctv[i] = (int*)malloc(sizeof(int) * 2);
	
	for(i = 0; i < N; i++)
	{
		for(j = 0; j < M; j++)
		{
			if(map[i][j] > 0 && map[i][j] < 6)
			{
				cctv[tmp][0] = i;
				cctv[tmp][1] = j;
				tmp++;
			}
		}
	}
}

void monitor(int* p, int* q, int d)
{
	while( (*p >= 0 && *p < N && *q >= 0 && *q < M) && map[*p][*q] != 6 )
	{
		if(map[*p][*q] <= 0)
		{
			map[*p][*q] = -1;
			overlap[*p][*q]++;
		}
		*p += move[d][0];
		*q += move[d][1];
	}
}

void unMonitor(int* p, int* q, int d, int rec)
{
	while( *p != cctv[rec][0] || *q != cctv[rec][1] )
	{
		*p -= move[d][0];
		*q -= move[d][1];
		if(map[*p][*q] == -1 && overlap[*p][*q] < 2)
			map[*p][*q] = 0;
		if(overlap[*p][*q] > 0)
			overlap[*p][*q]--;
	}
}

int cntBlank()
{
	int i, j;
	int tmp = 0;
	
	for(i = 0; i < N; i++)
		for(j = 0; j < M; j++)
			if(map[i][j] == 0)
				tmp++;
	return tmp;
}
