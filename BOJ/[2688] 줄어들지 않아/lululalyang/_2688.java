import java.io.*;

public class _2688 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] num = new int[T];
		
		int maxNum = 0;
		for(int t=0; t<T; t++) {
			num[t] = Integer.parseInt(br.readLine());
			maxNum = Math.max(maxNum, num[t]);
		}
		
		// dp�迭 �ʱ�ȭ - 1�ڸ����� �� ����(0~9)�� ����� ���� 1�̰�, ���� 1�ڸ� ���� �پ���� �ʴ� ������ 10��
		long[][] dp = new long[11][maxNum+1];
		dp[10][1] = 10;
		for(int i=0; i<10; i++) {
			dp[i][1] = 1;
		}
		
		NotDecrease(maxNum, dp);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			if(i != 0)	sb.append("\n" + dp[10][num[i]]);
			else	sb.append(dp[10][num[i]]);
		}
		System.out.print(sb.toString());
	}
	
	private static void NotDecrease(int N, long[][] dp) {
		for(int i=1; i<=N; i++) {
			dp[0][i] = 1;
			long sum = 1;
			for(int j=1; j<10; j++) {
				dp[j][i] = dp[j-1][i] + dp[j][i-1];
				sum += dp[j][i];
			}
			dp[10][i] = sum;
		}
	}
}
