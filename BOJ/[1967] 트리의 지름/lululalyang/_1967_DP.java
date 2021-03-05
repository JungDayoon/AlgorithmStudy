import java.io.*;
import java.util.*;

public class _1967_DP {
	static ArrayList<Edge>[] g;
	static int[] dist; // dist[v] = 정점 v와 v에서 가장 먼 서브트리 "내" 정점 사이의 거리 = Max(dist[자식노드] + 1)
	static int[] dist2; // dist2[v] = 정점 v와 v에서 두번째로 먼 서브트리 "내" 정점 사이의 거리
	static int[] far; // parent_far[v] = 정점 v와 v에서 가장 먼 서브트리 "외" 정점 사이의 거리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		g = new ArrayList[N+1]; // 노드 1 ~ (N+1)
		for(int i=1; i<N+1; i++)	g[i] = new ArrayList<>();
		dist = new int[N+1];
		dist2 = new int[N+1];
		far = new int[N+1];
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" "); // 부모노드, 자식노드, 간선 가중치 순
			int u = Integer.parseInt(s[0]); // 부모 노드
			int v = Integer.parseInt(s[1]); // 자식 노드
			int w = Integer.parseInt(s[2]); // 간선 가중치
			
			g[u].add(new Edge(v, w));
		}
		
		boolean[] visited = new boolean[N+1];
		dist_DFS(1, visited); // 1이 루트노드 => dist, dist2를 저장
		
		visited = new boolean[N+1];
		far_DFS(1, 0, visited);
		int max = far[1];
		for(int i=2; i<N+1; i++)
			max = Math.max(max, far[i]);
		
		System.out.print(max);
	}
	
	private static void far_DFS(int now, int parent_far, boolean[] visited) {
		visited[now] = true;
		far[now] = Math.max(dist[now], parent_far);
		for(Edge next : g[now]) {
			if(!visited[next.v]) {
				if(dist[now] == dist[next.v] + next.w) { // <부모노드(now)의 dist> 가 <자식노드(next)의 dist + 가중치>와 같으면
					far_DFS(next.v, Math.max(parent_far+next.w, dist2[now]+next.w), visited); // 부모노드에서 가장 먼 정점이 자식노드의 서브트렝 있는 경우이다 
																					// => dist2로 !				
				}else { // 부모 정점의 서브 트리에 없는 경우
					far_DFS(next.v, Math.max(parent_far+next.w, dist[now]+next.w), visited);
				}
			}
		}
	}
	private static void dist_DFS(int now, boolean[] visited) {
		visited[now] = true;
		dist[now] = 0; 
		dist2[now] = 0;
		for(Edge next : g[now]) {
			if(!visited[next.v]) {
				dist_DFS(next.v, visited);
				if(dist[next.v]+next.w > dist[now]) { // 가장 먼 값이 나오면 => dist갱신
					dist2[now] = dist[now]; // 2번재로 큰 값
					dist[now] = dist[next.v] + next.w; // 가장 큰 값
				}else if(dist[next.v]+next.w > dist2[now]) { // 가장 먼값은 아닌데, 두번째로 큰 값보다는 클때 -> dist2 갱신
					dist2[now] = dist[next.v] + next.w;
				}
//				dist[now] = Math.max(dist[now], dist[next.v]+next.w);
			}
		}
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
