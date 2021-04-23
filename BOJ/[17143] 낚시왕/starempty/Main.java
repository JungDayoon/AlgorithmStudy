import java.io.*;
import java.util.*;
public class Main {
	static int dx[] = {0,-1,1,0,0};
	static int dy[] = {0,0,0,1,-1};
	public static class shark{
		int x, y, v, dir, size;
		public shark(int x, int y, int v, int dir, int size) {
			this.x = x;
			this.y = y;
			this.v = v;
			this.dir = dir;
			this.size = size;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); //상어 수
		int map[][] = new int[R][C];
		List<shark> li = new ArrayList<>(); 
		boolean[] death = new boolean[m+1];
		if(m > 0) li.add(new shark(-1,-1,-1,-1,-1));  //list에 1부터 m까지 상어를 넣을 수 있도록
		for(int i = 1; i <= m; i++) { //map에 1부터 m으로 표시될 수 있도록
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			li.add(new shark(r, c, v, dir, size));
			map[r][c] = i;
		}
        
		int ans = 0;
		for(int i = 0; i < C; i++) {
			if(isEmpty(death)) break; //더이상 잡을 상어가 없는 경우
			//1. 가장 가까운 상어 낚시
			for(int j = 0; j < R; j++) {
				if(map[j][i] != 0 && !death[map[j][i]]) {
					ans += li.get(map[j][i]).size;
					//죽은 표시
					death[map[j][i]] = true;
					map[j][i] = 0;
					break;
				}
			}
			//2. 상어들 이동
			for(int j = 1; j < li.size(); j++) {
				if(death[j]) continue; //죽은 상어 제외
				shark cur = li.get(j);
				if(cur.dir < 3) { //x이동
					int nx = cur.x;
					int nd = cur.dir;
					for(int v = 0; v < cur.v; v++) {
						if(R == 1) break;
						if(-1==nx+dx[nd] || nx+dx[nd]==R) nd = (nd==1)?2:1;
						nx += dx[nd];
					}
					li.get(j).x = nx;
					li.get(j).dir = nd;
				}else { //y이동
					int ny = cur.y;
					int nd = cur.dir;
					for(int v = 0; v < cur.v; v++) {
						if(C == 1) break;
						if(-1==ny+dy[nd] || ny+dy[nd]==C) nd = (nd==3)?4:3;
						ny += dy[nd];
					}
					li.get(j).y = ny;
					li.get(j).dir = nd;
				}
			}
			
			//3. 잡아먹히는 상어 찾기 + map재정비
			int[][] copy = new int[R][C];
			for(int j = 1; j < li.size(); j++) {
				if(death[j]) continue; //죽은 상어 제외
				
				shark cur = li.get(j);
				int loc = copy[cur.x][cur.y];
				
				//map이 비어있는 경우
				if(loc == 0) {
					copy[cur.x][cur.y] = j; 
				}else { //map에 이미 상어가 있어서 비교해야하는 경우
					shark cmp = li.get(loc);
					if(cur.size > cmp.size) { 
						//새로 들어갈 내가 사이즈가 더 크다면 copy update
						death[loc] = true;
						copy[cur.x][cur.y] = j; 
					}else {
						death[j] = true;
					}
				}
			}
			//4. map 초기화 및 재정비
			init(map);
			for(int j = 1; j < li.size(); j++) {
				if(death[j]) continue;
				shark cur = li.get(j);
				map[cur.x][cur.y] = j; 
			}
		}
		System.out.println(ans);
	}
	private static void init(int[][] map) {
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[x].length; y++) {
				map[x][y] = 0;
			}
		}
	}
	private static boolean isEmpty(boolean[] death) {
		for(int i = 0; i < death.length; i++) {
			if(!death[i]) return false;
		}
		return true;
	}
}
