import java.io.*;
import java.util.*;

// ����� �ٸ�. Ȯ���غ���
public class _20208 {
	static int MaxMilkCnt = 0;
	static int[][] map;
	static ArrayList<int[]> milkLoc = new ArrayList<>();
	static int[] house = new int[2]; // ���� ��
	static int N; // ���ʸ��� ũ�� N
	static int H; // ���� ���� ������ �����ϴ� ü�� H
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]); 
		int M = Integer.parseInt(s[1]); // ���� �ʱ�ü�� M
		H = Integer.parseInt(s[2]); 
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] d = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(d[j]); // �������� 1, ���ʴ� 2, �� ���� 0
				if(map[i][j] == 1) { // ���� ��
					house[0] = i;
					house[1] = j;
				}else if(map[i][j] == 2) { // ���ʿ��� ��ġ
					milkLoc.add(new int[] {i, j});
				}
			}
		}
		
		boolean[] visited = new boolean[milkLoc.size()];
		ComputeMaxMilk(house[0], house[1], M, visited, 0);
		System.out.print(MaxMilkCnt);
	}
	
	private static int ComputeDist(int x1, int y1, int x2, int y2) { // (x1, y1)�� (x2, y2)������ �Ÿ�
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	private static void ComputeMaxMilk(int x, int y, int h, boolean[] visited, int milkCnt) {
		if(milkCnt>MaxMilkCnt && ComputeDist(x, y, house[0], house[1])<=h) {
			MaxMilkCnt = milkCnt;
		}
		
		
		for(int i=0; i<visited.length; i++) {
			if(!visited[i]) { // �湮���� ���� �����ε�
				int dist = ComputeDist(x, y, milkLoc.get(i)[0], milkLoc.get(i)[1]); //���� ��ġ���� �湮���� ���� ���ʱ����� �Ÿ�
				if(h >= dist) { // ���� ü������ �� ���ʱ��� �� �� ���� ��
					visited[i] = true;
					ComputeMaxMilk(milkLoc.get(i)[0], milkLoc.get(i)[1], h-dist+H, visited, milkCnt+1);
					visited[i] = false;
				}
			}
		}
	} 

}
