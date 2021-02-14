import java.io.*;
import java.util.*;

public class _1497 {
	static int MinGuitar = Integer.MAX_VALUE;
	static int MaxMusic = 0;
	static String[][] guitar;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]); // ��Ÿ ����
		M = Integer.parseInt(s[1]); // �� ����
		
		guitar = new String[N][M];
		for(int n=0; n<N; n++) {
			String[] d = br.readLine().split(" ");
			guitar[n] = d[1].split("");
		}
		
		for(int r=0; r<=N; r++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			Comb(N, r, 0, tmp);
		}
		
		if(MaxMusic == 0)
			System.out.print("-1");
		else
			System.out.print(MinGuitar);
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			CheckCanPlay(tmp);
			return;
		}
		
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static void CheckCanPlay(ArrayList<Integer> tmp) {
		boolean[] playedMusic = new boolean[M]; // ���ֵ� ��
		int musicCnt = 0; // ������ �� �ִ� ���� ����
		int guitarCnt = tmp.size(); // ����ϴ� ��Ÿ ����
		
		Iterator<Integer> itr = tmp.iterator();
		while(itr.hasNext()) {
			int now = itr.next();
			for(int i=0; i<M; i++) {
				if(guitar[now][i].equals("Y") && !playedMusic[i]) { 
					playedMusic[i] = true;
					musicCnt++;
				}
			}
		}
		
		
		if(MaxMusic < musicCnt) { // �� ���� ���� ������ �� �ִٸ�
			MaxMusic = musicCnt; 
			MinGuitar = guitarCnt; 
		}else if(MaxMusic == musicCnt) { // ���ְ����� �� ������ ���ٸ�
			MinGuitar = Math.min(MinGuitar, guitarCnt);
		}
	}
}
