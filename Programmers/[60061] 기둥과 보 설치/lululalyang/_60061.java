import java.io.*;
import java.util.*;

public class _60061 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] build_frame = new int[m][4];
		
		String[] s;
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<4; j++)
				build_frame[i][j] = Integer.parseInt(s[j]);
		}
		
		int[][] res = solution(n, build_frame);
		for(int i=0; i<res.length; i++) {
			for(int j : res[i]) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
	
	private static boolean Valid(int n, int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
	
	private static boolean ChkValid(int x, int y, int a, ArrayList<Integer>[][] build) {
		int n = build.length;
		if(a == 0) { // ���
			if(y == 0)	return true; // �ٴ��̸� ok
			if(Valid(n, x, y-1) && build[x][y-1].contains(0))	return true;
			if(Valid(n, x, y) && build[x][y].contains(1))	return true;
			if(Valid(n, x-1, y) && build[x-1][y].contains(1))	return true;
		}else { // a==1 // ��
			if(Valid(n, x, y-1) && build[x][y-1].contains(0))	return true;
			if(Valid(n, x+1, y-1) && build[x+1][y-1].contains(0))	return true;
			if(Valid(n, x-1, y) && Valid(n, x+1, y)) {
				if(build[x-1][y].contains(1) && build[x+1][y].contains(1)) // �� ���ʿ� ���� ���� ��
					return true;
			}
		}
		
		return false;
	}
	
	private static int[][] solution(int n, int[][] build_frame){
		int ansCnt = 0; // ��ġ�� ����
		
		ArrayList<Integer>[][] build = new ArrayList[n+1][n+1];
		for(int i=0; i<n+1; i++) {
			for(int j=0; j<n+1; j++) {
				build[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int[] now : build_frame) {
			int x = now[0];
			int y = now[1];
			int a = now[2]; // ���:0 // ��:1
			int b = now[3]; // ��ġ:1 // ����: 0
			
			if(b == 1) { // ��ġ ��
				if(ChkValid(x, y, a, build)) { // ��ġ �����ϸ�
					build[x][y].add(a);
					ansCnt++;
				}
			}else { // ���� ��
				build[x][y].remove(build[x][y].indexOf(a));
				ansCnt--;
				
				if(!ChkAllFrame(build)) { // �����ߴµ� ��Ģ�� ��߳��ٸ�
					build[x][y].add(a); // �ٽ� ��ġ
					ansCnt++;
				}
			}
		}
		
		int[][] ans = new int[ansCnt][3];
		int idx = 0;
		for(int i=0; i<n+1; i++) {
			for(int j=0; j<n+1; j++) {
				if(!build[i][j].isEmpty()) {
					if(build[i][j].size() == 2) { // �� �� ������
						ans[idx][0] = i;
						ans[idx][1] = j;
						ans[idx][2] = 0; // ���
						
						ans[++idx][0] = i;
						ans[idx][1] = j;
						ans[idx++][2] = 1; // ��
					}else { // �ϳ��� ���� ��
						ans[idx][0] = i;
						ans[idx][1] = j;
						ans[idx++][2] = build[i][j].get(0);
					}
				}
				if(idx == ansCnt)	break;
			}
			if(idx == ansCnt)	break;
		}
		
		return ans;
	}
	
	private static boolean ChkAllFrame(ArrayList<Integer>[][] build) {
		int n = build.length;
		boolean flag = true;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(Integer now : build[i][j]) {
					if(!ChkValid(i, j, now, build)) { // �ٽ� ��ġ�� �� �ִ���
						flag = false;
						break;
					}
				}
				if(!flag)	break;
			}
			if(!flag)	break;
		}
		return flag;
	}
	
//	private static class Frame{
//		int x;
//		int y;
//		int a;
//		
//		Frame(int x, int y, int a){
//			this.x = x;
//			this.y = y;
//			this.a = a;
//		}
//		
//		@Override
//		public boolean equals(Object o) {
//			Frame f = (Frame)o;
//			
//			if(this.x==f.x && this.y==f.y && this.a == f.a)
//				return true;
//			else
//				return false;
//		}
//	}
}
