import java.io.*;
import java.util.*;

public class _60058 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		System.out.print(solution(s));
	}
	
	private static String solution(String s) {
		StringBuilder ans = new StringBuilder();
		int sLen = s.length();
		if(sLen == 0)	return ""; // 1
		
		// 2. s를 균형잡힌 u, v로 분리
		String[] S = s.split("");
		StringBuilder u = new StringBuilder();
		StringBuilder v = new StringBuilder();
		
		String start = S[0];		
		int cnt1 = 1; // 시작 괄호 개수
		int cnt2 = 0; // 종료 괄호 개수
		int idx = 1;
		while(cnt1 != cnt2) {
			if(S[idx].equals(start))	cnt1++;
			else	cnt2++;
			idx++;
		}
		
		u.append(s.substring(0, idx));
		v.append(s.substring(idx));
		if(ChkRightStr(u.toString())) { // u가 올바른 문자열이라면
			String resV = solution(v.toString());
			return u.toString() + resV;
		}else { // 올바른 괄호 문자열이 아니라면
			StringBuilder tmp = new StringBuilder();
			tmp.append("(");
			String resV = solution(v.toString());
			tmp.append(resV + ")");
			
			String resU = u.substring(1, u.length()-1);
			resU = ChangeU(resU);
			tmp.append(resU);
			return tmp.toString();
		}
	}
	
	private static String ChangeU(String u) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<u.length(); i++) {
			if(u.charAt(i) == '(')
				sb.append(")");
			else
				sb.append("(");
		}
		
		return sb.toString();
	}
	
	private static boolean ChkRightStr(String s) {
		Stack<String> stack = new Stack<>();
		if(s.charAt(0) == ')') 	return false; // ')'로 시작하면 올바르지 않음
		
		String[] S = s.split("");
		for(int i=0; i<s.length(); i++) {
			if(S[i].equals("("))
				stack.add(S[i]);
			else {
				if(!stack.isEmpty()) {
					stack.pop();
				}else { // stack이 비었는데 ")"라면 올바르지 않은 문자열
					return false;
				}
			}
		}
		
		return true;
	}
}
