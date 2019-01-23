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
	static int[] dice;
	
	/*
	 * 동 (1)로 이동
	 * 1 <- 4, 3 <- 1, 4 <- 6, 6 <- 3
	 * 서 (2)로 이동
	 * 1 <- 3, 3 <- 6, 4 <- 1, 6 <- 4
	 * 북 (3)로 이동
	 * 1 <- 5, 2 <- 1, 5 <- 6, 6 <- 2
	 * 남 (4)로 이동
	 * 1 <- 2, 2 <- 6, 5 <- 1, 6 <- 5
	 */
	
	static void rolling(int direction) {
		int[] tempDice = dice.clone();
		
		switch(direction) {
		case 1: 
			dice[1] = tempDice[4];
			dice[3] = tempDice[1];
			dice[4] = tempDice[6];
			dice[6] = tempDice[3];
			break;
		case 2:
			dice[1] = tempDice[3];
			dice[3] = tempDice[6];
			dice[4] = tempDice[1];
			dice[6] = tempDice[4];
			break;
		case 3: 
			dice[1] = tempDice[5];
			dice[2] = tempDice[1];
			dice[5] = tempDice[6];
			dice[6] = tempDice[2];
			break;
		case 4: 
			dice[1] = tempDice[2];
			dice[2] = tempDice[6];
			dice[5] = tempDice[1];
			dice[6] = tempDice[5];
			break;
		}
	}
	
	static void moving(int cur_y, int cur_x, int cur_cmd, boolean success) {
		//System.out.println(cur_cmd + ": [" + cur_y + "][" + cur_x + "] - " + (cur_cmd < K ? cmd[cur_cmd] : 0));
		
		if(success) {
			System.out.println(dice[1]);
			int tempBottom = matrix[cur_y][cur_x];
			if(tempBottom == 0)
				matrix[cur_y][cur_x] = dice[6];
			else {
				matrix[cur_y][cur_x] = 0;
				dice[6] = tempBottom;
			}
		}
		
		if(cur_cmd == K)
			return;
		
		int new_y = cur_y + move[cmd[cur_cmd]][0];
		int new_x = cur_x + move[cmd[cur_cmd]][1];
		
		if(new_y < 0 || new_y >= N || new_x < 0 || new_x >= M) {
			moving(cur_y, cur_x, cur_cmd + 1, false);
		}
		else {
			rolling(cmd[cur_cmd]);
			moving(new_y, new_x, cur_cmd + 1, true);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		Y = sc.nextInt();
		X = sc.nextInt();
		K = sc.nextInt();
		
		matrix = new int[N][M];
		cmd = new int[K];
		
		dice = new int[7];

		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				matrix[y][x] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < K; i++) {
			cmd[i] = sc.nextInt();
		}
		
		moving(Y, X, 0, false);
	}

}
