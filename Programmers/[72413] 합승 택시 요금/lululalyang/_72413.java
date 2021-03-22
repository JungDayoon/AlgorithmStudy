import java.io.*;
import java.util.*;

public class _72413 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int s = Integer.parseInt(br.readLine());
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int flen = Integer.parseInt(br.readLine());
		int[][] fares = new int[flen][3];
		String[] S;
		for(int i=0; i<flen; i++) {
			S = br.readLine().split(" ");
			for(int j=0; j<3; j++)	fares[i][j] = Integer.parseInt(S[j]);
		}
		
		System.out.println(solution(n, s, a, b, fares));
	}
	
	private static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        ArrayList<Node>[] adj = new ArrayList[n+1];
        for(int i=1; i<n+1; i++)	adj[i] = new ArrayList<>();
        int fLen = fares.length;
        for(int i=0; i<fLen; i++) {
        	int u = fares[i][0];
        	int v = fares[i][1];
        	int w = fares[i][2];
        	
        	adj[u].add(new Node(v, w));
        	adj[v].add(new Node(u, w)); // �����
        }
        
        int[] StoAllDist = Dijkstra(adj, s, n);
        answer = StoAllDist[a] + StoAllDist[b]; // �ս����� �ʾ��� ���� ���
        
        for(int i=1; i<n+1; i++) {
        	if(i != s) { // ������� ������ ������ ������ ������� 
        		int ans = StoAllDist[i]; // ��������� i������ ���
        		int[] dist = Dijkstra(adj, i, n); // i���� ������ �������� ���
        		ans += dist[a];
        		ans += dist[b]; // i���� a, b������ ��� => i������ �ս�, �׵ڷ� ���� Ÿ�� ���
        		answer = Math.min(answer, ans);
        	}
        }
        
        return answer;
    }
	
	private static int[] Dijkstra(ArrayList<Node>[] adj, int s, int n) {
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int nowV = now.v;
			int nowW = now.w;
			
			if(dist[nowV] < nowW)	continue;
			
			// ���� dist������ �۰ų� ���� ���� ���� ��� �������ش�
			for(Node next : adj[nowV]) {
				int nextV = next.v;
				int shorter = dist[nowV] + next.w;
				if(dist[nextV] > shorter) {
					dist[nextV] = shorter;
					pq.add(new Node(nextV, shorter));
				}
			}
		}
		
		return dist;
	}
	
	
	private static class Node implements Comparable<Node>{
		private int v;
		private int w;
		
		Node(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.w >= n.w? 1 : -1;
		}
	}
}
