#define _CRT_SECURE_NO_WARNINGS
#define N_MAX 55

#include<iostream>
#include<stack>
#include<queue>
using namespace std;

int N, L, R, cnt;
int sum, num, val;

int A[N_MAX][N_MAX];
bool visited[N_MAX][N_MAX];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

stack<pair<int, int>> s;
queue<pair<int, int>> q;

bool checkT() {
	bool result = false;
	for (int i = 0; i < N ; i++) {
		for (int j = 0; j < N ; j++) {
			if (visited[i][j] == true)
				result = true;
		}
	}
	return result;
}

void DFS(pair<int, int> v) 
{
	int x, y, r, c, sub;
	pair<int, int> w;
	
	x = v.first;	y = v.second;

	if (visited[x][y] == true)
		return;

	// 방문체크, num 증가, 인구수 누적, 큐에 enqueue
	visited[x][y] = true;
	num++;
	sum += A[x][y];
	q.push(v);

	do {
		// w를 초기화한다. ( w: 다음 좌표 )
		w = make_pair(-1, -1);

		//이동가능한 정점이 있으면 w에 저장 후, 현재 좌표를 스택에 push한다.
		for (int i = 0; i < 4; i++) 
		{
			x = v.first;	y = v.second;
			r = x + dy[i];	c = y + dx[i]; // 상, 하, 좌, 우 순서
			
			// 배열 A 인덱스 유효성을 체크한다.
			if (!(0 <= r && 0 <= c && r < N && c < N))
				continue;

			// 인구수 차를 구한다.
			sub = A[x][y] - A[r][c];
			if (sub < 0) sub = -sub;

			// 경계선이 없고 해당 나라를 아직 방문하지 않은 경우
			if ( (L <= sub && sub <= R) && (visited[r][c] == false) ) { 
				// w : 다음 좌표
				w = make_pair(r, c);
				// 현재 좌표를 push한다.
				s.push(v);
				break;
			}
		}

		//연합을 이루는 경우
		while (!(w.first == -1 && w.second == -1)) 
		{
			// 방문체크, num 증가, 인구수 누적, 큐에 enqueue
			x = w.first;	y = w.second;
			visited[x][y] = true;
			num++;
			sum += A[x][y];
			q.push(w);

			// v(현재 좌표)를 w로 하여 반복한다.
			v = w;
			w = make_pair(-1, -1);

			//이동가능한 정점이 있으면 w에 저장 후, 현재 좌표를 스택에 push한다.
			for (int i = 0; i < 4; i++) 
			{
				r = x + dy[i];	c = y + dx[i];

				if (!(0 <= r && 0 <= c && r < N && c < N))
					continue;

				sub = A[x][y] - A[r][c];
				if (sub < 0) sub = -sub;

				if ((L <= sub && sub <= R) && (visited[r][c] == false)) {
					w = make_pair(r, c);
					s.push(v);
					break;
				}
			}
		}

		if (s.empty() == true) {
			v = make_pair(-1, -1);
			continue;
		}

		v = s.top();
		s.pop();

	} while (!(v.first == -1 && v.second == -1));

	if (num == 1) {
		num = sum = 0;
		visited[x][y] = false;
		q.pop();
	}
}

int main() {
	scanf("%d %d %d", &N, &L, &R);

	for (int i = 0; i < N ; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &A[i][j]);
		}
	}

	while (true)
	{
		sum = num = val = 0;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N ; j++) {
				visited[i][j] = false;
			}
		}

		//DFS로 방문체크를 하면서 sum,num을 구한다.
		for (int i = 0; i < N ; i++)
		{
			for (int j = 0; j < N ; j++) {
				sum = num = val = 0;

				DFS(make_pair(i,j));

				if (sum != 0 && num != 0) 
				{
					val = sum / num;

					// 큐에 있는 모든 좌표를 꺼내서 A배열 각 좌표에 해당하는 값을 val로 변경
					while (!q.empty()) 
					{
						int x, y;
						pair<int, int> p = q.front();
						q.pop();
						x = p.first;	y = p.second;

						A[x][y] = val;
					}
					
				}
			}
		}

		// 방문한 나라가 하나도 없는 경우(인구 이동이 일어나지 않는 경우) 종료한다.
		if (checkT() == false)
			break;

		// cnt를 증가시킨 후 다시 반복한다.
		cnt++; // 인구 이동 횟수 카운트
	}

	printf("%d\n", cnt);
	return 0;
}