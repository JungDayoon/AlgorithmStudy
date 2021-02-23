import java.util.Scanner;

public class _2133 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		
		System.out.print(Bottom_up(N, dp));
		sc.close();
	}
	
	private static int Bottom_up(int N, int[] dp) {
		if(N%2 == 1)	return 0; // Ȧ���̸� ����� �� 0
		
		dp[0] = 1;
		dp[2] = 3; // (3x2)�� ����� ���� 3
		for(int i=4; i<=N; i+=2) {
			for(int j=2; j<=i; j+=2) {
				int multiNum = (j==2)? 3 : 2; // dp[2]�� 3, �� ���� Ư�� ���� 2 
				dp[i] += multiNum * dp[i-j];
			}
		}
		
		return dp[N];
	} 
}
