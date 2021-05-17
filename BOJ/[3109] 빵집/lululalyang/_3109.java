import java.io.*;
import java.util.*;
public class _3109 {
	static String[][] map;
	static int R, C;
	static int[] dx = {-1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		map = new String[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().split("");
		}
		
		boolean[][] visited = new boolean[R][C];
		boolean[][] chkDone = new boolean[R][C];
		for(int i=0; i<R; i++)	Arrays.fill(chkDone[i], true);
		
		int res = 0;
		for(int i=0; i<R; i++) {
			if(dfs(i, 0, visited, chkDone))	res++;
		}
		
		System.out.print(res);
	}

	private static boolean dfs(int x, int y, boolean[][] visited, boolean[][] chkDone) {
		if(y == (C-1))	return true;
		
		boolean flag = false;
		for(int k=0; k<3; k++) {
			int rx = x + dx[k];
			int ry = y + 1;
			
			if(Valid(rx, ry) && chkDone[rx][ry]) {
				if(map[rx][ry].equals(".") && !visited[rx][ry]) {
					visited[rx][ry] = true;
					if(dfs(rx, ry, visited, chkDone)) {
						flag = true;
						break;
					}
					visited[rx][ry] = false;
				}
			}
		}
		if(!flag)  // 이 곳은 아무곳도 갈 수 없다는 것
			chkDone[x][y] = false;
		
		return flag;
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<R && y>=0 && y<C);
	}
}
