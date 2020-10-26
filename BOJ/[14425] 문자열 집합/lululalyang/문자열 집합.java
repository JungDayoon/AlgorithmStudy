import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class _14425 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] Scnt = br.readLine().split(" ");
		int N = Integer.parseInt(Scnt[0]);
		int M = Integer.parseInt(Scnt[1]);
		
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) { // ���� S
			map.put(br.readLine(), 1);
		}
		
		int cnt = 0;
		for(int i=0; i<M; i++) { // ã�ƾ� �� ���ڿ� M��
			if(map.containsKey(br.readLine())) { // true�� -> �����Ѵٴ� ��!
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
