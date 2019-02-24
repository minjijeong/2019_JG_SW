#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
using namespace std;

int N, M, C, T;
int maxSum;
int arr[11][11], bac1[11], bac2[11];

void InitFunc() {
	maxSum = 0;

	scanf("%d %d %d", &N, &M, &C);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &arr[i][j]);
		}
	}
}

void findMaxVal(int r1, int c1, int r2, int c2) {
	int sum1 = 0 , sum2 = 0;
	int answer1 = 0, answer2 = 0;
	
	for (int i = 0; i < M; i++) {
		bac1[i] = arr[r1][c1 + i];
		bac2[i] = arr[r2][c2 + i];
	}

	sort(&arr[r1][c1], &arr[r1][c1 + M]);
	sort(&arr[r2][c2], &arr[r2][c2 + M]);

	for (int i = M-1; i >= 0; i--) {
		if (sum1 + arr[r1][c1 + i] < C) {
			sum1 += arr[r1][c1 + i];
			answer1 += arr[r1][c1 + i] * arr[r1][c1 + i];
		}

		if (sum2 + arr[r2][c2 + i] < C) {
			sum2 += arr[r2][c2 + i];
			answer2 += arr[r2][c2 + i] * arr[r2][c2 + i];
		}
	}

	if (maxSum < answer1 + answer2) {
		maxSum = answer1 + answer2;
	}

	for (int i = 0; i < M; i++) {
		arr[r1][c1 + i] = bac1[i];
		arr[r2][c2 + i] = bac2[i];
	}

}

void solve() {

	for (int r1 = 0; r1 < N - M + 1; r1++) {
		for (int c1 = 0; c1 < N - M + 1; c1++) {

			for (int r2 = 0; r2 < N - M + 1; r2++) {
				for (int c2 = 0; c2 < N - M + 1; c2++) {

					if (r1 == r2) {
						if (c2+M-1 < c1 || c1+M-1 < c2)
							findMaxVal(r1, c1, r2, c2);
					}
					else {
						findMaxVal(r1, c1, r2, c2);
					}
				}
			}
		}
	}

	
}

int main() {
	scanf("%d", &T);

	for (int t = 1; t <= T; t++) 
	{
		InitFunc();
		
		solve();

		printf("#%d %d\n", t, maxSum);
	}

	
	return 0;
}