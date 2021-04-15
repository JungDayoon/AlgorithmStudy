import java.io.*;
import java.util.*;

public class Main_bj_17144_미세먼지안녕 {
	static int[][] map;
	static List<int[]> micro;
	static int r, c;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		
		int g = 0; //공기 청정기 위치 (g-1, 0), (g, 0);
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					g = i;
				}
			}
		}
		while(T-- > 0) {
			//미세먼지 찾기
			micro = new ArrayList<>();
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					if(map[i][j] > 0) micro.add(new int[] {i, j, map[i][j]});
				}
			}
			//확산
			difuse();
			//바람
			windUpper(g-1);
			windLower(g);
		}
		int ans = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				ans += map[i][j];
			}
		}
		System.out.println(ans+2);
	}
	
	static int[] dx = {1,-1,0, 0};
	static int[] dy = {0, 0,1,-1};
	private static void difuse() {
		
		for(int i = 0; i < micro.size(); i++) {
			int cur[] = micro.get(i);
			int x = cur[0], y = cur[1];
			int v = cur[2]/5;
			int cnt = 0;
			for(int d = 0; d < 4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(0>nx||nx>=r || 0>ny||ny>=c || map[nx][ny] == -1) continue;
				cnt++;
				map[nx][ny] += v;
			}
			if(map[x][y] > cur[2]) map[x][y] = map[x][y]-cur[2] + cur[2]-v*cnt;
			else map[x][y] = cur[2]-v*cnt;
		}
	}
	
	private static void windUpper(int g) {
		for(int i = g-1; i > 0; i--) {
			map[i][0] = map[i-1][0]; 
		}
		for(int i = 0; i < c-1; i++) {
			map[0][i] = map[0][i+1];
		}
		for(int i = 0; i < g; i++) {
			map[i][c-1] = map[i+1][c-1];
		}
		for(int i = c-1; i > 0; i--) {
			map[g][i] = map[g][i-1];
		}
		map[g][1] = 0; 
	}
	private static void windLower(int g) {
		for(int i = g+1; i < r-1; i++) {
			map[i][0] = map[i+1][0];
		}
		for(int i = 0; i < c-1; i++) {
			map[r-1][i] = map[r-1][i+1];
		}
		for(int i = r-1; i > g; i--) {
			map[i][c-1] = map[i-1][c-1];
		}
		for(int i = c-1; i > 0; i--) {
			map[g][i] = map[g][i-1];
		}
		map[g][1] = 0;
	}
}
