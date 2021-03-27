import java.io.*;
import java.util.*;

public class Main {
	static int n, min;
	static int[] link;
	static int[][] ability;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		ability = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		link = new int[n];
		min = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(min);
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == n/2) {
			boolean[] visit = new boolean[n];
			for(int i = 0; i < n/2; i++) {
				visit[link[i]] = true;
			}
			int s1 = 0, s2 = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(visit[i] && visit[j]) {
						s1 += ability[i][j];
					}
					else if(!visit[i] && !visit[j]) {
						s2 += ability[i][j];
					}
				}
			}
			min = Math.min(min, Math.abs(s1-s2));
			return;
		}
		for(int i = start; i < n; i++) {
			link[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
}