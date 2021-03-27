import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char [] a = br.readLine().toCharArray();
		char [] b = br.readLine().toCharArray();
		int lena = a.length;
		int lenb = b.length;
		int [][] check = new int[lena+1][lenb+1];
		int ans = Integer.MIN_VALUE;
		for(int i = 1; i <= lena; i++) {
			for(int j = 1; j <= lenb; j++) {
				if(a[i-1] == b[j-1]) check[i][j] = check[i-1][j-1]+1;
				ans = Math.max(ans, check[i][j]);
			}
		}
		System.out.println(ans);
	}
}