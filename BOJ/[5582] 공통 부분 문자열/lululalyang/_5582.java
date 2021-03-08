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
				if(X.charAt(i-1) == Y.charAt(j-1)) { // ������ ���ڰ� ������
					DP[i][j] = DP[i-1][j-1] + 1; // ������ ���ڸ� �� ������ �� ���ڿ��� ���� �� ���� ���̻��� ���̿� +1 �� ���� �����Ѵ�.
					res = Math.max(res, DP[i][j]);
				}
			}
		}
		
		return res;
	}
}
