import java.io.*;
import java.util.*;

public class _15681 {
	static int[] dp; // dp[i]: 노드 i를 루트로 하는 서브트리에 속한 정점의 수
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s;
		s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // 정점 개수
		int R = Integer.parseInt(s[1]); // 루트 번호
		int Q = Integer.parseInt(s[2]); // 쿼리 개수
		
		dp = new int[N+1];
		adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) adj[i] = new ArrayList<>();
		
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		Arrays.fill(dp, 1); // 자기자신뿐 => 1개로 초기화
		
		boolean[] visited = new boolean[N+1];
		dfs(R, visited);
		
		StringBuilder sb = new StringBuilder();
		for(int q=0; q<Q; q++) {
			int root = Integer.parseInt(br.readLine());
			if(q != 0)	sb.append("\n" + dp[root]);
			else	sb.append(dp[root]);
		}
		
		System.out.print(sb.toString());
	}
	
	private static int dfs(int now, boolean[] visited) { // now의 dp값을 리턴
		if(visited[now])	return dp[now];
		
		visited[now] = true;
		for(Integer next : adj[now]) {
			if(!visited[next])
				dp[now] += dfs(next, visited);
		}
		
		return dp[now];
	}
}
