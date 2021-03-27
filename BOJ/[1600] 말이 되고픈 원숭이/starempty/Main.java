import java.io.*;
import java.util.*;
public class Main {
	static int [] dx = {1,-1,0, 0};
	static int [] dy = {0, 0,1,-1};
	static int [] di = {-2,-1,-2,-1, 2, 1, 2, 1};
	static int [] dj = {-1,-2, 1, 2,-1,-2, 1, 2};
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][]map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][][] visit = new boolean[k+1][n][m];
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0, 0}); //x, y, count, k
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == n-1 && cur[1] == m-1) {
				System.out.println(cur[2]);
				return;
			}
			int nx = 0, ny = 0, nk = cur[3];
			if(nk < k) {
				for(int d = 0; d < 8; d++) {
					nx = cur[0]+di[d];
					ny = cur[1]+dj[d];
					if(0>nx||nx>=n || 0>ny||ny>=m || visit[nk+1][nx][ny] || map[nx][ny] == 1) continue;
					visit[nk+1][nx][ny] = true;
					q.offer(new int[] {nx, ny, cur[2]+1, nk+1});
				}
			}
			for(int d = 0; d < 4; d++) {
				nx = cur[0]+dx[d];
				ny = cur[1]+dy[d];
				if(0>nx||nx>=n || 0>ny||ny>=m || visit[nk][nx][ny] || map[nx][ny] == 1) continue;
				visit[nk][nx][ny] = true;
				q.offer(new int[] {nx, ny, cur[2]+1, nk});
			}
			
		}
		
		System.out.println(-1);
	}
}