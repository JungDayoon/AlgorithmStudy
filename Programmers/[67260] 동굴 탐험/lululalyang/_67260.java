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
		adj = new ArrayList[n]; // ��������Ʈ // 0�� ~  (n-1)��
		for(int i=0; i<n; i++)	adj[i] = new ArrayList<>();
		
		for(int i=0; i<n-1; i++) { 
			int u = path[i][0];
			int v = path[i][1];
			adj[u].add(v);
			adj[v].add(u);
		}
		
		boolean[] visited = new boolean[n]; // �湮�ߴ����� ���� 
		int[] before = new int[n]; // ������ �湮�ؾ��ϴ� ���
		int[] after = new int[n]; // ���Ŀ� �湮�ؾ��ϴ� ���
		
		int orderLen = order.length;
		for(int i=0; i<orderLen; i++)	before[order[i][1]] = order[i][0]; 
		if(before[0] != 0)	return false; // 0������ �湮�ؾ��� ���� �ִٸ� false -> 0������ ������ �Ա��ϱ�
		visited[0] = true;
		
		Stack<Integer> stack = new Stack<>();
		for(Integer next : adj[0])	stack.add(next);
		
		while(!stack.isEmpty()) {
			int now = stack.pop();
			if(visited[now]) continue; // �湮�� ���̸� �н�
			if(!visited[before[now]]) { // now������ �湮�� ���� �湮���� �ʾҴٸ�
				after[before[now]] = now;  // �� �� ���Ŀ� now �湮�� �� �ֵ��� after�� ����
				continue;
			}
			
			visited[now] = true; // �� ��찡 �ƴϸ� ���� �� �湮
			for(Integer next : adj[now]) 
				if(!visited[next])	stack.add(next);
			stack.add(after[now]);
		}
		
		for(int i=0; i<n; i++)
			if(!visited[i])	return false;
		return true;
	}
}
