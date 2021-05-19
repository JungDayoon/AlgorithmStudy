import java.io.*;
import java.util.*;
public class _16946 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static int[][] gcnt;
	static int[][] gnum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int[][] ans = new int[N][M];
		solution(ans);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if(i != 0)	sb.append("\n");
			for(int j=0; j<M; j++) {
				sb.append(ans[i][j]);
			}
		}
		System.out.print(sb.toString());
	}
	
	private static void solution(int[][] ans) {
		gnum = new int[N][M];
		gcnt = new int[N][M];
		
		int chk = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0 && gnum[i][j]==0) { // 빈칸이고, 군집 번호가 아직 정해지지 않은 칸일 때
					bfs(i, j, chk);
					chk++;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) { // 벽이라면
					Map<Integer, Boolean> done = new HashMap<>(); // 확인한 군집 번호
					int sum = 1; // 자기자신 포함
					for(int k=0; k<4; k++) {
						int rx = i + dx[k];
						int ry = j + dy[k];
						if(Valid(rx, ry) && !done.containsKey(gnum[rx][ry])) {
							sum += gcnt[rx][ry];
							done.put(gnum[rx][ry], true);
						}
					}
					ans[i][j] = sum%10;
				}
			}
		}
	}
	
	private static void bfs(int x, int y, int chk) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		gnum[x][y] = chk; // gnum으로 방문여부 체크
		
		int cnt = 0;
		ArrayList<int[]> loc = new ArrayList<>();
		while(!q.isEmpty()) {
			int[] now = q.poll();
			x = now[0];
			y = now[1];
			cnt++;
			loc.add(new int[] {x, y});
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry) && map[rx][ry]==0 && gnum[rx][ry]==0) {
					q.add(new int[] {rx, ry});
					gnum[rx][ry] = chk;
				}
			}
		}
		
		cnt %= 10;
		
		for(int[] now : loc) {
			x = now[0];
			y = now[1];
			
			gcnt[x][y] = cnt;
		}
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
}
