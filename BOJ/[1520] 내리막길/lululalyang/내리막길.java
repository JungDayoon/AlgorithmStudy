import java.io.*;
import java.util.*;

public class _1520 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int M;
	static int N;
	static int[][] route;
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		M = Integer.parseInt(s[0]); //세로
		N = Integer.parseInt(s[1]); //가로
		map = new int[M][N];
		route = new int[M][N];
		
		for(int i=0; i<M; i++) {
			Arrays.fill(route[i], -1);
			String[] d = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(d[j]);
			}	
		}
		
		System.out.println(findRoute(M-1, N-1));
	}
	
	private static int findRoute(int x, int y) {
		if(route[x][y] != -1) // 이미 갔던 길이면 -> 그 위치까지의 경로수를 return
			return route[x][y];
		if(x==0 && y==0) 
			return 1;
		
		route[x][y] = 0;
		
		for(int k=0; k<4; k++) {
			int rx = x + dx[k];
			int ry = y + dy[k];
			
			if(rx>=0 && rx<M && ry>=0 && ry<N) {
				if(map[x][y] < map[rx][ry]) { // 갈 수 있는 길이면 => (rx,ry)에서 (x,y)로 올 수 있으면
					route[x][y] += findRoute(rx, ry); // (x,y)로의 경로 수는 (rx,ry)로의 경로수를 더한 것(상하좌우)
				}
			}
		}
		
		return route[x][y];
	}
}
