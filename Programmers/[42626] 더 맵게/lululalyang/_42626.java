import java.util.*;
public class _42626 {

	public static void main(String[] args) {
		/* INPUT */
	}
	
	public int solution(int[] scoville, int K) {
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Integer s : scoville)   pq.add(s);
        int cnt = 0;
        int a, b;
        while(pq.peek() < K){
            if(pq.size() == 1) {
                ans = -1;
                break;
            }
            a = pq.poll();
            b = pq.poll();
            int value = a + b*2;
            pq.add(value);
            cnt++;
        }
        
        if(ans != -1)   ans = cnt;
        return ans;
    }
}
