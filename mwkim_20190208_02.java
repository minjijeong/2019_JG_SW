import java.util.Scanner;

public class mwkim_20190208_02 {
	static int[][] circle, cmd;
	static int N, score;
	static int[] gears;

	static void rotation(int[] circle, int direction) {
		int[] temp = circle.clone();
		
		if(direction == -1) {
			for(int i = 0; i < 8; i++) {
				if(i == 7) {
					circle[i] = temp[0];
				}
				else {
					circle[i] = temp[i + 1];
				}
			}
		}
		else {
			for(int i = 0; i < 8; i++) {
				if(i == 0) {
					circle[i] = temp[7];
				}
				else {
					circle[i] = temp[i - 1];
				}
			}
		}
	}
	
	static void moving(int loop) {
		if(loop == N) {
			
			/*
			 * for (int idx = 0; idx < 4; idx++) { for (int i = 0; i < 8; i++) {
			 * System.out.print(circle[idx][i]); } System.out.println(""); }
			 */
			  
			score = 1 * circle[0][0]+ 2 * circle[1][0] + 4 * circle[2][0] + 8 * circle[3][0];
			return;
		}
		
		int idx = cmd[loop][0] - 1;
		gears = new int[4];
		gears[idx] = cmd[loop][1];
		int temp_idx = idx;
		
		//정방향 검사
		for(int i = idx + 1; i < 4; i++) {
			if(gears[temp_idx] == 0) {
				temp_idx++;
				continue;
			}
			
			if(circle[temp_idx][2] == circle[i][6]) {
				gears[i] = 0;
				temp_idx++;
				continue;
			}
			gears[i] = gears[temp_idx] * (-1);
			temp_idx++;
		}
		
		temp_idx = idx;
		//역방향 검사
		for(int i = idx - 1; i >= 0; i--) {			
			if(gears[temp_idx] == 0) {
				temp_idx--;
				continue;
			}
			if(circle[temp_idx][6] == circle[i][2]) {
				gears[i] = 0;
				temp_idx--;
				continue;
			}
			gears[i] = gears[temp_idx] * (-1);
			temp_idx--;
		}
		for(int i = 0; i < 4; i++) {
			if(gears[i] == 0)
				continue;
			
			rotation(circle[i], gears[i]);
		}
		moving(loop + 1);
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		circle = new int[4][8];
		gears = new int[4];
		
		for(int num = 0; num < 4; num++) {
			String temp = sc.nextLine().trim();
			for(int idx = 0; idx < 8; idx++) {
				circle[num][idx] = Integer.parseInt(temp.substring(idx, idx + 1));
			}
		}
		N = sc.nextInt();
		cmd = new int[N][2];
		for(int i = 0; i < N; i++) {
			cmd[i][0] = sc.nextInt();
			cmd[i][1] = sc.nextInt();
		}
		
		moving(0);
		
		System.out.println(score);
	}

}
