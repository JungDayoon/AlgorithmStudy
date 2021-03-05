import java.io.*;
import java.util.*;

public class _17677 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		System.out.print(solution(str1, str2));
	}
	
	public static int solution(String str1, String str2) {
		int answer = 0;

		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		Map<String, Integer> set1 = makeSet(str1); // <����, ����>
		Map<String, Integer> set2 = makeSet(str2);
		
		if(set1.isEmpty() && set2.isEmpty()) // �� �� �������� ���
			return 1 * 65536;
		
		// ������ ����
		int interSection = 0;
		Iterator<String> itr = set1.keySet().iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			if(set2.containsKey(key)) {
				int min = Math.min(set1.get(key), set2.get(key));
				interSection += min;
			}
		}
		
		int Union = 0;
		itr = set1.keySet().iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			if(set2.containsKey(key)) {
				int max = Math.max(set1.get(key), set2.remove(key)); // �� �� ū������ ++, set2������ �����ش�
				Union += max;
			}else { // set2�� ������
				Union += set1.get(key);
			}
		}
		
		if(!set2.isEmpty()) { // set2�� �����ִٸ�
			itr = set2.keySet().iterator();
			while(itr.hasNext()) {
				Union += set2.get(itr.next()); // �����ִ� key���� value�� ++
			}
		}
		
		answer = (int)(((double)interSection/(double)Union) * 65536.0); 
		return answer;
	}
	
	private static boolean chkEnglish(char c1, char c2) {
		return (c1>='A' && c1<='Z' && c2>='A' && c2<='Z');
	}
	
	private static Map<String, Integer> makeSet(String str) {
		Map<String, Integer> set = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length()-1; i++) {
			if(chkEnglish(str.charAt(i), str.charAt(i+1))) { // �� ���� �� �����ڶ��
				sb.append(str.charAt(i) + "" + str.charAt(i+1));
				String now = sb.toString();
				if(set.containsKey(now)) {
					int cnt = set.remove(now);
					set.put(now, cnt+1);
				}else {
					set.put(now, 1);
				}
				
				sb.setLength(0); // StringBuilder �ʱ�ȭ
			}
		}
		
		
		return set;
	}
}
