import java.io.*;
import java.util.*;
public class _2075 {
	static int[][] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		num = new int[N][N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				num[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		System.out.print(solution(N));
	}
	
	private static int solution(int N) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++)	pq.add(num[N-1][i]); // 맨 마지막 행 모두
		
		for(int i=N-2; i>=0; i--) {
			int top = pq.peek();
			for(int j=0; j<N; j++) {
				int now = num[i][j];
				if(now>top && !pq.contains(now))	pq.add(now);	
			}
			int size = pq.size();
			if(size == N) { // N개만 있다면 그 때의 peek()이 중앙값
				break;
			}else if(size > N){ // N개보다 많다면 N개일때까지 pop
				while(pq.size() != N)	pq.remove();
			}
			// N개보다 적으면 다음 행을 추가한다.
		}
		return pq.peek();
	}
}
