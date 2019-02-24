import java.util.Scanner;

/**
 * sw expert arcademy 2115
 * @author rlaau
 *
 */
public class mwkim_20190225_01 {
	static int N, M, C, maxA, maxB, max_result;
	static int[][] matrix;
	static int[] A, B;
	
	//A 또는 B 일꾼이 채취한 벌꿀들 중 어떤 것을 선택할지를 combination으로 결정
	//제곱의 최대값을 maxA 또는 maxB에 삽입
	static void select(int[] value, int[] arr, int index, int n, int r, int target, boolean A) {
		if(r == 0) {
			int total = 0;
			int powTotal = 0;
			for(int i = 0; i < index; i++) {
				int powValue = (int)Math.pow((double)value[arr[i]], 2.0);
				if(total + value[arr[i]] <= C) {
					total += value[arr[i]];
					powTotal += powValue;
				}
			}
			if(A)
				maxA = Math.max(maxA, powTotal);
			else
				maxB = Math.max(maxB, powTotal);
		}
		else if(n == target){
			return;
		}
		else {
			arr[index] = target;
			select(value, arr, index + 1, n, r - 1, target + 1, A);
			select(value, arr, index, n, r, target + 1, A);
		}
	}
	
	//전체 벌꿀 매트릭스 중 A가 M만큼 선택하고 B가 M만큼 선택하도록 재귀로 실행
	static void getHoney(boolean A_C, boolean B_C, int cur_y, int cur_x) {
		if(A_C && B_C) {
			int[] arr = new int[M];
			maxA = 0;
			maxB = 0;
			for(int i = 1; i <= M; i++) {
				select(A, arr, 0, M, i, 0, true);
				select(B, arr, 0, M, i, 0, false);
			}
			int result = maxA + maxB;
			max_result = Math.max(max_result, result);
			//System.out.println("A: " + maxA + " | B: " + maxB);
		}
		
		if(cur_y >= N) {
			return;
		}
		
		if(!A_C) {
			int new_x = cur_x + (M - 1);
			if(new_x >= N) {
				getHoney(A_C, B_C, cur_y + 1, 0);
				return;
			}
			int num = 0;
			for(int x = cur_x; x <= new_x; x++) {
				A[num] = matrix[cur_y][x];
				num++;
			}
			getHoney(true, B_C, cur_y, new_x + 1);
			getHoney(A_C, B_C, cur_y, cur_x + 1);
		}
		else if(A_C && !B_C){
			int new_x = cur_x + (M - 1);
			if(new_x >= N) {
				getHoney(A_C, B_C, cur_y + 1, 0);
				return;
			}
			int num = 0;
			for(int x = cur_x; x <= new_x; x++) {
				B[num] = matrix[cur_y][x];
				num++;
			}
			getHoney(A_C, true, cur_y, new_x + 1);
			getHoney(A_C, B_C, cur_y, cur_x + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for(int test = 1; test <= testCase; test++) {
			max_result = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			matrix = new int[N][N];
			A = new int[M];
			B = new int[M];
			
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					matrix[y][x] = sc.nextInt();
				}
			}
			getHoney(false, false, 0, 0);
			System.out.println("#" + test + " " + max_result);
		}
	}

}
