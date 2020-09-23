import java.util.Scanner;

public class _1300 {
	static int N;
	static int k;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		k = sc.nextInt(); // 1 <= k <= 1000000000
		sc.close();
		
		int min = 1;
		int max = k;
		int result = 1;
		
		while(min <= max) {
			int mid = (min+max)/2;
			if(checkB_k_loc(mid) >= k) {
				result = mid;
				max = mid-1;
			}else{
				min = mid+1;
			}
		}
		System.out.println(result);
		//System.out.printf("userMemory: %6.2f MB", (double)(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024*1024));
	}
	
	static int checkB_k_loc(int mid) {
		int cnt=0;
		for(int i=1; i<=N; i++) {
			cnt+=Math.min(mid/i, N);
		}
			
		return cnt;
	}
}
