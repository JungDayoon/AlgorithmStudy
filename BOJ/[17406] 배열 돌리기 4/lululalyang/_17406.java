import java.io.*;
import java.util.*;
public class _17406 {
	static int Min = Integer.MAX_VALUE;
	static int[][] map;
	static Rotate[] R;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] S = br.readLine().split(" ");
		N = Integer.parseInt(S[0]); // 세로
		M = Integer.parseInt(S[1]); // 가로
		int K = Integer.parseInt(S[2]); // 회전 연산 개수
		map = new int[N+1][M+1];
		R = new Rotate[K];
		for(int i=1; i<N+1; i++) {
			S = br.readLine().split(" ");
			for(int j=1; j<M+1; j++) {
				map[i][j] = Integer.parseInt(S[j-1]);
			}
		}
		
		for(int i=0; i<K; i++) {
			S = br.readLine().split(" ");
			int r = Integer.parseInt(S[0]);
			int c = Integer.parseInt(S[1]);
			int s = Integer.parseInt(S[2]);
			
			R[i] = new Rotate(r-s, c-s, r+s, c+s);
		}
		
		boolean[] visited = new boolean[K];
		ArrayList<Integer> tmp = new ArrayList<>();
		Perm(K, K, visited, tmp);
		
		System.out.print(Min);
	}
	
	private static void Perm(int K, int r, boolean[] visited, ArrayList<Integer> tmp) {
		if(r==0) {
			// 이 순서로 회전시키기
			RotateByOrder(tmp);
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				tmp.add(i);
				Perm(K, r-1, visited, tmp);
				tmp.remove(tmp.indexOf(i));
				visited[i] = false;
			}
		}
	}
	
	private static void RotateByOrder(ArrayList<Integer> order) {
		int[][] maptmp = new int[N+1][M+1];
		for(int i=1; i<N+1; i++)	maptmp[i] = map[i].clone();
		
		for(Integer now : order) { // 회전
			int sx = R[now].sx;
			int sy = R[now].sy;
			int ex = R[now].ex;
			int ey = R[now].ey;
			
			int n = ex - sx + 1;
			int m = ey - sy + 1;
			int minus = 0;
			for(int i=sx, j=sy; i<sx+(n/2) && j<sy+(m/2); i++, j++) {
				RotateMap(i, j, n-minus, m-minus, maptmp);
				minus += 2;
			}
		}
		
		ComputeArrValue(maptmp);
	}
	
	private static void ComputeArrValue(int[][] maptmp) {
		int min = Integer.MAX_VALUE;
		for(int i=1; i<N+1; i++) {
			int sum = 0;
			for(int j=1; j<M+1; j++)	sum += maptmp[i][j];
			min = Math.min(min, sum);
		}
		Min = Math.min(Min, min);
	}
	
	private static void RotateMap(int i, int j, int n, int m, int[][] maptmp) {
		
		int prev=0, end=0;
		// ->
		prev = maptmp[i][j+m-1]; // 2번
		for(int y=j+m-1; y>j; y--)	maptmp[i][y] = maptmp[i][y-1];
		maptmp[i][j] = maptmp[i+1][j];
		
		// 아래
		end = maptmp[i+n-1][j+m-1];
		for(int x=i+n-1; x>i+1; x--)	maptmp[x][j+m-1] = maptmp[x-1][j+m-1];
		maptmp[i+1][j+m-1] = prev;
		prev = end; // 3번
		
		// <-
		end = maptmp[i+n-1][j]; // 4번
		for(int y=j; y<j+m-2; y++)	maptmp[i+n-1][y] = maptmp[i+n-1][y+1];
		maptmp[i+n-1][j+m-2] = prev;
		
		// 위로
		for(int x=i+1; x<i+n-2; x++)	maptmp[x][j] = maptmp[x+1][j];
		maptmp[i+n-2][j] = end;
	}
	
	private static class Rotate{
		private int sx;
		private int sy;
		private int ex;
		private int ey;
		
		Rotate(int sx, int sy, int ex, int ey){
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}
	}
}
