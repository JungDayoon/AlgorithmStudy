import java.io.*;
import java.util.*;
public class _67258 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] gems = br.readLine().split(" ");
		
		int[] res = solution(gems);
		System.out.print(res[0] + " " + res[1]);
	}
	
	private static int[] solution(String[] gems) {
		Set<String> kinds = new HashSet<>();
		kinds.addAll(Arrays.asList(gems));
		int cnt = kinds.size(); // 보석종류의 개수
		Map<String, Integer> gemmap = new HashMap<>(); // <보석 종류, 보석의 개수>
		Queue<String> gemsQ = new LinkedList<>();
		
		int realStart = 0; // 가장 적은 보석을 포함하는 시작점이 빠른 구간의 시작점
		int start = 0; // 현재 확인하려는 구간의 시작점
		int end =Integer.MAX_VALUE; // 구간에 포함되는 보석의 개수
		
		int gemsN = gems.length;
		for(int i=0; i<gemsN; i++) {
			gemmap.put(gems[i], gemmap.getOrDefault(gems[i], 0) + 1);
			gemsQ.add(gems[i]);
			
			while(true) {
				String now = gemsQ.peek(); // 현재 구간에 속하는 보석 중 첫번째 보석의 개수를 확인
				if(gemmap.get(now) != 1) { // 1개가 아니면 한개로 시작할 수 있도록 찾아준다
					start++;
					gemsQ.poll();
					gemmap.put(now, gemmap.get(now)-1);
				}else {
					break;
				}
			}
			if(gemmap.size()==cnt && end>gemsQ.size()) { // 모든 종류의 보석이 구간에 포함되고, 그 구간의 길이가 이전에 구한 것보다 짧다면
				realStart = start;
				end = gemsQ.size();
			}
		}
		return new int[] {realStart+1, realStart+end};
	}

}
