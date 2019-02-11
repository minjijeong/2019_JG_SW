package study_hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 메모리 초과... 
 * 모든 경우의 수 호출
 */
public class bj_15683_190210 {
	static int M, N, ans = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<Node> list = new ArrayList<Node>();
	static int[][] temp;
	static int[] output;
	static int count;
	static int result;
	static int size;
 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = (br.readLine()).split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		temp = new int[N][M];
		count = 0;
		
		// 지도 입력
		for(int i = 0; i < N; i++ ) {
			str = (br.readLine()).split(" "); 
			for(int j=0; j< M;j++) {
				map[i][j] = Integer.parseInt(str[j]);
				temp[i][j] = map[i][j];
				if(0 < map[i][j] &&  map[i][j] < 6) {
					list.add(new Node(i, j));
				}
			}
		}
		
		size = list.size();
		output = new int[size];
		
		// cctv 미존재
		if(size == 0) {
			check();
			result = count;
		}
		// cctv 존재
		else {
			for(int i = 0; i < 4; i++) {
				// cctv 방향의 경우의 수 
				output[0] = i+1;
				allCase(i,0);
			}
			
		}
		System.out.println(ans);
	
	}
	
	public static void allCase(int start, int depth) {
		// TODO Auto-generated method stub
		if(depth == size -1) {
			for(int i = 0; i < size; i++) {
				Node nd = list.get(i);
				Watch(nd, map[nd.x][nd.y], output[i]);
			}
			check();
			ans = Math.min(ans, count);
			Reset();
			return;
		}
		
		// 경우의 수 생성
		for(int i = 0; i < 4; i++) {
			output[depth+1] = i+1;
			allCase(i, depth+1);
		}
	}
	// cctv 종류와 방향에 따라 위치 지정
	public static void Watch(Node nd, int type, int dir) {
		if(type == 1) {
			if(dir == 1) {
				Move(nd,1);
			}
			else if(dir == 2) {
				Move(nd,2);
			}
			else if(dir == 3) {
				Move(nd,3);
			}
			else if(dir == 4) {
				Move(nd,4);
			}
		}
		else if(type == 2) {
			if(dir == 1) {
				Move(nd,1);
				Move(nd,3);
			}
			else if(dir == 2) {
				Move(nd,2);
				Move(nd,4);
			}
			else if(dir == 3) {
				Move(nd,3);
				Move(nd,1);
			}
			else if(dir == 4) {
				Move(nd,4);
				Move(nd,2);
			}			
		}
		else if(type == 3) {
			if(dir == 1) {
				Move(nd,1);
				Move(nd,2);
			}
			else if(dir == 2) {
				Move(nd,2);
				Move(nd,3);
			}
			else if(dir == 3) {
				Move(nd,3);
				Move(nd,4);
			}
			else if(dir == 4) {
				Move(nd,4);
				Move(nd,1);
			}			
		}
		else if(type == 4) {
			if(dir == 1) {
				Move(nd,1);
				Move(nd,2);
				Move(nd,3);
			}
			else if(dir == 2) {
				Move(nd,2);
				Move(nd,3);
				Move(nd,4);
			}
			else if(dir == 3) {
				Move(nd,3);
				Move(nd,4);
				Move(nd,5);
			}
			else if(dir == 4) {
				Move(nd,4);
				Move(nd,1);
				Move(nd,2);
			}
		}
		else if(type == 5) {
			Move(nd,1);
			Move(nd,2);
			Move(nd,3);			
			Move(nd,4);			
		}
	}
	
	// 확산
	public static void Move(Node nd, int dir) {
		int currentX = nd.x;
		int currentY = nd.y;
		int nextX = currentX;
		int nextY = currentY;
		
		if(dir == 1) {
			nextX = currentX - 1; 
			nextY = currentY;
		}
		else if(dir == 2) {
			nextX = currentX; 
			nextY = currentY + 1;
		}
		else if(dir == 3) {
			nextX = currentX + 1; 
			nextY = currentY;
		}
		else if(dir == 4) {
			nextX = currentX; 
			nextY = currentY - 1;
		}
		// 범위초과
		if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M ) {
			return;
		}
		// 벽일때 중지
		if(map[nextX][nextY] == 6) {
			return;
		}
		
		// 그외 방문처리
		if(map[nextX][nextY] == 0) {
			map[nextX][nextY] = 1;
		}	
		// 다음 좌표로 이동
		Move(new Node(nextX, nextY), dir);
	}
	
	public static void check() {
		count = 0; 
		for(int i = 0; i < N;i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					count++;
				}
			}
		}		
	}
	
	public static void Reset() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}
	
		
	public static class Node {
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x; 
			this.y = y; 
		}
		
	}
//	public static void print(int[][] tmp) {
//		System.out.println("************************");
//		for(int i = 0; i < N;i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(tmp[i][j]);
//			}
//			System.out.println("");
//		}
//		System.out.println("************************");
//	}
}
