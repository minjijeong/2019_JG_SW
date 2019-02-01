import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/14889
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190201_02 {
	static int[][] S;
	static boolean[] team;
	static int N, min_result;
	
	/**
	 * 팀별로 능력치 합산
	 * @param arr 조합된 배열
	 * @param size 조합된 배열에서 조합숫자에만 해당되는 개수
	 * @return
	 */
	static int[] calcTeamAbility(int[] arr, int size) {
		int result[] = new int[2];
		boolean[][] calculated = new boolean[N][N];

		//combination을 통해 얻은 조합 수에 해당하는 것만 Start팀으로
		for (int i = 0; i < size; i++) {
			team[arr[i]] = true;
		}
		
		//Start팀은 result[0], Link팀은 result[1]에 더해서 저장, 이미 연산한건 넘어가도록 구현
		for (int y = 0; y < N; y++) {
			if (team[y]) {
				for (int x = 0; x < N; x++) {
					if (calculated[y][x] || calculated[x][y])
						continue;
					if (y == x)
						continue;

					if (team[x]) {
						result[0] += (S[y][x] + S[x][y]);
						calculated[y][x] = true;
						calculated[x][y] = true;
					}
				}
			} else {
				for (int x = 0; x < N; x++) {
					if (calculated[y][x] || calculated[x][y])
						continue;
					if (y == x)
						continue;

					if (!team[x]) {
						result[1] += (S[y][x] + S[x][y]);
						calculated[y][x] = true;
						calculated[x][y] = true;
					}
				}
			}
		}

		return result;
	}
	
	/**
	 * nCr 조합 구하고 재귀 돌아서 r이 0이되면 각 팀별 능력치 구함
	 * @param arr 조합 수를 담을 배열
	 * @param index arr[index]에서 배열의 순서
	 * @param n N개의 수
	 * @param r 중에서 r개를 뽑기
	 * @param target 조합 수
	 */
	static void combination(int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			team = new boolean[N];
			int[] ability = calcTeamAbility(arr, index);
			min_result = Math.min(min_result, Math.abs((ability[0]) - (ability[1])));
		}
		else if(n == target)
			return;
		else {
			arr[index] = target;
			combination(arr, index + 1, n, r - 1, target + 1);
			combination(arr, index, n, r, target + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		S = new int[N][N];
		team = new boolean[N];
		min_result = 100 * (N/2);
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				S[y][x] = sc.nextInt();
			}
		}
		combination(new int[N], 0, N, (N/2), 0);
		System.out.println(min_result);
	}

}
