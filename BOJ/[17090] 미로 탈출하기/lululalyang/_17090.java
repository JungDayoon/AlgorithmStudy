import java.io.*;
public class _17090 {
	static String[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new String[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().split("");
		}
		
		System.out.print(solution());
	}
	
	private static int solution() {
		int cnt = 0;
		boolean[][] escape = new boolean[N][M];
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!escape[i][j]) {
					escape[i][j] = dfs(i, j, escape, visited);
				}
		
				if(escape[i][j])	cnt++;
			}
		}
		
		return cnt;
	}
	
	private static boolean dfs(int x, int y, boolean[][] escape, boolean[][] visited) {
		if(!Valid(x, y))	return true; // Å»Ãâ
		
		if(!visited[x][y]) {
			visited[x][y] = true;
			int dir = StrToInt(map[x][y]);
			int rx = x + dx[dir];
			int ry = y + dy[dir];
			
			escape[x][y] = dfs(rx, ry, escape, visited);
		}
		
		return escape[x][y];
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	private static int StrToInt(String dir) {
		if(dir.equals("U"))	return 0;
		else if(dir.equals("R"))	return 1;
		else if(dir.equals("D"))	return 2;
		else	return 3; // "L"
	}
}
