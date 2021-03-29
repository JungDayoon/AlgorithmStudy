import java.io.*;
import java.util.*;
public class _64065 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int[] res = solution(s);
		for(Integer i : res)	System.out.print(i + " ");
	}

	
	private static int[] solution(String s) {
		int[] answer = {};
		s = s.substring(2, s.length()-2);
		String[] set = s.split("},");
		int N = set.length;
		answer = new int[N];
		for(int i=1; i<N; i++) {
			set[i] = set[i].substring(1, set[i].length());
		}
		
		Arrays.sort(set, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		
		Map<Integer, Boolean> chk = new HashMap<>();
		int idx = 0;
		for(String str : set) {
			boolean flag = false;
			String[] S = str.split(",");
			int len = S.length;
			for(int i=0; i<S.length; i++) {
				int now = Integer.parseInt(S[i]);
				if(!chk.containsKey(now)) {
					chk.put(now, true);
					answer[idx++] = now;
					flag = true;
					break;
				}
			}
		}
		
		return answer;
	}
}
