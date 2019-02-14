package study_hw;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class bj_15684_01 {
	static int col,row,H;
	static int addLine = 0; // 추가라인
	static int[][] map;
	static int preX,preY;
	static int playPreX=-1,playPreY=-1;
	static int rtn = -1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc  = new Scanner(System.in);
		int i = 0;
		while(sc.hasNextLine()) {
			System.out.println(i);
			String str = sc.nextLine();
			if(str.equals("")||str == null) {
				break;
			}
			
			String[] tmp = (str).split(" ");
			if(i==0) {
				col = Integer.parseInt(tmp[0]);
				row = Integer.parseInt(tmp[1]);
				H = Integer.parseInt(tmp[2]);		
				map = new int[row][col];
			}
			else {
				int y = Integer.parseInt(tmp[0]);
				int x = Integer.parseInt(tmp[1]);
				System.out.println((x)+"////"+(y));
				System.out.println((x-1)+"////"+(y-1));
				map[y-1][x-1] = 1;
				System.out.println(map[y-1][x-1]);
			}
			i++;
		}
		System.out.println("데이터입력끝!");
		for(int m=0;m<row;m++) {
//			System.out.print(m);
			for(int j=0;j<col;j++) {
				System.out.print(" "+map[m][j]);
			}
			System.out.println("");
		}
		System.out.println("////////////////////////////////////////////////////");
		if(vaildCheck(col,row,H)) {	
//			if(i >= H) {
//				System.out.println("33333");
//				addLine = -1;
//			}
//			else {
				System.out.println("44444");
		
				System.out.println("시작");
				solve(0,0,0,false);
//			}
	
		}
		else {
			addLine = -1;
		}	
		System.out.println(addLine);
		sc.close();
		
	}
	// 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
	public static void solve(int idx, int curCol,int curRow, boolean lineFg) {
		System.out.println("==> idx :"+idx+" ==> curCol :"+curCol+" ==>curRow:"+curRow+" ==>col:"+col+" ==>row:"+row);

		int[][] tmp = map;		
		for(int i=0;i<row;i++) {
			System.out.print(i);
			for(int j=0;j<col;j++) {
				System.out.print(" "+map[i][j]);
			}
			System.out.println("");
		}
		
//		int m =  play(tmp,curCol,curRow);
//		System.out.println(m);
		// 사다리 최상단까지 올라와서도 결과없음
		//  (1 ≤ a ≤ H, 1 ≤ b ≤ N-1) . H개수보다 각 세로선가이 가질수 있는 가로선 개수는 작아야 한다.

		rtn = -1;
		// 사다리 게임 종료
		if(idx >= col) {
			System.out.println("#22222");
			
			if(addLine > 3)
				addLine = -1;
			return;				
		}
		else {
			System.out.println("#33333");
			if(curRow < 0 || curCol < 0 || curCol == col|| (curRow == row && curRow != 0)) {
				System.out.println("#11111");
				addLine = -1;
				return;				
			}
			if(lineFg) {
				tmp[curRow][curCol] = 1;
				System.out.println("#라인추가시도");
			}
			
			int res = play(tmp,curCol,curRow);
			// k 번제 사다리 타기 시작 
			// 다음번호로 이동 
			System.out.println("==> idx :"+idx+" ==> res :"+res);
			if(res == idx) {
				System.out.println("#44444");
				if(lineFg) {
					//idx 성공!
					map[curRow][curCol] = 1;
					addLine++;		
					System.out.println("#라인추가완료");
				}
				solve(idx+1,idx+1,0,false);	
			}
			// 사다리 수정해야돼!
			else {
				if(curRow == 0) {
					System.out.println("#55555");
					// 오른쪽확인
					// 오른쪽에 선그을 수 없음
					if(curCol+1 >= col){
						preX = -1;
						preY = -1;
						System.out.println("#col over 오른손을 추가됨 ");
						solve(idx,curCol,curRow+1,true);	
					}
					// 오른쪽에 선그을 수 있음
					else {
						System.out.println(curCol + "////"+col);
						// 선을 그을 수 없는 경우 
						if(preX == (curCol+1)||tmp[curRow][curCol+1] == 1 ) {
							System.out.println("#오른손을 선그을 수 있음 ");
							preX = curCol;
							preY = curRow;
							solve(idx,curCol,curRow+1,false);
						}
						// 오른쪽에 선그을 수 없음
						else {
							System.out.println("#오른쪽에 선그을 수 없음");
							if(tmp[curRow][curCol]==1) {
								solve(idx,curCol+1,curRow,false);														
							}
							else {
								solve(idx,curCol+1,curRow,true);
							}
						}						
					}
				}
				else {
					// 왼쪽확인
					// 왼쪽에 선그을 수 없음
					if(curCol-1 < 0 || curCol-1 >= col){
						preX = -1;
						preY = -1;
						solve(idx,curCol,curRow+1,false);
					}
					else{
						if(preX == (curCol-1)||tmp[curRow][curCol-1] == 1 ) {
							System.out.println("#왼손을 추가안됨 ");
							// 오른쪽확인
							if(curCol+1 != col) {
								if(tmp[curRow][curCol+1] != 1) {
									System.out.println("#오른손을 추가?? ");
									preX = curCol;
									preY = curRow;
									solve(idx,curCol+1,curRow,true);						
								}
								// 오른쪽에 선그을 수 없음
								else {
									System.out.println("#오른손을 추가안됨!! 그냥 패스 ");
									preX = -1;
									preY = -1;
									solve(idx,curCol,curRow+1,false);
								}																				
							}
							// 오른쪽에 선그을 수 없음
							else {
								preX = -1;
								preY = -1;
								solve(idx,curCol,curRow+1,false);
							}
						}
						// 왼손추가될수있음
						else {
							System.out.println("#왼손추가될수있음 ");
							preX = curCol;
							preY = curRow;						
							solve(idx,curCol-1,curRow,true);									
						}
					}	

				}
			}

		}	
	}
