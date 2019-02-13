import java.util.Scanner;

public class mwkim_20190215_01 {
	static int N, M, H, needsRow;
	static int[][] matrix;
	static int[] result;
	
	static boolean checkGames() {
		boolean check = true;
		result = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			moving(1, i, i);
			if(result[i] != i)
				return false;
		}
		
		return check;
	}
	
	static void moving(int row, int startCol, int curCol) {
		if(row == H + 1) {
			result[startCol] = curCol;
			return;
		}
		
		//오른쪽으로 이동
		if(matrix[row][curCol] == 1) {
			matrix[row][curCol + 1] = 0;
			moving(row, startCol, curCol + 1);
			matrix[row][curCol + 1] = -1;
		}
		//왼쪽으로 이동
		else if(matrix[row][curCol] == -1){
			matrix[row][curCol - 1] = 0;
			moving(row, startCol, curCol - 1);
			matrix[row][curCol - 1] = 1;
		}
		//아래로 이동
		else {
			moving(row + 1, startCol, curCol);
		}
	}
	
	static void arrayCopy(int[][] temp, int[][] origin) {
		for(int y = 1; y <= H; y++) {
			for(int x = 1; x <= N; x++) {
				temp[y][x] = origin[y][x];
			}
		}
	}
	
	// x = 1이면 맞긴 한데 시간초과
	// x = cur_x + 2면 예제가 안맞음 나중에 풀어보기
	static void buildRowLine(int cur_y, int cur_x, int rowLine) {
		if(checkGames() || rowLine > 3) {

			/*
			 * if (rowLine <= 3) { System.out.println(rowLine + "=====================");
			 * for (int y = 1; y <= H + 1; y++) { System.out.print(y + " "); for (int x = 1;
			 * x <= N; x++) { System.out.print(matrix[y][x] + "\t"); }
			 * System.out.println(""); } }
			 */
			
			needsRow = Math.min(needsRow, rowLine);
			return;
		}
		
		int[][] tempMatrix = new int[H + 2][N + 1];
		
		for(int y = cur_y; y <= H; y++) {
			for(int x = 1; x <= N - 1; x++) {
				if(matrix[y][x] != 0)
					continue;
				
				arrayCopy(tempMatrix, matrix);
				matrix[y][x] = 1;
				matrix[y][x + 1] = -1;
				buildRowLine(y, x+2, rowLine + 1);
				arrayCopy(matrix, tempMatrix);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		needsRow = (N - 1) * H;
		matrix = new int[H + 2][N + 1];
		result = new int[N + 1];
		
		for(int x = 0; x < M; x++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			
			matrix[row][col] = 1;
			matrix[row][col+1] = -1;
		}
		
		buildRowLine(0, 0, 0);
		
		System.out.println(needsRow > 3 ? -1 : needsRow);
	}

}
