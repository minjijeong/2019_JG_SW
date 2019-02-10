package study_hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class bj_14889_190201 {
	static int s[][] ;
	static int MIN;
	static int N;
	static Map<String,Integer> slist = new HashMap();
	static ArrayList<Integer> tA = new ArrayList<>();
	static ArrayList<Integer> tB = new ArrayList<>();
//	 static Queue<Score> slist = new LinkedList<>();
	 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		Score sc = new Score();
		
		// 선수별 능력치 입력
		N = Integer.parseInt(br.readLine());
		s = new int[N][N];

		for(int i=0;i<N;i++) {
			String[] tmp = (br.readLine()).split(" ");
			for(int j=0; j<N; j++) {
				s[i][j] = Integer.parseInt(tmp[j]);
			}
			
		}
		
		// 선수 조합별 능력치 합계처리
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i !=j && i < j) {
					slist.put(i + " " + j,s[i][j]+s[j][i]);
				}
			}
		}
		
		// 팀조합
		for(int i=1;i <= N/2; i++) {
			tA.add(i);
			recursive(tA,i+1);
		}		
		
//		System.out.println(slist);
		// 선수 조합별 능력치 비교
		for(int i=0; i < slist.size()/2; i++) {
		
				
			
		}
		
	}
    private static void recursive (ArrayList<Integer> tA, int cnt) {
        // 벽 세개 세웠으면 바이러스 퍼트리기
    	System.out.println(tA);
    	System.out.println(N);
        if (tA.size() >= N/2) {
        	// 팀간 능력치 비교
        	System.out.println("A팀구성!!");
        } else {
        	System.out.println(cnt);
        	System.out.println(tA.contains(cnt));
        	// tA에 존재하지 않으면
        	if(!tA.contains(cnt)) {
        		tA.add(cnt);
        	}
        	recursive(tA, ++cnt);
        }
        tA.clear();
    }
	

}

//class Score{
//	int member1;
//	int member2;
//	int sum ;
//	public Score(int member1,int member2, int sum) {
//		this.member1 = member1;
//		this.member2 = member2;
//		this.sum = sum;
//	}
//}