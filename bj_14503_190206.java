package study_hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_14503_190206 {
	static int[][] map = new int[50][50];
	static int[] dx = { 0, 1, 0, -1 }; // �� �� �� ��
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] visited = new boolean[50][50];
	static Robot start;
	static int m, n, ans;
	  
	public static void bfs() {
	    Queue<Robot> q = new LinkedList<>();
	    q.add(start);
	 
	    while (!q.isEmpty()) {
	        Robot pos = q.poll();
	        int d = pos.d;
	        int y = pos.r;
	        int x = pos.c;
	 
	        int nextDirection = d;
	        int nx = 0;
	        int ny = 0;
	        boolean flag = false;
	 
	        // ���� ���⿡ û������ ���� ���� Ž��
	        for (int i = 0; i < 4; i++) {
	            nextDirection = turnDirection(nextDirection);
	            nx = dx[nextDirection] + x;
	            ny = dy[nextDirection] + y;
	            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
	                if (map[ny][nx] == 0 && !visited[ny][nx]) {
	                    visited[ny][nx] = true;
	                    ++ans;
	                    q.add(new Robot(ny, nx, nextDirection));
	                    flag = true;
	    	            System.out.println(q.peek());
	                    break;
	                }
	            }

	        }
	 
	        // ����
	        if (!flag) {
	            nextDirection = backDirection(d);
	            nx = dx[nextDirection] + x;
	            ny = dy[nextDirection] + y;
	 
	            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
	                if (map[ny][nx] == 0) {
	                    q.add(new Robot(ny, nx, d));
	                }
	            }
	        }
	 
	    }
	}
	 
	public static int backDirection(int d) {
	    if (d == 0) {
	        return 2;
	    } else if (d == 1) {
	        return 3;
	    } else if (d == 2) {
	        return 0;
	    } else {
	        return 1;
	    }
	}
	 
	public static int turnDirection(int d) {
	    // 0 ��, 1 ��, 2 ��, 3 ��
	    if (d == 0) {
	        return 3;
	    } else if (d == 1) {
	        return 0;
	    } else if (d == 2) {
	        return 1;
	    } else {
	        return 2;
	    }
	}
	 
	public static class Robot {
	    int r;
	    int c;
	    int d;
	 
	    Robot(int r, int c, int d) {
	        this.r = r;
	        this.c = c;
	        this.d = d;
	    }
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// ���� �Է�
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
				
		for(int i = 0;i < m;i++) {
			System.out.println(i);
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	 
	    start = new Robot(s, e, d);
	    visited[s][e] = true;
	 
	    bfs();
	    System.out.println(ans + 1);
		
	}
}
//https://mygumi.tistory.com/240
