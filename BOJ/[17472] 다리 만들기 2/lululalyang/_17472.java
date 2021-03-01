import java.io.*;
import java.util.*;

public class _17472 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 1)	map[i][j] = -1;
			}
		}
		
		int IslandCnt = DivideIsland(map);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		MakeBridge(IslandCnt, pq, map); // 섬 사이 각 다리(Edge) pq에 
		
		System.out.print(Kruskal(IslandCnt, pq));
	}
	
	private static int find(int x, int[] p) {
		if(p[x] == x)	return x;
		p[x] = find(p[x], p);
		return p[x];
	}
	
	private static int Kruskal(int IslandCnt, PriorityQueue<Edge> pq) {
		int res = 0;
		int[] p = new int[IslandCnt+1];
		for(int i=1; i<=IslandCnt; i++) {
			p[i] = i; // make-set
		}
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int v1 = now.v1;
			int v2 = now.v2;
			int w = now.w;
			
			int root1 = find(v1, p); // find
			int root2 = find(v2, p);
			if(root1 == root2)	continue; // 루트노드 같으면 -> 사이클 생성 -> 패스
			
			res += w;
			p[root2] = root1; // union // 두 트리를 하나로 합친다 
		}
		
		int root = find(1, p);
		for(int i=2; i<=IslandCnt; i++) { // 모두의 루트노드가 같아야 하나로 연결된 것
			if(find(i, p) != root)
				return -1;
		}
		return res;
	}
	private static void AddtoQ(int IslandCnt, Queue<int[]> q, int[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == IslandCnt) {
					for(int k=0; k<4; k++)
						q.add(new int[] {i, j, 0, k}); // 좌표, 길이, 방향
				}
			}
		}
	}
	
	private static void MakeBridge(int IslandCnt, PriorityQueue<Edge> pq, int[][] map) {
		for(int i=1; i<=IslandCnt; i++) {
			Queue<int[]> q = new LinkedList<>();
			AddtoQ(i, q, map);
			
			boolean[] chkIsland = new boolean[IslandCnt+1]; // 해당 섬까지 다리를 만들었는지 여부
			chkIsland[i] = true;
			while(!q.isEmpty()) {
				int[] now = q.poll();
				int x = now[0];
				int y = now[1]; 
				int len = now[2];
				int dir = now[3];
				
				int rx = x + dx[dir];
				int ry = y + dy[dir];
				
				if(Valid(rx, ry, map.length, map[0].length)) {
					int loc = map[rx][ry]; // 해당 위치 정보
					if(loc == 0) { // 바다면
						q.add(new int[] {rx, ry, len+1, dir});
					}else if(loc != i){ // 다른 섬이면 (현재 섬은 i)
						if(!chkIsland[loc] && len>=2) {
							pq.add(new Edge(i, loc, len));
							chkIsland[loc] = true; // 해당 섬까지의 다리는 만들었음
						}
					}
				}
			}
		}
	}
	private static int MakeIsland(int x, int y, int IslandCnt, int[][] map) {
		IslandCnt++;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		map[x][y] = IslandCnt;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			x = now[0];
			y = now[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, map.length, map[0].length) && map[rx][ry]==-1) {
					map[rx][ry] = IslandCnt;
					q.add(new int[] {rx, ry});
				}
			}
		}
		
		return IslandCnt;
	}
	
	private static boolean Valid(int x, int y, int N, int M) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	private static int DivideIsland(int[][] map) { // 섬 나누기
		int IslandCnt = 0;
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == -1) {
					IslandCnt = MakeIsland(i, j, IslandCnt, map);
				}
			}
		}
		return IslandCnt;
	}
	
	private static class Edge implements Comparable<Edge>{
		private int v1;
		private int v2;
		private int w;
		
		Edge(int v1, int v2, int w){
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return (this.w >= e.w)? 1 : -1;
		}
		
		public String toString() {
			return v1 + " " + v2 + " " + w;
		}
	}
}
