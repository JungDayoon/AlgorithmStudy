import java.io.*;
import java.util.*;

public class _1600 {
	static int minMove = Integer.MAX_VALUE;
	static int[] dx1 = {-1, 0, 1, 0}; // 상하좌우
	static int[] dy1 = {0, 1, 0, -1};
	
	static int[] dx2 = {-2, -1, 1, 2, 2, 1, -1, -2}; // 말의 이동 
	static int[] dy2 = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int W = Integer.parseInt(s[0]);
		int H = Integer.parseInt(s[1]);
		int[][] map = new int[H][W];
		for(int i=0; i<H; i++) {
			String[] d = br.readLine().split(" ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(d[j]);
			}
		}
			
		findMinMove(K, map, W, H);
		if(minMove != Integer.MAX_VALUE)
			System.out.print(minMove);
		else
			System.out.print("-1");
	}

	private static void findMinMove(int K, int[][] map, int W, int H) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[H][W][31];
		visited[0][0][K] = true;
		q.add(new int[] {0, 0, K, 0}); // x, y, 남은 K값, 이때까지의 동작수
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0];
			int y = tmp[1];
			int remainK = tmp[2]; 
			int moveCnt = tmp[3];
			
			if(x==(H-1) && y==(W-1)) {
				minMove = Math.min(minMove, moveCnt);
				continue;
			}
			
			if(remainK > 0) {
				for(int k=0; k<8; k++) {
					int rx = x + dx2[k];
					int ry = y + dy2[k];
					
					if(checkValid(rx, ry, W, H) && map[rx][ry]!=1 && moveCnt<minMove && !visited[rx][ry][remainK-1]) {
						visited[rx][ry][remainK-1] = true;
						q.add(new int[] {rx, ry, remainK-1, moveCnt+1});
					}
				}
			}
			
			for(int k=0; k<4; k++) {
				int rx = x + dx1[k];
				int ry = y + dy1[k];
					
				if(checkValid(rx, ry, W, H) && map[rx][ry]!=1 && moveCnt<minMove && !visited[rx][ry][remainK]) {
					visited[rx][ry][remainK] = true;
					q.add(new int[] {rx, ry, remainK, moveCnt+1});
				}
			}
		}
	}
	
	private static boolean checkValid(int x, int y, int W, int H) {
		return (x>=0 && x<H && y>=0 && y<W);
	}
}
