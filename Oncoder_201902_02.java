/**
 * <pre>
 * 문제 설명
지금은 겨울입니다.
온코더 마을에는 겨울이 되면 연인에게 사탕을 주는 풍습이 있습니다.
풍습에 따라 당신이 가진 사탕 중 일부를 골라 연인에게 주려고 합니다.
각 사탕의 종류를 양의 정수로 표현하는 vector<int> type이 주어집니다.
당신은 vector<int> type 의 요소의 개수만큼 사탕을 가지고 있으며,
vector<int> type 의 i번째 요소는 i번째 사탕의 종류를 의미합니다.

당신은 다음 규칙으로 사탕을 고릅니다 :
- 고르는 사탕의 수가 K개일 경우, 그 사탕들의 종류는 1이상 K 이하의 사탕을 각각 하나씩 포함해야합니다.
- 최소 1개 이상의 사탕을 골라야 합니다.

사탕을 고르는 방법의 경우의 수를 리턴하세요.
사탕의 종류가 같아도 서로 다른 사탕이라면, 사탕을 고르는 방법이 다른 것으로 간주합니다. (예제 테스트케이스 2번 참고)

참고 / 제약 사항
[참고] 답은 항상 정수형 범위를 벗어나지 않습니다.
type 의 요소의 개수는 1개 이상 50개 이하입니다.
type 의 각 요소는 1 이상 50 이하의 정수입니다.
</pre>
 * @author 김명우
 *
 */
public class Oncoder_201902_02 {
	
	static int factorial(int num) {
		if(num <= 1)
			return 1;
		else
			return num * factorial(num - 1);
	}

	public static void main(String[] args) {
		int[] type = {1, 1, 1, 3};
		int success = 0;
		for(int K = 1; K <= type.length; K++) {
			int[] total = new int[K + 1];
			for(int y = K; y >= 1; y--) {
				int same = 0;
				for(int x = 0; x < type.length; x++) {
					if(y == type[x])
						same++;
				}
				total[y] = same;
			}
			int temp = total[1];
			for(int i = 2; i < total.length; i++) {
				temp *= total[i];
			}
			
			success += temp;
		}
		
		System.out.println(success);
	}

}
