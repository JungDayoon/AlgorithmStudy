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
			pq.add(new Edge(n1, n2, w)); // edge정렬은 힙을 이용 -> PriorityQueue로 구현
		}
		
		System.out.print(Kruskal());
	}
	
	static private void Make_set(){
		p = new int[V+1]; // 루트노드 저장
		for(int i=1; i<p.length; i++)
			p[i] = i; // 처음에는 자기 자신으로 루트 노드 지정
	}
	
	static private int find(int x) {
		if(x == p[x])	return x;
		p[x] = find(p[x]); // 루트 노드를 찾으면 바로 p[x]값을 바꿔줄 수 있도록 함 -> 시간 줄이기 위함
		return p[x];
	}
	
	static private void union(int root1, int root2) {
		p[root2] = root1;
	}
	
	static private int Kruskal() {
		int resW = 0; // MST의 가중치 
		
		Make_set(); // V개의 루트노드 생성 및 자기 자신으로 초기화
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int n1 = e.n1;
			int n2 = e.n2;
			int w = e.w;
			
			int root1 = find(n1);
			int root2 = find(n2);
			if(root1 == root2)	continue; // 두 노드의 루트가 같다는 것은 이 edge가 기존 MST에 추가되면 사이클을 이룬다는 것
			
			union(root1, root2); // 같지 않으면 n2를 n1의 자손으로 넣어 두 트리를 합친다
			resW += w; //mst의 가중치에 현재 edge의 가중치를 추가한다.
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
			return (this.w >= e.w) ? 1 : -1; //현재 객체의 가중치가 전달받은 객체의 가중치보다 작거나같으면 -1, 크면 1
		}
	}
}
