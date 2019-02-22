#include <iostream>
#include <stdlib.h>
#include <math.h>
#define tree_num 100*100

int yangbun_o[11][11] = { 0, };
int yangbun_t[11][11] = { 0, };
int tree[tree_num+1][3] = { 0, }; // x,y,old, 
int del[tree_num+1] = { 0, };
int d_tree_cnt = 0;
int	N = 0;
int M = 0;
int K = 0;

int temp[11][11] = { 0, };

int compare(const void *a, const void *b)
{
	int num1 = *(int *)a;
	int num2 = *(int *)b;

	if (num1 > num2)
		return -1;

	if (num1 < num2)
		return 1;

	return 0;
}

int sort()
{
	int i = 0;
	int j = 0;
	int temp1 = 0;
	int temp2 = 0;
	int temp3 = 0;

	for (i = 1; i < tree_num; i++)
	{
		for (j = 1; j < tree_num; j++)
		{
			if (tree[i][2] > tree[j][2])
			{
				temp1 = tree[i][0];
				temp2 = tree[i][1];
				temp3 = tree[i][2];
				tree[i][0] = tree[j][0];
				tree[i][1] = tree[j][1];
				tree[i][2] = tree[j][2];
				tree[j][0] = temp1;
				tree[j][1] = temp2;
				tree[j][2] = temp3;
			}
		}
	}
	return 0;
}

int spring(int *tree_cnt)
{
	int i = 0;

	sort();

	for (i = *tree_cnt; i >= 1; i--)
	{
		if (yangbun_t[tree[i][0]][tree[i][1]] >= tree[i][2] && tree[i][2] != -1)
		{
			yangbun_t[tree[i][0]][tree[i][1]] = yangbun_t[tree[i][0]][tree[i][1]] - tree[i][2];
			tree[i][2]++;
		}
		else if(yangbun_t[tree[i][0]][tree[i][1]] < tree[i][2] && tree[i][2] != -1)
		{
			del[i] = 1;
		}
	}
	return 0;
}
int summer(int *tree_cnt)
{
	int i = 0;
	int delete_tree_cnt = 0;
	for (i = 1; i <= *tree_cnt; i++)
	{
		if (del[i] == 1)
		{
			del[i] = 0;
			yangbun_t[tree[i][0]][tree[i][1]] = yangbun_t[tree[i][0]][tree[i][1]] + (int)floor(tree[i][2] / 2);
			tree[i][0] = 0;		tree[i][1] = 0;		tree[i][2] = 0;
			delete_tree_cnt++;
		}
	}
	d_tree_cnt = delete_tree_cnt;
	return 0;
}
int fall(int *tree_cnt)
{
	int i = 0;
	int temp_tree_cnt = *tree_cnt;
	for (i = 1; i <= *tree_cnt; i++)
	{
		if (tree[i][2] % 5 == 0 && tree[i][2] != 0)
		{
			if (tree[i][0] - 1 > 0 && tree[i][1] - 1 > 0)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0] - 1;
				tree[temp_tree_cnt][1] = tree[i][1] - 1;
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][0] - 1 > 0)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0] - 1;
				tree[temp_tree_cnt][1] = tree[i][1];
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][0] - 1 > 0 && tree[i][1] + 1 <= N)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0] - 1;
				tree[temp_tree_cnt][1] = tree[i][1] + 1;
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][1] - 1 > 0)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0];
				tree[temp_tree_cnt][1] = tree[i][1] - 1;
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][1] + 1 <= N)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0];
				tree[temp_tree_cnt][1] = tree[i][1] + 1;
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][0] + 1 <= N && tree[i][1] - 1 > 0)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0] + 1;
				tree[temp_tree_cnt][1] = tree[i][1] - 1;
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][0] + 1 <= N)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0] + 1;
				tree[temp_tree_cnt][1] = tree[i][1];
				tree[temp_tree_cnt][2] = 1;
			}
			if (tree[i][0] + 1 <= N && tree[i][1] + 1 <= N)
			{
				temp_tree_cnt = temp_tree_cnt + 1;
				tree[temp_tree_cnt][0] = tree[i][0] + 1;
				tree[temp_tree_cnt][1] = tree[i][1] + 1;
				tree[temp_tree_cnt][2] = 1;
			}
		}
	}
	*tree_cnt = temp_tree_cnt - d_tree_cnt;
	
	return 0;
}
int winter()
{
	int i = 0;
	int j = 0;
	for (i = 1; i <= N; i++)
		for (j = 1; j <= N; j++)
			yangbun_t[i][j] = yangbun_t[i][j] + yangbun_o[i][j];
	
	return 0;
}

