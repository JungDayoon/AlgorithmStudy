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
		int cnt = kinds.size(); // ���������� ����
		Map<String, Integer> gemmap = new HashMap<>(); // <���� ����, ������ ����>
		Queue<String> gemsQ = new LinkedList<>();
		
		int realStart = 0; // ���� ���� ������ �����ϴ� �������� ���� ������ ������
		int start = 0; // ���� Ȯ���Ϸ��� ������ ������
		int end =Integer.MAX_VALUE; // ������ ���ԵǴ� ������ ����
		
		int gemsN = gems.length;
		for(int i=0; i<gemsN; i++) {
			gemmap.put(gems[i], gemmap.getOrDefault(gems[i], 0) + 1);
			gemsQ.add(gems[i]);
			
			while(true) {
				String now = gemsQ.peek(); // ���� ������ ���ϴ� ���� �� ù��° ������ ������ Ȯ��
				if(gemmap.get(now) != 1) { // 1���� �ƴϸ� �Ѱ��� ������ �� �ֵ��� ã���ش�
					start++;
					gemsQ.poll();
					gemmap.put(now, gemmap.get(now)-1);
				}else {
					break;
				}
			}
			if(gemmap.size()==cnt && end>gemsQ.size()) { // ��� ������ ������ ������ ���Եǰ�, �� ������ ���̰� ������ ���� �ͺ��� ª�ٸ�
				realStart = start;
				end = gemsQ.size();
			}
		}
		return new int[] {realStart+1, realStart+end};
	}

}
