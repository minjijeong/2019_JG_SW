package study_hw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class bj_16235_01 {
	static int[][] A;
	static int[][] CurMap;
	static List<Tree> treeMap = new ArrayList<Tree>();;
	static int N,M,K;
	static int year=0;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static int treeCnt = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		year = K;
		
		A = new int[N+1][N+1];
		CurMap = new int[N+1][N+1];
		
		for(int i=1;i < N+1;i++) {
			for(int j=1;j<N+1;j++) {
				A[i][j] = sc.nextInt();
				CurMap[i][j] = 5;
			}
		}
		
		for(int i=1; i<M+1;i++) {
			int tmpY = sc.nextInt();
			int tmpX = sc.nextInt();
			int tmpZ = sc.nextInt();

			treeMap.add(new Tree(tmpY,tmpX,tmpZ));
			treeCnt++;
		}
		for(int k=1;k<=K;k++) {
			for(int i=treeMap.size()-1; i >= 0;i--) {
				Tree tmp = treeMap.get(i);
				System.out.println(k+" year "+i+"th "+"###x :"+tmp.x+"### y :"+tmp.y+"### age : "+tmp.age);

				if(CurMap[tmp.y][tmp.x] >= tmp.age) {
					spring(tmp,i);
					fall(tmp);
				}
				else {
					summer(tmp,i);
				}
				print();
			}
			winter();			
			System.out.println("########################");
			print();
			System.out.println("########################"+treeCnt);

		}
		
		System.out.println(treeCnt+"=>>>>>>>>>>>>"+treeMap.size());
		
	}

	public static void spring(Tree tmp,int i) {		
		System.out.println("######"+1);
		System.out.println(CurMap[tmp.y][tmp.x]);
		CurMap[tmp.y][tmp.x] -= tmp.age;
		tmp.age += 1;
		treeMap.set(i, new Tree(tmp.y,tmp.x,tmp.age));
		System.out.println(CurMap[tmp.y][tmp.x]);
	}
	public static void summer(Tree tmp,int i) {
		System.out.println("######"+2);
		System.out.println(CurMap[tmp.y][tmp.x]);					
		CurMap[tmp.y][tmp.x] += Math.floorDiv(tmp.age,2);
		System.out.println(CurMap[tmp.y][tmp.x]);
		treeMap.remove(i);
		treeCnt--;		
	}
	public static void winter() {
		// 겨울
		for(int h=1;h < N+1;h++) {
			for(int j=1;j<N+1;j++) {
				CurMap[h][j] += A[h][j];
			}
		}
	}

	public static void fall(Tree tmp) {
	    // 가을에는 나무가 번식한다. 나이가 5의 배수인 나무의
	    // 인접 8칸에 나이가 1인 나무가 생긴다.				
		if(tmp.age%5==0) {
			// 나무 추가 
			System.out.println("######"+3);
			for(int m=0;m<dx.length;m++) {
				if(tmp.x+dx[m] <= N  && tmp.x+dx[m] > 0 && tmp.y+dy[m] <= N && tmp.y+dy[m] > 0) {
					System.out.println((tmp.x+dx[m])+","+(tmp.y+dy[m]));
					treeMap.add(new Tree((tmp.y+dy[m]),(tmp.x+dx[m]),1));		
					treeCnt++;
					System.out.println("treeCnt :"+treeCnt);
				}
			}
		}
	}
	static void print(){
		int[][] tmp = CurMap;		
		for(int i=1;i<N+1;i++) {
			System.out.print(i);
			for(int j=1;j<N+1;j++) {
				System.out.print(" "+tmp[i][j]);
			}
			System.out.println("");
		}
	}
	
	static class Tree{
		int x;
		int y;
		int age;
		public Tree(int y, int x, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

}
