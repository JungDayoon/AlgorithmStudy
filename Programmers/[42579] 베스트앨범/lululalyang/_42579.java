import java.util.*;
public class _42579 {

	public static void main(String[] args){
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		int[] res = solution(genres, plays);
		for(int i : res)	System.out.print(i + " ");
	}

	private static int[] solution(String[] genres, int[] plays) { 
        int[] answer = {};
        ArrayList<Integer> ans = new ArrayList<>();
        
        int N = genres.length;
        Map<String, Integer> playSum = new HashMap<>(); // 재생횟수 합
        Map<String, PriorityQueue<Element>> playlist = new HashMap<>(); // 장르 내 순서
        
        for(int i=0; i<N; i++) {
        	String g = genres[i];
        	int p = plays[i];
        	
        	if(playSum.containsKey(g)) {
        		int prev = playSum.get(g);
        		playSum.put(g, prev+p);
        	}else {
        		playSum.put(g, p);
        	}
        	
        	if(playlist.containsKey(g)) {
        		PriorityQueue<Element> now = playlist.get(g);
        		now.add(new Element(i, p));
        		playlist.put(g, now);
        	}else {
        		PriorityQueue<Element> tmp = new PriorityQueue<>();
        		tmp.add(new Element(i, p));
        		playlist.put(g, tmp);
        	}
        }
        
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(playSum.entrySet());
		Collections.sort(entries, (o1, o2) -> o2.getValue() - o1.getValue());
		
		for(Map.Entry<String, Integer> entry : entries) {
			String g = entry.getKey();
			PriorityQueue<Element> pq = playlist.get(g);
			for(int i=0; i<2; i++) {
				if(pq.isEmpty())	break;
				Element e = pq.poll();
				ans.add(e.num);
			}
		}
		
		answer = new int[ans.size()];
		for(int i=0; i<ans.size(); i++)
			answer[i] = ans.get(i);
		
        return answer;
    }
	
	private static class Element implements Comparable<Element>{
		int num; // 고유번호
		int plays; // 재생횟수
		
		Element(int num, int plays){
			this.num = num;
			this.plays = plays;
		}
		
		@Override
		public int compareTo(Element e) {
			if(this.plays > e.plays)	return -1; // 내림차순
			else if(this.plays < e.plays)	return 1;
			else	return this.num - e.num; // 오름차순
		}
		
		public String toString() {
			return "("+num+", "+plays+")";
		}
	}
}
