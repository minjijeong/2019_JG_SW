import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/13460
 * </pre>
 * @author 김명우
 *
 */
public class BJ_13460 {
	static int N, M, min_tilt;
	static char[][] matrix;
	static int[][] move = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //동북서남
	static int[] H;
	static boolean[][][][] visit;
	
	static class PinBall {
		int[] R;
		int[] B;
		int loop;
		
		public PinBall(int[] R, int[] B, int loop) {
			this.R = R;
			this.B = B;
			this.loop = loop;
		}
	}
	
	//1: 성공, 0: 둘다 실패, -1: 파란공이 홀로 들어가서 실패
	static int check(int[] R, int[] B) {
		int success = 0;
		if(R[0] == H[0] && R[1] == H[1]) {
			success = 1;
		}
		if(B[0] == H[0] && B[1] == H[1]) {
			success = -1;
		}
		
		return success;
	}
	
	static int[] moving(int[] ball, int d) {
		int[] result = ball.clone();
		
		if(matrix[result[0]][result[1]] == 'O')
			return result;
		
		while(matrix[result[0] + move[d][0]][result[1] + move[d][1]] != '#') {			
			result[0] += move[d][0];
			result[1] += move[d][1];
			
			if(matrix[result[0]][result[1]] == 'O')
				break;
		}
		
		return result;
	}
	
	static void tilting(int[] R, int[] B) {
		Queue<PinBall> queue = new LinkedList<PinBall>();
		queue.add(new PinBall(R, B, 0));
		
		while(!queue.isEmpty()) {
			PinBall cur = queue.poll();
			
			//System.out.println("[" + cur.R[0] + "][" + cur.R[1] + "] | [" + cur.B[0] + "][" + cur.B[1] + "]: " + check(cur.R, cur.B));
			
			if(check(cur.R, cur.B) == 1) {
				/*System.out.println(cur.loop + ":==================");
				for(int y = 0; y < N; y++) {
					for(int x = 0; x < M; x++) {
						System.out.print(matrix[y][x]);
					}
					System.out.println("");
				}*/
				
				min_tilt = Math.min(min_tilt, cur.loop);
				return;
			}
			
			if(cur.loop >= 10)
				continue;
			
			for(int i = 0; i < 4; i++) {
				int[] new_R = new int[2];
				int[] new_B = new int[2];
				
				new_R = moving(cur.R, i);
				new_B = moving(cur.B, i);
				
				//새로 이동한것들이 겹친 경우
				if(check(new_R, new_B) == 0 && 
					new_R[0] == new_B[0] && new_R[1] == new_B[1]) {
					switch(i) {
					case 0:
						if(cur.R[1] > cur.B[1])
							new_B[1]--;
						else
							new_R[1]--;
						break;
					case 1:
						if(cur.R[0] < cur.B[0])
							new_B[0]++;
						else
							new_R[0]++;
						break;
					case 2:
						if(cur.R[1] < cur.B[1])
							new_B[1]++;
						else
							new_R[1]++;
						break;
					case 3:
						if(cur.R[0] > cur.B[0])
							new_B[0]--;
						else
							new_R[0]--;
						break;
					}
				}
				
				if(visit[new_R[0]][new_R[1]][new_B[0]][new_B[1]])
					continue;
				
				queue.add(new PinBall(new_R, new_B, cur.loop + 1));
				visit[new_R[0]][new_R[1]][new_B[0]][new_B[1]] = true;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new char[N][M];
		int[] R = new int[2];
		int[] B = new int[2];
		visit = new boolean[N][M][N][M];
		H = new int[2];
		min_tilt = Integer.MAX_VALUE;
		for(int y = 0; y < N; y++) {
			String temp = sc.next();
			matrix[y] = temp.toCharArray();
			
			for(int x = 0; x < matrix[y].length; x++) {
				if(matrix[y][x] == 'R') {
					matrix[y][x] = '.';
					R[0] = y;
					R[1] = x;
				}
				else if(matrix[y][x] == 'B') {
					matrix[y][x] = '.';
					B[0] = y;
					B[1] = x;
				}
				else if(matrix[y][x] == 'O') {
					H[0] = y;
					H[1] = x;
				}
			}
		}
		tilting(R, B);
		
		System.out.println(min_tilt > 10 ? -1 : min_tilt);
	}

}
