import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/15683
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190211_01 {
	static int N, M, cctv_num, blindArea;
	static int[][] matrix, cctv;
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //동남서북
	
	static int checkArea() {
		int area = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(matrix[y][x] == 0)
					area++;
			}
		}
		return area;
	}
	
	static int[] getDirection(int type, int direction) {
		int[] monitor = new int[4];

		switch(type) {
		case 1:
			monitor[direction] = 1;
			break;
		case 2:
			monitor[direction] = 1;
			monitor[(direction + 2) % 4] = 1;
			break;
		case 3:
			monitor[direction] = 1;
			monitor[(direction + 1) % 4] = 1;
			break;
		case 4:
			monitor[direction] = 1;
			monitor[(direction + 1) % 4]	= 1;
			monitor[(direction + 3) % 4]	= 1;
			break;
		case 5:
			monitor[0] = 1;
			monitor[1] = 1;
			monitor[2] = 1;
			monitor[3] = 1;
			break;
		}
		
		return monitor;
	}
	
	//동남서북 각 i방향에 따라 6이나 좌표가 벗어나기 전까지 -1(감시구역)으로 설정
	static void expansion(int cur_y, int cur_x, int[] direction) {
		for(int i = 0; i < 4; i++) {
			if(direction[i] == 0)
				continue;
			
			int new_y = cur_y;
			int new_x = cur_x;
			while(true) {
				new_y += move[i][0];
				new_x += move[i][1];
				
				if(new_y >= N || new_x >= M || new_y < 0 || new_x < 0)
					break;
				
				if(matrix[new_y][new_x] == 6)
					break;
				
				if(matrix[new_y][new_x] <= 0)
					matrix[new_y][new_x] = -1;
			}
		}
	}
	
	//cctv num 하나씩 증가하면서 재귀호출, cctv 방향을 4방향으로 바꾸면서 감시방향을 설정하고 재귀호출
	static void setCCTVArea(int num) {
		if(num == cctv_num) {
			blindArea = Math.min(blindArea, checkArea());
			return;
		}
		
		int[][] temp = new int[N][M];
		int type = cctv[num][2];
		int direction = cctv[num][3];
		for(int d = 0; d < 4; d++) {
			cctv[num][3] = (cctv[num][3] + 1) % 4;
			int[] new_d = getDirection(type, cctv[num][3]);
			arrayCopy(temp, matrix);
			expansion(cctv[num][0], cctv[num][1], new_d);
			setCCTVArea(num + 1);
			arrayCopy(matrix, temp);
		}
		cctv[num][3] = direction;
	}
	
	static void arrayCopy(int[][] copy, int[][] origin) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				copy[y][x] = origin[y][x];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new int[N][M];
		cctv = new int[8][4]; //y좌표, x좌표, 타입, 방향
		cctv_num = 0;
		blindArea = N * M;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				matrix[y][x] = sc.nextInt();
				if(matrix[y][x] > 0 && matrix[y][x] < 6) {
					cctv[cctv_num][0] = y;
					cctv[cctv_num][1] = x;
					cctv[cctv_num][2] = matrix[y][x];
					cctv_num++;
				}
			}
		}
		setCCTVArea(0);
		
		System.out.println(blindArea);
	}

}
