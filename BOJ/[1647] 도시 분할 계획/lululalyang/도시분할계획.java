import java.io.*;
import java.util.*;

public class _1647 {
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int V = Integer.parseInt(s[0]);
		int E = Integer.parseInt(s[1]);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i=0; i<E; i++) {
			s = br.readLine().split(" ");
			int n1 = Integer.parseInt(s[0]);
			int n2 = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);
			pq.add(new Edge(n1, n2, w));
		}
		
		System.out.print(Kruskal(V, E, pq));
	}
	
	static private void make_set(int[] p) {
		for(int i=1; i<p.length; i++) {
			p[i] = i;
		}
	}
	
	static private int find(int x) {
		if(p[x] == x) return x;
		p[x] = find(p[x]);
		return p[x];
	}
	
	static private void union(int root1, int root2) { // 2�� 1�� �ڼ�����
		p[root2] = root1;
	}
	static private int Kruskal(int V, int E, PriorityQueue<Edge> pq) {
		int res = 0;
		p = new int[V+1];
		
		make_set(p); // ���� �ڱ��ڽŸ����� �̷���� Ʈ�� ����
		int MaxW = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int n1 = e.n1;
			int n2 = e.n2;
			int w = e.w;
			
			int root1 = find(n1); // ������ ��Ʈ��� ã��
			int root2 = find(n2);
			if(root1 == root2)	continue; // �� ��Ʈ��尡 ������ ����Ŭ ���� -> ���� ���� Ȯ��
			
			union(root1, root2); // �׷��� ������ �� Ʈ�� ��ģ��.
			MaxW = Math.max(MaxW, w); // ���ƴٸ� ���� ū w�� �����صд�.
			res += w;
		}
		
		return (res - MaxW); // MST�� ����ġ���� ���� ū ����ġ�� �� �� (�� ������ �����ϱ�) 
	}
	static private class Edge implements Comparable<Edge>{
		int n1;
		int n2;
		int w;
		
		Edge(int n1, int n2, int w){
			this.n1 = n1;
			this.n2 = n2;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return (this.w >= e.w) ? 1 : -1; //���� ��ü�� ����ġ�� ���޹��� ��ü�� ����ġ���� �۰ų������� -1, ũ�� 1
		}
	}
}
