package study_hw;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class bj_15684_01 {
	static int col,row,H;
	static int addLine = 0; // �߰�����
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
		System.out.println("�������Է³�!");
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
		
				System.out.println("����");
				solve(0,0,0,false);
//			}
	
		}
		else {
			addLine = -1;
		}	
		System.out.println(addLine);
		sc.close();
		
	}
	// ������ 3���� ū ���̸� -1�� ����Ѵ�. ��, �Ұ����� ��쿡�� -1�� ����Ѵ�.
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
		// ��ٸ� �ֻ�ܱ��� �ö�ͼ��� �������
		//  (1 �� a �� H, 1 �� b �� N-1) . H�������� �� ���μ����� ������ �ִ� ���μ� ������ �۾ƾ� �Ѵ�.

		rtn = -1;
		// ��ٸ� ���� ����
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
				System.out.println("#�����߰��õ�");
			}
			
			int res = play(tmp,curCol,curRow);
			// k ���� ��ٸ� Ÿ�� ���� 
			// ������ȣ�� �̵� 
			System.out.println("==> idx :"+idx+" ==> res :"+res);
			if(res == idx) {
				System.out.println("#44444");
				if(lineFg) {
					//idx ����!
					map[curRow][curCol] = 1;
					addLine++;		
					System.out.println("#�����߰��Ϸ�");
				}
				solve(idx+1,idx+1,0,false);	
			}
			// ��ٸ� �����ؾߵ�!
			else {
				if(curRow == 0) {
					System.out.println("#55555");
					// ������Ȯ��
					// �����ʿ� ������ �� ����
					if(curCol+1 >= col){
						preX = -1;
						preY = -1;
						System.out.println("#col over �������� �߰��� ");
						solve(idx,curCol,curRow+1,true);	
					}
					// �����ʿ� ������ �� ����
					else {
						System.out.println(curCol + "////"+col);
						// ���� ���� �� ���� ��� 
						if(preX == (curCol+1)||tmp[curRow][curCol+1] == 1 ) {
							System.out.println("#�������� ������ �� ���� ");
							preX = curCol;
							preY = curRow;
							solve(idx,curCol,curRow+1,false);
						}
						// �����ʿ� ������ �� ����
						else {
							System.out.println("#�����ʿ� ������ �� ����");
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
					// ����Ȯ��
					// ���ʿ� ������ �� ����
					if(curCol-1 < 0 || curCol-1 >= col){
						preX = -1;
						preY = -1;
						solve(idx,curCol,curRow+1,false);
					}
					else{
						if(preX == (curCol-1)||tmp[curRow][curCol-1] == 1 ) {
							System.out.println("#�޼��� �߰��ȵ� ");
							// ������Ȯ��
							if(curCol+1 != col) {
								if(tmp[curRow][curCol+1] != 1) {
									System.out.println("#�������� �߰�?? ");
									preX = curCol;
									preY = curRow;
									solve(idx,curCol+1,curRow,true);						
								}
								// �����ʿ� ������ �� ����
								else {
									System.out.println("#�������� �߰��ȵ�!! �׳� �н� ");
									preX = -1;
									preY = -1;
									solve(idx,curCol,curRow+1,false);
								}																				
							}
							// �����ʿ� ������ �� ����
							else {
								preX = -1;
								preY = -1;
								solve(idx,curCol,curRow+1,false);
							}
						}
						// �޼��߰��ɼ�����
						else {
							System.out.println("#�޼��߰��ɼ����� ");
							preX = curCol;
							preY = curRow;						
							solve(idx,curCol-1,curRow,true);									
						}
					}	

				}
			}

		}	
	}
//	System.out.println("������!");
//	//���� col��° ������ ���� ����� �� ���ϱ�
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
//		// ����
//		if(direction == 0) {
//			
//		}
//		// ������
//		else if(direction == 1) {
//			if(tmp[curCol+1][curRow] != 1) {
//				tmp[curCol][curRow] = 1;
//				play(tmp,curCol,curRow);
//			}
//				solve(idx,curCol+1,curRow,true);						
//			}
//			// �����ʿ� ������ �� ����
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
//				//idx ����!
//				map[curCol][curRow] = 1;
//				addLine++;		
//			}
//			solve(idx+1,idx+1,0,false);	
//		}
//		// ��ٸ� �����ؾߵ�!
//		else {
//			if(curRow == 0) {
//				System.out.println("#55555");
//				// ������Ȯ��
//				// �����ʿ� ������ �� ����
//				if(tmp[curCol+1][curRow] != 1) {
//					solve(idx,curCol+1,curRow,true);						
//				}
//				// �����ʿ� ������ �� ����
//				else {
//					preX = curCol;
//					preY = curRow;
//					solve(idx,curCol,curRow+1,false);
//				}
//			}
//			else {
//				// ����Ȯ��
//				// ���ʿ� ������ �� ����
//				if(tmp[curCol-1][curRow] == 1 || preX == (curCol-1)) {
//					solve(idx,curCol,curRow+1,false);
//				}
//				else {
//					preX = curCol;
//					preY = curRow;						
//					solve(idx,curCol-1,curRow,true);
//				}
//				// ������Ȯ��
//				
//				// �Ʒ��� �̵�
//			}		
//		
//		
//	}
	
	public static int play(int[][] prev, int x,int y) {
		System.out.println("play!!!");
		System.out.println("==> x :"+x+" ==> y :"+y+" ==>col:"+col+" ==>row:"+row);
		System.out.println(rtn);
		// ���μ� ���� 
		if(y > row-1) {
			System.out.println("play#11111");
			rtn = x;
		}
		else {
			//���� ���μ��� ������ �ִٸ� ���μ�+1 �̵�
			if(x < col ) {
				System.out.println("play#33333");
				// �����ʿ� ���ֳ�?
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
						// ���ʿ� ���ֳ�?
						if(map[y][x-1]==1 && (playPreX != x-1 || playPreY != y)) {
							System.out.println("play#66666");
							playPreX = x;
							playPreY = y;
							play(prev,x-1,y);
						}
						else {
							System.out.println("play#77777");
							//���� ���μ� ������ ���ٸ� ���μ�+1 �̵�  
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
					// ���ʿ� ���ֳ�?
					if(map[y][x-1]==1 && (playPreX != x-1 || playPreY != y)) {
						System.out.println("play#88888");
						playPreX = x;
						playPreY = y;
						play(prev,x-1,y);
					}
					else {
						System.out.println("play#99999");
						//���� ���μ� ������ ���ٸ� ���μ�+1 �̵�  
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
//		 (2 �� N �� 10, 1 �� H �� 30, 0 �� M �� (N-1)��H)
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
