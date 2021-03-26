import java.io.*;
import java.util.*;
public class _4256 {
	static int[] preOrder; // 전위 순회
	static int[] inOrder; // 중위 순회
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s;
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			if(sb.length() != 0)	sb.append("\n");
			int N = Integer.parseInt(br.readLine());
			preOrder = new int[N];
			inOrder = new int[N];
			
			s = br.readLine().split(" ");
			for(int i=0; i<N; i++)	preOrder[i] = Integer.parseInt(s[i]);
			s = br.readLine().split(" ");
			for(int i=0; i<N; i++)	inOrder[i] = Integer.parseInt(s[i]);
			
			ArrayList<Integer> postOrder = PostOrder(0, N-1, 0, N-1);
			
			for(int i=0; i<N; i++) {
				if(i != 0)	sb.append(" ");
				sb.append(postOrder.get(i));
			}
		}
		
		System.out.print(sb.toString());
	}
	
	private static int FindNumIninOrder(int num) {
		int n = inOrder.length;
		for(int i=0; i<n; i++) {
			if(inOrder[i] == num)	return i;
		}
		return -1;
	}
	
	private static ArrayList<Integer> PostOrder(int prestart, int preend, int instart, int inend){
		int root = preOrder[prestart];
		int rIdx = FindNumIninOrder(root);
		
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		
		int leftCnt = rIdx - instart;
		if(instart == (rIdx-1))	left.add(inOrder[instart]);
		else if(instart < (rIdx-1))	left = PostOrder(prestart+1, prestart+leftCnt, instart, rIdx-1); // 왼쪽 서브트리로 다시 postOrder
		// start가 더 큰 경우는 left가 없을 때
		
		if((rIdx+1) == inend)		right.add(inOrder[inend]);
		else if((rIdx+1) < inend)		right = PostOrder(prestart+leftCnt+1, preend, rIdx+1, inend); // 오른쪽 서브트리로 다시 postOrder
		// end가 더 작은 경우는 right가 없을 때
		
		ArrayList<Integer> res = new ArrayList<>();
		AddBtoA(res, left);
		AddBtoA(res, right);
		res.add(root);
		
		return res;
	}

	private static void AddBtoA(ArrayList<Integer> A, ArrayList<Integer> B) { // B의 정수를 A에 더한다
		for(Integer b : B) {
			A.add(b);
		}
	}
}
