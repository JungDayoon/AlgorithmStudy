import java.io.*;
import java.util.*;

public class _2643 {
	static int maxCnt = 0;
	static ArrayList[] onto;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] paper = new int[N][2]; 		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<2; j++) {
				paper[i][j] = Integer.parseInt(s[j]);
			}
		}		
		
		findMax(paper, N);
		System.out.print(maxCnt);
	}
	
	private static void findMax(int[][] paper, int N) {
		onto = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			onto[i] = new ArrayList<Integer>();
			for(int j=0; j<N; j++) {
				if(j!=i && CanPuton(paper[i][0], paper[i][1], paper[j][0], paper[j][1])) {
					onto[i].add(j);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			recursion(i, 1);
		}
	}
	
	private static void recursion(int idx, int cnt) {
		Iterator<Integer> now = onto[idx].iterator();
		while(now.hasNext()) {
			recursion(now.next(), cnt+1);
		}
		
		maxCnt = Math.max(maxCnt, cnt);
	}
	private static boolean CanPuton(int a1, int b1, int a2, int b2) { //(a1, b1)에 (a2, b2)가 올라갈 수 있는지
		if(a1>=a2 && b1>=b2) // 그냥 올릴 수 있거나
			return true;
		else if(a1>=b2 && b1>=a2) // 90도 회전해서 올릴 수 있음
			return true;
		else // 그 외는 불가
			return false;
	}
}
