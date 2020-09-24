import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1992 {
	
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		QuadTree(0, 0, N);
	}

	static void QuadTree(int s_x, int s_y, int N) {
		int check = map[s_x][s_y];
		int cnt = 0;
		int flag = 0;
		for(int i=s_x; i<s_x+N; i++) {
			for(int j=s_y; j<s_y+N; j++) {
				if(map[i][j] == check) { 
					cnt++;
				}
				else{
					flag = 1;
					break;
				}
			}
			if(flag == 1) break;
		}
		if(cnt == N*N) {
			System.out.print(check);
			return;
		}else if(N != 1){
			int halfN = N/2;
			System.out.print("(");
			QuadTree(s_x, s_y, halfN);
			if(s_y+halfN < map.length)
				QuadTree(s_x, s_y+halfN, halfN);
			if(s_x+halfN < map.length)
				QuadTree(s_x+halfN, s_y, halfN);
			if((s_x+halfN<map.length) && (s_y+halfN<map.length))
				QuadTree(s_x+halfN, s_y+halfN, halfN);
			System.out.print(")");
		}
	}
}
