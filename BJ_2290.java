import java.util.Scanner;

public class BJ_2290 {
	static int s, row, col;
	static String n;
	static int[] num;
	static char[][] matrix;
	
	static void makeString(int cur_y, int cur_x, int idx, int num) {
		if(cur_y == 0) {
			if(num == 2 || num == 3 || num == 5 || num == 6 || 
				num == 7 || num == 8 || num == 9 || num == 0) {
				
				if(idx > 0 && idx < (s + 1)) {
					matrix[cur_y][cur_x] = '-';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			}
			else {
				matrix[cur_y][cur_x] = ' ';
				return;
			}
		}
		else if(cur_y == (int)Math.floor((double)row / 2.0)) {
			if (num == 2 || num == 3 || num == 4 || num == 5 || 
				num == 6 || num == 8 || num == 9) {

				if (idx > 0 && idx < (s + 1)) {
					matrix[cur_y][cur_x] = '-';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			} 
			else {
				matrix[cur_y][cur_x] = ' ';
				return;
			}
		}
		else if(cur_y == row - 1) {
			if (num == 2 || num == 3 || num == 5 || num == 6
				|| num == 8 || num == 9 || num == 0) {

				if (idx > 0 && idx < (s + 1)) {
					matrix[cur_y][cur_x] = '-';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			} 
			else {
				matrix[cur_y][cur_x] = ' ';
				return;
			}
		}
		//0, mid, row-1을 제외한 나머지 상단에서 | 표시
		else if(cur_y < (int)Math.floor((double)row / 2.0)){
			if(idx == (s + 1)) {
				if(num == 1 || num == 2 || num == 3 || num == 4 || 
				num == 7 || num == 8 || num == 9 || num == 0) {
					matrix[cur_y][cur_x] = '|';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			}
			else if(idx == 0) {
				if(num == 4 || num == 5 || num == 6 || 
					num == 8 || num == 9 || num == 0) {
					matrix[cur_y][cur_x] = '|';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			}
			else {
				matrix[cur_y][cur_x] = ' ';
				return;
			}
		}
		//0, mid, row-1을 제외한 나머지 하단에서 | 표시
		else {
			if(idx == (s + 1)) {
				if(num == 1 || num == 3 || num == 4 || num == 5 || 
				num == 6 || num == 7 || num == 8 || num == 9 || num == 0) {
					matrix[cur_y][cur_x] = '|';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			}
			else if(idx == 0) {
				if(num == 2 || num == 6 || num == 8 || num == 0) {
					matrix[cur_y][cur_x] = '|';
				}
				else
					matrix[cur_y][cur_x] = ' ';
				return;
			}
			else {
				matrix[cur_y][cur_x] = ' ';
				return;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextInt();
		n = sc.next();
		num = new int[n.length()];
		row = s * 2 + 3;
		col = ((s + 2) * n.length()) + (n.length() - 1);
		matrix = new char[row][col];
		int space = 0;
		
		for(int y = 0; y < row; y++) {
			space = 0;
			for(int i = 0; i < n.length(); i++) {
				num[i] = Integer.parseInt(n.substring(i, i+1));
				for(int x = 0; x < (s + 2); x++) {
					makeString(y, (i * (s + 2)) + x + space, x, num[i]);
				}
				space++;
			}
		}
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < col; x++) {
				System.out.print(matrix[y][x]);
			}
			System.out.println("");
		}
	}
}
