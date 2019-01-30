import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/14502
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190201_01 {
	static int N, M, max_result, firstVirus;
	static int[][] matrix, virus;
	static int[][] moving = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
	
	static int getResult() {
		int safetyArea = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(matrix[y][x] == 0)
					safetyArea++;
			}
		}
		return safetyArea;
	}
	
	static void virusDiffusion(int cur_y, int cur_x) {
		for(int i = 0; i < 4; i++) {
			int new_y = cur_y + moving[i][0];
			int new_x = cur_x + moving[i][1];
			
			if(new_y < 0 || new_x < 0 || new_y >= N || new_x >= M)
				continue;
			
			if(matrix[new_y][new_x] == 1 || matrix[new_y][new_x] == 2)
				continue;
			
			matrix[new_y][new_x] = 2;
			virusDiffusion(new_y, new_x);
		}
	}
	
	static void arrayCopy(int[][] copy, int[][] origin) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				copy[y][x] = origin[y][x];
			}
		}
	}
	
	static void buildWall(int cur_y, int cur_x, int wall) {
		if(wall == 3) {
			for(int i = 0; i < firstVirus; i++) {
				virusDiffusion(virus[i][0], virus[i][1]);
			}
			int temp = getResult();
			max_result = Math.max(max_result, temp);
			return;
		}
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(matrix[y][x] == 1 || matrix[y][x] == 2)
					continue;
				
				int[][] temp = new int[N][M];
				arrayCopy(temp, matrix);
				matrix[y][x] = 1;
				buildWall(y, x + 1, wall + 1);
				arrayCopy	(matrix, temp);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		matrix = new int[N][M];
		virus = new int[10][2];
		firstVirus = 0;
		max_result = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				matrix[y][x] = sc.nextInt();
				if(matrix[y][x] == 2) {
					virus[firstVirus][0] = y;
					virus[firstVirus][1] = x;
					firstVirus++;
				}
			}
		}
		
		buildWall(0, 0, 0);
		
		System.out.println(max_result);
	}

}