int main()
{
	int i = 0;
	int j = 0;
	int k_cnt = 0;
	int tree_cnt = 0;

	scanf("%d %d %d", &N, &M, &K);

	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= N; j++)
		{
			scanf("%d", &yangbun_o[i][j]);
			yangbun_t[i][j] = 5;
		}
	}

	for (i = 1; i <= M; i++)
		scanf("%d %d %d", &tree[i][0], &tree[i][1], &tree[i][2]);

	tree_cnt = M;

	while (k_cnt < K)
	{
		spring(&tree_cnt);
		summer(&tree_cnt);
		fall(&tree_cnt);
		winter();
		k_cnt++;
		if (tree_cnt == 0)
			break;
	}
	printf("%d", tree_cnt);
}
 

//#include <iostream>
//#include <stdlib.h>
//#include <math.h>
//#define tree_num 100*100
//
//int yangbun_o[11][11] = { 0, };
//int yangbun_t[11][11] = { 0, };
//int tree[tree_num + 1][3] = { 0, }; // x,y,old, 
//int del[tree_num + 1] = { 0, };
//int d_tree_cnt = 0;
//int	N = 0;
//int M = 0;
//int K = 0;
//
//int temp[11][11] = { 0, };
//
//int compare(const void *a, const void *b)
//{
//	int num1 = *(int *)a;
//	int num2 = *(int *)b;
//
//	if (num1 > num2)
//		return -1;
//
//	if (num1 < num2)
//		return 1;
//
//	return 0;
//}
//
//int sort()
//{
//	int i = 0;
//	int j = 0;
//	int temp1 = 0;
//	int temp2 = 0;
//	int temp3 = 0;
//
//	for (i = 0; i < tree_num; i++)
//	{
//		for (j = 0; j < tree_num; j++)
//		{
//			if (tree[i][2] > tree[j][2])
//			{
//				temp1 = tree[i][0];
//				temp2 = tree[i][1];
//				temp3 = tree[i][2];
//				tree[i][0] = tree[j][0];
//				tree[i][1] = tree[j][1];
//				tree[i][2] = tree[j][2];
//				tree[j][0] = temp1;
//				tree[j][1] = temp2;
//				tree[j][2] = temp3;
//			}
//		}
//	}
//	return 0;
//}
//
//int spring(int *tree_cnt)
//{
//	int i = 0;
//	//int a = 0;
//	//int b = 0;
//	//int delete_tree_cnt = 0;
//	//qsort(tree,tree_num, sizeof(int)*3, compare);
//	sort();
//
//	//printf("-------------------------------------\n");
//	//for (i = 0; i < *tree_cnt; i++)
//	//	printf("%d %d : %d\n", tree[i][0], tree[i][1], tree[i][2]);
//
//	for (i = *tree_cnt - 1; i >= 0; i--)
//	{
//		if (yangbun_t[tree[i][0]][tree[i][1]] >= tree[i][2] && tree[i][2] != -1)
//		{
//			yangbun_t[tree[i][0]][tree[i][1]] = yangbun_t[tree[i][0]][tree[i][1]] - tree[i][2];
//			tree[i][2]++;
//			//printf("-------------------------------------\n");
//			//for (a = 1; a <= N; a++)
//			//{
//			//	for (b = 1; b <= N; b++)
//			//	{
//			//		printf("%d ", yangbun_t[a][b]);
//			//	}
//			//	printf("\n");
//			//}
//		}
//		else if (yangbun_t[tree[i][0]][tree[i][1]] < tree[i][2] && tree[i][2] != -1)
//		{
//			del[i] = 1;
//		}
//	}
//
//	//printf("-------------------------------------\n");
//	//for (i = 0; i < *tree_cnt; i++)
//	//	printf("%d %d : %d\n", tree[i][0], tree[i][1], tree[i][2]);
//	return 0;
//}
//int summer(int *tree_cnt)
//{
//	int i = 0;
//	int delete_tree_cnt = 0;
//	//int a = 0;
//	//int b = 0;
//	for (i = 0; i < *tree_cnt; i++)
//	{
//		if (del[i] == 1)
//		{
//			del[i] = 0;
//			yangbun_t[tree[i][0]][tree[i][1]] = yangbun_t[tree[i][0]][tree[i][1]] + (int)floor(tree[i][2] / 2);
//			tree[i][0] = 0;		tree[i][1] = 0;		tree[i][2] = 0;
//			delete_tree_cnt++;
//			//printf("-------------------------------------\n");
//			//for (a = 1; a <= N; a++)
//			//{
//			//	for (b = 1; b <= N; b++)
//			//	{
//			//		printf("%d ", yangbun_t[a][b]);
//			//	}
//			//	printf("\n");
//			//}
//		}
//	}
//	d_tree_cnt = delete_tree_cnt;
//	return 0;
//}
//int fall(int *tree_cnt)
//{
//	int i = 0;
//	//int j = 0;
//	int temp_tree_cnt = *tree_cnt;
//	for (i = 0; i < *tree_cnt; i++)
//	{
//		if (tree[i][2] % 5 == 0 && tree[i][2] != 0)
//		{
//			if (tree[i][0] - 1 > 0 && tree[i][1] - 1 > 0)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0] - 1;
//				tree[temp_tree_cnt][1] = tree[i][1] - 1;
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][0] - 1 > 0)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0] - 1;
//				tree[temp_tree_cnt][1] = tree[i][1];
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][0] - 1 > 0 && tree[i][1] + 1 <= N)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0] - 1;
//				tree[temp_tree_cnt][1] = tree[i][1] + 1;
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][1] - 1 > 0)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0];
//				tree[temp_tree_cnt][1] = tree[i][1] - 1;
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][1] + 1 <= N)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0];
//				tree[temp_tree_cnt][1] = tree[i][1] + 1;
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][0] + 1 <= N && tree[i][1] - 1 > 0)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0] + 1;
//				tree[temp_tree_cnt][1] = tree[i][1] - 1;
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][0] + 1 <= N)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0] + 1;
//				tree[temp_tree_cnt][1] = tree[i][1];
//				tree[temp_tree_cnt][2] = 1;
//			}
//			if (tree[i][0] + 1 <= N && tree[i][1] + 1 <= N)
//			{
//				temp_tree_cnt = temp_tree_cnt + 1;
//				tree[temp_tree_cnt][0] = tree[i][0] + 1;
//				tree[temp_tree_cnt][1] = tree[i][1] + 1;
//				tree[temp_tree_cnt][2] = 1;
//			}
//		}
//	}
//
//	//printf("-------------------------------------\n");
//	//for (i = 0; i <= temp_tree_cnt; i++)
//	//	printf("%d %d : %d\n", tree[i][0], tree[i][1], tree[i][2]);
//
//
//	//for (i = 1; i <= N; i++)
//	//{
//	//	for (j = 1; j <= N; j++)
//	//	{
//	//		temp[i][j] = 0;
//	//	}
//	//}
//
//	//for (i = 0; i <= temp_tree_cnt; i++)
//	//{
//	//	if (tree[i][2] != 0)
//	//	{
//	//		temp[tree[i][0]][tree[i][1]]++;
//	//	}
//	//}
//	//printf("-------------------------------------\n");
//	//for (i = 1; i <= N; i++)
//	//{
//	//	for (j = 1; j <= N; j++)
//	//	{
//	//		printf("%d ", temp[i][j]);
//	//	}
//	//	printf("\n");
//	//}
//
//	*tree_cnt = temp_tree_cnt - d_tree_cnt;
//	d_tree_cnt = temp_tree_cnt;
//	return 0;
//}
//int winter()
//{
//	int i = 0;
//	int j = 0;
//	for (i = 1; i <= N; i++)
//		for (j = 1; j <= N; j++)
//			yangbun_t[i][j] = yangbun_t[i][j] + yangbun_o[i][j];
//
//	//printf("-------------------------------------\n");
//	//for (i = 1; i <= N; i++)
//	//{
//	//	for (j = 1; j <= N; j++)
//	//	{
//	//		printf("%d ", yangbun_t[i][j]);
//	//	}
//	//	printf("\n");
//	//}
//	return 0;
//}
//
//int main()
//{
//	int i = 0;
//	int j = 0;
//	int k_cnt = 0;
//	int tree_cnt = 0;
//
//	scanf("%d %d %d", &N, &M, &K);
//
//	for (i = 1; i <= N; i++)
//	{
//		for (j = 1; j <= N; j++)
//		{
//			scanf("%d", &yangbun_o[i][j]);
//			yangbun_t[i][j] = 5;
//		}
//	}
//
//	for (i = 1; i <= M; i++)
//		scanf("%d %d %d", &tree[i][0], &tree[i][1], &tree[i][2]);
//
//	tree_cnt = M;
//
//	while (k_cnt < K)
//	{
//		spring(&tree_cnt);
//		summer(&tree_cnt);
//		fall(&tree_cnt);
//		winter();
//		k_cnt++;
//		if (tree_cnt == 0)
//			break;
//	}
//	printf("%d", tree_cnt);
//	//printf("end");
//}
