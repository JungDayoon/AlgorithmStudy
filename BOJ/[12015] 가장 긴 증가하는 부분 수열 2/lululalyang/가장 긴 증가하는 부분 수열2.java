import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _12015 {
	static int[] input;
	static int N;
	static int flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		ArrayList<Integer> asc = new ArrayList<>();
		asc.add(0); //ù��° ���ڵ� �����ֱ� ����
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(s[i]);
			if(asc.get(asc.size()-1) < num) { //���� ���ڰ� �� ũ��
				asc.add(num);
			}else { //���� ���ڰ� �������� ���� �� ���� ū ������ �۰ų� ������
				int left = 0;
				int right = asc.size() - 1;
				
				while(left < right) {
					int mid = (left+right)/2;
					if(asc.get(mid) >= num) {
						right = mid;
					}else if(asc.get(mid) < num){
						left = mid+1;
					}
				}
				int idx = left;
				asc.remove(idx);
				asc.add(idx, num);
			}
		}
		
		System.out.print(asc.size()-1);
	}
	
	
}
