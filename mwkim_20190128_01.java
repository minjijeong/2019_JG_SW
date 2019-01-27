import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/12100
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190128_01 {
	static int N, maxValue;
	static int[][] matrix;
	
	// 상하좌우 0123
	static void moving(int direction, int[][] board) {		
		switch(direction) {
		case 0:
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					for(int i = x + 1; i < N; i++) {
						if(board[x][y] != 0) {
							if(board[i][y] != 0) {
								if(board[x][y] != board[i][y])
									break;
								else {
									board[x][y] = board[x][y] * 2;
									board[i][y] = 0;
									break;
								}
							}
						}
						else {
							if(board[i][y] != 0) {
								board[x][y] = board[i][y];
								board[i][y] = 0;
							}
						}
					}
				}
			}
			break;
		case 1:
			for(int y = N - 1; y >= 0; y--) {
				for(int x = N - 1; x >= 0; x--) {
					for(int i = x - 1; i >= 0; i--) {
						if(board[x][y] != 0) {
							if(board[i][y] != 0) {
								if(board[x][y] != board[i][y])
									break;
								else {
									board[x][y] = board[x][y] * 2;
									board[i][y] = 0;
									break;
								}
							}
						}
						else {
							if(board[i][y] != 0) {
								board[x][y] = board[i][y];
								board[i][y] = 0;
							}
						}
					}
				}
			}
			break;
		case 2:
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					for(int i = x + 1; i < N; i++) {
						if(board[y][x] != 0) {
							if(board[y][i] != 0) {
								if(board[y][x] != board[y][i])
									break;
								else {
									board[y][x] = board[y][x] * 2;
									board[y][i] = 0;
									break;
								}
							}
						}
						else {
							if(board[y][i] != 0) {
								board[y][x] = board[y][i];
								board[y][i] = 0;
							}
						}
					}
				}
			}
			break;
		case 3:
			for(int y = N - 1; y >= 0; y--) {
				for(int x = N - 1; x >= 0; x--) {
					for(int i = x - 1; i >= 0; i--) {
						if(board[y][x] != 0) {
							if(board[y][i] != 0) {
								if(board[y][x] != board[y][i])
									break;
								else {
									board[y][x] = board[y][x] * 2;
									board[y][i] = 0;
									break;
								}
							}
						}
						else {
							if(board[y][i] != 0) {
								board[y][x] = board[y][i];
								board[y][i] = 0;
							}
						}
					}
				}
			}
			break;
		}
		/*
		System.out.println("direction: " + direction + "-----------");
		for(int y = 0; y < N; y++) {
			for(int x = 0; x< N; x++) {
				System.out.print(board[y][x] + "\t");
			}
			System.out.println("");
		}
		*/
	}
	
	static void swipe(int loop, int direction) {
		if(loop == 5)
			return;
		
		int[][] temp = new int[N][N];
		for(int i = 0; i < 4; i++) {
			copy(temp, matrix);
			moving(i, matrix);
			findMax();
			swipe(loop + 1, i);
			copy(matrix, temp);
		}
	}
	
	static void copy(int[][] temp, int[][] origin) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				temp[y][x] = origin[y][x];
			}
		}
	}
	
	static void findMax() {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x< N; x++) {
				maxValue = Math.max(maxValue, matrix[y][x]);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		matrix = new int[N][N];
		maxValue = -100000000;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x< N; x++) {
				matrix[y][x] = sc.nextInt();
			}
		}
		swipe(0, -1);

		System.out.println(maxValue);
	}
}
