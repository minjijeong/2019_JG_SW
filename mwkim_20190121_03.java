import java.util.Scanner;

public class mwkim_20190121_03 {
	
	static int N, M, maxValue;
	static int[][] matrix;
	static int[][][] tetromino1 = {{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, 	// ㅡ 
												{{0, 0}, {1, 0}, {2, 0}, {3, 0}}};	// ㅣ
	static int[][][] tetromino2 = {{{0, 0}, {0, 1}, {1, 0}, {1, 1}}};	// ㅁ
	static int[][][] tetromino3 = {{{0, 0}, {1, 0}, {2, 0}, {2, 1}}, 	// ┗ㅡ
												{{0, 1}, {1, 1}, {2, 1}, {2, 0}}, 	// ┘L 옆으로 대칭
												{{0, 0}, {0, 1}, {1, 0}, {2, 0}},	//	Γ
												{{0, 0}, {0, 1}, {1, 1}, {2, 1}},	// ┐세로 긴 ㄱ
												{{0, 0}, {0, 1}, {0, 2}, {1, 2}},	// ㅡ┐
												{{0, 0}, {0, 1}, {0, 2}, {1, 0}},	// ┌ㅡ
												{{0, 0}, {1, 0}, {1, 1}, {1, 2}},	// L
												{{0, 2}, {1, 2}, {1, 1}, {1, 0}}};	// ㅡ┘
	static int[][][] tetromino4 = {{{0, 0}, {1, 0}, {1, 1}, {2, 1}}, 	// ㅣ┐
												{{0, 1}, {1, 1}, {1, 0}, {2, 0}}, 	// ┌ㅣ
												{{0, 2}, {0, 1}, {1, 1}, {1, 0}},	// ┘┌
												{{0, 0}, {0, 1}, {1, 1}, {1, 2}}};	// ┐└
	static int[][][] tetromino5 = {{{0, 0}, {0, 1}, {0, 2}, {1, 1}}, 	// ㅜ
												{{0, 1}, {1, 0}, {1, 1}, {2, 1}}, 	// ㅓ
												{{1, 0}, {0, 1}, {1, 1}, {1, 2}},		// ㅗ
												{{0, 0}, {1, 0}, {2, 0}, {1, 1}}};	// ㅏ

	static void calc(int cur_y, int cur_x, int[][][] tetromino) {
		
		for(int i = 0; i < tetromino.length; i++) {
			int y1 = cur_y + tetromino[i][0][0];
			int y2 = cur_y + tetromino[i][1][0];
			int y3 = cur_y + tetromino[i][2][0];
			int y4 = cur_y + tetromino[i][3][0];
			int x1 = cur_x + tetromino[i][0][1];
			int x2 = cur_x + tetromino[i][1][1];
			int x3 = cur_x + tetromino[i][2][1];
			int x4 = cur_x + tetromino[i][3][1];
			
			if(y1 >= N || y2 >= N || y3 >= N || y4 >= N)
				continue;
			
			if(x1 >= M || x2 >= M || x3 >= M || x4 >= M)
				continue;
			
			int calcValue = matrix[y1][x1] + matrix[y2][x2] + matrix[y3][x3] + matrix[y4][x4];
			maxValue = Math.max(maxValue, calcValue);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		maxValue = 0;
		matrix = new int[N][M];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				matrix[y][x] = sc.nextInt();
			}
		}
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				calc(y, x, tetromino1);
				calc(y, x, tetromino2);
				calc(y, x, tetromino3);
				calc(y, x, tetromino4);
				calc(y, x, tetromino5);
			}
		}
		
		System.out.println(maxValue);
	}

}
