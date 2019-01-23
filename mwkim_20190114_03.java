import java.util.Scanner;

/**
 * <pre>
 * 문제
다섯 개의 자연수가 있다. 이 수의 적어도 대부분의 배수는 위의 수 중 적어도 세 개로 나누어 지는 가장 작은 자연수이다.

서로 다른 다섯 개의 자연수가 주어질 때, 적어도 대부분의 배수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 다섯 개의 자연수가 주어진다. 100보다 작거나 같은 자연수이고, 서로 다른 수이다.

출력
첫째 줄에 적어도 대부분의 배수를 출력한다.
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190114_03 {
	static int[] number;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		number = new int[5];
		
		for(int i = 0; i < 5; i++) {
			number[i] = sc.nextInt();
		}
		
		int result = 0;
		int success = 0;
		while(true) {
			for(int i = 0; i < 5; i++) {
				if(result / number[i] > 0 && result % number[i] == 0) {
					success++;
				}
			}
			if(success >= 3)
				break;
			success = 0;
			result++;
		}
		System.out.println(result);
	}

}
