import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class _5653 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int[][] map;
	static int[][] exist; //0없음 1존재 2죽음
	static int[][] state; 
	static int alive = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			alive = 0;
			
			map = new int[351][351]; //배양용기 최대크기 150+50+150 (+1은 추가로)
			exist = new int[351][351];
			state = new int[351][351]; //각 세포의 상태 정보
			
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // 세로 크기
			int M = Integer.parseInt(s[1]); // 가로 크기
			int K = Integer.parseInt(s[2]); // 배양 시간
			
			for(int i=175-(N/2); i<175-(N/2)+N; i++) { //처음에 배양용기 정 가운데에 줄기세포 놔둔다
				String[] d = br.readLine().split(" ");
				int k=0;
				for(int j=175-(M/2); j<175-(M/2)+M; j++) {
					map[i][j] = Integer.parseInt(d[k++]);
					state[i][j] = map[i][j];
					if(map[i][j] != 0) {
						exist[i][j] = 1; //0없음 1존재 2죽음
						alive++;
					}
				}
			}
			
			for(int k=0; k<K; k++) { //K시간 동안
				Breeding();
			}
			
			System.out.println("#"+(t+1)+" "+alive);
		}
	}
	
	static ArrayList<int[]> zero = new ArrayList<>();
	static void Breeding() { //map:생명력 수치 //exist:세포 존재 유무 (0없음 1존재 2죽음)//state:세포의 상태
		
		zero.clear();
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(exist[i][j] == 1) { //세포 존재하면 -> 상태--
					if(state[i][j] == 0){ //상태0이면 번식해야되니까 
						zero.add(new int[] {i, j});
					}
					
					state[i][j]--;
					
					if((-1)*state[i][j] == map[i][j]) { // state == -map 이라면 => 죽은세포! 하지만 공간은 차지
						exist[i][j] = 2; //죽은세포
						alive--;
					}
				}
			}
		}
		
		for(int[] z : zero) {
			int x = z[0];
			int y = z[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(rx<0 || ry<0 || rx>=map.length || ry>=map[0].length) //범위 벗어나면
					continue;
				
				if(exist[rx][ry] == 0) // 번식하려는 곳에 세포 없다면 -> 번식! alive키워주기 !!!
				{ 
					exist[rx][ry] = 1;
					map[rx][ry] = map[x][y];
					state[rx][ry] = map[x][y];
					
					alive++;
					
				}else // exist[rx][ry]==1 || exist[rx][ry]==2 //살아있거나 죽어있거나 암튼 이미 세포있다면
				{ 
					if(map[rx][ry] == state[rx][ry]) { // 생명력수치와 상태가 같다면 -> 방금 생긴것!
						if(map[rx][ry] < map[x][y]) { // 현재 번식하려는 세포의 생명력 수치가 더 크다면
							map[rx][ry] = map[x][y];
							state[rx][ry] = map[x][y]; //큰 애가 번식해 자리 차지한다 ->원래있던 세포번식할 때 alive키워줬으니까 안해줘도 됨
						} // else -> 현재 번식 세포의 생명력이 더 작거나 같으면 그대로 놔둠
					} // else -> 방금 생긴 세포가 아니라면 -> 원래 있던 세포 -> 번식 못한다
				}
				
			}
		}
	}
}
