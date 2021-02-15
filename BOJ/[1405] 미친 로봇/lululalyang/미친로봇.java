import java.io.*;

public class _1405 {
	static int[] dx = {0, 0, 1, -1}; //0: 동, 1: 서, 2: 남, 3: 북
	static int[] dy = {1, -1, 0, 0};
	static boolean[][] visited = new boolean[29][29];
	static double res = 0.0;
	static double[] DirProb = new double[4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		for(int i=1; i<5; i++) {
			DirProb[i-1] = Integer.parseInt(s[i]) / 100.0;
		}
		
		visited[14][14] = true; //시작점
		Solution(N, 14, 14, 1);
		System.out.print(res);
	}

	private static void Solution(int n, int x, int y, double prob) {
		if(n == 0) {
			res += prob;
			return;
		}
		
		for(int k=0; k<4; k++) {
			int rx = x + dx[k];
			int ry = y + dy[k];
			
			if(rx>=0 && rx<29 && ry>=0 && ry<29 && !visited[rx][ry]) {
				visited[rx][ry] = true;
				double tmp = prob * DirProb[k];
				Solution(n-1, rx, ry, tmp);
				visited[rx][ry] = false;
			}
		}
	}
}
