import java.io.*;
import java.util.*;
public class _1715 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++)	pq.add(Integer.parseInt(br.readLine()));
		
		System.out.print(solution(pq));
	}
	
	private static int solution(PriorityQueue<Integer> pq) {
		int min = 0;
		
		while(pq.size() != 1) {
			int a = pq.poll();
			int b = pq.poll();
			min += (a+b);
			pq.add(a+b);
		}
		
		return min;
	}
}
