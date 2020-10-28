import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class _10816 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		String[] d = br.readLine().split(" ");
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(d[i]);
		
		int m = Integer.parseInt(br.readLine());
		int[] find = new int[m];
		String[] s = br.readLine().split(" ");
		for(int i=0; i<m; i++)
			find[i] = Integer.parseInt(s[i]);
		
		Arrays.sort(arr);
		
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			int search = find[i];
			int low = lowerBound(search, arr);
			int high = upperBound(search, arr);
			
			sb.append(high-low).append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int upperBound(int search, int[] arr) { //ã�� ������ ū ���� ó������ ������ index�� ����
		int left = 0;
		int right = arr.length;
		
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(arr[mid] > search) {
				right = mid;
			}else { //arr[mid] <= search
 				left = mid+1;
			}
		}
		return left;
	}
	
	private static int lowerBound(int search, int[] arr) { // ã�� ���� ���ų� ū ���� ó�������� index�� ����
		int left = 0;
		int right = arr.length; //�������� ���� ��� arr.length�� return�ϴϱ�
		
		while(left < right) {
			int mid = (left + right) / 2;
			if(arr[mid] >= search) {
				right = mid;
			}else { // arr[mid] < search
				left = mid+1;
			}
		}
		
		return left;
	}
}
