import java.io.*;
import java.util.*;
public class _20056 {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<FireBall> fireball = new ArrayList<>();
	static ArrayList<Integer> remove = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]); // 파이어볼 개수
		int K = Integer.parseInt(s[2]); // 명령 횟수
		
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			fireball.add(new FireBall(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4])));
		}
		
		System.out.print(solution(N, K));
	}
	
	private static int checkValid(int l, int N) {
		if(l > N)	l %= N;
		if(l < 1)	l = N-(Math.abs(l)%N);
		
		return l;
	}
	
	private static void MoveBall(int N, ArrayList<Integer>[][] count) {
		int M = fireball.size();
		for(int i=0; i<M; i++) {
			FireBall now = fireball.get(i); 
			int r = now.r;
			int c = now.c;
			int s = now.s;
			int d = now.d;
			
			r += dx[d]*s; // 이동
			c += dy[d]*s;
			
			r = checkValid(r, N);
			c = checkValid(c, N);
			now.r = r;
			now.c = c;
			count[r][c].add(i);
		}
	}
	
	private static void CheckSameLoc(ArrayList<Integer> balls, int r, int c) {
		int cnt = balls.size(); // 같은 칸에 있는 볼 개수
		int M = 0; // 질량 총합
		int S = 0; // 속도 총합
		int D = 0; // 방향 체크
		int firstD = -1; // 0이면 짝, 1이면 홀
		boolean flag = false;
		for(Integer i : balls) { // 질량, 속도 총합 구하고, 방향 구하기
			FireBall now = fireball.get(i);
			M += now.m;
			S += now.s;
			
			if(!flag) { // 첫 파이어볼이면
				if(now.d%2 == 0)	firstD = 0;
				else	firstD = 1;
				flag = true;
			}else { // 그렇지 않다면
				if(now.d%2 != firstD)	D = 1; // 첫번째 볼과 방향 홀짝이 다르다면
			}
		}
		
		if(M/5 == 0)	return;
		
		int newM = M/5;
		int newS = S/cnt;
		for(int i=D; i<=D+6; i+=2) { // 새로운 파이어볼 4개
			fireball.add(new FireBall(r, c, newM, newS, i));
		}
	}
	
	private static int solution(int N, int K) {
		int res = 0;
		
		for(int k=0; k<K; k++) {
			ArrayList<Integer>[][] count = new ArrayList[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++)	count[i][j] = new ArrayList<>();
			}
			MoveBall(N, count); // 모든 파이어볼 이동
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(count[i][j].size() > 1) { // 2개 이상이면
						CheckSameLoc(count[i][j], i, j);
						for(Integer idx : count[i][j])	remove.add(idx);
					}
				}
			}
			
			if(remove.isEmpty())	continue; // 지울게 없다면 패스
			Collections.sort(remove);
			int delete = 0;
			for(Integer i : remove) {
				fireball.remove(i-delete);
				delete++;
			}
			remove.clear();
		}
		
		for(FireBall now : fireball) {
			res += now.m;
		}
		
		return res;
	}
	
	private static class FireBall{
		private int r; 
		private int c; // 위치 좌표
		private int m; // 질량
		private int s; // 속도
		private int d; // 방향
		
		FireBall(int r, int c, int m, int s, int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
		public String toString() {
			return this.r+" "+this.c+" "+this.m+" "+this.s+" "+this.d+" ";
		}
	} 
}