//	System.out.println("수정해!");
//	//종료 col번째 선으로 가서 경우의 수 구하기
//	if(curCol > 0) {
//		if(map[curCol-1][curRow]==1) {
//			System.out.println("#66666");
//
//			solve(idx,curCol-1,curRow);
//		}
//		else {
//
//			if(curCol-2 > 0 && curCol+1 < col) {
//				System.out.println("#77777");
//
//				tmp[curCol-2][curRow] = 1;
//				if(play(tmp,curCol-2,curRow)==idx) {
//					System.out.println("#888888");
//
//					solve(idx,curCol-2,curRow);
//				}
//				else {
//					tmp = map;
//				}
//				
//				tmp[curCol+1][curRow] = 1;
//				if(play(map,curCol+1,curRow)==idx) {
//					System.out.println("#99999");
//
//					solve(idx,curCol+1,curRow);
//				}
//				else {
//					tmp = map;
//				}
//			}
//			System.out.println("#0000000");
//			solve(idx,curCol,curRow-1);
//		}
//	}
//}	
//	public static int check(int direction,int idx, int curCol,int curRow, boolean lineFg)  {
//		int[][] tmp = map;
//		
//		// 왼쪽
//		if(direction == 0) {
//			
//		}
//		// 오른쪽
//		else if(direction == 1) {
//			if(tmp[curCol+1][curRow] != 1) {
//				tmp[curCol][curRow] = 1;
//				play(tmp,curCol,curRow);
//			}
//				solve(idx,curCol+1,curRow,true);						
//			}
//			// 오른쪽에 선그을 수 없음
//			else {
//				preX = curCol;
//				preY = curRow;
//				solve(idx,curCol,curRow+1,false);
//			}			
//		}
//		
//		if(lineFg) {
//			tmp[curCol][curRow] = 1;
//		}
//		int res = play(tmp,curCol,curRow);	
//		if(res == idx) {
//			System.out.println("#44444");
//			if(lineFg) {
//				//idx 성공!
//				map[curCol][curRow] = 1;
//				addLine++;		
//			}
//			solve(idx+1,idx+1,0,false);	
//		}
//		// 사다리 수정해야돼!
//		else {
//			if(curRow == 0) {
//				System.out.println("#55555");
//				// 오른쪽확인
//				// 오른쪽에 선그을 수 있음
//				if(tmp[curCol+1][curRow] != 1) {
//					solve(idx,curCol+1,curRow,true);						
//				}
//				// 오른쪽에 선그을 수 없음
//				else {
//					preX = curCol;
//					preY = curRow;
//					solve(idx,curCol,curRow+1,false);
//				}
//			}
//			else {
//				// 왼쪽확인
//				// 왼쪽에 선그을 수 없음
//				if(tmp[curCol-1][curRow] == 1 || preX == (curCol-1)) {
//					solve(idx,curCol,curRow+1,false);
//				}
//				else {
//					preX = curCol;
//					preY = curRow;						
//					solve(idx,curCol-1,curRow,true);
//				}
//				// 오른쪽확인
//				
//				// 아래로 이동
//			}		
//		
//		
//	}
	
	public static int play(int[][] prev, int x,int y) {
		System.out.println("play!!!");
		System.out.println("==> x :"+x+" ==> y :"+y+" ==>col:"+col+" ==>row:"+row);
		System.out.println(rtn);
		// 가로선 종료 
		if(y > row-1) {
			System.out.println("play#11111");
			rtn = x;
		}
		else {
			//인접 가로선에 라인이 있다면 세로선+1 이동
			if(x < col ) {
				System.out.println("play#33333");
				// 오른쪽에 길있나?
				System.out.println(map[y][x]+"///"+playPreX+"///"+playPreY);
				if(map[y][x]==1  && (playPreX != x+1 || playPreY != y) ) {
					System.out.println("play#44444");	
					playPreX = x;
					playPreY = y;
					play(prev,x+1,y);				
				}
				else {
					System.out.println("play#55555");
					System.out.println(x-1);
					if(x-1 >= 0) {
						// 왼쪽에 길있나?
						if(map[y][x-1]==1 && (playPreX != x-1 || playPreY != y)) {
							System.out.println("play#66666");
							playPreX = x;
							playPreY = y;
							play(prev,x-1,y);
						}
						else {
							System.out.println("play#77777");
							//인접 가로선 라인이 없다면 가로선+1 이동  
							playPreX = x;
							playPreY = y;
							play(prev,x,y+1);				
						}						
					}
					else {
						System.out.println("play#??????"+x);
						play(prev,x,y+1);
					}
				}
			}
			else {
				System.out.println("play#22222");
				if(x-1 >= 0) {
					// 왼쪽에 길있나?
					if(map[y][x-1]==1 && (playPreX != x-1 || playPreY != y)) {
						System.out.println("play#88888");
						playPreX = x;
						playPreY = y;
						play(prev,x-1,y);
					}
					else {
						System.out.println("play#99999");
						//인접 가로선 라인이 없다면 가로선+1 이동  
						playPreX = x;
						playPreY = y;
						play(prev,x,y+1);				
					}						
				}
				else{
					System.out.println("play#??????"+x);
					play(prev,x,y+1);
				}				
			}			
		}
		System.out.println(rtn);
		return rtn;
	}
	public static boolean vaildCheck(int col,int row,int H) {
//		 (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤ (N-1)×H)
		if(col <2 && col > 11) {
			return false;
		}
		if(H <1 && H >31) {
			return false;
		}
		if(row < 0 && row > (col-1)*H) {
			return false;
		}
		return true;
	}
}
