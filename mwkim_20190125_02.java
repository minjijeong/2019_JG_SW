import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/13458
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190125_02 {
	static int N, B, C;
	static long min_result;
	static int[] A;
	static boolean[] A_first;
	
	static long calc(int classNum) {
		long result = 0;
		
		while(A[classNum] > 0) {
			if(A_first[classNum]) {
				if(C <= 0)
					return 0;
				if(A[classNum] > C) {	
					int temp = A[classNum] / C;
					int remainder = A[classNum] % C;
					result = result + (temp + (remainder == 0 ? 0 : 1));
					return result;
				}
				else {
					A[classNum] -= C;
					result++;
				}
			}
			else {
				if(B <= 0)
					return 0;
				A[classNum] -= B;
				result++;
			}
			A_first[classNum] = true;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		A = new int[N];
		A_first = new boolean[N];
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		B = sc.nextInt();
		C = sc.nextInt();
		min_result = 0;
		
		for(int i = 0; i < N; i++) {
			if(A[i] <= 0)
				continue;
			
			long result = calc(i);
			min_result += result;
		}
		
		System.out.println(min_result);
	}

}
