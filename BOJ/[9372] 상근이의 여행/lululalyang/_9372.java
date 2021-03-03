import java.io.*;
import java.util.*;

public class _9372 {
	static ArrayList<Integer>[] g;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] s;
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			if(sb.length() != 0)
				sb.append("\n");
			
			s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]);
			g = new ArrayList[N+1];
			for(int i=1; i<N+1; i++)
				g[i] = new ArrayList<>();
			int M = Integer.parseInt(s[1]);
			
			for(int i=0; i<M; i++) {
				s = br.readLine().split(" ");
				int u = Integer.parseInt(s[0]);
				int v = Integer.parseInt(s[1]);
				g[u].add(v);
				g[v].add(u);
			}
			cnt = 0;
			boolean[] visited = new boolean[N+1];
			visited[1] = true;
			dfs(1, visited);
			
			sb.append(cnt);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void dfs(int now, boolean[] visited) {
		Iterator<Integer> itr = g[now].iterator();
		while(itr.hasNext()) {
			int next = itr.next();
			if(!visited[next]) {
				visited[next] = true;
				cnt++;
				dfs(next, visited);
			}
		}
	}

}
