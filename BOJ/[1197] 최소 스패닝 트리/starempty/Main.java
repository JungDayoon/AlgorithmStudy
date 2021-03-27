import java.io.*;
import java.util.*;

public class Main {
	static node[] nodeList;
	static int[] parents;
	static public class node implements Comparable<node>{
		int from, to, weight;
		public node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
			st = new StringTokenizer(br.readLine(), " ");
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			parents = new int[v+1];
			nodeList = new node[e];
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				nodeList[i] = new node(from, to, weight);
			}
			
			Arrays.sort(nodeList);
			
			make();
			int result = 0;
			int cnt = 0;
			for(node n: nodeList) {
				if(union(n.from, n.to)) {
					result += n.weight;
					if(++cnt == v-1) break;
				}
			}
			System.out.println(result);
	}
	private static void make() {
		for(int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		parents[b] = a;
		return true;
	}
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]); 
	}
}
