import java.io.*;
import java.util.*;

public class _1941 {
	static int NumofCases = 0;
	static String[][] map = new String[5][5];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().split("");
		}
		
		ArrayList<Integer> tmp = new ArrayList<>();
		Comb(25, 7, 0, tmp);
//		ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 9, 11, 16));
//		chkPersons(tmp);
		System.out.print(NumofCases);
	}
	
	private static void chkPersons(ArrayList<Integer> SevenPrincess) { // 각 파 명수 세기
		int Ycnt = 0; //임도연파 명 수
		int Scnt = 0; //이다솜파 명 수
		Iterator<Integer> chk = SevenPrincess.iterator();
		while(chk.hasNext()) {
			int num = chk.next();
			if(map[num/5][num%5].equals("Y"))	Ycnt++;
			else	Scnt++;
				
			if(Ycnt >= 4)
				return;
		}
		
		chkAdjacent(SevenPrincess);
	}
	
	private static void chkAdjacent(ArrayList<Integer> sp) {
		boolean[] visited = new boolean[7];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sp.get(0)/5, sp.get(0)%5});
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0];
			int y = tmp[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(rx>=0 && rx<5 && ry>=0 && ry<5) {
					if(!sp.contains(rx*5 + ry)) {
						continue;
					}else if(!visited[sp.indexOf(rx*5 + ry)]){
						q.add(new int[] {rx, ry});
						visited[sp.indexOf(rx*5 + ry)] = true;
					}
				}
			}
		}
		
		for(int i=0; i<7; i++) {
			if(!visited[i])	return;
		}
		
//		System.out.println(sp);
		NumofCases++;
		return;
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) { //7명 뽑기
		if(r == 0) {
			chkPersons((ArrayList<Integer>) tmp.clone());
			return;
		}
				
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
}
