import java.util.Scanner;

/**
 * <pre>
 * 점심 시간에 산책을 다니는 사원들은 최근 날씨가 더워져, 사다리 게임을 통하여 누가 아이스크림을 구입할지 결정하기로 한다.

김 대리는 사다리타기에 참여하지 않는 대신 사다리를 그리기로 하였다.

사다리를 다 그리고 보니 김 대리는 어느 사다리를 고르면 X표시에 도착하게 되는지 궁금해졌다. 이를 구해보자.

아래 <그림 1>의 예를 살펴보면, 출발점 x=0 및 x=9인 세로 방향의 두 막대 사이에 임의의 개수의 막대들이 랜덤 간격으로 추가되고(이 예에서는 2개가 추가됨) 이 막대들 사이에 가로 방향의 선들이 또한 랜덤하게 연결된다.

X=0인 출발점에서 출발하는 사례에 대해서 화살표로 표시한 바와 같이, 아래 방향으로 진행하면서 좌우 방향으로 이동 가능한 통로가 나타나면 방향 전환을 하게 된다.

방향 전환 이후엔 다시 아래 방향으로만 이동하게 되며, 바닥에 도착하면 멈추게 된다.

문제의 X표시에 도착하려면 X=4인 출발점에서 출발해야 하므로 답은 4가 된다. 해당 경로는 별도로 표시하였다.
 


<그림 1> 사다리 게임에 대한 설명 (미니맵)

아래 <그림 2>와 같은 100 x 100 크기의 2차원 배열로 주어진 사다리에 대해서, 지정된 도착점에 대응되는 출발점 X를 반환하는 코드를 작성하라 (‘0’으로 채워진 평면상에 사다리는 연속된 ‘1’로 표현된다. 도착 지점은 '2'로 표현된다).
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190109_01 {

	static int[][] matrix;
	static boolean[][] visit;
	static int answer;
	static int[][] move = {{-1, 0}, {1, 0}, {0, 1}}; //좌 우 하
	
	static void searching(int start_x, int cur_x, int cur_y) {
		visit[cur_x][cur_y] = true;
		
		boolean ignore = false;
		
		for(int moving = 0; moving < 3; moving++) {
			int new_x = cur_x + move[moving][0];
			int new_y = cur_y + move[moving][1];

			if(new_x < 0 || new_x >= 100 || new_y < 0 || new_y >= 100)
				continue;
			
			if(matrix[new_x][new_y] == 0)
				continue;
			
			if(visit[new_x][new_y])
				continue;
			
			if(!ignore && (moving == 0 || moving == 1) && matrix[new_x][new_y] == 1)
				ignore = true;
			
			if(ignore && moving == 2)
				break;
			
			if(new_y == 99) {
				if(matrix[new_x][new_y] == 2) {
					answer = start_x;
					return;
				}
				else
					return;
			}
			
			searching(start_x, new_x, new_y);
		}
		visit[cur_x][cur_y] = false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int test = 1; test <= 10; test++) {
			int testCase = sc.nextInt();
			matrix = new int[100][100];
			visit = new boolean[100][100];
			
			answer = -1;
			
			for(int y = 0; y < 100; y++) {
				for(int x = 0; x < 100; x++) {
					matrix[x][y] = sc.nextInt();
				}
			}
			
			for(int x = 0; x < 100; x++) {
				if(matrix[x][0] == 0)
					continue;
				searching(x, x, 0);
			}
			
			System.out.println("#" + testCase + " " + answer);
		}
	}

}
