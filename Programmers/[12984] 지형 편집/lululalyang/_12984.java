public class _12984 {

	public static void main(String[] args) {
		int[][] land = {{1, 2}, {2, 3}};
//		int[][] land = {{4, 4, 3}, {3, 2, 2}, {2, 1, 0}};
		int P = 3;
		int Q = 2;
			
		System.out.print(solution(land, P, Q));
	}

	private static long solution(int[][] land, int P, int Q) {
        long answer = 0;
        int N = land.length;
        
        int min = 1000000000;
        int max = 0;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		min = Math.min(min, land[i][j]);
        		max = Math.max(max, land[i][j]);
        	}
        }
        answer = ParametricSearch(min, max, land, N, P, Q);
        return answer;
    }
	
	private static long ParametricSearch(long l, long r, int[][] land, int N, int P, int Q) {		
		long  m = 0;
		while(l <= r) {
			m = (l + r) / 2;
			if(l == r)	break;
			
			long cost1 = ComputeCost(N, m, P, Q, land);
			long cost2 = ComputeCost(N, m+1, P, Q, land);
			
			if(cost1 == cost2)	break;
			
			if(cost1 < cost2) {
				r = m;
			}else {  //(cost1 > cost2)
				l = m + 1;
			}
		}
		return ComputeCost(N, m, P, Q, land);
	}
	
	private static long ComputeCost(int N, long m, int P, int Q, int[][] land) {
		long cost = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int nowh = land[i][j];
				if(nowh < m) { 
					cost += (m - nowh) * P; // 추가
				}else if(nowh > m) {
					cost += (nowh - m) * Q; // 제거
				}
			}
		}
		return cost;
	}
}
