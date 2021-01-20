import java.io.*;
import java.util.*;

public class _5658 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // 숫자 개수
			int K = Integer.parseInt(s[1]); // K번째수로 큰 수
			
			int term = N/4; //한 변에 term개씩, term번 회전
			String[] inputNum = br.readLine().split("");
			Set<Integer> decimalSet = new TreeSet<Integer>();
			
			for(int i=0; i<term; i++) { // term번 회전
				DivideandTohex(inputNum, decimalSet, term, N);
				moveNum(inputNum);
			}
			
			List<Integer> decimalList = new ArrayList<Integer>(decimalSet);
			sb.append("#"+t+" "+decimalList.get(decimalList.size()-K)+"\n");
		}//테스트케이스 끝
		System.out.println(sb.toString());
	}
	
	static void moveNum(String[] inputNum) {
		String end = inputNum[inputNum.length-1];
		for(int i=inputNum.length-1; i>0; i--) {
			
			inputNum[i] = inputNum[i-1];
		}
		inputNum[0] = end;
	}
	
	static void DivideandTohex(String[] inputNum, Set<Integer> decimal, int term, int N) {
		StringBuilder sb1 = new StringBuilder();
		for(int i=0; i<=N-term; i+=term) {
			for(int j=i; j<i+term; j++) {
				sb1.append(inputNum[j]);
			}
			decimal.add(Integer.parseInt(sb1.toString(), 16));
			sb1.delete(0, sb1.length()); //초기화
		}
	}
	
}
