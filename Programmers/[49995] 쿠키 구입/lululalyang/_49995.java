public class _49995 {

	public static void main(String[] args){
		int[] cookie = {1,2,4,5};
		System.out.println(solution(cookie));
	}
	
	private static int solution(int[] cookie) {
		int MAX = 0;
		int N = cookie.length;
		int[] sum = new int[N+1];
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i-1] + cookie[i-1];
		}
		
		for(int m=0; m<N; m++) {
			for(int l=1; l<m+1; l++) {
				int bro1 = sum[m] - sum[l-1]; // l ~ m
				int remain = sum[N] - sum[m]; // 남은 개수 (bro2가 가질 수 있는 최대 개수)
				
				if(bro1 > remain)	continue; // bro2 최대 개수가 bro1 보다 작으면 불가능 => pass
				if(MAX > bro1)	continue; // 이미 구한 답보다 bro1이 작다면 확인할 필요 없다 => pass
				
				for(int r=m+1; r<=N; r++) {
					int bro2 = sum[r] - sum[m]; // (m+1) ~ r
					if(bro1 == bro2) {
						MAX = Math.max(bro2, MAX);
						break;
					}else if(bro1 < bro2)	break;
				}
			}
		}
		
		return MAX;
	}	
}
