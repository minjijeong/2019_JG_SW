import java.util.Scanner;

/**
 * <pre>
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD&categoryId=AV15Khn6AN0CFAYD&categoryType=CODE&&&&&&
 * 퀴즈 대회에 참가해서 우승을 하게 되면 보너스 상금을 획득할 수 있는 기회를 부여받는다.

우승자는 주어진 숫자판들 중에 두 개를 선택에서 정해진 횟수만큼 서로의 자리를 위치를 교환할 수 있다.

예를 들어, 다음 그림과 3, 2, 8, 8, 8 의 5개의 숫자판들이 주어지고 교환 횟수는 2회라고 하자.

교환전>



처음에는 첫번째 숫자판의 3과 마지막에 있는 8을 교환해서 8, 2, 8, 3, 8이 되었다.
 


다음으로, 두 번째 숫자판 2와 네 번째 숫자판의 8을 교환해서 8, 8, 8, 3, 2이 되었다.



정해진 횟수만큼 교환이 끝나면 숫자판의 위치에 부여된 가중치에 의해 상금이 계산된다.

숫자판의 오른쪽 끝에서부터 1원이고 왼쪽으로 한자리씩 갈수록 10의 배수만큼 커진다.

위의 예에서와 같이 최종적으로 숫자판들이 8,8,8,3,2의 순서가 되면 88832원의 보너스 상금을 획득한다.

여기서 주의할 것은 반드시 횟수만큼 교환이 이루어져야 하고 동일한 위치의 교환이 중복되어도 된다.

다음과 같은 경우 1회의 교환 횟수가 주어졌을 때 반드시 1회 교환을 수행하므로 결과값은 49가 된다.



94의 경우 2회 교환하게 되면 원래의 94가 된다.

정해진 횟수만큼 숫자판을 교환했을 때 받을 수 있는 가장 큰 금액을 계산해보자.
 * </pre>
 * 
 * @author 김명우
 *
 */

public class mwkim_20190101_02 {

	static int maxValue;


	static void changing(int change, char[] board, int swapCount, int left) {
		//System.out.println("[" + swapCount + "/" + change + "][left: " + left + "]: " + String.valueOf(board));
		if(change == swapCount) {
			int temp = Integer.parseInt(String.valueOf(board));
			if (temp > maxValue)
				maxValue = temp;
			return;
		}
		if(left == board.length - 2) {
			swap(board, left, left + 1);
			changing(change, board, swapCount + 1, left);
			return;
		}
		
		char maxVal = board[left];
		
		for(int n = left + 1; n < board.length; n++) {
			if(maxVal < board[n]) {
				maxVal = board[n];
			}
			else if(maxVal == board[n]) {
				swap(board, left, n);
				changing(change, board, swapCount + 1, left);
				swap(board, n, left);
			}
		}
		
		if(maxVal == board[left])
			changing(change, board, swapCount, left + 1);
		else {
			for(int n = left + 1; n < board.length; n++) {
				if(maxVal == board[n]) {
					swap(board, left, n);
					changing(change, board, swapCount + 1, left + 1);
					swap(board, n, left);
				}
			}
		}
	}

	static void swap(char[] board, int idx1, int idx2) {
		char tmp = board[idx1];
		board[idx1] = board[idx2];
		board[idx2] = tmp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for (int test = 1; test <= testCase; test++) {
			String num = sc.next();
			char[] board = new char[num.length()];
			int change = sc.nextInt();
			maxValue = 0;
			for (int i = 0; i < num.length(); i++) {
				board[i] = num.charAt(i);
			}
			changing(change, board, 0, 0);
			System.out.println("#" + test + " " + maxValue);
		}
	}
}