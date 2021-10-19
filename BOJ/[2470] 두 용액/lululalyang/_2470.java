import java.io.*;
import java.util.*;
public class _2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(s[i]));
		}
		
		solution(N, list);
	}
	
	private static void solution(int N, ArrayList<Integer> list) {
		Collections.sort(list); // 오름차순 정렬
		
		int min = Integer.MAX_VALUE;
		int ans1 = 0;
		int ans2 = 0;
		
		int l = 0;
		int r = N-1;
		
		while(l < r) {
			int sum = list.get(l) + list.get(r); // 혼합용액의 값
			if(min > Math.abs(sum)) { // 합이 0에 더 가깝다면
				min = Math.abs(sum);
				ans1 = list.get(l);
				ans2 = list.get(r);
			}
			
			if(sum > 0)		r--; // 합이 양수 => 값을 줄여야 함 => r--
			else if(sum < 0)	l++; // 합이 음수 => 값 늘려야 함 => l++
			else	break; // 합이 0 => 최적의 경우
		}
		
		if(ans1 < ans2)		System.out.print(ans1 + " " + ans2);
		else	System.out.print(ans2 + " " + ans1);
	}
	
}
