import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/16236
 * @author rlaau
 * <pre>
 */
public class mwkim_20190222_02 {
	static int N, result, fish_num;
	static int[][] matrix;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북동남서
	static boolean[][] visit;
	static int[][] fishes;
	
	static void moving(int[] cur_shark) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(cur_shark);
		int loop = 0;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			fish_num = 0;
			fishes = new int[N][N];
			visit = new boolean[N][N];
			searchFish(cur);
			
			//System.out.println(loop + " - [" + cur[0] + "][" + cur[1] + "]: " + cur[2] + ", " + cur[3] + " | " + result + ": " + fish_num);
			if(fish_num == 0)
				break;
			else {
				int min_distance = Integer.MAX_VALUE;
				int[] next = new int[4];
				next[0] = Integer.MAX_VALUE;
				next[1] = Integer.MAX_VALUE;
				
				for(int y = 0; y < N; y++) {
					for(int x = 0; x < N; x++) {
						if(fishes[y][x] == 0)
							continue;
						
						if(fishes[y][x] < min_distance) {	
							next[0] = y;
							next[1] = x;
							min_distance = fishes[y][x];
						}
						else if(fishes[y][x] == min_distance) {
							if(y < next[0]) {
								next[0] = y;
								next[1] = x;
								min_distance = fishes[y][x];
							}
							else if(y == next[0]) {
								if(x < next[1]) {
									next[0] = y;
									next[1] = x;
									min_distance = fishes[y][x];
								}
							}
						}
					}
				}
				matrix[next[0]][next[1]] = 0;
				cur[3]++;
				if(cur[3] == cur[2]) {
					cur[2]++;
					cur[3] = 0;
				}
				next[2] = cur[2];
				next[3] = cur[3];
				result += min_distance;
				queue.add(next);
				loop++;
			}
		}
		
		System.out.println(result);
	}
	
	static void searchFish(int[] current) {	
		Queue<int[]> node = new LinkedList<int[]>();
		int[] temp = new int[3];
		temp[0] = current[0];
		temp[1] = current[1];
		temp[2] = 0;
		node.add(temp);
		
		while(!node.isEmpty()) {
			int[] cur = node.poll();
			if(matrix[cur[0]][cur[1]] < current[2] && matrix[cur[0]][cur[1]] > 0) {
				if(fishes[cur[0]][cur[1]] == 0) {
					fishes[cur[0]][cur[1]] = cur[2];
					fish_num++;
				}
				else
					fishes[cur[0]][cur[1]] = Math.min(fishes[cur[0]][cur[1]], cur[2]);
			}
			
			for(int i = 0; i < 4; i++) {
				int[] next = new int[3];
				next[0] = cur[0] + move[i][0];
				next[1] = cur[1] + move[i][1];
				
				if(next[0] >= N || next[1] >= N || next[0] < 0 || next[1] < 0)
					continue;
				
				if(matrix[next[0]][next[1]] > current[2])
					continue;
				
				if(visit[next[0]][next[1]])
					continue;
				
				next[2] = cur[2] + 1;
				visit[next[0]][next[1]] = true;
				node.add(next);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		matrix = new int[N][N];
		int[] b_shark = new int[4]; //y좌표, x좌표, 나이, 먹은 물고기
		result = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				matrix[y][x] = sc.nextInt();
				
				if(matrix[y][x] == 9) {
					matrix[y][x] = 0;
					b_shark[0] = y;
					b_shark[1] = x;
					b_shark[2] = 2;
					b_shark[3] = 0;
				}
			}
		}
		
		moving(b_shark);
	}

}
