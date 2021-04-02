import java.util.*;
public class _64061 {

	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0}, {0,0,1,0,3}, {0,2,5,0,1}, {4,2,4,4,2}, {3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		
		System.out.print(solution(board, moves));
	}
	
	private static int solution(int[][] board, int[] moves) {
		int res = 0;
		int N = board.length;
		int[] loc = new int[N]; // 뽑아야하는 인형의 위치(맨 위의 인형의 위치)
		for(int i=0; i<N; i++) {
			int l = -1;
			for(int j=0; j<N; j++) {
				if(board[j][i] != 0) {
					l = j;
					break;
				}
			}
			if(l == -1)
				loc[i] = N;
			else	loc[i] = l;
		}
		
		Stack<Integer> basket = new Stack<>();
		for(Integer m : moves) {
			int bnum = m-1;
			if(loc[bnum] == N)		continue; // 인형이 없는 경우
			
			int num = board[loc[bnum]][bnum];
			board[loc[bnum]][bnum] = 0;
			loc[bnum]++;
			
			if(!basket.isEmpty()) { // 바구니가 비어있지 않다면
				if(basket.peek() == num) { // 연속 두개가 같은 인형이라면
					basket.pop();
					res += 2;
				}else {
					basket.add(num);
				}
			}else { // 바구니가 비어있다면
				basket.add(num);
			}
		}
		
		return res;
	}
}
