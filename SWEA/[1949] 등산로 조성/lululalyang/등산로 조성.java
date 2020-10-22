import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class _1949 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int maxLen = 0;
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			maxLen = 0;
			String[] d = br.readLine().split(" ");
			int N = Integer.parseInt(d[0]);
			K = Integer.parseInt(d[1]); //최대 공사 가능 깊이 // 1<=K<=5
			int[][] map = new int[N][N];
			
			ArrayList<int []> maxLoc = new ArrayList<>();
			int max = 0;
			for(int i=0; i<N; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(max < map[i][j]) { //max값보다 크면
						max = map[i][j];
						maxLoc.clear();
						maxLoc.add(new int[] {i, j});
					}else if(max == map[i][j]) { //max값과 같으면
						maxLoc.add(new int[] {i, j});
					}
				}
			}			
			
			boolean[][] visited = new boolean[N][N];
			int[][] tmpMap = new int[N][N];
			for(int[] tmp : maxLoc) {
				Initialmap(tmpMap, map);
				visited[tmp[0]][tmp[1]] = true;
				dfs(tmpMap, visited, tmp[0], tmp[1], 1, 0);
				visited = new boolean[N][N];
			}
			
			
			System.out.println("#"+(t+1)+" "+maxLen);
		}//테스트케이스 끝
	}
	
	static void Initialmap(int[][] tmpMap, int[][] map) { //map -> tmpMap
		for(int i=0; i<map.length; i++) {
			tmpMap[i] = map[i].clone();
		}
	}
	
	static void dfs(int[][] map, boolean[][] visited, int x, int y, int len, int YorN) {
		
		for(int k=0; k<4; k++) {
			int rx = x + dx[k];
			int ry = y + dy[k];
			
			if(rx>=0 && rx<map.length && ry>=0 && ry<map.length && !visited[rx][ry]) { //범위 내에 있고 방문하지 않았으면
				if(map[rx][ry] < map[x][y]) { //낮은 지형이라면
					visited[rx][ry] = true;
					dfs(map, visited, rx, ry, (len+1), YorN);
					visited[rx][ry] = false;
				}else if(map[rx][ry]==map[x][y] && YorN==0) { //높이가 같고, 이전에 깎지 않았으면
					map[rx][ry]--; //1만큼만 깎아줌 (k는 1이상 5이하니까 가능)
					visited[rx][ry] = true;
					dfs(map, visited, rx, ry, (len+1), 1);
					map[rx][ry]++;
					visited[rx][ry] = false;
				}else if(map[rx][ry]>map[x][y] && YorN==0) { //높은 지형인데 이전에 깎지 않았으면
					int diff = map[rx][ry] - map[x][y];
					if(diff < K) { //그 차이가 K보다 작으면 -> 깎기 가능
						map[rx][ry] -= (diff+1); //더 낮아지려면 diff+1 만큼 깎아야 함
						visited[rx][ry] = true;
						dfs(map, visited, rx, ry, (len+1), 1);
						map[rx][ry] += (diff+1);
						visited[rx][ry] = false;
					}else { //그차이가 더 크면 -> 깎기 불가능 -> 등산로 조성 끝
						maxLen = Math.max(maxLen, len);
					}
				}else { //높이가 높거나 같은데 이전에 깎았으면 -> 더이상 못깎는다 기회는 한번 -> 등산로 조성 끝
					maxLen = Math.max(maxLen, len);
				}
			}else { //범위 밖이거나 방문했던 곳이면
				maxLen = Math.max(maxLen, len);
			}
		}
	}
	
}
