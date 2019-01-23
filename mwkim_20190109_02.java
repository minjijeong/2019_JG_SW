import java.util.Scanner;

public class mwkim_20190109_02 {
	static int[] T;
	static int[] P;
	static int maxPay, N;
	
	static void calcBizDay(int curDay, int calcPay) {
		if(curDay == N) {
			maxPay = Math.max(calcPay, maxPay);
			return;
		}
		System.out.println(curDay + ": " + calcPay);
		
		calcBizDay(curDay + 1, calcPay);
		
		if(curDay + T[curDay] <= N)
			calcBizDay(curDay + T[curDay], calcPay + P[curDay]);
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		T = new int[N];
		P = new int[N];
		maxPay = 0;
		
		for(int i = 0; i < N; i++) {
			T[i]= sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		calcBizDay(0, 0);
		
		System.out.println(maxPay);
	}

}
