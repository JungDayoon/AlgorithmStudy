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
		int M = Integer.parseInt(s[1]); // ���̾ ����
		int K = Integer.parseInt(s[2]); // ��� Ƚ��
		
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
			
			r += dx[d]*s; // �̵�
			c += dy[d]*s;
			
			r = checkValid(r, N);
			c = checkValid(c, N);
			now.r = r;
			now.c = c;
			count[r][c].add(i);
		}
	}
	
	private static void CheckSameLoc(ArrayList<Integer> balls, int r, int c) {
		int cnt = balls.size(); // ���� ĭ�� �ִ� �� ����
		int M = 0; // ���� ����
		int S = 0; // �ӵ� ����
		int D = 0; // ���� üũ
		int firstD = -1; // 0�̸� ¦, 1�̸� Ȧ
		boolean flag = false;
		for(Integer i : balls) { // ����, �ӵ� ���� ���ϰ�, ���� ���ϱ�
			FireBall now = fireball.get(i);
			M += now.m;
			S += now.s;
			
			if(!flag) { // ù ���̾�̸�
				if(now.d%2 == 0)	firstD = 0;
				else	firstD = 1;
				flag = true;
			}else { // �׷��� �ʴٸ�
				if(now.d%2 != firstD)	D = 1; // ù��° ���� ���� Ȧ¦�� �ٸ��ٸ�
			}
		}
		
		if(M/5 == 0)	return;
		
		int newM = M/5;
		int newS = S/cnt;
		for(int i=D; i<=D+6; i+=2) { // ���ο� ���̾ 4��
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
			MoveBall(N, count); // ��� ���̾ �̵�
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(count[i][j].size() > 1) { // 2�� �̻��̸�
						CheckSameLoc(count[i][j], i, j);
						for(Integer idx : count[i][j])	remove.add(idx);
					}
				}
			}
			
			if(remove.isEmpty())	continue; // ����� ���ٸ� �н�
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
		private int c; // ��ġ ��ǥ
		private int m; // ����
		private int s; // �ӵ�
		private int d; // ����
		
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
