/**
 * <pre>
 * 문제 설명
현재까지 수업에서 받은 성적을 나타내는 int[] marks가 주어집니다.
각 점수는 0 이상 10 이하이며, 앞으로 남은 과제는 N개 입니다.
모든 과제의 평균 점수가 9.5점 이상이면 최종점수 10점을 받게 됩니다.
앞으로 남은 N개의 과제에서 모두 10점을 받는다고 가정했을 때,
최종점수가 10점이 되기 위한 N의 최소값을 리턴하세요.

참고 / 제약 사항
marks의 요소의 개수는 1개 이상 50개 이하입니다.
marks의 각 요소는 0 이상 10 이하의 정수입니다.
</pre>
 * @author 김명우
 *
 */
public class Oncoder_201902_01 {

	public static void main(String[] args) {
		int[] marks = {8, 9, 10};
		int total = 0;
		double avg = 0;
		int n = 0;
		for(int i = 0; i < marks.length; i++) {
			total += marks[i];
		}
		avg = (double)total / (double)marks.length;
		while(avg < 9.5) {
			total += 10;
			n++;
			avg = (double)total / (double)(marks.length + n);
		}
		
		System.out.println(n);
	}

}
