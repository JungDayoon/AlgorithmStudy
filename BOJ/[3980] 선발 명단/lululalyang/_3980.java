import java.io.*;
public class _3980 {
	static int[][] ability;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			if(sb.length() != 0)	sb.append("\n");
			ability = new int[11][11];
			max = 0;
			String[] s;
			for(int i=0; i<11; i++) {
				s = br.readLine().split(" ");
				for(int j=0; j<11; j++) {
					ability[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			boolean[] player = new boolean[11];
			solution(0, player, 0);
			sb.append(max);
		}
		System.out.print(sb.toString());
	}

	private static void solution(int pos, boolean[] player, int sum) {
		if(pos == 11) {
			max = Math.max(max, sum);
			return;
		}
		for(int i=0; i<11; i++) {
			int ab = ability[i][pos];
			if(!player[i] && ab!=0) {
				player[i] = true;
				solution(pos+1, player, sum+ab);
				player[i] = false;
			}
		}
	}
}
