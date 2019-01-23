import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/14499
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190125_01 {
	static int N, M, X, Y, K;
	static int[][] matrix;
	static int[] cmd;
	static int[][] move = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 순서대로 동서북남
	static int[][][] topMove = {{}, //1423 2314 3142 4231
											{{4, 3, 5, 2}, {5, 2, 3, 4}, {3, 4, 2, 5}, {2, 5, 4, 3}},
											{{4, 3, 1, 6}, {1, 6, 3, 4}, {3, 4, 6, 1}, {6, 1, 4, 3}},
											{{1, 6, 5, 2}, {5, 2, 6, 1}, {6, 1, 2, 5}, {2, 5, 1, 6}},
											{{6, 1, 5, 2}, {5, 2, 1, 6}, {1, 6, 2, 5}, {2, 5, 6, 1}},
											{{4, 3, 6, 1}, {6, 1, 3, 4}, {3, 4, 1, 6}, {1, 6, 4, 3}},
											{{4, 3, 2, 5}, {2, 5, 3, 4}, {3, 4, 5, 2}, {5, 2, 4, 3}}
										};
	static int[] value;
	
	/*
	 * 숫자가 위로 가는 방향을 1로 봤을 때...
	 * 동쪽으로 이동하면 1이 오른쪽을 쳐다봄 -> 방향 2
	 * 동쪽으로 또 이동하면 1이 아래를 쳐다봄 -> 방향 3
	 * 동쪽으로 또 이동하면 1이 왼쪽을 쳐다봄 -> 방향 4
	 * 
	 * 서쪽으로 이동하면 1이 왼쪽을 쳐다봄 -> 방향 4
	 * 서쪽으로 또 이동하면 1이 아래를 쳐다봄 -> 방향 3
	 * 서쪽으로 또 이동하면 1이 오른쪽을 쳐다봄 -> 방향 2
	 */
	/*
	 * 1 -> 동 -> 4, 1 -> 서 -> 3, 1 -> 북 -> 5, 1 -> 남 -> 2
	 * 1 -> 동 -> 5, 1 -> 서 -> 2, 1 -> 북 -> 3, 1 -> 남 -> 4
	 * 1 -> 동 -> 3, 1 -> 서 -> 4, 1 -> 북 -> 2, 1 -> 남 -> 5
	 * 1 -> 동 -> 2, 1 -> 서 -> 5, 1 -> 북 -> 4, 1 -> 남 -> 3
	 * 
	 * 2 -> 동 -> 4, 2 -> 서 -> 3, 2-> 북 -> 1, 2 -> 남 -> 6
	 * 2 -> 동 -> 1, 2 -> 서 -> 6, 2-> 북 -> 3, 2 -> 남 -> 4
	 * 2 -> 동 -> 3, 2 -> 서 -> 4, 2-> 북 -> 6, 2 -> 남 -> 1
	 * 2 -> 동 -> 6, 2 -> 서 -> 1, 2-> 북 -> 4, 2 -> 남 -> 3
	 * 
	 * 3 -> 동 -> 1, 3 -> 서 -> 6, 3 -> 북 -> 5, 3 -> 남 -> 2
	 * 3 -> 동 -> 5, 3 -> 서 -> 2, 3 -> 북 -> 6, 3 -> 남 -> 1
	 * 3 -> 동 -> 6, 3 -> 서 -> 1, 3 -> 북 -> 2, 3 -> 남 -> 5
	 * 3 -> 동 -> 2, 3 -> 서 -> 5, 3 -> 북 -> 1, 3 -> 남 -> 6
	 * 
	 * 4 -> 동 -> 6, 4 -> 서 -> 1, 4 -> 북 -> 5, 4 -> 남 -> 2
	 * 4 -> 동 -> 5, 4 -> 서 -> 2, 4 -> 북 -> 1, 4 -> 남 -> 6
	 * 4 -> 동 -> 1, 4 -> 서 -> 6, 4 -> 북 -> 2, 4 -> 남 -> 5
	 * 4 -> 동 -> 2, 4 -> 서 -> 5, 4 -> 북 -> 6, 4 -> 남 -> 1
	 * 
	 * 5 -> 동 -> 4, 5 -> 서 -> 3, 5 -> 북 -> 6, 5 -> 남 -> 1
	 * 5 -> 동 -> 6, 5 -> 서 -> 1, 5 -> 북 -> 3, 5 -> 남 -> 4
	 * 5 -> 동 -> 3, 5 -> 서 -> 4, 5 -> 북 -> 1, 5 -> 남 -> 6
	 * 5 -> 동 -> 1, 5 -> 서 -> 6, 5 -> 북 -> 4, 5 -> 남 -> 3
	 * 
	 * 6 -> 동 -> 4, 6 -> 서 -> 3, 6 -> 북 -> 2, 6 -> 남 -> 5
	 * 6 -> 동 -> 2, 6 -> 서 -> 5, 6 -> 북 -> 3, 6 -> 남 -> 4
	 * 6 -> 동 -> 3, 6 -> 서 -> 4, 6 -> 북 -> 5, 6 -> 남 -> 2
	 * 6 -> 동 -> 5, 6 -> 서 -> 2, 6 -> 북 -> 4, 6 -> 남 -> 3
	 */
	/*
	 * 1의 아래면: 6 --> 0 : 5
	 * 2의 아래면: 5 --> 1 : 4
	 * 3의 아래면: 4 --> 2 : 3
	 * 4의 아래면: 3 --> 3 : 2
	 * 5의 아래면: 2 --> 4 : 1
	 * 6의 아래면: 1 --> 5 : 0
	 */
	
	static void moving(int cur_y, int cur_x, int top, int cur_cmd, int cur_dir, boolean success) {
		System.out.println(cur_cmd + ": [" + cur_y + "][" + cur_x + "] - " + top + " | " + cur_dir + " | " + (cur_cmd < K ? cmd[cur_cmd] : 0));
		
		if(success) {
			System.out.println(value[top]);
			int tempBottom = matrix[cur_y][cur_x];
			if(tempBottom != 0) {
				matrix[cur_y][cur_x] = 0;
			}
			value[6 - top] = tempBottom;
		}
		
		if(cur_cmd == K)
			return;
		
		int new_y = cur_y + move[cmd[cur_cmd]][0];
		int new_x = cur_x + move[cmd[cur_cmd]][1];
		int new_dir = cur_dir;
		if(cmd[cur_cmd] == 1)
			new_dir = (cur_dir == 3) ? 0 : cur_dir + 1;
		else if(cmd[cur_cmd] == 2)
			new_dir = (cur_dir == 0) ? 3 : cur_dir - 1;
		
		if(new_y < 0 || new_y >= N || new_x < 0 || new_x >= M)
			moving(cur_y, cur_x, top, cur_cmd + 1, cur_dir, false);

		moving(new_y, new_x, topMove[top][cur_dir][cmd[cur_cmd] - 1], cur_cmd + 1, new_dir, true);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		Y = sc.nextInt();
		K = sc.nextInt();
		
		matrix = new int[N][M];
		cmd = new int[K];
		
		value = new int[7];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				matrix[y][x] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < K; i++) {
			cmd[i] = sc.nextInt();
		}
		
		moving(Y, X, 1, 0, 0, false);
	}

}
