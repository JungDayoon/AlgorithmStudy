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
	
	static private void union(int root1, int root2) { // 2를 1의 자손으로
		p[root2] = root1;
	}
	static private int Kruskal(int V, int E, PriorityQueue<Edge> pq) {
		int res = 0;
		p = new int[V+1];
		
		make_set(p); // 각자 자기자신만으로 이루어진 트리 생성
		int MaxW = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int n1 = e.n1;
			int n2 = e.n2;
			int w = e.w;
			
			int root1 = find(n1); // 각각의 루트노드 찾기
			int root2 = find(n2);
			if(root1 == root2)	continue; // 두 루트노드가 같으면 사이클 생성 -> 다음 엣지 확인
			
			union(root1, root2); // 그렇지 않으면 두 트리 합친다.
			MaxW = Math.max(MaxW, w); // 합쳤다면 가장 큰 w를 저장해둔다.
			res += w;
		}
		
		return (res - MaxW); // MST의 가중치에서 가장 큰 가중치를 뺀 값 (두 마을로 나누니까) 
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
			return (this.w >= e.w) ? 1 : -1; //현재 객체의 가중치가 전달받은 객체의 가중치보다 작거나같으면 -1, 크면 1
		}
	}
}
