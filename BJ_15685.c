#include <stdio.h>
#include <math.h>
#define MaxLine	1024

int N;
int x, y, d, g;
int ans = 0; 

int move[4][2] = { {0, 1}, {-1, 0}, {0, -1}, {1, 0} };
int route[MaxLine] = {0,};
int map[101][101] = {0,};

void calc(int line, int deg);
void createRoute();
int cntBox();

int main()
{
	scanf("%d", &N);
	
	createRoute();
	
	while(N-- != 0)
	{
		scanf("%d %d %d %d", &x, &y, &d, &g);
		calc(0, 0);
	}
	
	ans = cntBox();
	printf("%d", ans);
	
	return 0;
}

void calc(int line, int deg)
{
	if(deg == g + 1) return;

	if(deg == 0)
	{
		map[y][x] = 1;
		y += move[d][0];
		x += move[d][1];
		map[y][x] = 1;
		calc(1, deg + 1);
	}
	else
	{
		int i, dir;
		for(i = 0; i < line; i++)
		{
			dir = (route[line + i] + d) % 4;
			if( (y + move[dir][0]) >= 0 && (y + move[dir][0]) <= 100 )
				y += move[dir][0];
			if( (x + move[dir][1]) >= 0 && (x + move[dir][1]) <= 100 )
				x += move[dir][1];
			map[y][x] = 1;
		}
		calc(line * 2, deg + 1);
	}
}

void createRoute()
{
	int i, j;
	for(i = 0; i < 10; i++)
	{
		int k = (int)pow(2, i);
		for(j = k-1; j >= 0; j--)
		{
			route[k++] = (route[j] + 1) % 4;
		}
	}
}

int cntBox()
{
	int i, j;
	int tmp = 0;
	
	for(i = 0; i < 100; i++)
		for(j = 0; j < 100; j++)
			if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1)
				tmp++;
	
	return tmp;
}
