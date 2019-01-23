import java.util.Scanner;

/**
 * <pre>
 * 유엔 화학 무기 조사단이 대량 살상 화학 무기를 만들기 위해 화학 물질들이 저장된 창고를 조사하게 되었다.

창고에는 화학 물질 용기 n2개가 n x n으로 배열되어 있었다.

유엔 조사단은 각 용기를 조사하여 2차원 배열에 그 정보를 저장하였다.

빈 용기에 해당하는 원소는 ‘0’으로 저장하고, 화학 물질이 들어 있는 용기에 해당하는 용기는 화학 물질의 종류에 따라 ‘1’에서 ‘9’사이의 정수를 저장하였다.

다음 그림은 창고의 화학 물질 현황을 9x9 배열에 저장한 예를 보여준다.
 



유엔 조사단은 화학 물질이 담긴 용기들로부터 2가지 사항을 발견하였다.

1. 화학 물질이 담긴 용기들이 사각형을 이루고 있다. 또한, 사각형 내부에는 빈 용기가 없다.

예를 들어, 위의 그림에는 3개의 화학 물질이 담긴 용기들로 이루어진 사각형 A, B, C가 있다.

2. 2개의 화학 물질이 담긴 용기들로 이루어진 사각형들 사이에는 빈 용기들이 있다.

예를 들어, 위의 그림에서 A와 B사이와 B와 C사이를 보면, 빈 용기를 나타내는 ‘0’ 원소들이 2개의 사각형 사이에 있는 것을 알 수 있다.

단, A와 C의 경우와 같이 대각선 상으로는 빈 용기가 없을 수도 있다.

또한 유엔 조사단은 화학 물질이 담긴 용기들로 이루어진 사각형들 사이에 특정한 관계가 있는 것을 추후 조사를 통해서 알아내었다.

그 관계는 바로 화학 물질이 든 용기들로 이루어진 각 사각형을 행렬이라고 여겨, 행렬 간의 곱셈을 수행하는 방식으로 화학 물질을 섞는 것이다.

즉, 2개의 행렬 원소 간 곱셈은 2개의 행렬 원소에 대응되는 화학 물질을 섞는 것이다.

단, 섞은 물질들을 합치는 데는 시간이 걸리지 않는다고 가정한다.

예를 들어, 그림에서 3개의 행렬 A, B, C의 차원이 각각 3x4, 2x3, 4x5이므로, 행렬간 곱셈을 수행하기 위해 반드시 BxAxC로 곱셈이 수행되어야 한다.

그러나 어떤 행렬들을 먼저 곱하는 것에 따라 행렬 원소간의 곱셈 수가 달라질 수 있다.

예를 들어, 위 그림에서 3개의 행렬 (A(3x4), B(2x3), C(4x5))의 곱셈을 살펴보면, (B*A)*C, 즉, B*A를 먼저 수행하고 그 결과 행렬을 C 와 곱하면, 64번의 원소간 곱셈이 수행된다.

그러나 B*(A*C)의 경우는 90번의 곱셈이 필요하다.

유엔 조사단의 시간을 절약하기 위해, 창고의 용기들에 대한 2차원 배열에서 행렬(화학 물질이 든 용기들로 이루어진 사각형)들을 찾아내고,

찾아낸 행렬들 간의 곱셈에 필요한 최소 원소간의 곱셈 수 (2개의 화학 물질이 든 용기를 섞는 작업의 수)를 찾는 프로그램을 작성하시오.

[제약 사항]

n은 100 이하이다.

부분 행렬의 열의 개수는 서로 다르며 행렬의 행의 개수도 서로 다르다.

예를 들어, 3개의 부분행렬 행렬 (A(3x4), B(2x3), C(4x5))이 추출되었다면, 각 부분 행렬의 행의 수는 3, 2, 4로 서로 다르다.

마찬가지로 각 부분 행렬의 열의 수도 4, 3, 5로 서로 다르다.

그래서 각 테스트 케이스에서 추출된 행렬들 사이의 곱셈에는 반드시 1개의 수행 가능한 행렬 곱셈 순서만 존재한다.

행렬들 사이의 차원이 맞지 않아 곱셈을 수행할 수 없는 테스트 케이스는 주어지지 않는다.

테스트 케이스는 여러 개의 그룹으로 구성되며 아래와 같다.
그룹 1. n <= 10 이고 sub matrix의 개수 5개 이하
그룹 2. n <= 40 이고 5 < sub matrix <= 10
그룹 3. 40 < n <=80 이고 5 < sub matrix <= 10
그룹 4. 40 < n <=80 이고 10 < sub matrix <= 15
그룹 5. 80 < n<=100 이고 15 < sub matrix <= 20
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190114_01 {
	static int[][] matrix, square, firstPoints;
	static int n, fpNum, max_x, max_y, min_result;
	static int[][] move = {{1, 0}, {0, 1}}; // y, x | y축 이동 먼저, 다음에 x축 이동

	static void findSquare(int cur_y, int cur_x, int whereMove, int sqrNum) {
		if(cur_y > max_y || cur_x > max_x) {
			max_y = Math.max(cur_y, max_y);
			max_x = Math.max(cur_x, max_x);
			
			square[sqrNum][whereMove]++;
		}
		
		for(int i = 0; i < move.length; i++) {		
			int new_y = cur_y + move[i][0];
			int new_x = cur_x + move[i][1];
			
			if(new_y >= n || new_x >= n)
				continue;
			
			if(matrix[new_y][new_x] == 0)
				continue;
			
			findSquare(new_y, new_x, i, sqrNum);
		}
	}
	
	static void matrixCombination(int[] curr, int loop, int result) {
		if(loop == fpNum) {
			min_result = Math.min(min_result, result);
			return;
		}
		
		for(int i = 0; i < fpNum; i++) {
			int[] next = new int[2];
			
			if(curr[1] != square[i][0] && square[i][1] != curr[0])
				continue;
			
			if(curr[1] == square[i][0]) {
				result += curr[0] * curr[1] * square[i][1];
				next[0] = curr[0];
				next[1] = square[i][1];
			}
			else {
				result += square[i][0] * square[i][1] * curr[1];
				next[0] = square[i][0];
				next[1] = curr[1];
			}
			
			matrixCombination(next, loop + 1, result);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for(int test = 1; test <= testCase; test++) {
			n = sc.nextInt();
			matrix = new int[n][n];
			firstPoints = new int[n][2];
			fpNum = 0;
			min_result = 1000000;
			
			for(int y = 0; y < n; y++) {
				boolean firstPoint = false;
				for(int x = 0; x <n; x++) {
					matrix[y][x] = sc.nextInt();
					if(!firstPoint && matrix[y][x] != 0) {
						if(y > 0) {
							if(matrix[y - 1][x] == 0) {
								firstPoint = true;
								firstPoints[fpNum][0] = y;
								firstPoints[fpNum][1] = x;
								fpNum++;
							}
						}
						else {
							firstPoint = true;
							firstPoints[fpNum][0] = y;
							firstPoints[fpNum][1] = x;
							fpNum++;
						}
					}
					else if(matrix[y][x] == 0) {
						firstPoint = false;
					}
				}
			}
			square = new int[fpNum][2];
			
			for(int i = 0; i < fpNum; i++) {
				max_y = firstPoints[i][0];
				max_x = firstPoints[i][1];
				findSquare(firstPoints[i][0], firstPoints[i][1], 0, i);
				square[i][0]++;
				square[i][1]++;
			}
			
			for(int i = 0; i < fpNum; i++) {
				matrixCombination(square[i], 1, 0);				
			}
			
			System.out.println("#" + test + " " + min_result);
		}
	}

}
