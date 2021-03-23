import java.io.*;
import java.util.*;

public class _72415 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<ArrayList<Integer>> perm = new ArrayList<>(); // 순열 집합
	static Card[] cardInfo;
	static ArrayList<Integer> cardList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[4][4];
		for(int i=0; i<4; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<4; j++) {
				board[i][j] = Integer.parseInt(s[j]);
			}
		}
		int r = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		System.out.print(solution(board, r, c));
	}
	
	private static ArrayList<Integer> InitializeCard(int[][] board, Card[] card) {
		ArrayList<Integer> cardInfo = new ArrayList<>();
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				int nowCard = board[i][j];
				if(nowCard != 0) {
					if(card[nowCard] == null) {
						cardInfo.add(nowCard);
						card[nowCard] = new Card();
						card[nowCard].loc[0][0] = i;
						card[nowCard].loc[0][1] = j;
					}else {
						card[nowCard].loc[1][0] = i;
						card[nowCard].loc[1][1] = j;
					}
				}
			}
		}
		
		return cardInfo;
	}
	
	private static void Perm(int N, int r, int now, ArrayList<Integer> tmp, boolean[] visited) { // 순열
		if(now == N) {
			perm.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				tmp.add(i);
				visited[i] = true;
				Perm(N, r, now+1, tmp, visited);
				tmp.remove(tmp.indexOf(i));
				visited[i] = false;
			}
		}
	}
	
	private static int findCard(int r, int c, int findr, int findc, int[][] board) { // (r,c): 현위치 // (findr, findc): 찾아야하는 위치
		Queue<Move> q = new LinkedList<>();
		boolean[][] visited = new boolean[4][4];
		visited[r][c] = true;
		q.add(new Move(r, c, 0)); // 현재 위치 좌표, 조작 횟수
		
		int move = 0;
		
		while(!q.isEmpty()) {
			Move now = q.poll();
			int nr = now.r;
			int nc = now.c;
			int nmove = now.cnt;
			
			if(nr==findr && nc==findc) { // 카드 찾으면
				move = nmove;
				break;
			}
			
			for(int k=0; k<4; k++) { // 상하좌우 방향키 한 번
				int rx = nr + dx[k];
				int ry = nc + dy[k];
				
				if(rx>=0 && rx<4 && ry>=0 && ry<4 && !visited[rx][ry]) {
					q.add(new Move(rx, ry, nmove+1));
					visited[rx][ry] = true;
				}
				
				rx = nr;
				ry = nc;
				while(true) { // ctrl + 방향키
					int nx = rx+dx[k];
					int ny = ry+dy[k];
					
					if(nx>=0 && nx<4 && ny>=0 && ny<4) {
						rx = nx;
						ry = ny;
						if(board[nx][ny] != 0) 	break;
					}else {
						break;
					}
				} 
				
				if(!visited[rx][ry]) {
					q.add(new Move(rx, ry, nmove+1));
					visited[rx][ry] = true;
				}
			}
						
		}
		
		return move;
	}
	
	private static int SameCardDiffOrder(int flag, int findNum, int r, int c, int[][] board) {
		int move = 0;
		
		int findr = cardInfo[findNum].loc[flag][0];
		int findc = cardInfo[findNum].loc[flag][1];
		
		move = findCard(r, c, findr, findc, board);
		r = findr;
		c = findc;
		
		if(flag == 0) {
			findr = cardInfo[findNum].loc[1][0];
			findc = cardInfo[findNum].loc[1][1];
		}else { // flag == 1
			findr = cardInfo[findNum].loc[0][0];
			findc = cardInfo[findNum].loc[0][1];
		}
		
		move += findCard(r, c, findr, findc, board);
		
		return move;
	}
	
	static int MinCnt = Integer.MAX_VALUE;
	private static void ComputeMoveCnt(ArrayList<Integer> order, int now, int N, int moveCnt, int r, int c, int[][] board) {
		if(now == N) { // 모든 카드를 다 뒤집었으면
			MinCnt = Math.min(MinCnt, moveCnt);
			return;
		}
		
		int findNum = cardList.get(order.get(now)); // 뒤집으려는 카드 번호
		int[] find1 = cardInfo[findNum].loc[0];
		int[] find2 = cardInfo[findNum].loc[1];
		
		int move = SameCardDiffOrder(0, findNum, r, c, board);
		moveCnt += move + 2; // 움직 + 뒤집(2개)
		board[find1[0]][find1[1]] = 0;
		board[find2[0]][find2[1]] = 0;
		ComputeMoveCnt(order, now+1, N, moveCnt, find2[0], find2[1], board);
		moveCnt -= move;
		board[find1[0]][find1[1]] = findNum;
		board[find2[0]][find2[1]] = findNum;
		
		move = SameCardDiffOrder(1, findNum, r, c, board);
		moveCnt += move; // 움직 + 뒤집(2개, 위의 경우에서 빼주지 않음 => 더하지 않아도 됨)
		board[find1[0]][find1[1]] = 0;
		board[find2[0]][find2[1]] = 0;
		ComputeMoveCnt(order, now+1, N, moveCnt, find1[0], find1[1], board);
		board[find1[0]][find1[1]] = findNum;
		board[find2[0]][find2[1]] = findNum;
	}
	
	private static int solution(int[][] board, int r, int c) {
		cardInfo = new Card[7]; // 1~6까지의 카드 위치
		cardList = InitializeCard(board, cardInfo); // 카드 종류
		int cardN = cardList.size(); // 카드 종류 개수
		
		ArrayList<Integer> tmp = new ArrayList<>();
		boolean[] visited = new boolean[cardN];
		Perm(cardN, cardN, 0, tmp, visited);
		
		int test = findCard(3, 2, 0, 3, board);
		for(ArrayList<Integer> order : perm) { // order: 뒤집으려는 카드 인덱스(cardList에서의)의 순서
			ComputeMoveCnt(order, 0, cardN, 0, r, c, board);
		}
		
		return MinCnt;
	}
	
	private static class Card{
		int[][] loc;
		
		Card(){
			this.loc = new int[2][2]; // 카드 한 쌍(2개)의 좌표 (r, c)
		}
	}
	
	private static class Move implements Comparable<Move>{
		int r;
		int c;
		int cnt;
		
		Move(int r, int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Move m) {
			return this.cnt >= m.cnt? 1 : -1;
		}
	}
}
