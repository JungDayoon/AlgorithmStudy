import java.io.*;
import java.util.*;

public class _1967_Greedy {
	static ArrayList<Edge>[] g;
	static int[] dist; // dist[v] = (정점 v)와 (v에서 가장 먼 서브트리 내 정점) 사이의 거리 == MAX(dist[자식노드] + w)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		g = new ArrayList[N+1]; // 노드 1 ~ (N+1)
		for(int i=1; i<N+1; i++)	g[i] = new ArrayList<>();
		dist = new int[N+1];
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" "); // 부모노드, 자식노드, 간선 가중치 순
			int u = Integer.parseInt(s[0]); // 부모 노드
			int v = Integer.parseInt(s[1]); // 자식 노드
			int w = Integer.parseInt(s[2]); // 간선 가중치
			
			g[u].add(new Edge(v, w));
			g[v].add(new Edge(u, w));
		}
		
		boolean[] visited = new boolean[N+1];
		// 임의의 정점을 1이라고 가정
		int v2 = dist_DFS(1, visited); // 임의의 정점 v1(=1)에서 가장 먼 정점 v2찾기 ( v1을 루트라 생각!)
		visited = new boolean[N+1];
		int v3 = dist_DFS(v2, visited); // v2와 v2에서 가장 먼 정점 v3를 찾는다
		System.out.print(dist[v2]); // dist[v2]가 v2에서 가장 먼 거리 => v3와의 거리 => 지름
	}
	
	private static int dist_DFS(int now, boolean[] visited) {
		visited[now] = true;
		int farNode = now;
		dist[now] = 0;
		for(Edge next : g[now]) {
			if(!visited[next.v]) {
				int farNode_next = dist_DFS(next.v, visited); // 자식노드 next에서 next의 서브트리 중 가장 먼 정점
				if(dist[next.v] + next.w > dist[now]) {
					dist[now] = dist[next.v] + next.w;
					farNode = farNode_next;
				}
				
			}
		}
		return farNode; // now에서 now의 서브트리 중 가장 먼 정점 farNode
	}
	
	private static class Edge{
		private int v;
		private int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		public String toString() {
			return "(v: " + v + " w: " + w + ")";
		}
	}
}
