import java.io.*;

public class _60057 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		System.out.print(solution(s));
	}
	
	private static int solution(String s) {
		int ans = Integer.MAX_VALUE;
		String[] S = s.split("");
		int sLen = s.length();
		
		StringBuilder resultS = new StringBuilder();
		for(int tLen=1; tLen<=sLen; tLen++) { // tLen: 토큰 길이
			resultS.setLength(0); // resultS 초기화
			
			int idx = 0;
			if(idx+tLen > sLen) {
				for(int k=idx; k<sLen; k++)		resultS.append(S[k]);
				ans = Math.min(ans, resultS.length());
				continue;
			}
			
			StringBuilder sb = new StringBuilder();
			for(int k=idx; k<idx+tLen; k++)		sb.append(S[k]);
			int next = idx + tLen;
			int cnt = 1;
			while(idx < sLen) {
				if(idx == next)
					next = idx + tLen;
				
				if(next+tLen > sLen) { // 토큰 다음에 토큰 길이보다 적게 남았을 때
					if(cnt != 1) {
						resultS.append(cnt);
						for(int k=idx; k<idx+tLen; k++)		resultS.append(S[k]);
						for(int k=idx+(cnt*tLen); k<sLen; k++)	resultS.append(S[k]);
						break;
					}else {
						for(int k=idx; k<sLen; k++)		resultS.append(S[k]);
						break;
					}
				}else {
					StringBuilder sb_ = new StringBuilder();
					for(int k=next; k<next+tLen; k++)	sb_.append(S[k]);
					
					if((sb.toString()).equals(sb_.toString())) {
						cnt++;
						next = next + tLen;
					}else { // 다음 토큰과 다를 때
						if(cnt != 1) {
							resultS.append(cnt + sb.toString());
							cnt = 1;
						}else {
							resultS.append(sb.toString());
						}
						sb = sb_;
						idx = next;
					}
				}
			}
			ans = Math.min(ans, resultS.length());
		}
		
		return ans;
	}
}
