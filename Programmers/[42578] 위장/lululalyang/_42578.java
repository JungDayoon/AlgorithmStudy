import java.util.*;

public class _42578 {
	public static void main(String[] args) {
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
//		String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
//		String[][] clothes = {{"동그란안경", "얼굴"}, {"검정선글라스", "얼굴"}, {"파란티셔츠", "상의"}, {"청바지", "하의"}, {"긴코트", "겉옷"}};
		
		System.out.print(solution(clothes));
	}

	private static int solution(String[][] clothes) {
		int ans = 1;
		
		Map<String, Integer> map = new HashMap<>(); // <의상종류, 의상 개수> 
		ArrayList<String> kinds = new ArrayList<>(); //의상 종류
		
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
			ans *= (map.get(kinds.get(i))+1); // (의상종류개수 + 안입은경우)
		}
		ans--; // 모두 안입은 경우
		return ans;
	}
	
	
}
