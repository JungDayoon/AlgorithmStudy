
public class _12971 {

	public static void main(String[] args) {
//		int[] s = {14, 6, 5, 11, 3, 9, 2, 10};
		int[] s = {1, 3, 2, 5, 4};
//		int[] s = {1, 3, 4};
		
		System.out.print(solution(s));
	}
	
	private static  int solution(int sticker[]) {
        int answer = 0;
        int N = sticker.length;
        int[] dp1 = new int[N]; // 첫번째 스티커 뗀 경우
        int[] dp2 = new int[N]; // 안 뗀 경우
        
        if(N == 1)	answer = sticker[0];
        else if(N == 2)	answer = Math.max(sticker[0], sticker[1]);
        else {
        	dp1[0] = dp1[1] = sticker[0];
            dp2[1] = sticker[1];
            for(int i=2; i<N; i++) {
            	int now = sticker[i];
            	
            	if(i == N-1) { // dp1은 첫번째 스티커 뜯은 경우 => 마지막 스티커는 뜯을 수 없다
            		if(dp1[i-1] > dp1[i-2])	dp1[i] = dp1[i-1];
            		else	dp1[i] = dp1[i-2];
            	}else {
            		if(dp1[i-1] > (dp1[i-2]+now))	dp1[i] = dp1[i-1];
                	else	dp1[i] = dp1[i-2] + now;
            	}
            	
            	
            	if(dp2[i-1] > dp2[i-2]+now)	dp2[i] = dp2[i-1];
            	else	dp2[i] = dp2[i-2] + now;
            }
            
            answer = Math.max(dp1[N-1], dp2[N-1]);
        }
        return answer;
    }
}
