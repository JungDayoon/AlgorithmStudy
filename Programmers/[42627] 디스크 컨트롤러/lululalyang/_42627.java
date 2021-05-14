import java.util.*;
public class _42627 {

	public static void main(String[] args) {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
		System.out.print(solution(jobs));
	}
	
	private static int solution(int[][] jobs) {
        int ans = 0;
        int cnt = jobs.length;
        PriorityQueue<Ready> ready = new PriorityQueue<>();
        PriorityQueue<Job> job = new PriorityQueue<>();
        
        for(int i=0; i<cnt; i++)	ready.add(new Ready(jobs[i][0], jobs[i][1]));
        
        int now = 0;
        while(!ready.isEmpty()) {
        	while(!ready.isEmpty() && ready.peek().request <= now) {
        		Ready tmp = ready.poll();
        		job.add(new Job(tmp.request, tmp.jobtime));
        	}
        	
        	if(job.isEmpty()) {
        		now++;
        		continue;
        	}
        	
        	Job tmp = job.poll();
        	now += tmp.jobtime;
        	ans += (now - tmp.request);
        }
        
        while(!job.isEmpty()) {
        	Job tmp = job.poll();
        	now += tmp.jobtime;
        	ans += (now - tmp.request);
        }
        
        return ans / cnt;
    }
	
	private static class Ready implements Comparable<Ready>{
		int request;
		int jobtime;
		
		Ready(int request, int jobtime){
			this.request = request;
			this.jobtime = jobtime;
		}
		
		@Override
		public int compareTo(Ready r) { // 요청시간 기준 오름차순
			return (this.request - r.request);
		}
	}
	
	private static class Job implements Comparable<Job>{
		int request;
		int jobtime;
		
		Job(int request, int jobtime){
			this.request = request;
			this.jobtime = jobtime;
		}
		
		@Override
		public int compareTo(Job j) { // 작업시간 기준 오름차순
			return (this.jobtime - j.jobtime);
		}
	}
}
