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
		w = new int[n+1]; // ����ġ
		
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
		
		dp = new int[n+1][2]; // dp[i][0]: i�� �������� �ʾ��� ����� �ִ� ���������� ũ�� // dp[i][1]: i�� �������� ����� �ִ� ���������� ũ��
		dpList = new ArrayList[n+1][2];
		for(int i=1; i<n+1; i++) {
			for(int j=0; j<2; j++)	dpList[i][j] = new ArrayList<>();
		}
		
		int state0 = dfs(1, 0); // ��Ʈ����� 1�� �������� �ʾ��� ��
		int state1 = dfs(1, 1); // �������� ��
		
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
	
	private static int dfs(int now, int state) { // now�� state�϶��� now�� ��Ʈ���ϴ� ����Ʈ�������� �ִ� ���������� ũ�⸦ ����
		if(dp[now][state] != 0)
			return dp[now][state];
		
		if(state == 0) { // �������� ���� �� => �ڽĳ��� �����ϰų� �������� ���� �� �� ��� �� ū ������(�ִ� ���������� ũ�⸦ ���ϴϱ�)
			for(Integer next : tree[now]) {
				int state0 = dfs(next, 0); // �ڽĳ�带 �������� �ʴ� ���
				int state1 = dfs(next, 1); // �ڽĳ�带 �����ϴ� ���
				
				if(state0 > state1) {
					dp[now][0] += state0;
					for(Integer n : dpList[next][0])	dpList[now][0].add(n);
				}else {
					dp[now][0] += state1;
					for(Integer n : dpList[next][1])	dpList[now][0].add(n);
				}
			}
			
			return dp[now][0];
			
		}else { // ������ �� => �ڽĳ��� �������� ���� ���
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
