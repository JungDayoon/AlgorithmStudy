import java.io.*;

public class _17679 {
	static int[] dx = {0, 0, 1, 1};
	static int[] dy = {0, 1, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int m = Integer.parseInt(s[0]);
		int n = Integer.parseInt(s[1]);
		
		String[] board = br.readLine().split(" ");
		
		System.out.print(solution(m, n, board));
	}
	
	private static int solution(int m, int n, String[] board) {
		int ans = 0;
		String[][] map = new String[m][n];
		for(int i=0; i<m; i++)
			map[i] = board[i].split("");
		
		while(true) {
			int cnt = Remove(m, n, map);
			if(cnt == 0)	break; // 지울게 없을 때
			ans += cnt;
			CleanUp(m, n, map);
		}
		
		return ans;
	}
	
	private static void CleanUp(int m, int n, String[][] map) {
		for(int j=0; j<n; j++) { // 한 행씩
			int emptyIdx = m;
			for(int i=m-1; i>=0; i--) { // 밑에서부터
				if(map[i][j].equals("")) {
					for(int k=i-1; k>=0; k--) {
						if(!map[k][j].equals("")){
							map[i][j] = map[k][j];
							map[k][j] = "";
							break;
						}
					}
				}
			}
		}
	}
	
	private static int Remove(int m, int n, String[][] map) {
		int cnt = 0;
		int[][] remove = new int[m][n];
		
		for(int i=0; i<m-1; i++) { // 지워야하는 블록 체크
			for(int j=0; j<n-1; j++) {
				String now = map[i][j];
				if(!now.equals("")) {
					int same = 0;
					for(int k=1; k<4; k++) {
						int rx = i + dx[k];
						int ry = j + dy[k];
						if(map[rx][ry].equals(now))	same++;
					}
					
					if(same == 3) { // 지워야 하는 블록 네개라면
						for(int k=0; k<4; k++) {
							int rx = i + dx[k];
							int ry = j + dy[k];
							remove[rx][ry]++; // 체크
						}
					}
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(remove[i][j] != 0) { // 지워야하는 블록 지우기
					map[i][j] = "";
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
