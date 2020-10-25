import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;


public class _2800 {
	static ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>(); //지워야하는 괄호쌍의 idx
	static TreeSet<String> treeSet = new TreeSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		char[] cArr = input.toCharArray();
		
		ArrayList<Integer> open = new ArrayList<>(); //여는 괄호
		ArrayList<Integer> close = new ArrayList<>(); //닫는 괄호
		
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<cArr.length; i++) {
			if(cArr[i] != ')') {
				stack.push(cArr[i]);
			}else { // c가 ')'이면
				int popcnt = 0;
				while(stack.pop() != '(') {
					popcnt++;
				}
				for(int j=0; j<popcnt+2; j++)
					stack.push('0');
				open.add(i-popcnt-1);
				close.add(i);
			}
		}
		
		for(int r=1; r<=open.size(); r++) { //1개부터 괄호쌍개수만큼까지 어떤 쌍을 지울지 조합으로 선택 -> c에 담는다
	         ArrayList<Integer> tmp = new ArrayList<>();
	         Comb(tmp, open.size(), r, 0);
	    }
		
		for(ArrayList<Integer> ctmp : c) {
	        DeleteBracket(cArr, ctmp, open, close); //bCnt: 괄호쌍의 개수
	    }

		for(String rs : treeSet) {
			System.out.println(rs);
		}

	}
	
	static void DeleteBracket(char[] cArr, ArrayList<Integer> ctmp, ArrayList<Integer> open, ArrayList<Integer> close) {
		
		char[] cArrTmp = cArr.clone();
		int remove = 0;
		for(int delIdx : ctmp) {
			cArrTmp[open.get(delIdx)] = '_';
			cArrTmp[close.get(delIdx)] = '_';
			remove += 2;
		}
		
		StringBuilder sb = new StringBuilder(cArrTmp.length-remove);
		for(int i=0; i<cArrTmp.length; i++) {
			if(cArrTmp[i] != '_') {
				sb.append(cArrTmp[i]);
			}
		}
		
		treeSet.add(sb.toString());
	}
	
	static void Comb(ArrayList<Integer> tmp, int n, int r, int start) {
		if(r == 0) {
			c.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		for(int i=start; i<n; i++) {
			tmp.add(i);
			Comb(tmp, n, r-1, i+1);
			tmp.remove(tmp.indexOf(i));
		}
	}
}
