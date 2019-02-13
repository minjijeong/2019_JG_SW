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
		if(row > H) {
			result[startCol] = curCol;
			return;
		}
		
		//오른쪽으로 이동하면 이동한 위치의 연결선을 지우고 재귀호출
		if(matrix[row][curCol] == 1) {
			matrix[row][curCol + 1] = 0;
			moving(row, startCol, curCol + 1);
			matrix[row][curCol + 1] = -1;
		}
		//왼쪽으로 이동하면 이동한 위치의 연결선을 지우고 재귀호출
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
	
	static void buildRowLine(int cur_y, int rowLine) {
		if(checkGames()) {

			/*if (rowLine <= 3) {
				System.out.println(rowLine + "=====================");
				for (int y = 1; y <= H; y++) {
					System.out.print(y + " ");
					for (int x = 1; x <= N; x++) {
						System.out.print(matrix[y][x] + "\t");
					}
					System.out.println("");
				}
			}*/
			
			needsRow = Math.min(needsRow, rowLine);
			return;
		}
		//rowLine이 3인 경우이므로 이미 재귀호출을 3번함
		if(rowLine > 2)
			return;
		
		for(int y = cur_y; y <= H; y++) {
			for(int x = 1; x <= N - 1; x++) {
				//case1: 현재 위치에 연결선이 이미 있을 때
				//case2: 현재 위치 오른쪽에 연결선이 이미 있을 때
				if(matrix[y][x] != 0 || matrix[y][x + 1] != 0)
					continue;
				
				matrix[y][x] = 1;
				matrix[y][x + 1] = -1;
				buildRowLine(y, rowLine + 1);
				matrix[y][x] = 0;
				matrix[y][x + 1] = 0;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		needsRow = Integer.MAX_VALUE;
		matrix = new int[H + 1][N + 1];
		result = new int[N + 1];
		
		for(int x = 0; x < M; x++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			
			matrix[row][col] = 1;
			matrix[row][col+1] = -1;
		}
		
		buildRowLine(1, 0);
		
		System.out.println(needsRow > 3 ? -1 : needsRow);
	}

}
