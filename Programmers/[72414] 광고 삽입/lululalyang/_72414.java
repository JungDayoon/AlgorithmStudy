import java.io.*;

public class _72414 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String play_time = br.readLine();
		String adv_time = br.readLine();
		int N = Integer.parseInt(br.readLine());
		String[] logs = new String[N];
		for(int i=0; i<N; i++) {
			logs[i] = br.readLine();
		}
		
		
		System.out.print(solution(play_time, adv_time, logs));
	}
	
	private static long timeToN(String time) {
		long res = 0;
		String[] split = time.split(":");
		for(int i=0; i<3; i++) {
			int mult = (int) Math.pow(60, 2-i);
			res += mult * Integer.parseInt(split[i]);
		}
		return res;
	}
	
	private static String NtoTime(long N) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<3; i++) {
			if(i != 0)	sb.append(":");
			int mult = (int) Math.pow(60, 2-i);
			long time = N / mult;
			if((time%10) == time)	sb.append("0"+time); // 한자리 수라면
			else	sb.append(time);
			N %= mult;
		}
		return sb.toString();
	}
	
	private static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        long N = timeToN(play_time);
        long[] AllTime = new long[(int) (N+1)];
        long adv = timeToN(adv_time);
        
        int logsLen = logs.length;
        for(int i=0; i<logsLen; i++) {
        	String[] StartEnd = logs[i].split("-");
        	long start = timeToN(StartEnd[0]); // 시청 시작 시간
         	long end = timeToN(StartEnd[1]); // 시청 끝 시간
        	
         	AllTime[(int) start]++; // 시작 때 한명 추가
         	AllTime[(int) end]--; // 끝날 때 한명 제거
        }
        
        for(int i=1; i<N+1; i++) {
        	AllTime[i] = AllTime[i-1] + AllTime[i]; // i시간에 몇명 시청하고 있는지
        }
        
        for(int i=1; i<N+1; i++) {
        	AllTime[i] = AllTime[i-1] + AllTime[i]; // i시간까지 몇명이나 봤는지 (누적 시청자 수)
        }
        
        long maxPeople = 0;
        long maxStart = -1;
        for(long i=adv-1; i<N+1; i++) {
        	long nowTime;
        	if(i-adv == -1)	nowTime = AllTime[(int) i]; // 0~i-adv	
        	else nowTime = AllTime[(int) i] - AllTime[(int) (i-adv)]; //이 시간대에 광고를 넣는다면 보게 될 사람 수 // i까지 본 시청자 수에서 i-adv까지 본 시청자 수를 뺀값이 현재 시간대(i-adv+1 ~ i)에 보는 시청자 수가 된다
        	
        	if(maxPeople < nowTime) { // 이때 시청자수가 더 많다면
        		maxPeople = nowTime; // 최대 시청자수 갱신
        		maxStart = i-adv + 1; // 그때의 시작 시각 갱신
        	}
        }
        
        answer = NtoTime(maxStart);
        return answer;
    }
}
