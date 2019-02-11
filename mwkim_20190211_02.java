import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/15685
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190211_02 {
	static int N, square;
	static boolean[][] matrix;
	static int[][] cmd;
	static int[][] move = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //동북서남

	static int getSquare() {
		int square = 0;
		for(int y = 0; y < 101; y++) {
			for(int x = 0; x < 101; x++) {
				//System.out.print(matrix[y][x] ? "." : "*");
				if(y < 100 && x < 100) {
					if(matrix[y][x] && matrix[y+1][x] && matrix[y][x+1] && matrix[y+1][x+1]	)
						square++;
				}
			}
			//System.out.println("");
		}
		
		return square;
	}
	
	static void drawCurve(int loop) {
		if(loop == N) {
			square = getSquare();
			return;
		}
		
		int cur_x = cmd[loop][0];
		int cur_y = cmd[loop][1];
		int cur_d = cmd[loop][2];
		int cur_g = cmd[loop][3];
		int[] temp = new int[(int)Math.pow(2.0, cur_g * 1.0)];
		temp[0] = cur_d;
		
		//방향구하기
		for(int g = 0; g < cur_g; g++) {
			int new_g = (int) Math.pow(2.0, (g + 1) * 1.0);
			for(int i = 0; i < (new_g / 2); i++) {
				temp[new_g - i - 1] = (temp[i] + 1) % 4;
			}
		}
		
		//매트릭스에 그리기
		if(cur_y < 101 &&  cur_x < 101 && cur_y >= 0 && cur_x >= 0)
			matrix[cur_y][cur_x] = true;
		
		for(int i = 0; i < (int)Math.pow(2.0, cur_g * 1.0); i++){
			cur_y += move[temp[i]][0];
			cur_x += move[temp[i]][1];
			if(cur_y >= 101 || cur_x >= 101 || cur_y < 0 || cur_x <0)
				continue;
			
			matrix[cur_y][cur_x] = true;
		}
		
		drawCurve(loop + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		matrix = new boolean[101][101];
		cmd = new int[N][4];
		for(int i = 0; i < N; i++) {
			cmd[i][0] = sc.nextInt();	//x
			cmd[i][1] = sc.nextInt();	//y
			cmd[i][2] = sc.nextInt();	//시작방향
			cmd[i][3] = sc.nextInt();	//세대
		}
		drawCurve(0);
		System.out.println(square);
	}
}
