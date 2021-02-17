import java.io.*;
import java.util.*;

public class _2146 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int N;
	static int Qidx = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 1)
					map[i][j] = -1;
			}
		}
		
		Initialize(); // 각 섬을 1부터의 자연수로 구분
		System.out.print(ComputeMinDist());
	}
	
	private static void AddtoQ(Queue<int[]> q, int idx, boolean[][] visited) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == idx) {
					q.add(new int[] {i, j, 0});
					visited[i][j] = true;
				}
			}
		}
	}
	
	private static int ComputeMinDist() {
		int MinDist = Integer.MAX_VALUE;
		for(int i=1; i<=Qidx; i++) {
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			AddtoQ(q, i, visited);
			
			boolean flag = false;
			while(!q.isEmpty()) {
				int[] now = q.poll();
				int x = now[0];
				int y = now[1];
				int dist = now[2];
				
				for(int k=0; k<4; k++) {
					int rx = x + dx[k];
					int ry = y + dy[k];
					if(Valid(rx, ry) && !visited[rx][ry]) {
						if(map[rx][ry] == 0) {
							q.add(new int[] {rx, ry, dist+1});
							visited[rx][ry] = true;
						}else {
							MinDist = Math.min(MinDist, dist);
							flag = true;
							break;
						}
					}
				}
				
				if(flag)	break;
			}
		}
		return MinDist;
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	
	private static void CompleteMap(int x, int y) {
		map[x][y] = ++Qidx;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y, 0});
		
		while(!q.isEmpty()){
			int[] now = q.poll();
			int nowx = now[0];
			int nowy = now[1];
			
			for(int k=0; k<4; k++) {
				int rx = nowx + dx[k];
				int ry = nowy + dy[k];
				
				if(Valid(rx, ry) && map[rx][ry]==-1) {
					map[rx][ry] = Qidx;
					q.add(new int[] {rx, ry, 0});					
				}
			}
		}
	}
	
	private static void Initialize() { // 섬 찾고
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == -1) {
					CompleteMap(i, j);
				}
			}
		}
//		System.out.println(Qidx);
	}
}
