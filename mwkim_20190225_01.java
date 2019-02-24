import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * sw expert arcademy 2115
 * @author rlaau
 *
 */
public class mwkim_20190225_01 {
	static int N, M, C, maxA, maxB, max_result;
	static int[][] matrix;
	static int[][] worker;
	
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
	static void getHoney(int complete, int cur_y, int cur_x) {
		if(complete == 2) {
			int[] arr = new int[M];
			maxA = 0;
			maxB = 0;
			for(int i = 1; i <= M; i++) {
				select(worker[0], arr, 0, M, i, 0, true);
				select(worker[1], arr, 0, M, i, 0, false);
			}
			int result = maxA + maxB;
			max_result = Math.max(max_result, result);
			//System.out.println("A: " + maxA + " | B: " + maxB);
			return;
		}
		
		if(cur_y >= N) {
			return;
		}
		
		int new_x = cur_x + (M - 1);
		if(new_x >= N) {
			getHoney(complete, cur_y + 1, 0);
			return;
		}
		int num = 0;
		for(int x = cur_x; x <= new_x; x++) {
			worker[complete][num] = matrix[cur_y][x];
			num++;
		}
		getHoney(complete + 1, cur_y, new_x + 1);
		getHoney(complete, cur_y, cur_x + 1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			max_result = 0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			matrix = new int[N][N];
			worker = new int[2][M];
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					matrix[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			getHoney(0, 0, 0);
			System.out.println("#" + test + " " + max_result);
		}
	}

}
