import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/14503
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190208_01 {
	static int N, M, clean;
	static int[][] matrix;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북 동 남 서
	static Queue<int[]> queue;
	
	static void moving(int[] first) {
		queue.add(first);
		boolean doClean = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(doClean) {
				matrix[cur[0]][cur[1]] = 2;
				clean++;
				//System.out.println("[" + cur[0] + "][" + cur[1] + "]: 청소");
			}
			
			int new_d = cur[2];
			int new_y = cur[0];
			int new_x = cur[1];
			
			int back_y = cur[0];
			int back_x = cur[1];
			
			int loop = 0;
			boolean cleanable = false;
			while(loop < 4) {
				new_d = getDirection(new_d, false);
				new_y = cur[0] + move[new_d][0];
				new_x = cur[1] + move[new_d][1];
				
				if(new_y >= N || new_y < 0 || new_x >= M || new_x < 0)
					continue;
				
				if(matrix[new_y][new_x] == 0) {
					cleanable = true;
					break;
				}
				
				loop++;
			}
			int[] new_arr = new int[3];
			if(!cleanable) {
				int back_d = getDirection(cur[2], true);
				back_y = cur[0] + move[back_d][0];
				back_x = cur[1] + move[back_d][1];
				
				if(new_y >= N || new_y < 0 || new_x >= M || new_x < 0)
					return;
				
				if(matrix[back_y][back_x] == 1)
					return;
				
				new_arr[0] = back_y;
				new_arr[1] = back_x;
				new_arr[2] = cur[2];
				queue.add(new_arr);
				doClean = false;
				continue;
			}
			new_arr[0] = new_y;
			new_arr[1] = new_x;
			new_arr[2] = new_d;
			queue.add(new_arr);
			doClean = true;
		}
	}
	
	/**
	 * 
	 * @param cur_dir
	 * @param mode false: 단순 왼쪽 리턴 true: 후진 방향 리턴
	 * @return
	 */
	static int getDirection(int cur_dir, boolean mode) {
		int new_dir = -1;
		
		if(mode) {
			switch(cur_dir) {
			case 0:
				new_dir = 2;
				break;
			case 1:
				new_dir = 3;
				break;
			case 2:
				new_dir = 0;
				break;
			case 3:
				new_dir = 1;
				break;
			}
		}
		else {
			switch(cur_dir) {
			case 0:
				new_dir = 3;
				break;
			case 1:
				new_dir = 0;
				break;
			case 2:
				new_dir = 1;
				break;
			case 3:
				new_dir = 2;
				break;
			}
		}
		
		return new_dir;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		clean = 0;
		
		int[] first = new int[3];
		first[0] = sc.nextInt();
		first[1] = sc.nextInt();
		first[2] = sc.nextInt();
		
		matrix = new int[N][M];
		queue = new LinkedList<int[]>();
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				matrix[y][x] = sc.nextInt();
			}
		}
		moving(first);
		System.out.println(clean);
	}

}
