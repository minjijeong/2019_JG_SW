import java.util.Scanner;

public class mwkim_20190121_01 {

	static int[][] matrix, slope;
	static int N, L, way;
	static int[][] builded;
	static boolean recently;
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // x+1, y+1, x-1, y-1
	
	static void findWay(int cur_y, int cur_x, int whereMove) {
		//System.out.println(whereMove + ": [" + cur_y + "][" + cur_x + "] - " + builded[cur_y][cur_x] + " - " + way);
		
		if((whereMove == 0 && cur_x >= N - 1)
		|| (whereMove == 1 && cur_y >= N - 1)
		|| (whereMove == 2 && cur_x <= 0)
		|| (whereMove == 3 && cur_y <= 0)) {
			if(!recently) {
				way++;
				recently = true;
			}
				
			return;
		}
		int new_y = cur_y + move[whereMove][0];
		int new_x = cur_x + move[whereMove][1];
		
		if(builded[new_y][new_x] > 1)
			return;
		
		if(matrix[cur_y][cur_x] == matrix[new_y][new_x] 
		|| (matrix[new_y][new_x] - matrix[cur_y][cur_x] == builded[cur_y][cur_x])
		|| (matrix[new_y][new_x] + builded[new_y][new_x] == matrix[cur_y][cur_x])) {
			findWay(new_y, new_x, whereMove);
		}
	}
	
	static void installSlope(int cur_y, int cur_x, int whereMove) {
		if((whereMove == 0 && cur_x >= N - 1)
		|| (whereMove == 1 && cur_y >= N - 1)
		|| (whereMove == 2 && cur_x <= 0)
		|| (whereMove == 3 && cur_y <= 0))
			return;
		
		int slope_y = cur_y + slope[whereMove][0];
		int slope_x = cur_x + slope[whereMove][1];
		int new_y = cur_y + move[whereMove][0];
		int new_x = cur_x + move[whereMove][1];
		
		//조건 4
		if((whereMove == 0 && slope_x >= N)
		|| (whereMove == 1 && slope_y >= N)
		|| (whereMove == 2 && slope_x < 0)
		|| (whereMove == 3 && slope_y < 0))
			return;
		
		//조건 2
		if(matrix[slope_y][slope_x] - matrix[cur_y][cur_x] != 1) {
			installSlope(new_y, new_x, whereMove);
			return;
		}
		
		//조건 3
		boolean buildable = true;
		for(int i = 0; i < L; i++) {
			int build_y = cur_y + (move[whereMove][0]) * i;
			int build_x = cur_x + (move[whereMove][1]) * i;
			
			if(build_y >= N || build_x >= N || build_y < 0 || build_x < 0)
				continue;
			
			if(matrix[cur_y][cur_x] != matrix[build_y][build_x])
				buildable = false;
		}
		if(!buildable) {
			installSlope(new_y, new_x, whereMove);
			return;
		}
		else {
			for(int i = 0; i < L; i++) {
				int build_y = cur_y + (move[whereMove][0]) * i;
				int build_x = cur_x + (move[whereMove][1]) * i;
				builded[build_y][build_x]++;
			}
			installSlope(new_y, new_x, whereMove);
		}
	}
	
	static void removeSlope(int cur_y, int cur_x, int whereMove) {
		if(whereMove == 0 || whereMove == 2) {
			for(int i = 0; i < N; i++) {
				builded[cur_y][i] = 0;
			}
		}
		else {
			for(int i = 0; i < N; i++) {
				builded[i][cur_x] = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		way = 0;
		recently = false;
		matrix = new int[N][N];
		slope = new int[4][4];
		
		slope[0][0] = 0;
		slope[0][1] = L;
		slope[1][0] = L;
		slope[1][1] = 0;
		slope[2][0] = 0;
		slope[2][1] = -L;
		slope[3][0] = -L;
		slope[3][1] = 0;
		
		builded = new int[N][N];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				matrix[y][x] = sc.nextInt();
			}
		}
		
		for(int y = 0; y < N; y++) {
			recently = false;
			installSlope(y, 0, 0);
			installSlope(y, N - 1, 2);
			findWay(y, 0, 0);
			findWay(y, N - 1, 2);
			removeSlope(y, 0, 2);
		}
		
		for(int x = 0; x < N; x++) {
			recently = false;
			installSlope(0, x, 1);
			installSlope(N - 1, x, 3);
			findWay(0, x, 1);
			findWay(N - 1, x, 3);
			removeSlope(0, x, 3);
		}
		
		System.out.println(way);
	}

}
