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
	static int N, result;
	static int[][] matrix;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북동남서
	
	static void moving(int[] cur_shark) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(cur_shark);
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int[] fish = searchFish(cur);
			
			//먹을 수 있는 물고기가 없을 때
 			if(fish[0] == cur[0] && fish[1] == cur[1])
				break;
			else {
				int[] next = new int[4];
				next[0] = fish[0];
				next[1] = fish[1];

				matrix[next[0]][next[1]] = 0;
				cur[3]++;
				if(cur[3] == cur[2]) {
					cur[2]++;
					cur[3] = 0;
				}
				next[2] = cur[2];
				next[3] = cur[3];
				result += fish[2];
				queue.add(next);
			}
		}
		
		System.out.println(result);
	}
	
	static int[] searchFish(int[] current) {	
		Queue<int[]> node = new LinkedList<int[]>();
		int[] temp = new int[3];
		int min_distance = Integer.MAX_VALUE;
		boolean[][] visit = new boolean[N][N];
		temp[0] = current[0];
		temp[1] = current[1];
		temp[2] = 0;
		node.add(temp);
		
		while(!node.isEmpty()) {
			int[] cur = node.poll();
			//먹을 수 있는 물고기
			if(matrix[cur[0]][cur[1]] < current[2] && matrix[cur[0]][cur[1]] > 0) {
				//최단 거리인 물고기면 갱신
				if(min_distance > cur[2]) {
					temp[0] = cur[0];
					temp[1] = cur[1];
					temp[2] = cur[2];
					min_distance = cur[2];
				}
				else if(min_distance == cur[2]) {
					//거리는 같은데 새로운 좌표가 y값이 더 작을 때
					if(temp[0] > cur[0]) {
						temp[0] = cur[0];
						temp[1] = cur[1];
						temp[2] = cur[2];
					}
					//거리 같고 y좌표도 같다면 x좌표 낮은 걸 보기
					else if(temp[0] == cur[0]) {
						if(temp[1] > cur[1]) {
							temp[0] = cur[0];
							temp[1] = cur[1];
							temp[2] = cur[2];
						}
					}
				}
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
		return temp;
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
