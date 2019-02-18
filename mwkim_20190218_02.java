import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/16234
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190218_02 {
	static int N, L, R, open_num;
	static int[][] state;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북동남서
	
	//state(0, 0)부터 시작해서 차례대로 While문을 통해 연합을 만듦
	//연합이 존재 (united_info[united_num][0] > 0)하면 연합번호(united_num) 증가
	//각 스테이트에서 연합이 여러개 존재할 수 있으므로 한번에 연합만들고 한번에 state에 값 갱신
	static int getUnited() {
		Queue<int[]> queue = new LinkedList<int[]>();
		int[][] united_info = new int[N * N][2];
		int[][] united_map = new int[N][N];
		int united_num = 0;
		boolean[][] checked = new boolean[N][N];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(checked[y][x])
					continue;
				
				int[] temp = new int[2];
				temp[0] = y;
				temp[1] = x;
				queue.add(temp);
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					for(int i = 0; i < 4; i++) {
						int[] next = new int[2];
						next[0] = cur[0] + move[i][0];
						next[1] = cur[1] + move[i][1];
						
						if(next[0] >= N || next[1] >= N || next[0] < 0 || next[1] < 0)
							continue;
						
						if(Math.abs(state[cur[0]][cur[1]] - state[next[0]][next[1]]) < L)
							continue;
						
						if(Math.abs(state[cur[0]][cur[1]] - state[next[0]][next[1]]) > R)
							continue;
						
						if(!checked[next[0]][next[1]]) {
							united_info[united_num][0]++;
							united_info[united_num][1] += state[next[0]][next[1]];
							united_map[next[0]][next[1]] = united_num + 1;
							checked[next[0]][next[1]] = true;
							queue.add(next);
						}
					}
				}
				if(united_info[united_num][0] > 0) {
					united_map[y][x] = united_num + 1;
					united_num++;
				}
			}
		}
		if(united_num > 0) {
			for(int i = 0; i < united_num; i++) {
				united_info[i][1] = (int)Math.floor((double)united_info[i][1] / (double)united_info[i][0]);
				for(int y = 0; y < N; y++) {
					for(int x = 0; x < N; x++) {
						if(united_map[y][x] == i + 1) {
							state[y][x] = united_info[i][1];
						}
					}
				}
			}
		}		
		
		return united_num;
	}
	
	static void open() {
		while(true) {
			int united_num = getUnited();
			if(united_num > 0) {
				open_num++;
			}
			else
				break;
			
			if(open_num == 2000)
				break;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		state = new int[N][N];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				state[y][x] = sc.nextInt();
			}
		}
		open();
		System.out.println(open_num);
	}

}
