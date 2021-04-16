import java.io.*;
public class _17144 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] dust;
	static int[] cleaner;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int R = Integer.parseInt(s[0]);
		int C = Integer.parseInt(s[1]);
		int T = Integer.parseInt(s[2]);
		
		dust = new int[R][C];
		boolean flag = false;
		for(int i=0; i<R; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<C; j++) {
				dust[i][j] = Integer.parseInt(s[j]);
				if(dust[i][j]==-1 && !flag) {
					cleaner = new int[] {i, i+1};
					flag = true;
				}
			}
		}
		
		System.out.print(solution(R, C, T));
	}
	
	private static int solution(int R, int C, int T) {
		int remain = 0;
		for(int t=0; t<T; t++) {
			SpreadDust(R, C); // 미세먼지 확산
			for(int i=0; i<2; i++) { // 공기청정기 작동
				if(i == 0)	UpCleaner(cleaner[i], R, C);
				else	DownCleaner(cleaner[i], R, C);
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(dust[i][j] != -1)	remain += dust[i][j];
			}
		}
		
		return remain;
	}
	
	private static void DownCleaner(int cloc, int R, int C) { // 아래쪽 청정기
		for(int i=cloc+1; i<R-1; i++)	dust[i][0] = dust[i+1][0]; // 위쪽으로
		for(int j=0; j<C-1; j++)	dust[R-1][j] = dust[R-1][j+1]; // 왼쪽으로
		for(int i=R-1; i>cloc; i--)	dust[i][C-1] = dust[i-1][C-1]; // 아래쪽으로
		for(int j=C-1; j>1; j--)	dust[cloc][j] = dust[cloc][j-1]; // 오른쪽으로
		dust[cloc][1] = 0; // 청정기 바로 옆
	}
	
	private static void UpCleaner(int cloc, int R, int C) { // 위쪽 청정기
		for(int i=cloc-1; i>0; i--)	dust[i][0] = dust[i-1][0]; // 아래쪽으로
		for(int j=0; j<C-1; j++)	dust[0][j] = dust[0][j+1]; // 왼쪽으로
		for(int i=0; i<cloc; i++)	dust[i][C-1] = dust[i+1][C-1]; // 위쪽으로
		for(int j=C-1; j>1; j--)	dust[cloc][j] = dust[cloc][j-1]; // 오른쪽으로
		dust[cloc][1] = 0; // 청정기 바로 옆
	}

	private static void SpreadDust(int R, int C) {
		int[][] plus = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(dust[i][j] > 0) { // 먼지가 있다면
					int sdust = dust[i][j] / 5; // 확산되는 먼지의 양
					int cnt = 0; // 확산되는 칸 수
					for(int k=0; k<4; k++) {
						int rx = i + dx[k];
						int ry = j + dy[k];
						if(Valid(rx, ry, R, C) && dust[rx][ry]!=-1) {
							plus[rx][ry] += sdust;
							cnt++;
						}
					}
					dust[i][j] -= (sdust*cnt);
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				dust[i][j] += plus[i][j];
			}
		}
	}
	
	private static boolean Valid(int x, int y, int R, int C) {
		return (x>=0 && x<R && y>=0 && y<C);
	}
}
