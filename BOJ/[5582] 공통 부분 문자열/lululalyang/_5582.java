import java.io.*;
public class _5582 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String X = br.readLine();
		String Y = br.readLine();
		
		System.out.print(solution(X, Y));
	}
	
	private static int solution(String X, String Y) {
		int res = 0;
		int lenX = X.length();
		int lenY = Y.length();
		
		int[][] DP = new int[lenX+1][lenY+1];
		for(int i=1; i<lenX+1; i++) {
			for(int j=1; j<lenY+1; j++) {
				if(X.charAt(i-1) == Y.charAt(j-1)) { // 마지막 글자가 같으면
					DP[i][j] = DP[i-1][j-1] + 1; // 마지막 글자를 뺀 나머지 두 문자열의 가장 긴 공통 접미사의 길이에 +1 한 값을 저장한다.
					res = Math.max(res, DP[i][j]);
				}
			}
		}
		
		return res;
	}
}
