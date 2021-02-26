import java.io.*;

public class _2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] s;
		int[][] num = new int[N][3];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				num [i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int[] max_min = Compute_max_min(N, num);
		System.out.print(max_min[0] + " " + max_min[1]);
	}
	
	private static int[] Compute_max_min(int N, int[][] num) {
		int[][] max = new int[N][3]; // 현재 칸까지의 최대 점수
		int[][] min = new int[N][3]; // 현재 칸까지의 최소 점수
		
		for(int i=0; i<3; i++) {
			max[0][i] = num[0][i];
			min[0][i] = num[0][i];
		}
		
		for(int i=1; i<N; i++) { // 0:a, 1:b, 2:c
			// max
			int max_ab = Math.max(max[i-1][0], max[i-1][1]);
			int max_bc = Math.max(max[i-1][1], max[i-1][2]);
			max[i][0] = max_ab + num[i][0];
			max[i][1] = Math.max(max_ab, max_bc) + num[i][1];
			max[i][2] = max_bc + num[i][2];
			
			// min
			int min_ab = Math.min(min[i-1][0], min[i-1][1]);
			int min_bc = Math.min(min[i-1][1], min[i-1][2]);
			min[i][0] = min_ab + num[i][0];
			min[i][1] = Math.min(min_ab, min_bc) + num[i][1];
			min[i][2] = min_bc + num[i][2];
		}
		
		int maxScore = Math.max(max[N-1][0], max[N-1][1]);
		maxScore = Math.max(maxScore, max[N-1][2]);
		
		int minScore = Math.min(min[N-1][0], min[N-1][1]);
		minScore = Math.min(minScore, min[N-1][2]);
		
		int[] res = {maxScore, minScore};
		return res;
	}
	
	
}
