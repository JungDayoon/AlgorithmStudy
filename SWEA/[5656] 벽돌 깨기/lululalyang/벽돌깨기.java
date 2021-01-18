import java.io.*;
import java.util.*;

public class _5656 {
	static int Mincnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //�׽�Ʈ ���̽� ����
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			Mincnt = Integer.MAX_VALUE;
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); //���� ����
			int W = Integer.parseInt(s[1]); //����
			int H = Integer.parseInt(s[2]); //����
			int[][] brick = new int[H][W]; //�־��� ���� ����
			
			for(int i=0; i<H; i++) {
				String[] d = br.readLine().split(" ");
				for(int j=0; j<W; j++) {
					brick[i][j] = Integer.parseInt(d[j]);
				}
			}
			
			recursion(N, brick);
			
			sb.append("#"+t+" "+Mincnt+"\n");
		}//���̽����̽� ��
		
		System.out.print(sb.toString());
	}
	
	private static void recursion(int marble, int[][] arr) { // ���� ����, ���� arr���� 
		if(marble == 0) {
			Mincnt = Math.min(Mincnt, remainBrick(arr));
			return;
		}
		
		int[][] tmpArr = new int[arr.length][arr[0].length];
		for(int j=0; j<arr[0].length; j++) { // ���پ� ���� ����߸���
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
	
	private static void crashBrick(int idx, int[][] arr) { //idx��° �ٿ� ������ �������� �� ������ ��ȭ
		boolean check = false;
		Queue<int[]> q = new LinkedList<>(); // x, y, ������
		for(int h=0; h<arr.length; h++) {
			if(arr[h][idx] != 0) {
				check = true;
				q.add(new int[] {h, idx, arr[h][idx]});
				
				while(!q.isEmpty()) {
					int[] now = q.poll();
					int x = now[0];
					int y = now[1];
					int value = now[2];
					arr[x][y] = 0; // �켱 �� ������ ������ (�� �������)
					
					if(value > 1) { // �������� 1���� ũ�� -> �ֺ� ������ ������ ��
						value -= 1; // (������ - 1)��ŭ
						
						for(int j=y-value; j<=y+value; j++) { //����
							if(j>=0 && j<arr[0].length && arr[x][j] != 0) {
								q.add(new int[] {x, j, arr[x][j]});
							}
						}
						
						for(int i=x-value; i<=x+value; i++) { //����
							if(i>=0 && i<arr.length && arr[i][y] != 0) {
								q.add(new int[] {i, y, arr[i][y]});
							}
						}
					}
				}
				break;
			}
		}
		if(check) // ���� ������ �������� �������ֱ�
			cleanBrick(arr);
	}
}
