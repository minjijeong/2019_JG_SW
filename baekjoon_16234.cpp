#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <string.h>

int N = 0; // 1<= N <=50
int L = 0; // 1<= L <= R
int R = 0; // L<= R <= 100
bool A_open[50][50][4] = { 0, };
int A[50][50] = { 0, };
int united[50][50] = { 0, }; // 연합에 속한 국가 좌표
int labeling = 1;
int cnt = 0;
int move_flag = 0;

int open_case_check()
{
	int i = 0;
	int j = 0;
	int k = 0;

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			if (i - 1 >= 0) // 북
			{
				if (L <= abs(A[i][j] - A[i - 1][j]) && abs(A[i][j] - A[i - 1][j]) <= R)
				{
					A_open[i][j][0] = true;
				}
			}
			if (j + 1 < N) // 동
			{
				if (L <= abs(A[i][j] - A[i][j + 1]) && abs(A[i][j] - A[i][j + 1]) <= R)
				{
					A_open[i][j][1] = true;
				}
			}
			if (i + 1 < N) // 남
			{
				if (L <= abs(A[i][j] - A[i + 1][j]) && abs(A[i][j] - A[i + 1][j]) <= R)
				{
					A_open[i][j][2] = true;
				}
			}
			if (j - 1 >= 0) // 서
			{
				if (L <= abs(A[i][j] - A[i][j - 1]) && abs(A[i][j] - A[i][j - 1]) <= R)
				{
					A_open[i][j][3] = true;
				}
			}
		}
	}

	return 0;
}

int find_united(int i, int j)
{
	united[i][j] = labeling;
	cnt++;

	if (A_open[i][j][0] == 1)
	{
		A_open[i][j][0] = 0;
		if (united[i - 1][j] == 0)
		{
			find_united(i - 1, j);
		}
	}
	if (A_open[i][j][1] == 1)
	{
		A_open[i][j][1] = 0;
		if (united[i][j + 1] == 0)
		{
			find_united(i, j + 1);
		}
	}
	if (A_open[i][j][2] == 1)
	{
		A_open[i][j][2] = 0;
		if (united[i + 1][j] == 0)
		{
			find_united(i + 1, j);
		}
	}
	if (A_open[i][j][3] == 1)
	{
		A_open[i][j][3] = 0;
		if (united[i][j - 1] == 0)
		{
			find_united(i, j - 1);
		}
	}
	return 0;
}

int move(int labeling)
{
	int i = 0;
	int j = 0;
	int sum = 0;
	int labeling_cnt = 1;
	int moved_people = 0;

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			if (united[i][j] == labeling)
			{
				sum = sum + A[i][j];
				labeling_cnt++;
			}
		}
	}

	moved_people = floor(sum / (labeling_cnt-1));
	
	if (moved_people == sum)
	{
		return 0;
	}
	else
	{
		move_flag = 1;
	}
	
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			if (united[i][j] == labeling)
			{
				A[i][j] = moved_people;
			}
		}
	}
	//printf("--------------------------------\n");
	//for (i = 0; i < N; i++)
	//{
	//	for (j = 0; j < N; j++)
	//	{
	//		printf("%d ", A[i][j]);
	//	}
	//	printf("\n");
	//}

	return 0;
}

int main()
{
	int i = 0;
	int j = 0;
	int a = 0;
	int b = 0;
	int move_cnt = 0;
	scanf("%d %d %d", &N, &L, &R);
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			scanf("%d", &A[i][j]);
		}
	}

	while (1)
	{
		labeling = 1;

		open_case_check(); // 국경선 열 조건 확인 후 각 국경선 방향에 맞게 A_open = true로

		for (i = 0; i < N; i++)
		{
			for (j = 0; j < N; j++)
			{
				if (united[i][j] == 0)
				{
					find_united(i, j); //국경선 방향 열린 것을 통해 연합을 찾음
					//for (a = 0; a < N; a++)
					//{
					//	for (b = 0; b < N; b++)
					//	{
					//		printf("%d ", united[a][b]);
					//	}
					//	printf("\n");
					//}

					labeling++;
				}
			}
		}

		for (i = 1; i < labeling; i++)
		{
			move(i); //인구이동  // 소수점 버림
		}
		
		if (move_flag == 1)
		{
			move_flag = 0;
			move_cnt++;
		}
		else if(move_flag == 0)
		{
			break;
		}

		for (i = 0; i < N; i++)
			memset(united[i], 0, sizeof(int) * 50);
	}
	printf("%d", move_cnt);
}

