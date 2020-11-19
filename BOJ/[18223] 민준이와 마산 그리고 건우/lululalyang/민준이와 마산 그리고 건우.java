import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _18223 {
	static ArrayList<Integer>[] parents;
	static int flag = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int V = Integer.parseInt(s[0]); // 정점 개수  2<=V<=5000
		int E = Integer.parseInt(s[1]); // 간선 개수 1<=E<=10000 
		int P = Integer.parseInt(s[2]); // 건우 1<=P<=V
		parents = new ArrayList[V+1]; //부모노드를 이어준다 (경로가 여러개일수있으니까)
		
		ArrayList<Edge>[] adj = new ArrayList[V+1];
		for(int i=1; i<V+1; i++)
			adj[i] = new ArrayList<>();
		
		for(int e=0; e<E; e++) {
			String[] d = br.readLine().split(" ");
			int x = Integer.parseInt(d[0]);
			int y = Integer.parseInt(d[1]);
			int dist = Integer.parseInt(d[2]);
			adj[x].add(new Edge(y, dist));
			adj[y].add(new Edge(x, dist)); // 양방향
		}
		
		int[] D = new int[V+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[1] = 0;
		
		
		for(int i=1; i<V+1; i++)
			parents[i] = new ArrayList<>();
		
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for(Edge next : adj[now.v]) {
				if(!visited[next.v] && D[next.v]>D[now.v]+next.w) {
					D[next.v] = D[now.v] + next.w;
					pq.add(new Edge(next.v, D[next.v]));
					
					parents[next.v].clear(); // 더 작은 최단거리가 생겼으니까 원래 있던 부모노드 없애주고
					parents[next.v].add(now.v); // 새 부모노드 붙여줌
				}else if(!visited[next.v] && D[next.v]==D[now.v]+next.w){
					parents[next.v].add(now.v);
				}
			
			}
			visited[now.v] = true;
		}
		
		findGeonwoo(V, P);
		if(flag == 1)
			System.out.print("SAVE HIM");
		else
			System.out.println("GOOD BYE");
	}
	
	static void findGeonwoo(int here, int P) {
		if(here == 1) { // 출발지오면
			if(here == P) //1번에 건우가 있을수도 있다
				flag = 1;
			
			return;
		}
		
		if(here == P) { // 건우 찾으면
			flag = 1;
			return;
		}
		
		for(Integer p : parents[here]) {
			findGeonwoo(p, P);
			if(flag == 1)
				return;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int v;
		int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			if(this.w > o.w)
				return 1;
			else if(this.w < o.w)
				return -1;
			else
				return 0;
		}
	}
}
