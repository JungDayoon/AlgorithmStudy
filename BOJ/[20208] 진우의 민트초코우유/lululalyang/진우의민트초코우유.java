import java.io.*;
import java.util.*;

// 결과값 다름. 확인해보기
public class _20208 {
	static int MaxMilkCnt = 0;
	static int[][] map;
	static ArrayList<int[]> milkLoc = new ArrayList<>();
	static int[] house = new int[2]; // 진우 집
	static int N; // 민초마을 크기 N
	static int H; // 민초 마실 때마다 증가하는 체력 H
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]); 
		int M = Integer.parseInt(s[1]); // 진우 초기체력 M
		H = Integer.parseInt(s[2]); 
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] d = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(d[j]); // 진우집은 1, 민초는 2, 빈 땅은 0
				if(map[i][j] == 1) { // 진우 집
					house[0] = i;
					house[1] = j;
				}else if(map[i][j] == 2) { // 민초우유 위치
					milkLoc.add(new int[] {i, j});
				}
			}
		}
		
		boolean[] visited = new boolean[milkLoc.size()];
		ComputeMaxMilk(house[0], house[1], M, visited, 0);
		System.out.print(MaxMilkCnt);
	}
	
	private static int ComputeDist(int x1, int y1, int x2, int y2) { // (x1, y1)와 (x2, y2)사이의 거리
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	private static void ComputeMaxMilk(int x, int y, int h, boolean[] visited, int milkCnt) {
		if(milkCnt>MaxMilkCnt && ComputeDist(x, y, house[0], house[1])<=h) {
			MaxMilkCnt = milkCnt;
		}
		
		
		for(int i=0; i<visited.length; i++) {
			if(!visited[i]) { // 방문하지 않은 민초인데
				int dist = ComputeDist(x, y, milkLoc.get(i)[0], milkLoc.get(i)[1]); //현재 위치에서 방문하지 않은 민초까지의 거리
				if(h >= dist) { // 남은 체력으로 이 민초까지 갈 수 있을 때
					visited[i] = true;
					ComputeMaxMilk(milkLoc.get(i)[0], milkLoc.get(i)[1], h-dist+H, visited, milkCnt+1);
					visited[i] = false;
				}
			}
		}
	} 

}
