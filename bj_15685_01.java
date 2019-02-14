package study_hw;

import java.util.*;

public class bj_15685_01 {
	static ArrayList<Node> list;
	static ArrayList<Node> nlist;
	static int[][] map = new int[100][100];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	static int ans;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int xx,yy,dd,gg;
		list = new ArrayList<Node>();

		for(int i = 0; i <N; i++) {
			xx = sc.nextInt();
			yy = sc.nextInt();
			dd = sc.nextInt();
			gg = sc.nextInt();
			list.add(new Node(xx,yy,dd,gg));
		}
		
		// 1번항목 부터 검색 시작
		solve(0);
		System.out.println(ans);
		
		// 소켓종료
		sc.close();
		
	}
	public static void solve(int i) { //, int x, int y, int d, int g
		Node tmp = list.get(i);
		// 드래곤 커브 미존재 
		if(i == list.size()) {
			return;
		}
		// 드래곤 커브 존재시
		else {
			dfs(i,0);
			// 다음 커브 진행
			solve(i+1);
		}
	}
	// 세대 증가
	public static void dfs(int idx,int curGen) {
		Node tmp = list.get(idx);
		// 세대 증가 끝!
		if(curGen == tmp.g) {
			return;
		}
		// 해당 세대 까지 그리기 
		draw(tmp.x, tmp.y, tmp.d, curGen);						
		dfs(idx, curGen+1);
	}
	// 
	public static void draw(int x,int y, int d, int g) {
		Node tmp = new Node(x,y,d,g);
		nlist = new ArrayList();
		
		if(g > 0) {
			for(int i = 0; i < nlist.size();i++) {
				
			}
		}
	}
	public ArrayList<Node> lineDraw(int x,int y, int d, int g) {
		int curX = x;
		int curY = y; 
		int curD = d;
		int nextX = curX; 
		int nextY = curY;
		int nextD = (curD+2)% 4;
		ArrayList<Node> nlist = new ArrayList();
		
		
		if(curX < 100 && 0 < curX && curY < 100 && 0 < curY  ) {
			map[curX][curY] = 1;
			nextX = curX + dx[nextD];
			nextY = curY + dy[nextD];
			
			if(nextX < 100 && 0 < nextX && nextY < 100 && 0 < nextY  ) {
				map[nextX][nextY] = 1;
			}
			nlist.add(nlist.size() -1,new Node(nextX,nextY,nextD,g));
		}
		return nlist; 
	}
	// 방향
	public static int[] direction(int d) {
		int[] dir = new int[2];
		dir[0] = d;
		dir[1] = (d+2)%4;
		return dir;
	}
	
	// 회전 
	public static void turning(int dd, int[][] map) {
		
	}
	
	// 정사각형 개수 구하기 
	public int count() {
//		int[][] map
		return 0;
	}
	
	static class Node{
		int x;
		int y;
		int d; 
		int g;
		public Node(int x,int y,int d,int g){
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}
}
