#define _CRT_SECURE_NO_WARNINGS
#define N_MAX 55

#include<iostream>
#include<queue>
using namespace std;

int N, L, R, cnt;
int sum, num, val;

int A[N_MAX][N_MAX];
bool visited[N_MAX][N_MAX];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

queue<pair<int, int>> q;

void DFS(int x, int y) {
	int r, c, sub;

	if (visited[x][y] == true)
		return;

	// 방문체크, num 증가, 인구수 누적, 큐에 enqueue
	visited[x][y] = true;
	num++;
	sum += A[x][y];
	q.push(make_pair(x, y));

	//이동 가능한 좌표가 있으면 w에 저장 후, 현재 좌표를 스택에 push한다.
	for (int i = 0; i < 4; i++)
	{
		r = x + dy[i];
		c = y + dx[i];

		if (!(0 <= r && 0 <= c && r < N && c < N))
			continue;

		// 인구수 차를 구한다.
		sub = A[x][y] - A[r][c];
		if (sub < 0) sub = -sub;

		if ((L <= sub && sub <= R) && (visited[r][c] == false)) {
			DFS(r, c);
		}
	}
}

int main() {
	scanf("%d %d %d", &N, &L, &R);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &A[i][j]);
		}
	}

	while (true) {
		int groupNum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum = num = val = 0;

				DFS(i, j);

				if (num > 1) {
					// 연합을 이루는 경우
					val = sum / num;

					while (!q.empty())
					{
						int x, y;
						pair<int, int> p = q.front();
						x = p.first;
						y = p.second;

						A[x][y] = val;
						q.pop();

						//연합 개수 카운트
						groupNum++;
					}
				}
				else if (num == 1) {
					// 하나만 방문한 경우 다시 초기화 해야 한다.
					q.pop();
					visited[i][j] = false;
				}
			}
		}

		if (groupNum == 0)
			break;
		else
			cnt++;
	}

	printf("%d\n", cnt);
	return 0;
}