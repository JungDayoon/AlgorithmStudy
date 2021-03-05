import java.io.*;
import java.util.*;

public class _1967_Greedy {
	static ArrayList<Edge>[] g;
	static int[] dist; // dist[v] = (���� v)�� (v���� ���� �� ����Ʈ�� �� ����) ������ �Ÿ� == MAX(dist[�ڽĳ��] + w)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		g = new ArrayList[N+1]; // ��� 1 ~ (N+1)
		for(int i=1; i<N+1; i++)	g[i] = new ArrayList<>();
		dist = new int[N+1];
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" "); // �θ���, �ڽĳ��, ���� ����ġ ��
			int u = Integer.parseInt(s[0]); // �θ� ���
			int v = Integer.parseInt(s[1]); // �ڽ� ���
			int w = Integer.parseInt(s[2]); // ���� ����ġ
			
			g[u].add(new Edge(v, w));
			g[v].add(new Edge(u, w));
		}
		
		boolean[] visited = new boolean[N+1];
		// ������ ������ 1�̶�� ����
		int v2 = dist_DFS(1, visited); // ������ ���� v1(=1)���� ���� �� ���� v2ã�� ( v1�� ��Ʈ�� ����!)
		visited = new boolean[N+1];
		int v3 = dist_DFS(v2, visited); // v2�� v2���� ���� �� ���� v3�� ã�´�
		System.out.print(dist[v2]); // dist[v2]�� v2���� ���� �� �Ÿ� => v3���� �Ÿ� => ����
	}
	
	private static int dist_DFS(int now, boolean[] visited) {
		visited[now] = true;
		int farNode = now;
		dist[now] = 0;
		for(Edge next : g[now]) {
			if(!visited[next.v]) {
				int farNode_next = dist_DFS(next.v, visited); // �ڽĳ�� next���� next�� ����Ʈ�� �� ���� �� ����
				if(dist[next.v] + next.w > dist[now]) {
					dist[now] = dist[next.v] + next.w;
					farNode = farNode_next;
				}
				
			}
		}
		return farNode; // now���� now�� ����Ʈ�� �� ���� �� ���� farNode
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
