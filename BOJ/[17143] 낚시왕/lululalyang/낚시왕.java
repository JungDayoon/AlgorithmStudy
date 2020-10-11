import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _17143 {
	static int sharksizeSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		int R = Integer.parseInt(s[0]); //격자판의 크기 세로
		int C = Integer.parseInt(s[1]); //격자판의 크기 가로
		int M = Integer.parseInt(s[2]); //상어의 수
		Shark[] shark = new Shark[M];
		boolean[] caught = new boolean[M]; //잡혔으면 ture, 안잡혔으면 false
		
		if(M == 0) {
			System.out.println("0");
			System.exit(0);
		}
		
		for(int i=0; i<M; i++) {
			String[] d = br.readLine().split(" ");
			shark[i] = new Shark(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), Integer.parseInt(d[3]), Integer.parseInt(d[4]));
		}
		
		Arrays.sort(shark);
		
		for(int k=1; k<=C; k++) { //k는 낚시왕의 위치
			catchShark(k, shark, caught, R); // 낚시왕이 있는 열에 있는 상어 중 땅과 제일 가까운 상어 잡는다
			moveShark(shark, caught, R, C); // 상어 이동 시킨다
			checkSharkLoc(shark, caught, R, C); // 상어 이동 마친 후 한 칸에 두 마리 이상 있을 경우 크기가 가장 큰 상어가 나머지 상어 모두 잡아먹는다

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
	static void checkSharkLoc(Shark[] shark, boolean[] caught, int R, int C) { //상어위치 담은 map 만들기
		int[][] map = new int[R+1][C+1];
		initial(map); //다 -1로
		
		for(int i=0; i<shark.length; i++) {
			if(!caught[i]){
				if(map[shark[i].r][shark[i].c] == -1) { // 아무 상어 없으면
					map[shark[i].r][shark[i].c] = i; //상어의 index
				}else { //거기에 상어 있으면
					caught[map[shark[i].r][shark[i].c]] = true; //원래 있던 상어 없애고 (오름차순으로 정렬되어있으니까)
					map[shark[i].r][shark[i].c] = i; //그리고 그자리에 현재 상어 넣는다
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
							shark[i].r = Math.abs(shark[i].r)+2; //row바꿔주고
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
							shark[i].c = Math.abs(shark[i].c)+2; //col바꿔주고
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
			if(shark[i].c == king && !caught[i]) { //안잡힌 상어가 낚시왕이랑 같은 열이면
				if(min > shark[i].r) {
					min = shark[i].r;
					sharkidx = i;
				}
			}
		}
		if(sharkidx != -1) {
			sharksizeSum += shark[sharkidx].z;
			caught[sharkidx] = true; //잡힌 상어
		}
	}

}

class Shark implements Comparable<Shark>{
	int r; // 세로idx
	int c; // 가로idx
	int s; // 속도
	int d; // 방향
	int z; // 크기
	
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