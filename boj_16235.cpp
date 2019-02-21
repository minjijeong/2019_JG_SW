#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;

typedef struct tree {
	int x, y, age;
}Tree;

int N, M, K;
int A[11][11]; // S2D2가 추가할 양분
int ground[11][11]; // 현재 땅에 있는 양분

// 주변 8개 좌표 for문으로 돌리기 위한 배열
int dx[8] = { -1,-1,-1,0,0,1,1,1 };
int dy[8] = { -1,0,1,-1,1,-1,0,1 };

vector<Tree> trees; // 현재 살아있는 나무
queue<Tree> DeadQ; // 죽은 나무
queue<Tree> FiveQ; // 나이가 5의배수인 나무
queue<Tree> AliveQ; // 살아남은 나무
queue<Tree> BirthQ; // 새로 생겨난 나무

//age 오름차순으로 구조체 정렬을 위한 함수
bool comp(const Tree &a, const Tree &b) {
	return a.age < b.age;
}

void InitFunc() {
	// N,M,K 입력
	scanf("%d %d %d", &N, &M, &K);

	// S2D2가 추가할 양분 입력
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			scanf("%d", &A[r][c]);
		}
	}

	// 나무의 좌표와 나이 입력
	for (int i = 0; i < M; i++) {
		Tree tmp;
		scanf("%d %d %d", &tmp.x, &tmp.y, &tmp.age);
		trees.push_back(tmp);
	}

	// 땅의 양분을 5로 초기화
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			ground[i][j] = 5;
		}
	}
}

void SpringFunc() {
	//나무가 자신의 나이만큼 양분을 먹고, 나이가 1증가한다.
	for(int i = 0 ; i<trees.size(); i++)
	{
		int x = trees[i].x, y = trees[i].y, age = trees[i].age;

		// 양분이 부족한 경우 죽는다.
		if (ground[x][y] < age) {
			DeadQ.push(trees[i]);
		}
		else { 
			// 양분을 먹고 나이를 먹는다.
			ground[x][y] -= age;
			trees[i].age++;
			
			// 살아남은 나무 push
			AliveQ.push(trees[i]);

			if (trees[i].age % 5 == 0)
				// 나이가 5의 배수인 나무 push
				FiveQ.push(trees[i]);
		}
	}
}

void SummerFunc() {
	//봄에 죽었던 나무들이 양분이 된다.
	while (!DeadQ.empty())	{
		Tree tree = DeadQ.front();
		DeadQ.pop();

		ground[tree.x][tree.y] += tree.age / 2;
	}
}

void AutumnFunc() {
	//나이가 5의 배수인 나무들이 번식한다.
	while (!FiveQ.empty()) {
		Tree tmp = FiveQ.front();
		int x = tmp.x, y = tmp.y;
		int a, b;

		FiveQ.pop();

		for (int i = 0; i < 8; i++) {
			// 좌표 주변 8칸에 나이 1인 나무가 추가된다.
			a = x + dx[i];		b = y + dy[i];
			if (1 <= a && a <= N && 1 <= b && b <= N) {
				BirthQ.push({a,b,1});
			}
		}
	}
}

void WinterFunc() {
	// S2D2가 양분을 추가한다.
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			ground[r][c] += A[r][c];
		}
	}
}

int main() {
	// 입력 & 초기화
	InitFunc();

	// 나이가 작은 순으로 정렬
	sort(trees.begin(), trees.end(), comp);

	while (K--)
	{
		// 봄
		SpringFunc();
		// 여름
		SummerFunc();
		// 가을
		AutumnFunc();
		// 겨울
		WinterFunc();

		// trees벡터를 비운다.
		trees.clear();

		// 생겨난 나무와 살아남은 나무를 push
		while (!BirthQ.empty()) {
			trees.push_back(BirthQ.front());
			BirthQ.pop();
		}

		while (!AliveQ.empty()) {
			trees.push_back(AliveQ.front());
			AliveQ.pop();
		}
	}

	// 현재 살아있는 나무의 개수 출력
	cout << trees.size();

	return 0;
}
