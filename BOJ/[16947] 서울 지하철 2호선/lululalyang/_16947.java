import java.io.*;
import java.util.*;
public class _16947 {
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++)	adj[i] = new ArrayList<>();
		
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		int[] nextNode = new int[N+1];
		boolean[] visited = new boolean[N+1];
		int last = dfs(1, nextNode, visited);
		
		boolean[] cycle = new boolean[N+1];
		ChkCycle(last, cycle, nextNode);
		
		int[] ans = new int[N+1];
		solution(N, ans, cycle);
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N+1; i++) {
			if(i != 1)	sb.append(" ");
			sb.append(ans[i]);
		}
		System.out.print(sb.toString());
	}
	
	private static void solution(int N, int[] ans, boolean[] cycle) {
		for(int i=1; i<N+1; i++) {
			if(cycle[i]) { // 사이클이면 0
				ans[i] = 0;
				continue;
			}
			// 그렇지 않으면 bfs로 거리 계산
			ans[i] = bfs(i, N, cycle);
		}
	}
	
	private static int bfs(int v, int N, boolean[] cycle) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.add(new int[] {v, 0});
		visited[v] = true;
		
		int res = -1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			v = now[0];
			int dist = now[1];
			if(cycle[v]) {
				res = dist;
				break;
			}
			
			for(Integer next : adj[v]) {
				if(!visited[next]) {
					visited[next] = true;
					q.add(new int[] {next, dist+1});
				}
			}
		}
		
		return res;
	}
	
	private static void ChkCycle(int now, boolean[] cycle, int[] nextNode) {
		cycle[now] = true;
		if(cycle[nextNode[now]])	return;
		ChkCycle(nextNode[now], cycle, nextNode);
	}
	
	private static int dfs(int now, int[] nextNode, boolean[] visited) {
		visited[now] = true;
		for(Integer next : adj[now]) {
			if(nextNode[next] != now) { // 바로 이전 노드가 아니고
				if(!visited[next]) {
					nextNode[now] = next;
					int last = dfs(next, nextNode, visited);
					if(last != -1)	return last;
				}else {
					nextNode[now] = next;
					return next; // 사이클 찾음
				}
			}
		}
		
		visited[now] = false;
		return -1;
	} 
}
