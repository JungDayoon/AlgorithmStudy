import java.io.*;

public class _1509 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("");
		
		System.out.print(MinPalindrome(str));
	}
	
	private static int MinPalindrome(String[] str) {
		int len = str.length;
		
		boolean[][] isPal = new boolean[len][len]; // isPal[i][j]: i~j까지의 문자열이 팰린드롬이면 true, 그렇지 않으면 false
		computeIsPal(str, len, isPal);
		
		int[] dp = new int[len]; // dp[i]: 0~i까지 문자열에서의 팰린드롬 분할 수
		dp[0] = 1; // 0~0 문자열에서의 팰린드롬 분할 수는 1(자기자신 뿐)
		for(int i=1; i<len; i++) {
			if(isPal[0][i])	dp[i] = 1; // 0~i까지 팰린드롬이라면, 0~i까지 문자열에서의 팰린드롬 분할 수는 1이 최소
			else { // 0~i까지가 팰린드롬이 아니라면
				dp[i] = dp[i-1] + 1; // dp[i]의 최대값
				for(int j=1; j<i; j++) { 
					if(isPal[j][i]) // j~i까지의 문자열이 팰린드롬이라면, 	
						dp[i] = Math.min(dp[i], dp[j-1]+1); // (j-1)의 팰린드롬 분할 수에 +1 (+1: j~i까지의 문자열이 팰린드롬이므로)
				}
			}
			
		}
		
		return dp[len-1];
	}
	
	private static void computeIsPal(String[] str, int len, boolean[][] isPal) {
		for(int i=0; i<len; i++) { // (i+1): 현재 구하려는 팰린드롬 문자열의 길이
			for(int j=0; j<len-i; j++) {
				if(i == 0) { // 길이가 1인 팰린드롬 문자열 => 모두가 팰린드롬이다.
					isPal[j][j] = true;
				}else if(i == 1) { // 길이가 2인 팰린드롬 문자열 => 두 문자가 같으면 팰린드롬, 다르면 팰린드롬이 아니다. 
					isPal[j][j+1] = (str[j].equals(str[j+1]));
				}else { // 길이 3부터 len까지의 팰린드롬 문자열 => 양 끝 문자를 제외한 문자열이 팰린드롬이고, 양 끝 문자가 같으면 팰린드롬, 그렇지 않으면 팰린드롬이 아니다.
					isPal[j][j+i] = (isPal[j+1][j+i-1] && str[j].equals(str[j+i]));
				}
			}
		}
	}
}
