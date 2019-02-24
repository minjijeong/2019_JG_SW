
public class combi {
	static int max_result = 0;

	public static void main(String[] args) {
		int[] arr = new int[10];
		int[] a = {9, 5, 7, 9, 6, 1, 4, 3, 2, 8};
		for(int i = 1; i <= 10; i++) {
			asdf(a, arr, 0, 10, i, 0);
		}
		
		System.out.println(max_result);
	}
	
	static void asdf(int[] a, int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			int total = 0;
			int powTotal = 0;
			for(int i = 0; i < index; i++) {
				int powValue = (int)Math.pow((double)a[arr[i]], 2.0);
				if(total + a[arr[i]] <= 10) {
					total += a[arr[i]];
					powTotal += powValue;
				}
			}
			max_result = Math.max(max_result, powTotal);
		}
		else if(n == target)
			return;
		else {
			arr[index] = target;
			asdf(a, arr, index + 1, n, r - 1, target + 1);
			asdf(a, arr, index, n, r, target + 1);
		}
	}
}
