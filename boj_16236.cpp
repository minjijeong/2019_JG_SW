#define _CRT_SECURE_NO_WARNINGS
#define INF 9999999
#include<iostream>
#include<queue>
using namespace std;

int N, x, y;
int map[25][25];
int sz, eat, answer;

int dx[4] = {-1,0,0,1};
int dy[4] = {0,-1,1,0};

bool visited[25][25];
bool eatable[25][25];
bool passable[25][25];

int dist[25][25];
int minDist;

pair<int, int> p_min, p;
queue<pair<pair<int, int>,int>> Q;
pair<pair<int, int>, int> v;

void InitFunc() {
	minDist = INF;

	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);

			// 시작 점을 찾는다.
			if (map[i][j] == 9) {
				sz = 2;
				map[i][j] = 0;
				x = i;
				y = j;
			}
		}
	}
}

// 거리 구하기
void getDist(int x, int y, int d) {

	passable[x][y] = false;
	Q.push(make_pair(make_pair(x, y), d));

	while(!Q.empty())
	{
		v = Q.front();
		p = v.first;

		x = p.first;
		y = p.second;
		d = v.second;

		Q.pop();

		if (eatable[x][y] == true && d < dist[x][y]) {
			dist[x][y] = d;
			if (d < minDist)
			//먹으러 가기에 가장 가까운 물고기를 찾을때 사용
				minDist = d;
		}

		for (int i = 0; i < 4; i++) {
			int a, b;
			a = x + dx[i];
			b = y + dy[i];

			if (0 <= a && a <= N && 0 <= b && b <= N && passable[a][b] == true) {
				passable[a][b] = false;
				Q.push(make_pair(make_pair(a, b),d+1));
			}
		}
	}
}

pair<int, int> getMinDistPoint() {
	if (minDist != INF)
	{
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				/*  
					상단, 왼쪽에 있는 것이 우선이므로
					순차적으로 확인하다가 첫번째 물고기 좌표를 반환한다.
				 */
				if (dist[i][j] == minDist && eatable[i][j] == true) {
					return make_pair(i, j);
				}
			}
		}
	}
	// 먹을 수 있는 물고기가 없다는 것을 의미한다.
	return make_pair(-1, -1);
}

void go() {
	int a, b;
	a = p_min.first;
	b = p_min.second;

	eat++;
	if (sz == eat) {
		sz++;
		eat = 0;
	}

	answer += dist[a][b];

	// 다음 반복을 위한 초기화 작업
	map[a][b] = 0;
	eatable[a][b] = false;
	x = a;
	y = b;
}

int main() {
	InitFunc();

	while (true) 
	{
		minDist = INF;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				passable[i][j] = true;
				dist[i][j] = INF;

				if (map[i][j] == 0)
					continue;

				//먹을 수 있는 물고기인지 확인
				if (sz > map[i][j]) {
					eatable[i][j] = true;
				}

				//이동 가능한지 알기 위한 배열
				if (sz < map[i][j]) {
					passable[i][j] = false;
				}
			}
		}

		//거리를 구한다.
		getDist(x, y, 0);

		//최소 거리 좌표를 구한다.
		p_min = getMinDistPoint();

		if (p_min.first == -1 && p_min.second == -1)
			break;

		//물고기를 먹고 이동한다.
		go();
	}

	printf("%d\n", answer);

	return 0;
}