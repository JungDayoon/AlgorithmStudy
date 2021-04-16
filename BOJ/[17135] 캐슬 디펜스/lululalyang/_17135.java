import java.io.*;
import java.util.*;
public class _17135 {
	static int Max = 0; // 공격으로 제거할 수 있는 적의 최대 수
	static int[][] state;
	static int N, M, D;
	static int AllEnemy = 0; // 적 명수
	
	static int[] dx = {0, -1, 0};
	static int[] dy = {-1, 0, 1}; // 서, 북, 동
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		D = Integer.parseInt(s[2]);
		state = new int[N][M];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				state[i][j] = Integer.parseInt(s[j]);
				if(state[i][j] == 1)	AllEnemy++; 
			}
		}
		
		ArrayList<Integer> tmp = new ArrayList<>();
		Comb(M, 3, 0, tmp);
		System.out.print(Max);
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			GameStart(tmp);
			return;
		}
		
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static void GameStart(ArrayList<Integer> Loc) {
		int turn = 0;
		int Arrive = 0; // 성에 도착한 적의 수
		int Die = 0; // 궁수의 공격으로 죽은 적의 수
		int[][] Stmp = new int[N][M]; // 이번 게임에서 사용할 판
		for(int i=0; i<N; i++)	Stmp[i] = state[i].clone();
		
		while(AllEnemy != (Arrive + Die)) { // 모든 적이 다 제거될 때까지
			Die += ArcherAttack(Loc, Stmp);
			Arrive += EnemyMove(Stmp, turn);
			turn++;
		}
		
		Max = Math.max(Max, Die);
	}
	
	private static int EnemyMove(int[][] Stmp, int turn) {
		int Arrive = 0;
		for(int j=0; j<M; j++) { // 맨 마지막 행에 있는 적 => 성에 도착하므로 제거됨
			if(Stmp[N-1][j] == 1) {
				Arrive++;
				Stmp[N-1][j] = 0;
			}
		}
		
		for(int i=N-1; i>turn; i--) {
			for(int j=0; j<M; j++) {
				Stmp[i][j] = Stmp[i-1][j]; // 남아있는 적은 한칸씩 아래로
			}
		}
		
		for(int j=0; j<M; j++)	Stmp[turn][j] = 0; // 한 칸 내려감 => 이 줄은 적 없다
		return Arrive;
	}
	
	private static int ArcherAttack(ArrayList<Integer> Loc, int[][] Stmp) {
		int Die = 0;
		ArrayList<int[]> DieEnemy = new ArrayList<>(); // 죽인 적의 위치
		for(Integer loc : Loc) { // 각 궁수마다
			int er=-1, ec=-1;
			Queue<int[]> q = new LinkedList<>(); // r, c, 이동거리
			boolean[][] visited = new boolean[N][M];
			q.add(new int[] {N, loc, 0});
			
			while(!q.isEmpty()) {
				int[] now = q.poll();
				int x = now[0];
				int y = now[1];
				int d = now[2];
				
				if(d == D)	break;
				if(er!=-1 && ec!=-1)	break; // 이미 가까운 적을 찾았다면
				
				boolean flag = false;
				for(int k=0; k<3; k++) {
					int rx = x + dx[k];
					int ry = y + dy[k];
					
					if(Valid(rx, ry)) {
						if(Stmp[rx][ry] == 1) {
							er = rx;
							ec = ry;
							flag = true;
							break;
						}else { // 빈공간이면
							visited[rx][ry] = true;
							q.add(new int[] {rx, ry, d+1});
						}
					}
				}
				
				if(flag)	break;
			}
			
			if(er==-1 && ec==-1)	continue;
			DieEnemy.add(new int[] {er, ec});
		}
		
		for(int[] dieloc : DieEnemy) {
			int x = dieloc[0];
			int y = dieloc[1];
			if(Stmp[x][y] == 1) {
				Stmp[x][y] = 0;
				Die++;
			}
		}
		
		return Die;
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
}
