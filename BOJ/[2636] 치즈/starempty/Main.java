import java.io.*;
import java.util.*;
public class Main {
	static int [][] cheese;
	static int n, m;
	static int [] dx = {1,-1,0, 0};
	static int [] dy = {0, 0,1,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cheese = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0, time = 0;
		while(true) {
			bfs(); //바깥공기 치즈 분리
			
			Deque<int[]> melt = new ArrayDeque<>();//녹일 치즈
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(cheese[i][j] == 1) {
						int nx = 0, ny = 0;
						for(int dir = 0; dir < 4; dir++) {
							nx = i+dx[dir];
							ny = j+dy[dir];
							if(0<=nx&&nx<n && 0<=ny&&ny<m && cheese[nx][ny] == 2) {
								cheese[i][j] = 3;
								melt.offer(new int[] {i, j});
								break;
							}
						}
					}
				}
			}
			if(melt.size() == 0) break; //녹일 치즈가 없다
			
			cnt = melt.size(); //녹일 치즈 수
			
            time++; //1시간 경과
			
			for(int[] cur: melt) { //치즈 녹아 없어지다
				cheese[cur[0]][cur[1]] = 2;
			}
		}
		System.out.println(time);
		System.out.println(cnt);
	}
	private static void bfs() {
		Deque<int[]> q=  new ArrayDeque<>();
		boolean [][] visit = new boolean[n][m];
		q.offer(new int[] {0, 0});
		visit[0][0] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			cheese[cur[0]][cur[1]] = 2;
			int nx = 0, ny = 0; 
			for(int i = 0; i < 4; i++) {
				nx = cur[0]+dx[i];
				ny = cur[1]+dy[i];
				if(0>nx||nx>=n || 0>ny||ny>=m || visit[nx][ny] || cheese[nx][ny] == 1) continue;
				visit[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
}