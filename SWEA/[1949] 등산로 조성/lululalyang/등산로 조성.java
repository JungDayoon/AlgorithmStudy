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
			K = Integer.parseInt(d[1]); //�ִ� ���� ���� ���� // 1<=K<=5
			int[][] map = new int[N][N];
			
			ArrayList<int []> maxLoc = new ArrayList<>();
			int max = 0;
			for(int i=0; i<N; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(max < map[i][j]) { //max������ ũ��
						max = map[i][j];
						maxLoc.clear();
						maxLoc.add(new int[] {i, j});
					}else if(max == map[i][j]) { //max���� ������
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
		}//�׽�Ʈ���̽� ��
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
			
			if(rx>=0 && rx<map.length && ry>=0 && ry<map.length && !visited[rx][ry]) { //���� ���� �ְ� �湮���� �ʾ�����
				if(map[rx][ry] < map[x][y]) { //���� �����̶��
					visited[rx][ry] = true;
					dfs(map, visited, rx, ry, (len+1), YorN);
					visited[rx][ry] = false;
				}else if(map[rx][ry]==map[x][y] && YorN==0) { //���̰� ����, ������ ���� �ʾ�����
					map[rx][ry]--; //1��ŭ�� ����� (k�� 1�̻� 5���ϴϱ� ����)
					visited[rx][ry] = true;
					dfs(map, visited, rx, ry, (len+1), 1);
					map[rx][ry]++;
					visited[rx][ry] = false;
				}else if(map[rx][ry]>map[x][y] && YorN==0) { //���� �����ε� ������ ���� �ʾ�����
					int diff = map[rx][ry] - map[x][y];
					if(diff < K) { //�� ���̰� K���� ������ -> ��� ����
						map[rx][ry] -= (diff+1); //�� ���������� diff+1 ��ŭ ��ƾ� ��
						visited[rx][ry] = true;
						dfs(map, visited, rx, ry, (len+1), 1);
						map[rx][ry] += (diff+1);
						visited[rx][ry] = false;
					}else { //�����̰� �� ũ�� -> ��� �Ұ��� -> ���� ���� ��
						maxLen = Math.max(maxLen, len);
					}
				}else { //���̰� ���ų� ������ ������ ������� -> ���̻� ����´� ��ȸ�� �ѹ� -> ���� ���� ��
					maxLen = Math.max(maxLen, len);
				}
			}else { //���� ���̰ų� �湮�ߴ� ���̸�
				maxLen = Math.max(maxLen, len);
			}
		}
	}
	
}
