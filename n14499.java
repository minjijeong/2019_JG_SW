package other;
import java.util.Scanner;

public class n14499 {
	public static Scanner sc;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int repeat = sc.nextInt();
		int [] dice = new int [6];
		int [] [] map = new int[N][M];
		
		for(int i=0;i<N ; i++) {
			for(int j=0;j<M;j++) {
				map[i][j] =sc.nextInt();
			}
		}
		move(dice, map,x,y,repeat);
	}
	
	public static void move(int [] dice , int[][] map,int x, int y, int repeat) {
		
		for(int i=0;i<repeat;i++) {
			int command = sc.nextInt();
			try {
			switch (command) {   // 벗어나지않게 try catch로 묶어주면 될듯
			case 1:{ // 동쪽으로 이동
				//다이스 배열 위치변경해주고~
				if(map[x][++y] == 0) {
					map[x][y] = dice[2];
				}else {
					dice[2] = map[x][y];
					map[x][y] =0;
				}
				movedDice(dice, command);
				break;
			}
			case 2:{ // 서쪽으로 이동
				
				if(map[x][--y] == 0) {
					map[x][y] = dice[1];
				}else {
					dice[1] = map[x][y];
					map[x][y] =0;
				}
				movedDice(dice, command);
				break;
			}
			case 3:{ // 북쪽으로 이동
				
				if(map[--x][y] == 0) {
					map[x][y] = dice[3];
				}else {
					dice[3] = map[x][y];
					map[x][y] =0;
				}
				movedDice(dice, command);
				break;
			}
			case 4:{ // 남쪽으로 이동
				
				
				if(map[++x][y] == 0) {
					map[x][y] = dice[4];
				}else {
					dice[4] = map[x][y];
					map[x][y] =0;
				}
				movedDice(dice, command);
				break;
			}
			}
			System.out.println(dice[5]);
			}catch(Exception e) {
				if(x<0) x=0;
				if(y<0) y=0;
				if(x>map.length-1) x=map.length-1;
				if(y>map[0].length-1) y=map[0].length-1;
			}
		}
	}
	public static int [] movedDice(int [] dice,int command) {
		int temp;
		switch (command) { 
		case 1:{  //동쪽
			temp = dice[0];
			dice[0]=dice[2];
			dice[2]=dice[5];
			dice[5]=dice[1];
			dice[1]=temp;
			break;
		}
		case 2:{ // 서쪽으로 이동
			temp =dice[0];
			dice[0]=dice[1];
			dice[1]=dice[5];
			dice[5]=dice[2];
			dice[2]=temp;
			break;
		}
		case 3:{ // 북쪽으로 이동
			temp =dice[0];
			dice[0] = dice[3];
			dice[3] =dice[5];
			dice[5]=dice[4];
			dice[4]=temp;
			
			break;
		}
		case 4:{ // 남쪽으로 이동
			temp = dice[0];
			dice[0] = dice[4];
			dice[4]=dice[5];
			dice[5] = dice[3];
			dice[3]=temp;
			break;
		}
		}
		return dice;
	}
}

