import java.io.*;
import java.util.*;
public class _64063 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long K = Long.parseLong(br.readLine());
		String[] s = br.readLine().split(" ");
		long[] room_num = new long[s.length];
		for(int i=0; i<s.length; i++) {
			room_num[i] = Long.parseLong(s[i]);
		}
		
		long[] res = solution(K, room_num);
		for(long r : res)	System.out.print(r + " ");
	}
	
	private static long[] solution(long K, long[] room_number) {
		int N = room_number.length;
		long[] answer = new long[N];
		Map<Long, Long> bigger = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			long guest = room_number[i]; // �մ��� ���ϴ� �� ��ȣ
			if(!bigger.containsKey(guest)) { // �� ���̶��
				answer[i] = guest;
				long next = dfs(bigger, guest+1);
				bigger.put(guest, next);
			}else { // ����� �ƴ϶��
				answer[i] = dfs(bigger, bigger.get(guest));
				long next = dfs(bigger, answer[i]+1);
				bigger.replace(guest, next);
				bigger.put(answer[i], next);
			}
		}
		return answer;
	}
	
	private static long dfs(Map<Long, Long> bigger, long guest) {
		if(!bigger.containsKey(guest)) // guest�� �� ���̶��
			return guest;
		
		bigger.replace(guest, dfs(bigger, bigger.get(guest)));
		return bigger.get(guest);
	}
}
