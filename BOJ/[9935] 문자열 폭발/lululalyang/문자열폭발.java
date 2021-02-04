import java.io.*;
import java.util.*;

public class _9935 {
	
	static String[] Bomb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String BOMB = br.readLine();
		
		System.out.print(stringBomb(S, BOMB));	
	}	
	
	private static String stringBomb(String S, String BOMB) {
		if(!S.contains(BOMB))
			return S;
		
		Stack<String> stack = new Stack<>();
		Stack<String> chk = new Stack<>();
		
		String[] s = S.split("");
		String[] bomb = BOMB.split("");
		int bombLen = bomb.length;
		
		for(int i=0; i<s.length; i++) {
			stack.add(s[i]);
			if(stack.peek().equals(bomb[bombLen-1])) { //폭발문자열 마지막과 같으면
				int flag = 0;
				for(int j=bombLen-1; j>=0; j--) {
					if(stack.empty()) {
						flag = 1;
						break;
					}
					else if(stack.peek().equals(bomb[j])) {
						chk.add(stack.pop());
					}else {
						flag = 1;
						break;
					}
				}
				
				if(flag == 1) { // 폭발 문자열이 아님
					while(!chk.empty()) {
						stack.add(chk.pop());
					}
				}else { // 폭발 문자열이라면
					chk.clear(); // 버린다
				}
			}
		}
		
		if(stack.empty())
			return "FRULA";
		
		StringBuilder sb = new StringBuilder();
		while(!stack.empty()) {
			sb.append(stack.pop());
		}
		
		return sb.reverse().toString();
	}
}
