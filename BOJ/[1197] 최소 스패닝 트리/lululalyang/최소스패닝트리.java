import java.io.*;
import java.util.*;

public class _1197 {
	static int V;
	static int E;
	static PriorityQueue<Edge> pq;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		V = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);
		
		pq = new PriorityQueue<>();
		for(int i=0; i<E; i++) {
			s = br.readLine().split(" ");
			int n1 = Integer.parseInt(s[0]);
			int n2 = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);
			pq.add(new Edge(n1, n2, w)); // edge������ ���� �̿� -> PriorityQueue�� ����
		}
		
		System.out.print(Kruskal());
	}
	
	static private void Make_set(){
		p = new int[V+1]; // ��Ʈ��� ����
		for(int i=1; i<p.length; i++)
			p[i] = i; // ó������ �ڱ� �ڽ����� ��Ʈ ��� ����
	}
	
	static private int find(int x) {
		if(x == p[x])	return x;
		p[x] = find(p[x]); // ��Ʈ ��带 ã���� �ٷ� p[x]���� �ٲ��� �� �ֵ��� �� -> �ð� ���̱� ����
		return p[x];
	}
	
	static private void union(int root1, int root2) {
		p[root2] = root1;
	}
	
	static private int Kruskal() {
		int resW = 0; // MST�� ����ġ 
		
		Make_set(); // V���� ��Ʈ��� ���� �� �ڱ� �ڽ����� �ʱ�ȭ
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int n1 = e.n1;
			int n2 = e.n2;
			int w = e.w;
			
			int root1 = find(n1);
			int root2 = find(n2);
			if(root1 == root2)	continue; // �� ����� ��Ʈ�� ���ٴ� ���� �� edge�� ���� MST�� �߰��Ǹ� ����Ŭ�� �̷�ٴ� ��
			
			union(root1, root2); // ���� ������ n2�� n1�� �ڼ����� �־� �� Ʈ���� ��ģ��
			resW += w; //mst�� ����ġ�� ���� edge�� ����ġ�� �߰��Ѵ�.
		}
		
		return resW;
	}
	static class Edge implements Comparable<Edge>{
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
