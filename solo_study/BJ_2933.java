package solo_study;

import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/2933 포기
 * </pre>
 * @author rlaau
 *
 */
public class BJ_2933 {
	static char[][] matrix;
	static int R, C, N;
	static int[] cmd;
	static boolean[] hitCol;
	
	static void fall() {
		for(int x = 0; x < C; x++) {
			if(hitCol[x])
				continue;
			
			int height = 0;
			for(int y = R - 1; y >= 0; y--) {
				if(matrix[y][x] == 'x') {
					height = (R - 1) - y;
					break;
				}
			}
			if(height <= 0)
				continue;
			
			for(int y = (R - 1) - height; y >= 0; y--) {
				int swapNum = 0;
				int cur_y = y;
				while(swapNum < height) {
					swap(x, cur_y, cur_y+1);
					swapNum++;
					cur_y++;
				}
			}
		}
	}
	
	static void fall(int curCol) {
		int cluster = R - 1;
		for(int y = R - 1; y >= 0; y--) {
			if(matrix[y][curCol] == '.')
				continue;
			
			int cur_y = y;
			while(cur_y < cluster) {
				swap(curCol, cur_y, cur_y+1);
				cur_y++;
			}
			cluster = cur_y;
		}
	}
	
	static void swap(int col, int row1, int row2) {
		char temp = matrix[row1][col];
		matrix[row1][col] = matrix[row2][col];
		matrix[row2][col] = temp;
	}
	
	static void isHit(int height, boolean left, int curCol) {
		if(left) {
			if(curCol == C - 1)
				return;
			
			if(matrix[R - height][curCol] == 'x') {
				matrix[R - height][curCol] = '.';
				hitCol[curCol] = true;
				fall(curCol);
				return;
			}
			else {
				isHit(height, left, curCol + 1);
			}
		}
		else {
			if(curCol == 0)
				return;
			
			if(matrix[R - height][curCol] == 'x') {
				matrix[R - height][curCol] = '.';
				hitCol[curCol] = true;
				fall(curCol);
				return;
			}
			else {
				isHit(height, left, curCol - 1);
			}
		}
	}
	
	static void throwStick(int loop) {
		if(loop == N) {
			fall();
			for(int y = 0; y < R; y++) {
				for(int x = 0; x < C; x++) {
					System.out.print(matrix[y][x]);
				}
				System.out.println("");
			}
			return;
		}
		boolean left = ((loop % 2) == 0);
		isHit(cmd[loop], left, left ? 0 : C - 1);
		throwStick(loop + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		matrix = new char[R][C];
		hitCol = new boolean[C];
		for(int y = 0; y < R; y++) {
			String temp = sc.next();
			for(int x = 0; x < C; x++) {
				matrix[y][x] = temp.charAt(x);
			}
		}
		
		N = sc.nextInt();
		cmd = new int[N];
		for(int i = 0; i < N; i++) {
			cmd[i] = sc.nextInt();
		}
		
		throwStick(0);
	}
}
