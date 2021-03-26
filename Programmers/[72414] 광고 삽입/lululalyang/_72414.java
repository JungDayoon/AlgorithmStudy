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
			if((time%10) == time)	sb.append("0"+time); // ���ڸ� �����
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
        	long start = timeToN(StartEnd[0]); // ��û ���� �ð�
         	long end = timeToN(StartEnd[1]); // ��û �� �ð�
        	
         	AllTime[(int) start]++; // ���� �� �Ѹ� �߰�
         	AllTime[(int) end]--; // ���� �� �Ѹ� ����
        }
        
        for(int i=1; i<N+1; i++) {
        	AllTime[i] = AllTime[i-1] + AllTime[i]; // i�ð��� ��� ��û�ϰ� �ִ���
        }
        
        for(int i=1; i<N+1; i++) {
        	AllTime[i] = AllTime[i-1] + AllTime[i]; // i�ð����� ����̳� �ô��� (���� ��û�� ��)
        }
        
        long maxPeople = 0;
        long maxStart = -1;
        for(long i=adv-1; i<N+1; i++) {
        	long nowTime;
        	if(i-adv == -1)	nowTime = AllTime[(int) i]; // 0~i-adv	
        	else nowTime = AllTime[(int) i] - AllTime[(int) (i-adv)]; //�� �ð��뿡 ���� �ִ´ٸ� ���� �� ��� �� // i���� �� ��û�� ������ i-adv���� �� ��û�� ���� ������ ���� �ð���(i-adv+1 ~ i)�� ���� ��û�� ���� �ȴ�
        	
        	if(maxPeople < nowTime) { // �̶� ��û�ڼ��� �� ���ٸ�
        		maxPeople = nowTime; // �ִ� ��û�ڼ� ����
        		maxStart = i-adv + 1; // �׶��� ���� �ð� ����
        	}
        }
        
        answer = NtoTime(maxStart);
        return answer;
    }
}
