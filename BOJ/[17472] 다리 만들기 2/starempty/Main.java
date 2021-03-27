import java.io.*;
import java.util.*;
public class Main {
	static int[][] arr;
	static ArrayList<int[]> island = new ArrayList<>();
	static int[] dx = {1,-1,0, 0};
	static int[] dy = {0, 0,1,-1};
	static int n, m;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) arr[i][j] = -2;
				if(arr[i][j] == 1) {
					island.add(new int[] {i, j});
					arr[i][j] = -1;
				}
			}
		}
		//섬 구분
		int cnt = 0;
		for(int[] v: island) {
			if(arr[v[0]][v[1]] != -1) continue;
			bfs(v, cnt++);
		}
		
		//각 섬을 연결하는 최단 거리 찾기
		int[][] adjMatrix = new int[cnt][cnt];
		for(int i = 0; i < cnt; i++) {
			for(int j = 0; j < cnt; j++) {
				if(i == j) continue;
				adjMatrix[i][j] = calculateMinDist(i, j);
			}
		}
		int ans = 0;
		int[] dist = new int[cnt];
		for(int i = 0; i < cnt; i++) {
			dist[i] = adjMatrix[0][i];
		}
		boolean[] visit = new boolean[cnt];
		for(int c = 1; c < cnt; c++) {
			int min = INF;
			int minV = 0;
			for(int i = 1; i < cnt; i++) {
				if(!visit[i] && min > dist[i]) {
					min = dist[i];
					minV = i;
				}
			}
			if(minV == 0) {
				System.out.println(-1);
				return;
			}
			ans += min;
			visit[minV] = true;
			for(int i = 1; i < cnt; i++) {
				if(visit[i] || adjMatrix[minV][i] == 0 || adjMatrix[minV][i] == INF) continue;
				dist[i] = Math.min(dist[i], adjMatrix[minV][i]);
			}
		}
		System.out.println(ans==INF?-1:ans);
	}
	
	private static void bfs(int[] v, int cnt) {
		Deque<int[]> q = new ArrayDeque<>();
		
		if(arr[v[0]][v[1]] != -1) return;
		
		arr[v[0]][v[1]] = cnt;
		q.offer(v);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			int nx = 0, ny = 0;
			for(int d = 0; d < 4; d++) {
				nx = cur[0]+dx[d];
				ny = cur[1]+dy[d];
				if(0>nx||nx>=n || 0>ny||ny>=m || arr[nx][ny] != -1) continue;
				arr[nx][ny] = cnt;
				q.offer(new int[] {nx, ny});
			}
		}
	}
	
	private static int calculateMinDist(int d1, int d2) {
		int min = INF;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] != d1) continue;
				min = Math.min(min, bridge(i, j, d2));
			}
		}
		return min;
	}
	
	private static int bridge(int x, int y, int d2) {
		int value = INF;
		for(int d = 0; d < 4; d++) {
			int bcnt = 0;
			int nx = x, ny = y;
			while(true) {
				nx += dx[d];
				ny += dy[d];
				if(0>nx||nx>=n || 0>ny||ny>=m) break;
				if(arr[nx][ny] == d2) {
					if(bcnt >= 2) value = Math.min(value, bcnt);
					break;
				}
				if(arr[nx][ny] != -2) break;
				bcnt++;
			}
		}
		return value;
	}
}