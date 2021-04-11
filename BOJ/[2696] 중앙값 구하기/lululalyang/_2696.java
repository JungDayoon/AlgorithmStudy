import java.io.*;
import java.util.*;
public class _2696 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			if(sb.length() != 0)	sb.append("\n");
			int M = Integer.parseInt(br.readLine());
			
			String[] s;
			ArrayList<Integer> list = new ArrayList<>();
			if(M>10) {
				if(M%10 == 0) {
					for(int i=0; i<M/10; i++) {
						s = br.readLine().split(" ");
						for(int j=0; j<s.length; j++)	list.add(Integer.parseInt(s[j]));
					}
				}else {
					for(int i=0; i<M/10 + 1; i++) {
						s = br.readLine().split(" ");
						for(int j=0; j<s.length; j++)	list.add(Integer.parseInt(s[j]));
					}
				}
			}else {
				s = br.readLine().split(" ");
				for(int j=0; j<s.length; j++)	list.add(Integer.parseInt(s[j]));
			}
			
			int mincnt = M/2 + 1;
			sb.append(mincnt); // 중앙값의 개수
			
			ArrayList<Integer> minlist = solution(M, list);		
			if(mincnt > 10) { // 10개보다 많을 때
				for(int i=0; i<mincnt; i++) {
					if(i%10 == 0)	sb.append("\n" + minlist.get(i));
					else	sb.append(" " + minlist.get(i));
				}
			}else { // 10개 이하일 때
				for(int i=0; i<mincnt; i++) {
					if(i%10 == 0)	sb.append("\n");
					
					if(i != 0)	sb.append(" " + minlist.get(i));
					else	sb.append(minlist.get(i));
				}
			}
		}
		System.out.print(sb.toString());
	}

	private static ArrayList<Integer> solution(int M, ArrayList<Integer> list){
		ArrayList<Integer> minlist = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		ArrayList<Integer> trash = new ArrayList<>();
		for(int i=0; i<M; i++) {
			int now = list.get(i);
			pq.add(now);
			
			if(i%2 == 0) {
				if(i == 0) minlist.add(now);
				else {
					trash.clear();
					for(int j=0; j<i/2; j++)	trash.add(pq.poll());
					
					minlist.add(pq.peek()); // 젤 윗값이 중앙값
					
					for(int j=0; j<trash.size(); j++)	pq.add(trash.get(j));
				}
			}
		}
		
		return minlist;
	}
}
