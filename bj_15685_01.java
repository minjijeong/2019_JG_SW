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
		
		// 1���׸� ���� �˻� ����
		solve(0);
		System.out.println(ans);
		
		// ��������
		sc.close();
		
	}
	public static void solve(int i) { //, int x, int y, int d, int g
		Node tmp = list.get(i);
		// �巡�� Ŀ�� ������ 
		if(i == list.size()) {
			return;
		}
		// �巡�� Ŀ�� �����
		else {
			dfs(i,0);
			// ���� Ŀ�� ����
			solve(i+1);
		}
	}
	// ���� ����
	public static void dfs(int idx,int curGen) {
		Node tmp = list.get(idx);
		// ���� ���� ��!
		if(curGen == tmp.g) {
			return;
		}
		// �ش� ���� ���� �׸��� 
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
	// ����
	public static int[] direction(int d) {
		int[] dir = new int[2];
		dir[0] = d;
		dir[1] = (d+2)%4;
		return dir;
	}
	
	// ȸ�� 
	public static void turning(int dd, int[][] map) {
		
	}
	
	// ���簢�� ���� ���ϱ� 
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
