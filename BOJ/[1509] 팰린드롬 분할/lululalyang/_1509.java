import java.io.*;

public class _1509 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("");
		
		System.out.print(MinPalindrome(str));
	}
	
	private static int MinPalindrome(String[] str) {
		int len = str.length;
		
		boolean[][] isPal = new boolean[len][len]; // isPal[i][j]: i~j������ ���ڿ��� �Ӹ�����̸� true, �׷��� ������ false
		computeIsPal(str, len, isPal);
		
		int[] dp = new int[len]; // dp[i]: 0~i���� ���ڿ������� �Ӹ���� ���� ��
		dp[0] = 1; // 0~0 ���ڿ������� �Ӹ���� ���� ���� 1(�ڱ��ڽ� ��)
		for(int i=1; i<len; i++) {
			if(isPal[0][i])	dp[i] = 1; // 0~i���� �Ӹ�����̶��, 0~i���� ���ڿ������� �Ӹ���� ���� ���� 1�� �ּ�
			else { // 0~i������ �Ӹ������ �ƴ϶��
				dp[i] = dp[i-1] + 1; // dp[i]�� �ִ밪
				for(int j=1; j<i; j++) { 
					if(isPal[j][i]) // j~i������ ���ڿ��� �Ӹ�����̶��, 	
						dp[i] = Math.min(dp[i], dp[j-1]+1); // (j-1)�� �Ӹ���� ���� ���� +1 (+1: j~i������ ���ڿ��� �Ӹ�����̹Ƿ�)
				}
			}
			
		}
		
		return dp[len-1];
	}
	
	private static void computeIsPal(String[] str, int len, boolean[][] isPal) {
		for(int i=0; i<len; i++) { // (i+1): ���� ���Ϸ��� �Ӹ���� ���ڿ��� ����
			for(int j=0; j<len-i; j++) {
				if(i == 0) { // ���̰� 1�� �Ӹ���� ���ڿ� => ��ΰ� �Ӹ�����̴�.
					isPal[j][j] = true;
				}else if(i == 1) { // ���̰� 2�� �Ӹ���� ���ڿ� => �� ���ڰ� ������ �Ӹ����, �ٸ��� �Ӹ������ �ƴϴ�. 
					isPal[j][j+1] = (str[j].equals(str[j+1]));
				}else { // ���� 3���� len������ �Ӹ���� ���ڿ� => �� �� ���ڸ� ������ ���ڿ��� �Ӹ�����̰�, �� �� ���ڰ� ������ �Ӹ����, �׷��� ������ �Ӹ������ �ƴϴ�.
					isPal[j][j+i] = (isPal[j+1][j+i-1] && str[j].equals(str[j+i]));
				}
			}
		}
	}
}
