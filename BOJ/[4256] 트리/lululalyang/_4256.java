import java.io.*;
import java.util.*;
public class _4256 {
	static int[] preOrder; // ���� ��ȸ
	static int[] inOrder; // ���� ��ȸ
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
		else if(instart < (rIdx-1))	left = PostOrder(prestart+1, prestart+leftCnt, instart, rIdx-1); // ���� ����Ʈ���� �ٽ� postOrder
		// start�� �� ū ���� left�� ���� ��
		
		if((rIdx+1) == inend)		right.add(inOrder[inend]);
		else if((rIdx+1) < inend)		right = PostOrder(prestart+leftCnt+1, preend, rIdx+1, inend); // ������ ����Ʈ���� �ٽ� postOrder
		// end�� �� ���� ���� right�� ���� ��
		
		ArrayList<Integer> res = new ArrayList<>();
		AddBtoA(res, left);
		AddBtoA(res, right);
		res.add(root);
		
		return res;
	}

	private static void AddBtoA(ArrayList<Integer> A, ArrayList<Integer> B) { // B�� ������ A�� ���Ѵ�
		for(Integer b : B) {
			A.add(b);
		}
	}
}
