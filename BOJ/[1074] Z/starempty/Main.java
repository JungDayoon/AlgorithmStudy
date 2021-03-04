import java.util.Scanner;

public class Main {
	static int cnt, r, c;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		int size = (int) Math.pow(2, n);
		cnt = 0;
		find(size, 0, 0);
		sc.close();
	}

	private static void find(int s, int x, int y) {
		if (s == 1) {
			if (x == r && y == c) {
				System.out.println(cnt);
				System.exit(0);
			}
			cnt++;
			return;
		}
		s /= 2;
		if(r < x+s && c < y+s) find(s, x, y);
		else cnt += s*s;
		if(r < x+s && y+s <= c) find(s, x, y+s);
		else cnt += s*s;
		if(x+s <= r && c < y+s) find(s, x+s, y);
		else cnt += s*s;
		if(x+s <= r && y+s <= c) find(s, x+s, y+s);
		else cnt += s*s;
	}
}
