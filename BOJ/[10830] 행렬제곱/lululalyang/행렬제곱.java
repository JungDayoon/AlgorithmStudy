import java.io.*;
import java.util.*;

public class _10830 {
	static int[][] b;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		long B = Long.parseLong(s[1]);
		
		b = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] d = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				b[i][j] = Integer.parseInt(d[j]);
			}
		}
		
		int[][] res = Sol(B);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(res[i][j]);
				if(j!=(N-1))
					sb.append(" ");
			}
			if(i!=(N-1))
				sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static int[][] Multi(int[][] x, int[][] y){
		int N = x.length;
		int[][] res = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//res[i][j]
				int sum = 0;
				for(int k=0; k<N; k++) {
					sum += x[i][k]*y[k][j];
				}
				res[i][j] = sum%1000;
			}
		}
		return res;
	}
	
	private static int[][] Sol(Long _B){
		if(_B == 1L) {
			for(int i=0; i<b.length; i++) {
				for(int j=0; j<b[0].length; j++)
					b[i][j] %= 1000;
			}
			return b;
		}else if(_B == 2L)
			return Multi(b, b);
		
		// _B >= 3
		if(_B%2 == 1) {
			return Multi(b, Sol(_B-1L));
		}else {
			int[][] tmp = Sol(_B/2L);
			return Multi(tmp, tmp);
		}
	}
}
