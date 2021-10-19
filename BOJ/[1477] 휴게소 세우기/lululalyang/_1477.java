import java.io.*;
import java.util.*;
public class _1477 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		int N = Integer.parseInt(s[0]); // 휴게소 개수
		int M = Integer.parseInt(s[1]); // 더 지으려는 휴게소 개수
		int L = Integer.parseInt(s[2]); // 고속도로의 길이
		
		s = br.readLine().split(" ");
		ArrayList<Integer> rest = new ArrayList<>();
		for(int i=0; i<N; i++) {
			rest.add(Integer.parseInt(s[i]));
		}
		
		System.out.print(solution(N, M, L, rest));
	}
	
	private static int solution(int N, int M, int L, ArrayList<Integer> rest) {
		Collections.sort(rest); // 오름차순 정렬
		rest.add(L); // 도착점 추가
		N++;
		
		int l = 1;
		int r = 1000;
		int ans = 0;
		while(l <= r) {
			int m = (l+r) / 2;
			
			if(CheckBuild(N, M, m, rest)) { // 가능하면 => 간격 줄인다
				ans = m;
				r = m-1;
			}else { // 불가능하면 => 간격 늘린다
				l = m+1;
			}
		}
		
		return ans;		
	}
	
	private static boolean CheckBuild(int N, int M, int dist, ArrayList<Integer> rest) { // M개의 휴게소 지어서 최대간격 dist가 가능한지
		int now = 0;
		int i = 0;
		while(i < N) {
			int next = rest.get(i);
			if((next-now) <= dist) {
				i++; // 간격으로 가능
				now = next;
			}else {
				if(M>0) { // 지을 수 있는 휴게소가 남았다면
					M--;
					now += dist;
				}else // dist간격으로 안되는데, 지을 수 있는 남은 휴게소가 없다면
					return false;
			}
		}
		return true;
	}
}
