import java.io.*;
import java.util.*;

public class _5656 {
	static int Mincnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			Mincnt = Integer.MAX_VALUE;
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); //구슬 개수
			int W = Integer.parseInt(s[1]); //가로
			int H = Integer.parseInt(s[2]); //세로
			int[][] brick = new int[H][W]; //주어진 벽돌 정보
			
			for(int i=0; i<H; i++) {
				String[] d = br.readLine().split(" ");
				for(int j=0; j<W; j++) {
					brick[i][j] = Integer.parseInt(d[j]);
				}
			}
			
			recursion(N, brick);
			
			sb.append("#"+t+" "+Mincnt+"\n");
		}//테이스케이스 끝
		
		System.out.print(sb.toString());
	}
	
	private static void recursion(int marble, int[][] arr) { // 구슬 개수, 현재 arr상태 
		if(marble == 0) {
			Mincnt = Math.min(Mincnt, remainBrick(arr));
			return;
		}
		
		int[][] tmpArr = new int[arr.length][arr[0].length];
		for(int j=0; j<arr[0].length; j++) { // 한줄씩 구슬 떨어뜨린다
			arrAtoB(arr, tmpArr);
			crashBrick(j, tmpArr);
			recursion(marble-1, tmpArr);
		}
	}
	
	private static void arrAtoB(int[][] a, int[][] b) {
		for(int i=0; i<a.length; i++) {
			b[i] = a[i].clone();
		}
	}
	
	private static int remainBrick(int[][] arr) {
		int remain = 0;
		for(int j=0; j<arr[0].length; j++) {
			for(int i=arr.length-1; i>=0; i--) {
				if(arr[i][j] == 0)	break;
				else	remain++;
			}
		}
		
		return remain;
	}
	
	private static void cleanBrick(int[][] arr) {
		int now;
		for(int i=0; i<arr[0].length; i++) {
			now = arr.length - 1;
			for(int j=arr.length-1; j>=0; j--) {
				if (arr[j][i] != 0) {
					if (j != now) {
						arr[now][i] = arr[j][i];
						arr[j][i] = 0;
						now--;
					}else if(j == now)
						now--;
				}
			}
		}
	}
	
	private static void crashBrick(int idx, int[][] arr) { //idx번째 줄에 구슬이 떨어졌을 때 벽돌의 변화
		boolean check = false;
		Queue<int[]> q = new LinkedList<>(); // x, y, 벽돌값
		for(int h=0; h<arr.length; h++) {
			if(arr[h][idx] != 0) {
				check = true;
				q.add(new int[] {h, idx, arr[h][idx]});
				
				while(!q.isEmpty()) {
					int[] now = q.poll();
					int x = now[0];
					int y = now[1];
					int value = now[2];
					arr[x][y] = 0; // 우선 그 벽돌은 깨지고 (값 상관없이)
					
					if(value > 1) { // 벽돌값이 1보다 크면 -> 주변 벽돌도 깨져야 됨
						value -= 1; // (벽돌값 - 1)만큼
						
						for(int j=y-value; j<=y+value; j++) { //가로
							if(j>=0 && j<arr[0].length && arr[x][j] != 0) {
								q.add(new int[] {x, j, arr[x][j]});
							}
						}
						
						for(int i=x-value; i<=x+value; i++) { //세로
							if(i>=0 && i<arr.length && arr[i][y] != 0) {
								q.add(new int[] {i, y, arr[i][y]});
							}
						}
					}
				}
				break;
			}
		}
		if(check) // 깨진 벽돌이 있을때만 정리해주기
			cleanBrick(arr);
	}
}
