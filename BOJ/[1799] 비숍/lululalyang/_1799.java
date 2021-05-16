import java.io.*;
public class _1799 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int blackMax = 0;
	static int whiteMax = 0;
	static int[] dx = {-1, -1, 1, 1};
	static int[] dy = {-1, 1, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++)	map[i][j] = Integer.parseInt(s[j]);
		}
		
		visited = new boolean[N][N];
		Bishop_black(0, 0, 0);
		visited = new boolean[N][N];
		Bishop_white(0, 1, 0);
		System.out.print(blackMax + whiteMax);
	}
	
	private static void Bishop_black(int x, int y, int cnt) {
		blackMax = Math.max(blackMax, cnt);
		
		if(y >= N) {
			x++;
			y = (x%2 == 0)? 0 : 1;
		}
		
		if(x >= N)	return; // 체스판 모두 확인 완료
		
		if(ChkValid(x, y)) { // 놓을 수 있다면
			visited[x][y] = true;
			Bishop_black(x, y+2, cnt+1);
			visited[x][y] = false;
		}
		
		Bishop_black(x, y+2, cnt);
	}
	
	private static void Bishop_white(int x, int y, int cnt) {
		whiteMax = Math.max(whiteMax, cnt);
		
		if(y >= N) {
			x++;
			y = (x%2 == 0)? 1: 0;
		}
		
		if(x >= N)	return;
		
		if(ChkValid(x, y)) {
			visited[x][y] = true;
			Bishop_white(x, y+2, cnt+1);
			visited[x][y] = false;
		}
		
		Bishop_white(x, y+2, cnt);
	}
	
	private static boolean ChkValid(int x, int y) {
		if(map[x][y] == 0)	return false; // 비숍 놓을 수 없는 자리
		
		for(int k=0; k<4; k++) {
			int rx = x;
			int ry = y;
			
			while(Valid(rx+dx[k], ry+dy[k])) { // 범위 내라면
				rx += dx[k];
				ry += dy[k];
				if(visited[rx][ry])	return false; // 비솝이 있으면 안됨
			}
		}
		return true;
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
