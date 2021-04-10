import java.io.*;
import java.util.*;
public class _67257 {
	static ArrayList<ArrayList<Integer>> order = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String exp = br.readLine();

		System.out.print(solution(exp));
	}
	
	private static long solution(String expression) {
		long res = 0;
		Map<String, Integer> operator = new HashMap<>();
		int idx = 0;
		
		ArrayList<String> expList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<expression.length(); i++) {
			char c = expression.charAt(i);
			if('0'<=c && c<='9')	sb.append(c);
			else { // 연산자라면
				expList.add(sb.toString());
				sb.setLength(0);
				expList.add(Character.toString(c));
				if(!operator.containsKey(Character.toString(c))) {
					operator.put(Character.toString(c), idx);
					idx++;
				}
			}
		}
		expList.add(sb.toString()); // 마지막 숫자
		
		int operN = operator.size();
		if(operN == 1) { // 연산자 1개
			ArrayList<String> tmpexp = new ArrayList<>();
			for(int i=0; i<expList.size(); i++) {
				String now = expList.get(i);
				if(now.matches("[0-9]*")) { // 숫자라면
					tmpexp.add(now);
				}else { // 연산자라면
					long x = Long.parseLong(tmpexp.remove(tmpexp.size()-1));
					long y = Long.parseLong(expList.get(i+1));
					
					tmpexp.add(Long.toString(compute(x, y, now)));
					i++;
				}
			}
			res = Long.parseLong(tmpexp.get(0));
			if(res < 0)	res = Math.abs(res);
		}else { // 연산자가 2개 또는 3개일 때
			ArrayList<Integer> tmp = new ArrayList<>();
			boolean[] visited = new boolean[operN];
			Perm(operN, 0, tmp, visited);
			
			for(ArrayList<Integer> priOrder : order) { // 우선순위에 따라
				ArrayList<String> exp = (ArrayList<String>)expList.clone();
				for(Integer nowOper : priOrder) { // 지금 계산해야하는 연산자 nowOper
					ArrayList<String> tmpexp = new ArrayList<>();
					for(int i=0; i<exp.size(); i++) {
						String now = exp.get(i);
						if(now.matches("[0-9]*")) {
							tmpexp.add(now);
						}else {
							if(operator.get(now) != null) {
								if(operator.get(now) == nowOper) { // 현재 우선순위의 연산자라면
									long x = Long.parseLong(tmpexp.remove(tmpexp.size()-1));
									long y = Long.parseLong(exp.get(i+1));
									i++;
									tmpexp.add(Long.toString(compute(x, y, now)));
								}else {
									tmpexp.add(now); 
								}
							}else {
								tmpexp.add(now); // 음수 여기에서 추가됨
							}
							
						}
					}
					exp = tmpexp;
				}
				
				long ans = Long.parseLong(exp.get(0));
				if(ans < 0)	ans = Math.abs(ans);
				
				res = Math.max(res, ans);
			}
		}
		return res;
	}
	
	private static void Perm(int N, int r, ArrayList<Integer> tmp, boolean[] visited) {
		if(N == r) {
			order.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				tmp.add(i);
				visited[i] = true;
				Perm(N, r+1, tmp, visited);
				visited[i] = false;
				tmp.remove(tmp.indexOf(i));
			}
		}
	}
	
	private static long compute(long x, long y, String operator) {
		long res = 0;
		
		if(operator.equals("*"))	res = x * y;
		else if(operator.equals("+")) 	res = x + y;
		else	res = x - y;
		
		return res;
	}
}
