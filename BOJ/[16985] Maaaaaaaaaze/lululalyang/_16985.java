import java.io.*;
import java.util.*;

public class _16985 {
	static int CNT = Integer.MAX_VALUE;
	static ArrayList<int[][]> AllPlate = new ArrayList<>(5);
	
	static int[] dx = {-1, 0, 0, 0, 0, 1};
	static int[] dy = {0, -1, 0, 1, 0, 0};
	static int[] dz = {0, 0, 1, 0, -1, 0};
	
	static int[][][] EE = {{{0, 0, 0}, {0, 0, 4}, {0, 4, 0}, {0, 4, 4}},
			   {{4, 4, 4}, {4, 4, 0}, {4, 0, 4}, {4, 0, 0}}}; // Entrance and Exit
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s; 
		for(int i=0; i<5; i++) {
			int[][] newPlate = new int[5][5];
			for(int j=0; j<5; j++) {
				s = br.readLine().split(" ");
				for(int k=0; k<5; k++) {
					newPlate[j][k] = Integer.parseInt(s[k]);
				}
			}
			AllPlate.add(newPlate);
		}
		ArrayList<int[][]> tmp = new ArrayList<>(5);
		AdjustPlate(0, tmp);
		if(CNT != Integer.MAX_VALUE)
			System.out.print(CNT);
		else
			System.out.print("-1");
	}
	
	private static int[][] RotatePlate(int[][] nowPlate, int d){
		int[][] newPlate = new int[5][5];
		if(d == 0) { // 0이면 그대로
			for(int i=0; i<5; i++)
				newPlate[i] = nowPlate[i].clone();
		}
		else if(d == 1) {
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					newPlate[i][j] = nowPlate[4-j][i];
				}
			}
		}else if(d == 2) {
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					newPlate[i][j] = nowPlate[4-i][4-j];
				}
			}
		}else { // (d == 3)
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					newPlate[i][j] = nowPlate[j][4-i];
				}
			}
		}
		
		return newPlate;
	}
	
	private static void AdjustPlate(int nowIdx, ArrayList<int[][]> tmp) {
		if(nowIdx == 5) {
			int[] order = new int[5];
			boolean[] visited = new boolean[5];
			SetPlateOrder(tmp, order, visited, 0);
			return;
		}
		
		for(int d=0; d<4; d++) {
			int[][] newPlate = RotatePlate(AllPlate.get(nowIdx), d); 
			tmp.add(newPlate);
			AdjustPlate(nowIdx+1, tmp);
			tmp.remove(nowIdx);
		}
	}
	
	private static void tmpTomapByorder(int[][][] map, ArrayList<int[][]> tmp, int[] order) {
		for(int i=0; i<5; i++) {
			int[][] now = tmp.get(order[i]);
			for(int j=0; j<5; j++) {
				map[i][j] = now[j].clone();
			}
		}
	}
	
	private static int Maze(int[][][] map, Queue<Loc> q, boolean[][][] visited, int destX, int destY, int destZ) {
		int res = -1;
		while(!q.isEmpty()) {
			Loc now = q.poll();
			int x = now.x;
			int y = now.y;
			int z = now.z;
			int cnt = now.cnt;
			
			if(x==destX && y==destY && z==destZ) { // 출구에 도착하면
				res = cnt;
				break;
			}
			
			for(int k=0; k<6; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				int rz = z + dz[k];
				
				if(Valid(rx, ry, rz) && map[rx][ry][rz]==1 && !visited[rx][ry][rz]) {
					q.add(new Loc(rx, ry, rz, cnt+1));
					visited[rx][ry][rz] = true;
				}
			}
		}
		return res;
	}
	
	private static boolean Valid(int x, int y, int z) {
		return (x>=0 && x<5 && y>=0 && y<5 && z>=0 && z<5);
	}
	
	private static void ComputeMoveCnt(int[][][] map) { // 위 꼭짓점 네 개의 입구에서 아래 꼭짓점 네개의 출구로 이동횟수 확인 
		// 입구 (0, 0, 0), (0, 0, 4), (0, 4, 0), (0, 4, 4)
		// 출구 (4, 4, 4), (4, 4, 0), (4, 0, 4), (4, 0, 0)
		int thisRes = Integer.MAX_VALUE;
		Queue<Loc> q = new LinkedList<>();
		boolean[][][] visited;
		int x, y, z, res;
		
		for(int i=0; i<4; i++) {
			visited = new boolean[5][5][5];
			x = EE[0][i][0];
			y = EE[0][i][1];
			z = EE[0][i][2];
			if(map[x][y][z] == 0) // 입구가 들어갈 수 없는 곳이라면 패쓰
				continue;
			
			q.add(new Loc(x, y, z, 0)); // 입구가 들어갈 수 있는 곳이라면 확인
			visited[x][y][z] = true;
			res = Maze(map, q, visited, EE[1][i][0], EE[1][i][1], EE[1][i][2]); 
			if(res != -1)	thisRes = Math.min(thisRes, res);
			q.clear();
		}
		
		if(thisRes != Integer.MAX_VALUE) {
			CNT = Math.min(CNT, thisRes);
		}
	}
	
	private static void SetPlateOrder(ArrayList<int[][]> tmp, int[] order, boolean[] visited, int depth) { // 정한 판 다섯개의 순서를 정한다 => 순열로
		if(depth == 5) {
			int[][][] map = new int[5][5][5];
			tmpTomapByorder(map, tmp, order);
			ComputeMoveCnt(map);
			return;
		}
		
		for(int i=0; i<5; i++) {
			if(visited[i] != true) {
				visited[i] = true;
				order[depth] = i;
				SetPlateOrder(tmp, order, visited, depth+1);
				visited[i] = false;
			}
		}
	}
	
//	private static void Perm(int[] output, boolean[] visited, int depth, int n, int r) { // nPr 순열!
//		if(depth == r) {
//			for (int i = 0; i < r; i++)
//	            System.out.print(output[i] + " ");
//	        System.out.println();
//	        return;
//		}
//		
//		for(int i=0; i<n; i++) {
//			if(visited[i] != true) {
//				visited[i] = true;
//				output[depth] = i;
//				Perm(output, visited, depth+1, n, r);
//				visited[i] = false; // 다시 돌려줌
//			}
//		}
//	}
	
	private static class Loc{
		private int x;
		private int y;
		private int z;
		private int cnt;
		
		Loc(int x, int y, int z, int cnt){
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}
	}
}
