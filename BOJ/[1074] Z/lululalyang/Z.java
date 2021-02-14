import java.io.*;

public class _1074 {
	static int res = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int r = Integer.parseInt(s[1]);
		int c = Integer.parseInt(s[2]);

		
		int check; // [[0, 1], [2, 3]]
		if(r%2 == 0) { 
			if(c%2 == 0) // (¦, ¦)
				check = 0;
			else { // (¦, Ȧ)
				check = 1;
				c--;
			}
		}else {
			if(c%2 == 0) { // (Ȧ, ¦)
				check = 2;
				r--;
			}
			else { // (Ȧ, Ȧ)
				check = 3;
				r--;
				c--;
			}
		}
		
		Recursive(N, r, c, 0);
		int result = res + check;
		System.out.print(result);
	}

	private static void Recursive(int N, int r, int c, int first) {
		if(N == 1) {
			res = first;
			return;
		}
		
		int check;
		int half = (int) (Math.pow(2, N)) / 2;
		if(r >= half) {
			if(c >= half)
				check = 3;
			else
				check = 2;
		}else {
			if(c >= half)
				check = 1;
			else
				check = 0;
		}
		
		first += Math.pow(2, 2*(N-1)) * check;
		if(check == 0)
			Recursive(N-1, r, c, first);
		else if(check == 1)
			Recursive(N-1, r, c-half, first);
		else if(check == 2)
			Recursive(N-1, r-half, c, first);
		else
			Recursive(N-1, r-half, c-half, first);
		
	}
}
