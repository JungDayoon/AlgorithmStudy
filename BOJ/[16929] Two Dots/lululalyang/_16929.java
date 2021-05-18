import java.io.*;
public class _16929 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static String[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new String[N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().split("");
		}
		
		boolean flag = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				boolean[][] visited = new boolean[N][M];
				visited[i][j] = true;
				if(dfs(i, j, map[i][j], i, j, 1, visited)) {
					flag = true;
					break;
				}
			}
			if(flag)	break;
		}
		
		if(flag)	System.out.print("Yes");
		else	System.out.print("No");
	}
	
	private static boolean dfs(int x, int y, String color, int sx, int sy, int cnt, boolean[][] visited) {
		for(int k=0; k<4; k++) {
			int rx = x + dx[k];
			int ry = y + dy[k];
			
			if(Valid(rx, ry) && map[rx][ry].equals(color)) {
				if(!visited[rx][ry]) {
					visited[rx][ry] = true;
					boolean res = dfs(rx, ry, color, sx, sy, cnt+1, visited);
					if(res)	return true;
				}else if(rx==sx && ry==sy && cnt>=4) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
}
