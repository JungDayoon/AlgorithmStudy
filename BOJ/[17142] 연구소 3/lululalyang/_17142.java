import java.io.*;
import java.util.*;
public class _17142 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static ArrayList<int[]> Birus = new ArrayList<>();
	static int Min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 2)	Birus.add(new int[] {i, j});
			}
		}
		
		ArrayList<Integer> tmp = new ArrayList<>();
		Comb(Birus.size(), M, 0, tmp);
		
		if(Min == Integer.MAX_VALUE)	Min = -1;
		System.out.print(Min);
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			SpreadBirus(tmp);
			return;
		}
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static void SpreadBirus(ArrayList<Integer> bLoc) {
		int N = map.length;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		for(Integer i : bLoc) {
			int[] now = Birus.get(i);
			int x = now[0];
			int y = now[1];
			q.add(new int[] {x, y, 0});
			visited[x][y] = true;
		}
		
		int res = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int t = now[2];
			
			if(map[x][y]==0) // 빈공간으로 퍼졌을때만
				res = Math.max(res, t);
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N) && map[rx][ry]!=1 && !visited[rx][ry]) { // 벽이 아니고 방문하지 않은 곳일때
					q.add(new int[] {rx, ry, t+1});
					visited[rx][ry] = true;
					
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j]==0)	return; // 빈공간인데 방문하지 않은 곳이 있다면 // 바이러스를 퍼뜨릴 수 없는 곳
			}
		}
		
		Min = Math.min(Min, res);
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
