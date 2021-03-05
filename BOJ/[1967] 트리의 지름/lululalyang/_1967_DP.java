import java.io.*;
import java.util.*;

public class _1967_DP {
	static ArrayList<Edge>[] g;
	static int[] dist; // dist[v] = ���� v�� v���� ���� �� ����Ʈ�� "��" ���� ������ �Ÿ� = Max(dist[�ڽĳ��] + 1)
	static int[] dist2; // dist2[v] = ���� v�� v���� �ι�°�� �� ����Ʈ�� "��" ���� ������ �Ÿ�
	static int[] far; // parent_far[v] = ���� v�� v���� ���� �� ����Ʈ�� "��" ���� ������ �Ÿ�
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		g = new ArrayList[N+1]; // ��� 1 ~ (N+1)
		for(int i=1; i<N+1; i++)	g[i] = new ArrayList<>();
		dist = new int[N+1];
		dist2 = new int[N+1];
		far = new int[N+1];
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" "); // �θ���, �ڽĳ��, ���� ����ġ ��
			int u = Integer.parseInt(s[0]); // �θ� ���
			int v = Integer.parseInt(s[1]); // �ڽ� ���
			int w = Integer.parseInt(s[2]); // ���� ����ġ
			
			g[u].add(new Edge(v, w));
		}
		
		boolean[] visited = new boolean[N+1];
		dist_DFS(1, visited); // 1�� ��Ʈ��� => dist, dist2�� ����
		
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
				if(dist[now] == dist[next.v] + next.w) { // <�θ���(now)�� dist> �� <�ڽĳ��(next)�� dist + ����ġ>�� ������
					far_DFS(next.v, Math.max(parent_far+next.w, dist2[now]+next.w), visited); // �θ��忡�� ���� �� ������ �ڽĳ���� ����Ʈ�� �ִ� ����̴� 
																					// => dist2�� !				
				}else { // �θ� ������ ���� Ʈ���� ���� ���
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
				if(dist[next.v]+next.w > dist[now]) { // ���� �� ���� ������ => dist����
					dist2[now] = dist[now]; // 2����� ū ��
					dist[now] = dist[next.v] + next.w; // ���� ū ��
				}else if(dist[next.v]+next.w > dist2[now]) { // ���� �հ��� �ƴѵ�, �ι�°�� ū �����ٴ� Ŭ�� -> dist2 ����
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
