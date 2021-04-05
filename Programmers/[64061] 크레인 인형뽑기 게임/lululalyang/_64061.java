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
		int[] loc = new int[N]; // �̾ƾ��ϴ� ������ ��ġ(�� ���� ������ ��ġ)
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
			if(loc[bnum] == N)		continue; // ������ ���� ���
			
			int num = board[loc[bnum]][bnum];
			board[loc[bnum]][bnum] = 0;
			loc[bnum]++;
			
			if(!basket.isEmpty()) { // �ٱ��ϰ� ������� �ʴٸ�
				if(basket.peek() == num) { // ���� �ΰ��� ���� �����̶��
					basket.pop();
					res += 2;
				}else {
					basket.add(num);
				}
			}else { // �ٱ��ϰ� ����ִٸ�
				basket.add(num);
			}
		}
		
		return res;
	}
}
