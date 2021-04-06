import java.io.*;
import java.util.*;

public class _1261 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int M = Integer.parseInt(s[0]); // 가로
		int N = Integer.parseInt(s[1]); // 세로
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			s = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		System.out.print(solution(M, N, map));
	}
	
	private static int solution(int M, int N, int[][] map) {
		int res = Integer.MAX_VALUE;
		int[][] breakWall = new int[N][M];
		for(int i=0; i<N; i++)	Arrays.fill(breakWall[i], Integer.MAX_VALUE);
		
		Queue<Wall> q = new LinkedList<>();
		q.add(new Wall(0, 0, 0));
		breakWall[0][0] = 0; // 시작점
		
		while(!q.isEmpty()) {
			Wall now = q.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.breakCnt;
			
			if(x==(N-1) && y==(M-1)) {
				res = Math.min(res, cnt);
				continue;
			}
			if(breakWall[x][y] < cnt)	continue;
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				int rcnt;
				
				if(Valid(N, M, rx, ry)) { // 범위내에 존재할 때
					if(map[rx][ry] == 1) { // 벽이면 => 부신다
						rcnt = cnt + 1; 
					}else {
						rcnt = cnt;
					}
					
					if(breakWall[rx][ry] > rcnt) {
						breakWall[rx][ry] = rcnt;
						q.add(new Wall(rx, ry, rcnt));
					}
				}
			}
		}
		
		return res;
	}
	
	private static boolean Valid(int N, int M, int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	private static class Wall{
		private int x;
		private int y;
		private int breakCnt; // 벽을 부신 개수
		
		Wall(int x, int y, int breakCnt){
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
		}
	}
}
