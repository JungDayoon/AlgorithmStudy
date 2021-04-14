import java.io.*;
import java.util.*;
public class _67260 {
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] path = new int[n-1][2];
		String[] s;
		for(int i=0; i<n-1; i++) {
			s = br.readLine().split(" ");
			path[i][0] = Integer.parseInt(s[0]);
			path[i][1] = Integer.parseInt(s[1]);
		}
		
		int m = Integer.parseInt(br.readLine());
		int[][] order = new int[m][2];
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			order[i][0] = Integer.parseInt(s[0]);
			order[i][1] = Integer.parseInt(s[1]);
		}
		
		System.out.print(solution(n, path, order));
	}
	
	private static boolean solution(int n, int[][] path, int[][] order) {
		adj = new ArrayList[n]; // 인접리스트 // 0번 ~  (n-1)번
		for(int i=0; i<n; i++)	adj[i] = new ArrayList<>();
		
		for(int i=0; i<n-1; i++) { 
			int u = path[i][0];
			int v = path[i][1];
			adj[u].add(v);
			adj[v].add(u);
		}
		
		boolean[] visited = new boolean[n]; // 방문했는지의 여부 
		int[] before = new int[n]; // 이전에 방문해야하는 노드
		int[] after = new int[n]; // 이후에 방문해야하는 노드
		
		int orderLen = order.length;
		for(int i=0; i<orderLen; i++)	before[order[i][1]] = order[i][0]; 
		if(before[0] != 0)	return false; // 0이전에 방문해야할 방이 있다면 false -> 0번방이 유일한 입구니까
		visited[0] = true;
		
		Stack<Integer> stack = new Stack<>();
		for(Integer next : adj[0])	stack.add(next);
		
		while(!stack.isEmpty()) {
			int now = stack.pop();
			if(visited[now]) continue; // 방문한 방이면 패스
			if(!visited[before[now]]) { // now이전에 방문할 방을 방문하지 않았다면
				after[before[now]] = now;  // 그 방 이후에 now 방문할 수 있도록 after에 저장
				continue;
			}
			
			visited[now] = true; // 두 경우가 아니면 현재 방 방문
			for(Integer next : adj[now]) 
				if(!visited[next])	stack.add(next);
			stack.add(after[now]);
		}
		
		for(int i=0; i<n; i++)
			if(!visited[i])	return false;
		return true;
	}
}
