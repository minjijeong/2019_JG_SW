import java.util.Scanner;

public class mwkim_20190125_03 {
	static int[] num, calc, use;
	static int N, max_result, min_result;

	static void calculate(int order, int result) {
		if(order == N) {
			max_result = Math.max(max_result, result);
			min_result = Math.min(min_result, result);
			System.out.println(max_result + ":" + min_result);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(use[i] == calc[i])
				continue;
			
			String a = "";
			int temp = result;
			switch(i) {
			case 0:
				a = "+";
				result = result + num[order];
				break;
			case 1:
				a = "-";
				result = result - num[order];
				break;
			case 2:
				a = "*";
				result = result * num[order];
				break;
			case 3:
				a = "/";
				result = result / num[order];
				break;
			}
			System.out.println(order + ": " + temp +  a + num[order]);
			use[i]++;
			calculate(order + 1, result);
			use[i]--;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for(int test = 1; test <= testCase; test++) {
			N = sc.nextInt();
			num = new int[N];
			calc = new int[4];
			use = new int[4];
			max_result = 0;
			min_result = 100000000;
			
			for(int i = 0; i < 4; i++) {
				calc[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				num[i] = sc.nextInt();
			}
			
			calculate(1, num[0]);
			
			System.out.println("#" + test + " " + (max_result - min_result));
		}
	}

}
