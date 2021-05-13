import java.util.*;
import java.io.*;
public class _21611 {
	static int[] bomb = new int[4]; // 1~3
	static int[] dx = {0, -1, 1, 0, 0}; // 상하좌우
	static int[] dy = {0, 0, 0, -1, 1};
	static int[][] map;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int[][] info = new int[M][2]; // 방향, 거리
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<2; j++)	info[i][j] = Integer.parseInt(s[j]);
		}
		
		solution(M, info);
		System.out.print(bomb[1] + bomb[2]*2 + bomb[3]*3);
	}
	
	// 테케3번 두번돌고 세번째 명령할때 remain비어있음
	private static void solution(int M, int[][] info) {
		ArrayList<Integer> remain;
		for(int i=0; i<M; i++) {
			int d = info[i][0];
			int s = info[i][1];
			remain = new ArrayList<>(); // 남은 구슬 리스트
			
			Attack(d, s);
			remainTolist(remain);
			Explosion(remain);
			Grouping(remain);
			remainToMap(remain);
		}
	}
	
	private static void remainTolist(ArrayList<Integer> remain) { // 남은 구슬 remain으로
		int x = N/2;
		int y = N/2;
		
		int size = 1;
		int dir = 3;
		boolean flag = false;
		boolean zero;
		while(true) {
			zero = false;
			for(int i=1; i<=size; i++) {
				x += dx[dir];
				y += dy[dir];
				
				int num = map[x][y];
				if(num == 0) {
					if(!zero)	zero = true;
					else	break;
				}else {
					if(zero)	zero = false;
					remain.add(num);
				}
			}
			if(zero && size!=1)	break; // 남은 구슬이 없는것
			if(flag && size==(N-1))	break; // 마지막 한 줄 확인
			
			if(!flag)	flag = true;
			else {
				flag = false;
				size++;
			}
			dir = nextDir(dir);
		}
		//zero true면 그만
		if(zero) return;
		for(int i=1; i<=N-1; i++) {
			x += dx[3];
			y += dy[3];
			
			int num = map[x][y];
			if(num != 0)	remain.add(num); // 0이 아니면
		}
	}
	
	private static void remainToMap(ArrayList<Integer> remain) {
		int x = N/2;
		int y = N/2;
		
		int size = 1;
		int dir = 3;
		boolean flag = false;
		
		int ridx = 0;
		boolean done = false;
		while(true) {
			for(int i=1; i<=size; i++) {
				x += dx[dir];
				y += dy[dir];
				
				if(done)	map[x][y] = 0;
				else {
					if(ridx < remain.size()) {
						map[x][y] = remain.get(ridx++);
					}else {
						done = true;
						map[x][y] = 0;
					}
				}
			}
			
			if(flag && size==(N-1))	break; // 마지막 한 줄 확인
			
			if(!flag)	flag = true;
			else {
				flag = false;
				size++;
			}
			dir = nextDir(dir);
		}
		
		for(int i=1; i<=N-1; i++) {
			x += dx[3];
			y += dy[3];
			
			if(done)	map[x][y] = 0;
			else {
				if((ridx+1) < remain.size()) {
					map[x][y] = remain.get(ridx++);
				}else	done = true;
			}
		}
	} 
	
	private static void Grouping(ArrayList<Integer> remain) {
		int i=0;
		while(i<remain.size()) {
			int num = remain.get(i);
			int cnt = 1;
			for(int j=i+1; j<remain.size(); j++) {
				if(remain.get(j) == num)	cnt++;
				else	break;
			}
			
			for(int j=0; j<cnt; j++)	remain.remove(i);
			remain.add(i, num);
			remain.add(i, cnt);
			i += 2;
		}
	}
	
	private static void Explosion(ArrayList<Integer> remain) {
		boolean flag = false;
		do {
			flag = false;
			int i=0;
			while(i < remain.size()) {
				int num = remain.get(i);
				int same = 1;
				for(int j=i+1; j<remain.size(); j++) {
					if(remain.get(j) == num)	same++;
					else	break;
				}
				
				if(same >= 4) { // 같은게 4개 이상이면
					for(int j=0; j<same; j++)	remain.remove(i);
					bomb[num] += same;
					flag = true;
				}else	i++;
			}
			
		}while(flag); // 터질구슬이 없을 때까지
		
	}
	
	private static int nextDir(int dir) {
		int res = -1; 
		switch(dir) {
		case 3 : res = 2;
				break;
		case 2 : res = 4;
				break;
		case 4 : res = 1;
				break;
		case 1 : res = 3;
				break;
		}
		return res;
	}
	
	private static void Attack(int d, int s) { // 상어의 공격
		int x = N/2;
		int y = N/2;
		
		for(int i=1; i<=s; i++) {
			x = x + dx[d];
			y = y + dy[d];
			
			map[x][y] = 0;
		}
	}
}
