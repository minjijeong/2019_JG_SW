package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//벌꿀채취
public class SWEA_2115_2 {

	static int N, M, C, map[][], temp, ans, D[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //벌통의 크기
			M = Integer.parseInt(st.nextToken()); //선택할 수 있는 벌통의 개수
			C = Integer.parseInt(st.nextToken()); //꿀을 채취할 수 있는 최대 양
			map = new int[N][N];
			ans = 0; //최대 이익
			D = new int[N][N]; //(i, j)위치부터 M개 벌통 선택할 때 최대 수익
			//입력
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//D[][] 구하기
			for(int i=0; i<N; i++) {
				for(int j=0; j+M<=N; j++) {
					temp = 0;
					dfs(i, j, 0, 0, 0);
					D[i][j] = temp;
				}
			}
			//첫 번째 일꾼 선택
			for(int i=0; i<N; i++) {
				for(int j=0; j+M<=N; j++) {
					//두 번째 일꾼 선택
					int si = i;
					int sj = j+M;
					if(sj==N) {
						si++;
						sj = 0;
					}
					for(int i2=si;i2<N; i2++) {
						for(int j2=sj; j2+M<=N; j2++) {
							ans = Math.max(ans, D[i][j]+D[i2][j2]);
						}
						sj = 0;
					}
				}
			}
			
			System.out.println("#"+T+" "+ans);
		}
	}

	static void dfs(int x, int y, int sum, int profit, int cnt) {
		if(cnt==M) {
			temp = Math.max(temp, profit);
			return;
		}
		//현재 벌통 선택
		if(sum+map[x][y]<=C) {
			dfs(x, y+1, sum+map[x][y], profit+map[x][y]*map[x][y], cnt+1);
		}
		//선택x
		dfs(x, y+1, sum, profit, cnt+1);
	}

}
