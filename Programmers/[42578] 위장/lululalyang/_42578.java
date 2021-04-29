import java.util.*;

public class _42578 {
	public static void main(String[] args) {
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
//		String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
//		String[][] clothes = {{"���׶��Ȱ�", "��"}, {"�������۶�", "��"}, {"�Ķ�Ƽ����", "����"}, {"û����", "����"}, {"����Ʈ", "�ѿ�"}};
		
		System.out.print(solution(clothes));
	}

	private static int solution(String[][] clothes) {
		int ans = 1;
		
		Map<String, Integer> map = new HashMap<>(); // <�ǻ�����, �ǻ� ����> 
		ArrayList<String> kinds = new ArrayList<>(); //�ǻ� ����
		
		for(int i=0; i<clothes.length; i++) {
			if(map.containsKey(clothes[i][1])) {
				int prev = map.get(clothes[i][1]);
				map.replace(clothes[i][1], prev+1);
			}else {
				map.put(clothes[i][1], 1);
				kinds.add(clothes[i][1]);
			}
		}
		
		int kindCnt = kinds.size();
		for(int i=0; i<kindCnt; i++) {
			ans *= (map.get(kinds.get(i))+1); // (�ǻ��������� + ���������)
		}
		ans--; // ��� ������ ���
		return ans;
	}
	
	
}
