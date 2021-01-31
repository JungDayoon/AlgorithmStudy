import java.io.*;
import java.util.*;

public class _17825 {
	static ArrayList[] map = new ArrayList[5];
	static int MaxScore = 0;
	static int[] dice = new int[10];
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		for(int i=0; i<dice.length; i++) { // 주사위 초기화
			dice[i] = Integer.parseInt(s[i]);
		}

		Horse[] h = new Horse[4]; // 말 4개 위치 초기화
		for(int i=0; i<4; i++) {
			h[i] = new Horse(0, 0);
		}
		
		initializeMap(map);
		CalculScore(h, 0, 0);
		
		System.out.print(MaxScore);
	}
	
	private static void CalculScore(Horse[] h, int score, int turn) throws CloneNotSupportedException {
		if(turn == 10) {
			MaxScore = Math.max(MaxScore, score);
			return;
		}
		
		Horse[] tmph = new Horse[4];
		for(int i=0; i<4; i++) {
			if(h[i].y == -1) // 도착한 말은 선택 X
				continue;
			
			for(int j=0; j<4; j++)
				tmph[j] = (Horse)h[j].clone();
			
			if(moveHorse(tmph, i, dice[turn])) { //이동가능하면
				int plusScore = 0;
				if(tmph[i].y != -1) {
					plusScore = ((Integer) map[tmph[i].x].get(tmph[i].y)).intValue();
					score += plusScore;
				}
				CalculScore(tmph, score, turn+1);
				score -= plusScore;
			}
		}
	}
	
	private static boolean moveHorse(Horse[] h, int now, int diceNum) {
		int[] chk = new int[2];
		chk[0] = h[now].x;
		chk[1] = h[now].y + diceNum;
		
		switch(chk[0]) {
		case 0:
			if(chk[1] >= 21) chk[1] = -1; // 도착
			else {
				if(chk[1] == 5) {
					chk[0] = 1;
					chk[1] = 0;
				}else if(chk[1] == 10) {
					chk[0] = 2;
					chk[1] = 0;
				}else if(chk[1] == 15) {
					chk[0] = 3;
					chk[1] = 0;
				}
			}
 			break;
		case 1:
		case 2:
		case 3:
			if(chk[1] == map[chk[0]].size()) {
				chk[0] = 4;
				chk[1] = 0;
			}else if(chk[1] > map[chk[0]].size()) {
				chk[1] = chk[1] - map[chk[0]].size();
				chk[0] = 4;
				
				if(chk[1] == 3) {
					chk[0] = 0;
					chk[1] = 20;
				}else if(chk[1] > 3)
					chk[1] = -1;
			}
			break;
		case 4:
			if(chk[1] == 3) {
				chk[0] = 0;
				chk[1] = 20;
			}else if(chk[1] > 3)	chk[1] = -1; // 도착
			break;
		}
		
		if(chk[1] != -1) {
			if(!checkDup(h, now, chk))	return false;
		}
		
		h[now].x = chk[0];
		h[now].y = chk[1];
		return true;
	}
	
	private static boolean checkDup(Horse[] h, int now, int[] chk) {
		for(int i=0; i<4; i++) {
			if(i!=now) {				
				if(h[i].x==chk[0] && h[i].y==chk[1])
					return false;
			}
		}
		return true;
	}
	
	private static void initializeMap(ArrayList[] map) {
		map[0] = new ArrayList<Integer>();
		for(int i=0; i<=40; i+=2)
			map[0].add(i);
		
		map[1] = new ArrayList<Integer>(Arrays.asList(10, 13, 16, 19));
		map[2] = new ArrayList<Integer>(Arrays.asList(20, 22, 24));
		map[3] = new ArrayList<Integer>(Arrays.asList(30, 28, 27, 26));
		map[4] = new ArrayList<Integer>(Arrays.asList(25, 30, 35));
		
	}
	
	private static class Horse implements Cloneable{
		int x;
		int y;
		
		Horse(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
}
