import java.io.*;
import java.util.*;

public class _2213 {
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] tree;
	static int[][] dp;
	static ArrayList<Integer>[][] dpList;
	static int[] w;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		w = new int[n+1]; // 가중치
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			w[i+1] = Integer.parseInt(s[i]);
		}
		
		adj = new ArrayList[n+1];
		tree = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			adj[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		boolean[] visited = new boolean[n+1];
		makeTree(1, visited);
		
		dp = new int[n+1][2]; // dp[i][0]: i를 포함하지 않았을 경우의 최대 독립집합의 크기 // dp[i][1]: i를 포함했을 경우의 최대 독립집합의 크기
		dpList = new ArrayList[n+1][2];
		for(int i=1; i<n+1; i++) {
			for(int j=0; j<2; j++)	dpList[i][j] = new ArrayList<>();
		}
		
		int state0 = dfs(1, 0); // 루트노드인 1을 포함하지 않았을 때
		int state1 = dfs(1, 1); // 포함했을 때
		
		StringBuilder sb = new StringBuilder();
		if(state0 > state1) {
			sb.append(state0 + "\n");
			ArrayList<Integer> res = dpList[1][0];
			Collections.sort(res);
			for(int i=0; i<res.size(); i++) {
				if(i != 0)	sb.append(" " + res.get(i));
				else	sb.append(res.get(i));
			}
		}else {
			sb.append(state1 + "\n");
			ArrayList<Integer> res = dpList[1][1];
			Collections.sort(res);
			for(int i=0; i<res.size(); i++) {
				if(i != 0)	sb.append(" " + res.get(i));
				else	sb.append(res.get(i));
			}
		}

		System.out.print(sb.toString());
	}
	
	private static int dfs(int now, int state) { // now가 state일때의 now를 루트로하는 서브트리에서의 최대 독립집합의 크기를 리턴
		if(dp[now][state] != 0)
			return dp[now][state];
		
		if(state == 0) { // 포함하지 않을 때 => 자식노드는 포함하거나 포함하지 않을 때 두 경우 중 큰 값으로(최대 독립집합의 크기를 구하니까)
			for(Integer next : tree[now]) {
				int state0 = dfs(next, 0); // 자식노드를 포함하지 않는 경우
				int state1 = dfs(next, 1); // 자식노드를 포함하는 경우
				
				if(state0 > state1) {
					dp[now][0] += state0;
					for(Integer n : dpList[next][0])	dpList[now][0].add(n);
				}else {
					dp[now][0] += state1;
					for(Integer n : dpList[next][1])	dpList[now][0].add(n);
				}
			}
			
			return dp[now][0];
			
		}else { // 포함할 때 => 자식노드는 포함하지 않을 경우
			dpList[now][1].add(now);
			for(Integer next : tree[now]) {
				dp[now][1] += dfs(next, 0);
				for(Integer n : dpList[next][0])
					dpList[now][1].add(n);
			}
			
			dp[now][1] += w[now];
			return dp[now][1];
		}
	}
	
	private static void makeTree(int now, boolean[] visited) {
		if(visited[now])	return;
		
		visited[now] = true;
		for(Integer next : adj[now]) {
			if(!visited[next]) {
				tree[now].add(next);
				makeTree(next, visited);
			}
		}
		
	}
}
