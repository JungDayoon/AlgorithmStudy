import java.util.Scanner;

public class _1309 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[N+1][3];
		System.out.print(Bottom_up(N, dp));
		sc.close();
	}
	
	private static int Bottom_up(int N, int[][] dp) {
		dp[1][0] = 1; // N=1일 때, 이 줄에 아무 사자도 배치하지 않는 경우 -> 1가지
		dp[1][1] = 1; // N=1일 때, 이 줄의 왼쪽 칸에 사자를 배치하는 경우 -> 1가지
		dp[1][2] = 1; // N=1일 때, 이 줄의 오른쪽 칸에 사자를 배치하는 경우 -> 1가지		
		for(int i=2; i<=N; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		
		return (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
	}
}
