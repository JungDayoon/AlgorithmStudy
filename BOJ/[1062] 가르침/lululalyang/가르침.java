import java.io.*;
import java.util.*;

public class _1062 {
	static ArrayList<String> Remainder 
		= new ArrayList<>(Arrays.asList("b", "d", "e", "f", "g", "h", "j", "k", "l", "m", "o", "p", "q", "r", "s", "u", "v", "w", "x", "y", "z"));
	static int MaxCnt = 0;
	static String[][] words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int K = Integer.parseInt(s[1]);
		
		words = new String[N][];
		for(int i=0; i<N; i++) {
			words[i] = br.readLine().split("");
		}

		System.out.print(ComputeMaxCnt(N, K));
	}
	
	private static int ComputeMaxCnt(int N, int K) {
		if(K < 5) // 접두사 & 접미사인 a,n,t,i,c를 모르면 -> 아무 단어도 읽을 수 없다
			return 0;
		else if(K == 26) // 알파벳 전체 알면 -> 주어진 단어 다 읽을 수 있다
			return N;
		
		ArrayList<String> tmp = new ArrayList<>(Arrays.asList("a", "n", "t", "i", "c"));
		int _K = K - 5; //필수 글자 a,n,t,i,c
		Backtracking(21, _K, 0, tmp);
		return MaxCnt;
	}
	
	private static void Backtracking(int N, int r, int start, ArrayList<String> tmp) {
		if(r == 0) {
			CheckReadable(tmp);
			return;
		}
		
		for(int i=start; i<N; i++) {
			tmp.add(Remainder.get(i));
			Backtracking(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(Remainder.get(i)));
		}
	}
	
	private static void CheckReadable(ArrayList<String> tmp) {
		int cnt = 0;
		for(int i=0; i<words.length; i++) {
			int flag = 0;
			for(int j=4; j<words[i].length-4; j++) {
				if(!tmp.contains(words[i][j])) {
					flag = 1;
					break;
				}
			}
			if(flag == 0)
				cnt++;
		}
		
		MaxCnt = Math.max(MaxCnt, cnt);
	}
}
