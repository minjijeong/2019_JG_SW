import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/5373
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190218_01 {
	static char[][][] cube =	{{},
										{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}},
										{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}},
										{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}},
										{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}},
										{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}},
										{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}}};
	/*
	 * 		2
	 * 3	1	4
	 * 		5
	 * 		6
	 * 초기상태
	 * w: 흰색 (2)  / y: 노란색 (5)  / r: 빨간색 (1) 
	 * o: 오렌지색 (6) / g: 초록색 (3) / b: 파란색 (4)
	 */
	static int testCase;
	static int[][] cmd;
	static int[] direction;
	
	static void execute() {
		int test = 0;
		int n = 0;
		char[][][] origin = new char[7][3][3];
		arrayCopy(origin, cube);
		while(test < testCase) {
			if(cmd[test].length == n) {
				print();
				test++;
				n = 0;
				arrayCopy(cube, origin);
				continue;
			}
			valueChange(Math.abs(cmd[test][n]), cmd[test][n] > 0);
			//System.out.println(n + ":====================");
			//print();
			n++;
		}
	}
	
	static void arrayCopy(char[][][] temp, char[][][] origin) {
		for(int a = 1; a < 7; a++) {
			for(int y = 0; y < 3; y++) {
				for(int x = 0; x < 3; x++) {
					temp[a][y][x] = origin[a][y][x];
				}
			}
		}
	}
	
	static void print() {
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				System.out.print(cube[2][y][x]);
			}
			System.out.println("");
		}
	}
	
	static void valueChange(int aspect, boolean d) {
		//이웃 면: 순서대로 위 오른쪽 아래 왼쪽
		int[] n = new int[4];
		int[][] y = new int[4][3];
		int[][] x = new int[4][3];
		char[][][] temp = cube.clone();

		switch(aspect) {
		case 1:
			n[0] = 2; n[1] = 4; n[2] = 5; n[3] = 3;
			y[0][0] = 2; y[0][1] = 2; y[0][2] = 2; 
			y[1][0] = 0; y[1][1] = 1; y[1][2] = 2; 
			y[2][0] = 0; y[2][1] = 0; y[2][2] = 0; 
			y[3][0] = 2; y[3][1] = 2; y[3][2] = 2; 
			x[0][0] = 0; x[0][1] = 1; x[0][2] = 2; 
			x[1][0] = 0; x[1][1] = 0; x[1][2] = 0; 
			x[2][0] = 2; x[2][1] = 1; x[2][2] = 0; 
			x[3][0] = 2; x[3][1] = 1; x[3][2] = 0; 
			break;
		case 2:
			n[0] = 6; n[1] = 4; n[2] = 1; n[3] = 3;
			y[0][0] = 2; y[0][1] = 2; y[0][2] = 2; 
			y[1][0] = 0; y[1][1] = 0; y[1][2] = 0; 
			y[2][0] = 0; y[2][1] = 0; y[2][2] = 0; 
			y[3][0] = 0; y[3][1] = 0; y[3][2] = 0; 
			x[0][0] = 0; x[0][1] = 1; x[0][2] = 2; 
			x[1][0] = 2; x[1][1] = 1; x[1][2] = 0; 
			x[2][0] = 2; x[2][1] = 1; x[2][2] = 0; 
			x[3][0] = 2; x[3][1] = 1; x[3][2] = 0; 
			break;
		case 3:
			n[0] = 2; n[1] = 1; n[2] = 5; n[3] = 6;
			y[0][0] = 0; y[0][1] = 1; y[0][2] = 2; 
			y[1][0] = 0; y[1][1] = 1; y[1][2] = 2; 
			y[2][0] = 0; y[2][1] = 1; y[2][2] = 2; 
			y[3][0] = 0; y[3][1] = 1; y[3][2] = 2; 
			x[0][0] = 0; x[0][1] = 0; x[0][2] = 0; 
			x[1][0] = 0; x[1][1] = 0; x[1][2] = 0; 
			x[2][0] = 0; x[2][1] = 0; x[2][2] = 0; 
			x[3][0] = 0; x[3][1] = 0; x[3][2] = 0; 
			break;
		case 4:
			n[0] = 2; n[1] = 6; n[2] = 5; n[3] = 1;
			y[0][0] = 0; y[0][1] = 0; y[0][2] = 0; 
			y[1][0] = 2; y[1][1] = 1; y[1][2] = 0; 
			y[2][0] = 2; y[2][1] = 1; y[2][2] = 0; 
			y[3][0] = 2; y[3][1] = 1; y[3][2] = 0; 
			x[0][0] = 0; x[0][1] = 1; x[0][2] = 2; 
			x[1][0] = 2; x[1][1] = 2; x[1][2] = 2; 
			x[2][0] = 2; x[2][1] = 2; x[2][2] = 2; 
			x[3][0] = 2; x[3][1] = 2; x[3][2] = 2; 
			break;
		case 5:
			n[0] = 1; n[1] = 4; n[2] = 6; n[3] = 3;
			y[0][0] = 2; y[0][1] = 2; y[0][2] = 2; 
			y[1][0] = 2; y[1][1] = 2; y[1][2] = 2; 
			y[2][0] = 0; y[2][1] = 0; y[2][2] = 0; 
			y[3][0] = 2; y[3][1] = 2; y[3][2] = 2; 
			x[0][0] = 0; x[0][1] = 1; x[0][2] = 2; 
			x[1][0] = 0; x[1][1] = 1; x[1][2] = 2; 
			x[2][0] = 2; x[2][1] = 1; x[2][2] = 0; 
			x[3][0] = 0; x[3][1] = 1; x[3][2] = 2; 
			break;
		case 6:
			n[0] = 5; n[1] = 4; n[2] = 2; n[3] = 3;
			y[0][0] = 2; y[0][1] = 2; y[0][2] = 2; 
			y[1][0] = 2; y[1][1] = 1; y[1][2] = 0; 
			y[2][0] = 0; y[2][1] = 0; y[2][2] = 0; 
			y[3][0] = 0; y[3][1] = 1; y[3][2] = 2; 
			x[0][0] = 0; x[0][1] = 1; x[0][2] = 2; 
			x[1][0] = 2; x[1][1] = 2; x[1][2] = 2; 
			x[2][0] = 2; x[2][1] = 1; x[2][2] = 0; 
			x[3][0] = 0; x[3][1] = 0; x[3][2] = 0; 
			break;
		}
		if(d) {		
			for(int i = 0; i < 4; i++) {
				int m = (i == 0 ? 3 : i - 1);
				cube[n[i]][y[i][0]][x[i][0]] = temp[n[m]][y[m][0]][x[m][0]];
				cube[n[i]][y[i][1]][x[i][1]] = temp[n[m]][y[m][1]][x[m][1]];
				cube[n[i]][y[i][2]][x[i][2]] = temp[n[m]][y[m][2]][x[m][2]];
			}
		}
		else {
			for(int i = 0; i < 4; i++) {
				int m = (i + 1) % 4;
				cube[n[i]][y[i][0]][x[i][0]] = temp[n[m]][y[m][0]][x[m][0]];
				cube[n[i]][y[i][1]][x[i][1]] = temp[n[m]][y[m][1]][x[m][1]];
				cube[n[i]][y[i][2]][x[i][2]] = temp[n[m]][y[m][2]][x[m][2]];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		testCase = sc.nextInt();
		cmd = new int[testCase][];
		
		for(int y = 0; y < testCase; y++) {
			int n = sc.nextInt();
			cmd[y] = new int[n];

			for(int x = 0; x < n; x++) {
				String temp = sc.next().trim();
				int aspect = 0;
				int direction = 0;
				switch(temp.charAt(0)) {
				case 'U':
					aspect = 2;
					break;
				case 'D':
					aspect = 5;
					break;
				case 'F':
					aspect = 1;
					break;
				case 'B':
					aspect = 6;
					break;
				case 'L':
					aspect = 3;
					break;
				case 'R':
					aspect = 4;
					break;
				}
				if(temp.charAt(1) == '+')
					direction = 1;
				else
					direction = -1;
				
				cmd[y][x] = aspect * direction;
			}
		}
		
		execute();
	}

}
