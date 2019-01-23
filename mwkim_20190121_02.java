import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class mwkim_20190121_02 {
	static int N, M, min_result;
	static char[][] matrix;
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // x+1, y+1, x-1, y-1
	static int[] hole;
	static BufferedWriter bw;
	
	static int moving(int[] locationR, int[] locationB, int direction) {
		int[] newR = new int[2];
		int[] newB = new int[2];
		newR[0] = locationR[0];
		newR[1] = locationR[1];
		newB[0] = locationB[0];
		newB[1] = locationB[1];
		
		while((newR[0] == locationR[0] && newR[1] == locationR[1]) 
			&& (newB[0] == locationB[0] && newB[1] == locationB[1])) {
			newR[0] = locationR[0] + move[direction][0];
			newR[1] = locationR[1] + move[direction][1];
			newB[0] = locationB[0] + move[direction][0];
			newB[1] = locationB[1] + move[direction][1];
			
			System.out.println(direction + " - R: [" + locationR[0] + "][" + locationR[1] + "] | B: [" + locationB[0] + "][" + locationB[1] + "]");
			
			if(locationR[0] == locationB[0]) {
				if((direction == 0 && locationR[1] < locationB[1])
				&& (direction == 2 && locationR[1] > locationB[1])){
					oneStepMove(locationB, newB);
					oneStepMove(locationR, newR);
				}
				else {
					oneStepMove(locationR, newR);
					oneStepMove(locationB, newB);
				}
			}
			else if(locationR[1] == locationB[1]) {
				if((direction == 1 && locationR[0] < locationB[0])
				|| (direction == 3 && locationR[0] > locationB[0])){
					oneStepMove(locationB, newB);
					oneStepMove(locationR, newR);
				}
				else {
					oneStepMove(locationR, newR);
					oneStepMove(locationB, newB);
				}
			}
			else {
				oneStepMove(locationR, newR);
				oneStepMove(locationB, newB);
			}
		}
		if(hole[0] == locationB[0] && hole[1] == locationR[1])
			return -1;
		else {
			if(hole[0] == locationR[0] && hole[1] == locationR[1])
				return 1;
			else
				return 0;
		}
	}
	
	static void oneStepMove(int[] curr, int[] next) {
		if(matrix[next[0]][next[1]] == '.') {
			swap(matrix[next[0]][next[1]], matrix[curr[0]][curr[1]]);
			curr[0] = next[0];
			curr[1] = next[1];
		}
		else if(matrix[next[0]][next[1]] == 'O') {
			curr[0] = next[0];
			curr[1] = next[1];
		}
	}
	
	static void tilting(int[] locationR, int[] locationB, int loop) throws IOException {
		if(loop == 2) {
			return;
		}
		//bw.write(loop + " - R: [" + locationR[0] + "][" + locationR[1] + "] | B: [" + locationB[0] + "][" + locationB[1] + "]\n");
		//System.out.println(loop + " - R: [" + locationR[0] + "][" + locationR[1] + "] | B: [" + locationB[0] + "][" + locationB[1] + "]");
		for(int i = 0; i < move.length; i++) {
			int[] tempR = locationR;
			int[] tempB = locationB;
			char[][] tempMap = matrix;
			int result = moving(locationR, locationB, i);

			if(result > 0) {
				min_result = Math.min(loop + 1, min_result);
				return;
			}
			
			tilting(locationR, locationB, loop + 1);

			locationR = tempR;
			locationB = tempB;
			matrix = tempMap;
		}
	}
	
	static void swap(char ch1, char ch2) {
		char temp = ch1;
		ch1 = ch2;
		ch2 = temp;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		min_result = 10;
		matrix = new char[N][M];
		int[] locationR = new int[2];
		int[] locationB = new int[2];
		hole = new int[2];
		bw = new BufferedWriter(new FileWriter("test.txt"));
		
		for(int y = 0; y < N; y++) {
			matrix[y] = sc.next().toCharArray();
			
			for(int x = 0; x < M; x++) {
				if(matrix[y][x] == 'R') {
					locationR[0] = y;
					locationR[1] = x;
				}
				else if(matrix[y][x] == 'B') {
					locationB[0] = y;
					locationB[1] = x;
				}
				else if(matrix[y][x] == 'O') {
					hole[0] = y;
					hole[1] = x;
				}
			}
		}
		tilting(locationR, locationB, 0);
		
		System.out.println(min_result == 10 ? -1 : min_result);
		bw.flush();
		bw.close();
	}

}
