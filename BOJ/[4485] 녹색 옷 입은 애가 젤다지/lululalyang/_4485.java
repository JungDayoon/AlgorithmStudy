import java.io.*;
import java.util.*;
public class _4485 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N;
		String[] s;
		int t = 1; // 테스트케이스 번호
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0)	break;
			if(sb.length() != 0)	sb.append("\n");
			
			int[][] black = new int[N][N]; // 도둑루피
			for(int i=0; i<N; i++) {
				s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					black[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			sb.append("Problem "+(t++)+": "+solution(N, black));
		}
		
		System.out.print(sb.toString());
	}
	
	private static int solution(int N, int[][] black) {
		int res = Integer.MAX_VALUE;
		int[][] lost = new int[N][N]; // 잃는 루피의 최솟값
		for(int i=0; i<N; i++)	Arrays.fill(lost[i], Integer.MAX_VALUE);
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, black[0][0]}); // (x, y), 잃은 루피
		lost[0][0] = black[0][0];
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int l = now[2];
			
			if(x==(N-1) && y==(N-1)) {
				res = Math.min(res, l);
				continue;
			}
			if(lost[x][y] < l)	continue;
			
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(N, rx, ry)) {
					if(lost[rx][ry] > (l + black[rx][ry])) {
						lost[rx][ry] = l + black[rx][ry];
						q.add(new int[] {rx, ry, lost[rx][ry]});
					}
				}
			}
		}
		
		return res;
	}
	
	private static boolean Valid(int N, int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
