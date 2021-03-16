import java.io.*;

public class _11062 {
	static int[] num;
	static int[][] score;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			if(t != 0)	sb.append("\n");
			
			int N = Integer.parseInt(br.readLine());
			num = new int[N];
			String[] s = br.readLine().split(" ");
			for(int i=0; i<N; i++)
				num[i] = Integer.parseInt(s[i]);
			
			score = new int[N][N];
			
			sb.append(solution(true, 0, N-1));			
		}
		
		System.out.print(sb.toString());
	}
	
	private static int solution(boolean turn, int s, int l) {
		if(s > l)	return 0;
		if(score[s][l] != 0)
			return score[s][l];
		
		if(turn) { // ±Ù¿ì
			score[s][l] = Math.max(solution(!turn, s+1, l) + num[s], solution(!turn, s, l-1) + num[l]);
		}else {
			score[s][l] = Math.min(solution(!turn, s+1, l), solution(!turn, s, l-1));
		}
		
		return score[s][l];
	}
	
}
