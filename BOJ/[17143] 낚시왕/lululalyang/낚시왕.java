import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _17143 {
	static int sharksizeSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		int R = Integer.parseInt(s[0]); //�������� ũ�� ����
		int C = Integer.parseInt(s[1]); //�������� ũ�� ����
		int M = Integer.parseInt(s[2]); //����� ��
		Shark[] shark = new Shark[M];
		boolean[] caught = new boolean[M]; //�������� ture, ���������� false
		
		if(M == 0) {
			System.out.println("0");
			System.exit(0);
		}
		
		for(int i=0; i<M; i++) {
			String[] d = br.readLine().split(" ");
			shark[i] = new Shark(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), Integer.parseInt(d[3]), Integer.parseInt(d[4]));
		}
		
		Arrays.sort(shark);
		
		for(int k=1; k<=C; k++) { //k�� ���ÿ��� ��ġ
			catchShark(k, shark, caught, R); // ���ÿ��� �ִ� ���� �ִ� ��� �� ���� ���� ����� ��� ��´�
			moveShark(shark, caught, R, C); // ��� �̵� ��Ų��
			checkSharkLoc(shark, caught, R, C); // ��� �̵� ��ģ �� �� ĭ�� �� ���� �̻� ���� ��� ũ�Ⱑ ���� ū �� ������ ��� ��� ��ƸԴ´�

		}
		System.out.println(sharksizeSum);
	}
	
	
	
	static void initial(int[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = -1;
			}
		}
	}
	static void checkSharkLoc(Shark[] shark, boolean[] caught, int R, int C) { //�����ġ ���� map �����
		int[][] map = new int[R+1][C+1];
		initial(map); //�� -1��
		
		for(int i=0; i<shark.length; i++) {
			if(!caught[i]){
				if(map[shark[i].r][shark[i].c] == -1) { // �ƹ� ��� ������
					map[shark[i].r][shark[i].c] = i; //����� index
				}else { //�ű⿡ ��� ������
					caught[map[shark[i].r][shark[i].c]] = true; //���� �ִ� ��� ���ְ� (������������ ���ĵǾ������ϱ�)
					map[shark[i].r][shark[i].c] = i; //�׸��� ���ڸ��� ���� ��� �ִ´�
				}
			}
		}
	}
	
	static void moveShark(Shark[] shark, boolean[] caught, int R, int C) {
		int divideN1 = 2 * (R-1);
		int divideN2 = 2 * (C-1);
		int speed = 0;
		for(int i=0; i<shark.length; i++) {
			if(!caught[i]) {
				switch(shark[i].d) {
				case 1: 
				case 2:
					speed = shark[i].s % divideN1;
					if(shark[i].d == 2) {
						if((shark[i].r+=speed) > R) {
							shark[i].r = 2*R - shark[i].r;
							if(shark[i].r < 1) {
								shark[i].r = Math.abs(shark[i].r) + 2;
							}else {
								shark[i].d--;
							}
						}
					}else { //shark[i][3] == 1
						if((shark[i].r-=speed) < 1) {
							shark[i].r = Math.abs(shark[i].r)+2; //row�ٲ��ְ�
							if(shark[i].r > R) {
								shark[i].r = 2*R - shark[i].r; // R - (shark[i][1] - R);
							}else {
								shark[i].d++;
							}
						}
					}
					break;
				case 3:
				case 4:
					speed = shark[i].s % divideN2;
					if(shark[i].d == 3) {
						if((shark[i].c+=speed) > C) {
							shark[i].c = 2*C - shark[i].c;
							if(shark[i].c < 1) {
								shark[i].c = Math.abs(shark[i].c) + 2;
							}else {
								shark[i].d++;
							}
						}
					}else { //shark[i][3] == 4
						if((shark[i].c-=speed) < 1) {
							shark[i].c = Math.abs(shark[i].c)+2; //col�ٲ��ְ�
							if(shark[i].c > C) {
								shark[i].c = 2*C - shark[i].c; // C - (shark[i][1] - C);
							}else {
								shark[i].d--;
							}
						}
					}
					break;
				}
			}
		}
	}
	
	static void catchShark(int king, Shark[] shark, boolean[] caught, int R) {
		int min = R+1;
		int sharkidx = -1;
		for(int i=0; i<shark.length; i++) {
			if(shark[i].c == king && !caught[i]) { //������ �� ���ÿ��̶� ���� ���̸�
				if(min > shark[i].r) {
					min = shark[i].r;
					sharkidx = i;
				}
			}
		}
		if(sharkidx != -1) {
			sharksizeSum += shark[sharkidx].z;
			caught[sharkidx] = true; //���� ���
		}
	}

}

class Shark implements Comparable<Shark>{
	int r; // ����idx
	int c; // ����idx
	int s; // �ӵ�
	int d; // ����
	int z; // ũ��
	
	Shark(int r, int c, int s, int d, int z){
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}

	@Override
	public int compareTo(Shark s) {
		if(this.z < s.z) {
			return -1;
		}else if(this.z == s.z) {
			return 0;
		}else {
			return 1;
		}
	}
}