//#include <iostream>
//#include <stdlib.h>
//#include <math.h>
//#include <string.h>
//
//int N = 0; // 1<= N <=50
//int L = 0; // 1<= L <= R
//int R = 0; // L<= R <= 100
//bool A_open[50][50][4] = { 0, };
//int A[50][50] = { 0, };
//int united[50][50] = { 0, }; // 연합에 속한 국가 좌표
//int labeling = 1;
//int cnt = 0;
//int move_flag = 0;
//
//int open_case_check()
//{
//	int i = 0;
//	int j = 0;
//	int k = 0;
//
//	for (i = 0; i < N; i++)
//	{
//		for (j = 0; j < N; j++)
//		{
//			if (i - 1 >= 0) // 북
//			{
//				if (L <= abs(A[i][j] - A[i - 1][j]) && abs(A[i][j] - A[i - 1][j]) <= R)
//				{
//					A_open[i][j][0] = true;
//				}
//			}
//			if (j + 1 < N) // 동
//			{
//				if (L <= abs(A[i][j] - A[i][j + 1]) && abs(A[i][j] - A[i][j + 1]) <= R)
//				{
//					A_open[i][j][1] = true;
//				}
//			}
//			if (i + 1 < N) // 남
//			{
//				if (L <= abs(A[i][j] - A[i + 1][j]) && abs(A[i][j] - A[i + 1][j]) <= R)
//				{
//					A_open[i][j][2] = true;
//				}
//			}
//			if (j - 1 >= 0) // 서
//			{
//				if (L <= abs(A[i][j] - A[i][j - 1]) && abs(A[i][j] - A[i][j - 1]) <= R)
//				{
//					A_open[i][j][3] = true;
//				}
//			}
//		}
//	}
//
//	return 0;
//}
//
//int find_united(int i, int j)
//{
//	united[i][j] = labeling;
//	cnt++;
//
//	if (A_open[i][j][0] == 1)
//	{
//		A_open[i][j][0] = 0;
//		if (united[i - 1][j] == 0)
//		{
//			find_united(i - 1, j);
//		}
//	}
//	if (A_open[i][j][1] == 1)
//	{
//		A_open[i][j][1] = 0;
//		if (united[i][j + 1] == 0)
//		{
//			find_united(i, j + 1);
//		}
//	}
//	if (A_open[i][j][2] == 1)
//	{
//		A_open[i][j][2] = 0;
//		if (united[i + 1][j] == 0)
//		{
//			find_united(i + 1, j);
//		}
//	}
//	if (A_open[i][j][3] == 1)
//	{
//		A_open[i][j][3] = 0;
//		if (united[i][j - 1] == 0)
//		{
//			find_united(i, j - 1);
//		}
//	}
//	return 0;
//}
//
//int move(int labeling)
//{
//	int i = 0;
//	int j = 0;
//	int sum = 0;
//	int labeling_cnt = 1;
//	int moved_people = 0;
//
//	for (i = 0; i < N; i++)
//	{
//		for (j = 0; j < N; j++)
//		{
//			if (united[i][j] == labeling)
//			{
//				sum = sum + A[i][j];
//				labeling_cnt++;
//			}
//		}
//	}
//
//	moved_people = floor(sum / (labeling_cnt - 1));
//
//	if (moved_people == sum)
//	{
//		return 0;
//	}
//	else
//	{
//		move_flag = 1;
//	}
//
//	for (i = 0; i < N; i++)
//	{
//		for (j = 0; j < N; j++)
//		{
//			if (united[i][j] == labeling)
//			{
//				A[i][j] = moved_people;
//			}
//		}
//	}
//	//printf("--------------------------------\n");
//	//for (i = 0; i < N; i++)
//	//{
//	//	for (j = 0; j < N; j++)
//	//	{
//	//		printf("%d ", A[i][j]);
//	//	}
//	//	printf("\n");
//	//}
//
//	return 0;
//}
//
//int main()
//{
//	int i = 0;
//	int j = 0;
//	int a = 0;
//	int b = 0;
//	int move_cnt = 0;
//	scanf("%d %d %d", &N, &L, &R);
//	for (i = 0; i < N; i++)
//	{
//		for (j = 0; j < N; j++)
//		{
//			scanf("%d", &A[i][j]);
//		}
//	}
//
//	while (1)
//	{
//		labeling = 1;
//
//		open_case_check(); // 국경선 열 조건 확인 후 각 국경선 방향에 맞게 A_open = true로
//
//		for (i = 0; i < N; i++)
//		{
//			for (j = 0; j < N; j++)
//			{
//				if (united[i][j] == 0)
//				{
//					find_united(i, j); //국경선 방향 열린 것을 통해 연합을 찾음
//									   //for (a = 0; a < N; a++)
//									   //{
//									   //	for (b = 0; b < N; b++)
//									   //	{
//									   //		printf("%d ", united[a][b]);
//									   //	}
//									   //	printf("\n");
//									   //}
//					labeling++;
//				}
//			}
//		}
//
//		for (i = 1; i < labeling; i++)
//		{
//			move(i); //인구이동  // 소수점 버림
//		}
//
//		if (move_flag == 1)
//		{
//			move_flag = 0;
//			move_cnt++;
//		}
//		else if (move_flag == 0)
//		{
//			break;
//		}
//
//		for (i = 0; i < N; i++)
//			memset(united[i], 0, sizeof(int) * 50);
//	}
//	printf("%d", move_cnt);
//}