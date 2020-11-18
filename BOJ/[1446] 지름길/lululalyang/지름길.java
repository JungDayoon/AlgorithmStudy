import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class _1446 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] d = br.readLine().split(" ");
		int N = Integer.parseInt(d[0]); //������ ����
		int D = Integer.parseInt(d[1]); //��ӵ����� ����
		
		TreeMap<Integer, ArrayList<Edge>> adj = new TreeMap<Integer, ArrayList<Edge>>();
		for(int n=0; n<N; n++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int dist = Integer.parseInt(s[2]); 
			
			if(!adj.containsKey(a)) { //�� ������ ��尡 ������
				ArrayList<Edge> tmp1 = new ArrayList<>();
				tmp1.add(new Edge(b, dist));
				adj.put(a, (ArrayList<Edge>)tmp1.clone());
				if(!adj.containsKey(b)) {
					ArrayList<Edge> tmp2 = new ArrayList<>();
					adj.put(b, (ArrayList<Edge>)tmp2.clone());
				}
			}else { //�����ϴ� ������ �����
				ArrayList<Edge> curr = adj.get(a);
				adj.remove(a);
				curr.add(new Edge(b, dist));
				adj.put(a, (ArrayList<Edge>) curr.clone());
			}
			
			if(!adj.containsKey(b)) {
				ArrayList<Edge> tmp2 = new ArrayList<>();
				adj.put(b, (ArrayList<Edge>)tmp2.clone());
			}
		}
		
		if(!adj.containsKey(0)) {
			ArrayList<Edge> tmp3 = new ArrayList<>();
			adj.put(0, (ArrayList<Edge>)tmp3.clone());
		}
		
		TreeMap<Integer, ArrayList<Edge>> adj2 = new TreeMap<>();
		
		Set<Integer> keys = adj.keySet();
		Set<Map.Entry<Integer, ArrayList<Edge>>> entries = adj.entrySet();
		for(Map.Entry<Integer, ArrayList<Edge>> entry : entries) {
			int key = entry.getKey();
			ArrayList<Edge> tmp4 = entry.getValue();
			
			for(Integer k : keys) {
				if(k>key && k<=D) {
					tmp4.add(new Edge(k, (k-key)));
					break;
				}
			}
			adj2.put(key, (ArrayList<Edge>)tmp4.clone());
		}
		Set<Integer> keys2 = adj2.keySet();
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		TreeMap<Integer, Boolean> visited = new TreeMap<>(); // ��� �湮 ����
		for(Integer key : keys2) { //�� fasle�� �ʱ�ȭ
			visited.put(key, false);
		}
		
		TreeMap<Integer, Integer> result = new TreeMap<>(); // 0������ �ּ� �Ÿ���
		for(Integer key : keys2) {
			result.put(key, Integer.MAX_VALUE); 
		}
		result.put(0, 0); //����� �ּҰŸ��� 0����
		pq.add(new Edge(0, 0));
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int v = now.v;
			for(Edge next : adj2.get(now.v)) {
				int next_key = next.v;
				int compare = result.get(v)+next.dist;
				if(visited.get(next_key)==false && result.get(next_key)>compare) { //�湮���� ���� ����̰�, ���ļ� ���� �Ÿ��� �� ª����
					result.put(next_key, compare);
					pq.add(new Edge(next_key, compare));
				}
			}
			visited.put(v, true);
		}
		
		int answer = 0;
		if(!adj2.containsKey(D)) { //������ ��尡 ���ٸ�
			int here = 0;
			for(Integer node : keys2) {
				if(node < D)
					here = node;
			}
			answer += result.get(here);
			answer += (D-here);
		}else
			answer = result.get(D);
		
		System.out.println(answer);
		
		
	}
	
	static class Edge implements Comparable<Edge>{
		int v;
		int dist;
		
		Edge(int v, int dist){
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.dist > o.dist)
				return 1;
			else if(this.dist < o.dist)
				return -1;
			else
				return 0;
		}
		
		
	}
}
