/**
 * <pre>
 * 문제 설명
유라는 복권을 좋아합니다.
그녀가 최근 관심을 가진 복권은 온코더 복권입니다.

 

온코더 복권 티켓은 표로 이루어져 있습니다.
이 표는 int N행과 5열로 이루어진 직사각형 격자로,
표의 칸은 1 이상 5×N 이하의 정수이며 동일한 숫자는 없습니다.
복권 추첨일이 되면, 주최측은 1이상 5×N 이하의 정수 중에서 서로 다른 5개 숫자를 무작위로 선택합니다.
5개의 숫자가 선택될 확률은 각각 동일합니다.
이 숫자들을 우승 번호라고 부릅니다.
이때 우승 번호가 3개 이상 같은 행에 있는 티켓은 당첨 티켓이 됩니다.
유라는 한 장의 온코더 복권 티켓을 살 예정입니다.
숫자의 배치에 따라 다양한 종류의 티켓이 존재하겠지만,
모든 종류의 티켓은 각각 판매될 확률이 같습니다.
유라가 당첨 티켓을 살 확률을 리턴하세요.

참고 / 제약 사항
리턴된 값은 1E-9 이하의 오차를 허용합니다.
N은 1 이상 100 이하의 정수입니다.

테스트 케이스
int N = 1 리턴(정답): 1
모든 티켓은 1, 2, 3, 4, 5의 순열로 이루어진 한 줄짜리 티켓입니다.
가능한 우승 번호는 {1, 2, 3, 4, 5}로 한 가지 입니다.
따라서 모든 티켓은 5개의 우승 번호를 전부 포함하게 되어 모든 티켓이 당첨 티켓이 됩니다.

int N = 2 리턴(정답): 1
어떤 우승 번호가 선택되더라도 적어도 하나의 행에는 최소 3개의 우승 번호가 들어가게 됩니다.

int N = 9 리턴(정답): 0.0589387923
</pre>
 * @author 김명우
 *
 */
public class Oncoder_201902_03 {

	static int factorial(int num) {
		if(num <= 1)
			return 1;
		else
			return num * factorial(num - 1);
	}
	
	public static void main(String[] args) {
		int N = 1;
		
		int num = N * 5;
		int totalCase = factorial(num) / (factorial(num - 5) * factorial(5));
		
		for(int i = 1; i <= N; i++) {
			
		}
		int selectCase = factorial(num) / (factorial(num - 5) * factorial(5));
	}
}
