import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/16235
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190222_01 {
	static int N, M, K, result;
	static int[][] range = {{-1, -1}, {-1, 0}, {-1, 1},
									{0, -1}, {0, 1},
									{1, -1}, {1, 0}, {1, 1}};
	static Tree[][] trees;
	
	static class Tree{
		ArrayList<Integer> tree;
		ArrayList<Integer> die;
		int curFood;
		int addFood;
		
		public Tree() {
			this.tree = new ArrayList<Integer>();
			this.die = new ArrayList<Integer>();
			this.curFood = 5;
		}
		
		public void addTree(int age) {
			tree.add(age);
		}
	}
	
	static void running() {
		int years = 0;
		while(years < K) {
			for(int season = 0; season < 4; season++) {
				for(int y = 0; y < N; y++) {
					for(int x = 0; x < N; x++) {
						switch(season) {
						case 0:
							Collections.sort(trees[y][x].tree);
							ArrayList<Integer> newTree = new ArrayList<Integer>();
							for(int i = 0; i < trees[y][x].tree.size(); i++) {
								if(trees[y][x].curFood >= trees[y][x].tree.get(i)) {
									trees[y][x].curFood -= trees[y][x].tree.get(i);
									newTree.add(trees[y][x].tree.get(i) + 1);
									//System.out.println(years + " - 나이[" + y + "][" + x + "][" + i + "]: " + trees[y][x].tree.get(i) + " - 양분: " + trees[y][x].curFood);
								}
								else {
									//System.out.println(years + " - 죽음[" + y + "][" + x + "][" + i + "]: " + trees[y][x].tree.get(i) + " - 양분: " + trees[y][x].curFood);
									trees[y][x].die.add(trees[y][x].tree.get(i));
								}
							}
							trees[y][x].tree = new ArrayList<Integer>();
							for(int i = 0; i < newTree.size(); i++) {
								trees[y][x].tree.add(newTree.get(i));
							}
							
							break;
						case 1:
							for(int i = 0; i < trees[y][x].die.size(); i++) {
								trees[y][x].curFood += (int)Math.floor(((double)trees[y][x].die.get(i) / 2.0));
							}
							trees[y][x].die = new ArrayList<Integer>();
							break;
						case 2:
							for(int i = 0; i < trees[y][x].tree.size(); i++) {
								if(trees[y][x].tree.get(i) % 5 == 0) {
									for(int j = 0; j < 8; j++) {
										int new_y = y + range[j][0];
										int new_x = x + range[j][1];
										
										if(new_y >= N || new_x >= N || new_y < 0 || new_x < 0)
											continue;
										
										trees[new_y][new_x].tree.add(1);
									}
								}
							}
							break;
						case 3:
							trees[y][x].curFood += trees[y][x].addFood;
							break;
						}
					}
				}
			}
			years++;
		}
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				result += trees[y][x].tree.size();
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		trees = new Tree[N][N];
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				trees[y][x] = new Tree();
				trees[y][x].addFood = sc.nextInt();
			}
		}
		
		for(int i = 0; i < M; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			trees[y - 1][x - 1].addTree(sc.nextInt());
		}
		
		running();
		
		System.out.println(result);
	}
}