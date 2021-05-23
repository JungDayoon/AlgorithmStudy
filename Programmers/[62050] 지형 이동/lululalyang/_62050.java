import java.util.*;
public class _62050 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	public static void main(String[] args) {
		int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
//		int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
		int h = 3;
		
		System.out.print(solution(land, h));
	}
	
	private static int solution(int[][] land, int height) {
		N = land.length;
		int[][] sep = new int[N][N]; // 구역 나누기
		int sidx = 0;
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				if(sep[i][j] == 0) {
					bfs(i, j, land, sep, height, ++sidx);
				}
			}
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		GetEdge(sep, pq, land); // 구역 사이 edge 구하기
		
		int res = Kruskal(pq, sidx);
		return res;
	}
	
	private static int Kruskal(PriorityQueue<Edge> pq, int N) {
		int cost = 0;
		int[] p = new int[N+1]; // 1~N
		for(int i=1; i<=N; i++)	p[i] = i; // 자기자신만의 트리 (make-set)
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int u = now.u;
			int v = now.v;
			int w = now.w;
			
			int pu = find(u, p);
			int pv = find(v, p);
			
			if(pu == pv)	continue; // 사이클 생성하면 pass
			
			cost += w;
			p[pv] = pu; // union
		}
		
		return cost;
	}
	
	private static int find(int v, int[] p) {
		if(p[v] == v)	return v;
		
		p[v] = find(p[v], p);
		return p[v];
	}
	
	private static void GetEdge(int[][] map, PriorityQueue<Edge> pq, int[][] land) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int now = map[i][j];
				int nowland = land[i][j];
				for(int k=1; k<3; k++) {
					int ri = i + dx[k];
					int rj = j + dy[k];
					
					if(Valid(ri, rj) && now!=map[ri][rj]) { // 서로 다른 구역이라면
						pq.add(new Edge(now, map[ri][rj], Math.abs(land[ri][rj] - nowland)));
					}
				}
			}
		}
	}
	
	private static void bfs(int x, int y, int[][] land, int[][] sep, int height, int sidx) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		sep[x][y] = sidx;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			x = now[0];
			y = now[1];
			int h = land[x][y];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry) && sep[rx][ry]==0 && Math.abs(h-land[rx][ry])<=height) {
					q.add(new int[] {rx ,ry});
					sep[rx][ry] = sidx;
				}
			}
		}
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 &&y<N);
	}
	
	private static class Edge implements Comparable<Edge>{
		int u;
		int v;
		int w;
		
		Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return (this.w - e.w);
		}
	}
}
