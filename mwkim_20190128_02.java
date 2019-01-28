import java.util.Scanner;

public class mwkim_20190128_02 {
	static int N, K, Len, L, second;
	static int[][] matrix;
	static int[][] snake;
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};	// 우 하 좌 상
	static int[] cmd_sec;
	static String[] cmd;
	
	// 우 하 좌 상 0, 1, 2, 3
	// 우 L -> 상, 우 D -> 하 | 0 -> 3, 0 -> 1
	// 하 L -> 우, 하 D -> 좌 | 1 -> 0, 1 -> 2
	// 좌 L -> 하, 좌 D -> 상 | 2 -> 1, 2 -> 3
	// 상 L -> 좌, 상 D -> 우 | 3 -> 2, 3 -> 0
	static void moving(int cur_y, int cur_x, int sec, int direction, int state) {
		second = sec;

		System.out.println(sec + "초: --------------------------------");
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.print(matrix[y][x] + "\t");
			}
			System.out.println("");
		}

		if(cur_y < 0 || cur_y >= N || cur_x < 0 || cur_x >= N)
			return;
		if(matrix[cur_y][cur_x] == 1)
			return;
		if(matrix[cur_y][cur_x] == 2) {
			Len++;
		}
		else {
			if(Len > 0)
				matrix[snake[Len - 1][0]][snake[Len - 1][1]] = 0;
		}
		int[][] temp = new int[K][2];
		copy(temp, snake);
		snake[0][0] = cur_y;
		snake[0][1] = cur_x;
		matrix[snake[0][0]][snake[0][1]] = 1;
		
		for(int i = 0; i < Len - 1; i++) {
			snake[i + 1][0] = temp[i][0]; 
			snake[i + 1][1] = temp[i][1];
			matrix[snake[i + 1][0]][snake[i + 1][1]] = 1;
		}
		
		int new_d = direction;
		int new_s = state;
		if(state <= L - 1) {
			if(sec == cmd_sec[state]) {
				new_d = getDirection(direction, cmd[state]);
				new_s++;
			}
		}
		
		int head_ny = snake[0][0] + move[new_d][0];
		int head_nx = snake[0][1] + move[new_d][1];
		
		moving(head_ny, head_nx, sec + 1, new_d, new_s);
	}
	
	static void copy(int[][] temp, int[][] origin) {
		for(int y = 0; y < origin.length; y++	) {
			for(int x = 0; x < origin[y].length; x++) {
				temp[y][x] = origin[y][x];
			}
		}
	}
	
	// 우 L -> 상, 우 D -> 하 | 0 -> 3, 0 -> 1
	// 하 L -> 우, 하 D -> 좌 | 1 -> 0, 1 -> 2
	// 좌 L -> 하, 좌 D -> 상 | 2 -> 1, 2 -> 3
	// 상 L -> 좌, 상 D -> 우 | 3 -> 2, 3 -> 0
	static int getDirection(int direction, String next) {
		int new_direction = 0;
		
		if(next.equals("L")) {
			switch(direction) {
			case 0:
				new_direction = 3;
				break;
			case 1:
				new_direction = 0;
				break;
			case 2:
				new_direction = 1;
				break;
			case 3:
				new_direction = 2;
				break;
			}
		}
		else {
			switch(direction) {
			case 0:
				new_direction = 1;
				break;
			case 1:
				new_direction = 2;
				break;
			case 2:
				new_direction = 3;
				break;
			case 3:
				new_direction = 0;
				break;
			}
		}
		
		return new_direction;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		Len = 1;
		second = 0;

		matrix = new int[N][N];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				matrix[y][x] = 0;
			}
		}
		
		for(int i = 0; i < K; i++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			matrix[row][col] = 2;
		}
		
		L = sc.nextInt();
		cmd_sec = new int[L];
		cmd = new String[L];
		for(int i = 0; i < L; i++) {
			cmd_sec[i] = sc.nextInt();
			cmd[i] = sc.next();
		}
		snake = new int[K][2];
		snake[0][0] = 0;
		snake[0][1] = 0;
		matrix[0][0] = 1;
		
		moving(snake[0][0], snake[0][1], 0, 0, 0);
		
		System.out.println(second);
	}

}
