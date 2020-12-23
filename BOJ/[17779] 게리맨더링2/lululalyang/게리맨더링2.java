import java.io.*;
import java.util.*;

public class _17779 {
	static int Min = Integer.MAX_VALUE;
	static int N;
	static int[][] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1; j<=N; j++)
				A[i][j] = Integer.parseInt(s[j-1]);
		}
		
		for(int x=1; x<=N-2; x++) {
			for(int y=2; y<=N-1; y++) {
				Compute_d1d2(x, y); //x행,y열
			}
		}
		
		System.out.println(Min);
	}
	
	private static int Compute_Sum(int sx, int ex, int sy, int ey, boolean[][] visited) {
		// sx: 행 시작점 // ex: 행 마지막점 -> 포함!
		// sy: 열 시작점 // ey: 열 마지막점 -> 포함!
		int sum = 0;
		for(int i=sx; i<=ex; i++) {
			for(int j=sy; j<=ey; j++) {
				if(!visited[i][j])
					sum += A[i][j];
			}
		}
		
		return sum;
	}
	
	private static void Compute_Min(int x, int y, int d1, int d2) {
		int[] sum = new int[6]; // 1~5번 선거구의 인구수
		
		/* 5번 선거구 인구수 계산 & 5번 선거구에 속하는 구역 true로 체크 */
		boolean[][] visited = new boolean[N+1][N+1]; //true면 선거구 5
		sum[5] += A[x][y];
		visited[x][y] = true;
		
		int front = -1; 
		int back = 1;
		int flag1 = 0; // front 값 바뀌었는지
		int flag2 = 0; // back 값 바뀌었는지
		
		for(int i=x+1; i<=x+d1+d2; i++) {
			for(int j=y+front; j<=y+back; j++) {
				sum[5] += A[i][j];
				visited[i][j] = true;
			}
			
			if(y+front!=y-d1 && flag1==0)
				front--;
			else if(y+front == y-d1) {
				flag1 = 1;
				front++;
			}else if(y+front!=y-d1 && flag1==1)
				front++;
				
			if(y+back!=y+d2 && flag2==0)
				back++;
			else if(y+back == y+d2) {
				flag2 = 1;
				back--;
			}else if(y+back!=y+d2 && flag2==1)
				back--;
		}
		
		/* 1번 ~ 4번 선거구의 인구수 계산 */
		sum[1] = Compute_Sum(1, x+d1-1, 1, y, visited);
		sum[2] = Compute_Sum(1, x+d2, y+1, N, visited);
		sum[3] = Compute_Sum(x+d1, N, 1, y-d1+d2-1, visited);
		sum[4] = Compute_Sum(x+d2+1, N, y-d1+d2, N, visited);
		
		Arrays.sort(sum); //최소값은 index1
		Min = Math.min(Min, (sum[5]-sum[1]));		
	}
	
	private static void Compute_d1d2(int x, int y) {
		int d1 = 1;
		while(d1<=(y-1)) {
			int d2 = 1;
			while((x+d1+d2)<=N && (y+d2)<=N) {
				
				Compute_Min(x, y, d1, d2);
				d2++;
			}
			d1++;
		}
	}
}
