import java.io.*;
import java.util.*;

public class _42888 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] record = new String[5];
		for(int i=0; i<5; i++) {
			record[i] = br.readLine();
		}
		
		String[] res = solution(record);
		for(String r : res) {
			System.out.println(r);
		}
	}
	
	private static String[] solution(String[] record) {
		String[] answer = {};
		
		String[][] command = new String[record.length][3];
		Map<String, String> User = new HashMap<>(); // <UID, �г���>
		for(int i=0; i<record.length; i++) {
			command[i] = record[i].split(" "); // command, uid, �г���
			String Do = command[i][0];
			String uid = command[i][1];
			if(Do.equals("Enter") || Do.equals("Change")) {
				String name = command[i][2];
				if(User.containsKey(uid)) { // ���� ������ ������ �ִ��� �����ϰ� �ٽ� put
					User.remove(uid);
					User.put(uid, name);
				}else {
					User.put(uid, name); // ������ ���� �߰�
				}
			}
		}
		
		ArrayList<String> res = new ArrayList<>();
		for(int i=0; i<command.length; i++) {
			if(command[i][0].equals("Enter")) {
				String name = User.get(command[i][1]);
				res.add(name+"���� ���Խ��ϴ�.");
			}else if(command[i][0].equals("Leave")) {
				String name = User.get(command[i][1]);
				res.add(name+"���� �������ϴ�.");
			}
		}
				
		answer = res.toArray(new String[res.size()]);
		return answer;
	}
}
