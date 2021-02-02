import java.io.*;
import java.util.*;

public class _11000 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] ST = new int[N][2]; //0번째: S, 1번째: T 
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			ST[i][0] = Integer.parseInt(s[0]);
			ST[i][1] = Integer.parseInt(s[1]);
		}
		
		
		Arrays.sort(ST, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0])
					return o1[0] - o2[0];
				else
					return o1[1] - o2[1];
			}
		});
		
		System.out.print(ComputeClassCnt(ST, N));
	}
	
	private static int ComputeClassCnt(int[][] ST, int N) {
		ArrayList<Integer> Class = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 끝나는 시간을 저장!?
		
		pq.add(ST[0][1]);
		for(int i=1; i<N; i++) {
			if(ST[i][0] < pq.peek()) {
				pq.add(ST[i][1]);
			}else {	// ST[i][0] >= pq.peek()
				pq.remove();
				pq.add(ST[i][1]);
			}
		}
		
		return pq.size();
	}

}
