import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _2383 {
	static ArrayList<ArrayList<Integer>> C = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int minTime = 2000000000;
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] stairs = new int[2][3]; // ����� x, y��ǥ, ����
			int sIndex = 0;
			ArrayList<int []> q = new ArrayList<>();
			for(int i=0; i<N; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(map[i][j] > 1) { //����̸�
						stairs[sIndex][0] = i;
						stairs[sIndex][1] = j;
						stairs[sIndex++][2] = map[i][j];
					}else if(map[i][j] == 1) { //����̸�
						q.add(new int[] {i, j}); //��ġ ť�� ����
					}
				}
			}
			
			for(int i=0; i<=q.size()/2; i++) {
				ArrayList<Integer> tmp = new ArrayList<>();
				comb(q.size(), i, 0, tmp);
				ArrayList<Integer> C2 = new ArrayList<Integer>();
				for(ArrayList<Integer> a : C) {
					for(int j=0; j<q.size(); j++) {
						if(!a.contains(j))
							C2.add(j);
					}
					
					minTime = Math.min(minTime, distToStairs(a, C2, stairs, q));
					minTime = Math.min(minTime, distToStairs(C2, a, stairs, q));
					C2.clear();
				}
				C.clear();
			}
			
			System.out.println("#"+t+" "+minTime);
			//result[t] = minTime;
		}
	}
	
	static int move(ArrayList<Integer> dist, ArrayList<Integer> s, int dN, int N) {
		for(int i=0; i<N; i++) {
			if(dist.get(i)!=-100) { //���� �����ϸ� (-100�� �ƴϸ�)
				if(dist.get(i) == -1) { //�Ÿ����� -1�̸� ������ �� �ִ�
					if(s.size() < 3) { //��ܿ� 3���� ���� ������ ��ܿ� ���� �� �ִ�
						s.add(dist.get(i));
						dist.set(i, -100);
						dN--;
					}
					continue;
				}
					
				dist.set(i, dist.get(i)-1); //��ܱ��� ��ĭ�� �̵� => �Ÿ� 1�� �ٿ���
			}
		}
		return dN;
	}
	
	static int remove(ArrayList<Integer> s, int stairL, int finish) {
		for(int i=0; i<s.size(); i++)	
			s.set(i, s.get(i)-1);
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.addAll(s);
		for(int i=0; i<s.size(); i++) {
			if(s.get(i) == stairL) {
				tmp.remove(tmp.indexOf(s.get(i)));
				finish++;
			}
		}
		s.clear();
		s.addAll(tmp);
		return finish;
	}
	
	static int calculation(ArrayList<Integer> dist1, ArrayList<Integer> dist2, int[][] stairs, int peopleN) {
		int finish = 0;
		int time = 0;
		ArrayList<Integer> s1 = new ArrayList<>(); // ��� A
		ArrayList<Integer> s2 = new ArrayList<>(); // ��� B
		int sl1 = (-1) * stairs[0][2] -1; // A ��� ���� * (-1) -1 // ��� ���� �� 1���Ŀ� ������ �� �����Ƿ�
		int sl2 = (-1) * stairs[1][2] -1; // B ��� ���� * (-1) -1 // ��� ���� �� 1���Ŀ� ������ �� �����Ƿ�
		int reald1N = dist1.size();
		int reald2N = dist2.size();
		
		int d1N = reald1N;
		int d2N = reald2N;
		while(finish != peopleN) {
			time++;
			
			if(d1N != 0)
				d1N = move(dist1, s1, d1N, reald1N); //��ܱ��� �Ÿ� �ٿ��ְ�, ��ܿ� 3���� ������ ��ܿ� �ø���
			if(d2N != 0)
				d2N = move(dist2, s2, d2N, reald2N);
			
			if(s1.size() != 0)
				finish = remove(s1, sl1, finish); // ��ܿ� �ִ� �ֵ� ��ĭ�� �����ְ�
			if(s2.size() != 0)
				finish = remove(s2, sl2, finish); // ���� �� ����� �� �����Դٸ� ��ܿ��� �����ش�
		}
		
		return time;
	}
	
	static int distToStairs(ArrayList<Integer> C1, ArrayList<Integer> C2, int[][] stairs, ArrayList<int []> q) { //q�� ��� x,y��ǥ
		ArrayList<Integer> dist1 = new ArrayList<Integer>(); // C1 ~ A ��� �Ÿ�
		ArrayList<Integer> dist2 = new ArrayList<Integer>(); // C2 ~ B ��� �Ÿ�
			
		for(Integer p1 : C1) {
			int[] pLoc = q.get(p1);
 			dist1.add(Math.abs((pLoc[0]-stairs[0][0])) + Math.abs((pLoc[1]-stairs[0][1]))); //�����ġ��  A ��� ���� �Ÿ�		
		}
		
		for(Integer p2 : C2) {
			int[] pLoc = q.get(p2);
			dist2.add(Math.abs((pLoc[0]-stairs[1][0])) + Math.abs((pLoc[1]-stairs[1][1]))); //�����ġ��  B ��� ���� �Ÿ�		
		}
		
		return calculation(dist1, dist2, stairs, q.size());
	}
	
	static void comb(int N, int r, int start, ArrayList<Integer> tmp) { //�������� ������ ������� ����Ʈ��
		if(r == 0) {
			C.add((ArrayList<Integer>) tmp.clone());
			return;
		}
		for(int i=start; i<N; i++) {
			tmp.add(i);
			comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
}
