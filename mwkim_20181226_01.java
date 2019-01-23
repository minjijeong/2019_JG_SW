import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/9095
정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
1+1+1+1
1+1+2
1+2+1
2+1+1
2+2
1+3
3+1
정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
</pre>
 * @author 김명우
 *
 */
public class mwkim_20181226_01 {

	static int[] matrix;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for(int test = 1; test <= testCase; test++) {
			int n = sc.nextInt();
			matrix = new int[n+1];
			
			matrix[1] = 1; // 1
			matrix[2] = 2; // 1+1, 2
			matrix[3] = 4; // 1+1+1, 1+2, 2+1, 3
			
			for(int i = 4; i <= n; i++) {
				matrix[i] = matrix[i - 3] + matrix[i - 2] + matrix[i - 1];
			}
			
			System.out.println(matrix[n]);
		}
	}

}
