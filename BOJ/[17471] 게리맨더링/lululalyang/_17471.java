import java.io.*;
import java.util.*;
public class _17471 {
	static int Min = Integer.MAX_VALUE; // ���������
	static int[] P;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		P = new int[N+1]; // �� ������ �α���
		String[] s = br.readLine().split(" ");
		for(int j=1; j<N+1; j++)	P[j] = Integer.parseInt(s[j-1]);
		
		adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			adj[i] = new ArrayList<>();
			s = br.readLine().split(" ");
			int cnt = Integer.parseInt(s[0]);
			for(int j=1; j<cnt+1; j++) {
				adj[i].add(Integer.parseInt(s[j]));
			}
		}
		
		solution(N);
		if(Min == Integer.MAX_VALUE)	System.out.print(-1);
		else	System.out.print(Min);
	}
	
	private static void solution(int N) {
		ArrayList<Integer> tmp = new ArrayList<>();
		for(int i=1; i<=N/2; i++) {
			Comb(N, i, 1, tmp);
		}
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			ArrayList<Integer> tmp2 = new ArrayList<>();
			for(int i=1; i<N+1; i++)
				if(!tmp.contains(i))	tmp2.add(i);
			CheckAdj(N, tmp, tmp2);
			return;
		}
		for(int i=start; i<N+1; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static void CheckAdj(int N, ArrayList<Integer> tmp, ArrayList<Integer> tmp2) { // �� ���ű��� ������ �� ������ ����Ǿ��ִ���
		if(ChkOneRange(N, tmp) && ChkOneRange(N, tmp2)) { // ���� �Ѵ� ������ �Ǿ� �ִٸ�
			int A=0, B=0; 
			for(Integer i : tmp)	A += P[i]; // �� �α��� ���ؼ�
			for(Integer i : tmp2)	B += P[i];	
			
			Min = Math.min(Min, Math.abs(A-B)); // �� ���̷� Min ����
		}
	}
	
	private static boolean ChkOneRange(int N, ArrayList<Integer> Range) {
		int RangeCnt = Range.size();
		boolean[] visited = new boolean[RangeCnt];
		Queue<Integer> q = new LinkedList<>();
		q.add(Range.get(0));
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(Integer next : adj[now]) {
				if(Range.contains(next) && !visited[Range.indexOf(next)]) {
					q.add(next);
					visited[Range.indexOf(next)] = true;
				}
			}
		}
		
		for(int i=0; i<RangeCnt; i++)
			if(!visited[i])	return false; // �湮 ���� ������ �ִٸ� => ������� ���� ����
		
		return true;
	}
}
