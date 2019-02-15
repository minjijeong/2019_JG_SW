import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/15686
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190215_02 {
	static int[][] c_shop, house;
	static int N, M, min_distance, c_num, h_num;
	static boolean[] c_closed;

	static int getDistance() {
		int total = 0;
		
		//각 집(h)에서 각 치킨집(c)까지의 y좌표 x좌표 거리를 절대값으로 계산
		//계산 값 중 최소값을 total에 더하기
		for(int h = 0; h < h_num; h++) {
			int h_distance = Integer.MAX_VALUE;
			for(int c = 0; c < c_num; c++) {
				if(c_closed[c])
					continue;
				
				int temp = Math.abs(house[h][0] - c_shop[c][0]) + Math.abs(house[h][1] - c_shop[c][1]);
				h_distance = Math.min(h_distance, temp);
				
			}
			total += h_distance;
		}
		
		return total;
	}
	
	static void closeShop(int c_idx, int c_remain) {
		if(c_remain == M) {
			min_distance = Math.min(min_distance, getDistance());
			return;
		}
		
		//i번째 치킨집 문닫고 재귀호출
		//i번째 치킨집 문안닫는다면 false로 복구해놓고 다시 반복문
		for(int i = c_idx; i < c_num; i++) {
			c_closed[i] = true;
			closeShop(i + 1, c_remain - 1);
			c_closed[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		min_distance = Integer.MAX_VALUE;
		c_shop = new int[13][2];
		house = new int[2 * N][2];
		c_closed = new boolean[13];
		h_num = 0;
		c_num = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				int temp = sc.nextInt();
				
				if(temp == 1) {
					house[h_num][0] = y;
					house[h_num][1] = x;
					h_num++;
				}
				else if(temp == 2) {
					c_shop[c_num][0] = y;
					c_shop[c_num][1] = x;
					c_num++;
				}
			}
		}
		closeShop(0, c_num);
		
		System.out.println(min_distance);
	}

}
