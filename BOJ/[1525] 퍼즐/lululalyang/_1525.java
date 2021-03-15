import java.io.*;
import java.util.*;

public class _1525 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = new int[9];
		int idx = 0;
		int zx = 0, zy = 0;
		for(int i=0; i<3; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				input[idx+j] = Integer.parseInt(s[j]);
				if(input[idx+j] == 0) {
					zx = i;
					zy = j;
				}
			}
			idx += 3;
		}
		
		System.out.print(solution(input, zx, zy));
	}
	
	private static StringBuilder turnTostr(int[] list) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++)
			sb.append(list[i]);
		
		return sb;
	}
	
	private static boolean ChkDone(StringBuilder list) {
		for(int i=1; i<9; i++) {
			if((list.charAt(i-1)-'0') != i)
				return false;
		}
		return true;
	}
	
	private static int solution(int[] input, int zx, int zy) {
		int res = -1;
		Queue<Data> q = new LinkedList<>();
		Map<String, Boolean> visited = new HashMap<>();
		q.add(new Data(turnTostr(input), zx, zy, 0));
		visited.put(turnTostr(input).toString(), true);
		
		while(!q.isEmpty()) {
			Data now = q.poll();
			StringBuilder list = now.list;
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int zero = 3*x + y;
			
			if(ChkDone(list)) { // 완성시켰다면
				res = cnt;
				break;
			}
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				if(!Valid(rx, ry))	continue; // 범위 내에 있을 때만
				int rloc = 3*rx + ry;
				char num = list.charAt(rloc);
				String nStr = Character.toString(num);
				
				StringBuilder tmp = new StringBuilder(list.substring(0));
				tmp.replace(rloc, rloc+1, "0");
				tmp.replace(zero, zero+1, nStr);
				
				if(!visited.containsKey(tmp.toString())) {
					visited.put(tmp.toString(), true);
					q.add(new Data(tmp, rx, ry, cnt+1));
				}
			}
		}
		
		return res;
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<3 && y>=0 && y<3);
	}
	
	private static class Data{
		StringBuilder list;
		int x;
		int y; 
		int cnt;
		
		Data(StringBuilder list, int x, int y, int cnt){
			this.list = list;
			this.x = x; // 0의 위치
			this.y = y;
			this.cnt = cnt;
		}
	}
}
