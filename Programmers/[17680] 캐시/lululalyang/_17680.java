import java.io.*;
import java.util.*;

public class _17680 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cacheSize = Integer.parseInt(br.readLine());
		String[] cities = br.readLine().split(" ");
		
		System.out.print(solution(cacheSize, cities));
	}
	
	private static int solution(int cacheSize, String[] cities) {
        int time = 0;
        TreeMap<Integer, String> cache = new TreeMap<>();
        Map<String, Integer> cityName = new HashMap<>();
        
        for(int i=0; i<cities.length; i++) { // i�� ����
        	String city = cities[i].toUpperCase(); // ��ҹ��� ����X => ��� �빮�ڷ�
        	
        	if(cache.containsValue(city)) { // ĳ�ÿ� �ִٸ�
        		time += 1; // cache hit!
        		
        		int order = cityName.remove(city);
        		cache.remove(order);
        		cache.put(i, city);
        		cityName.put(city, i);
        	}else {
        		time += 5; //cache miss!
        		if(cacheSize == 0)	continue;
        		if(cache.size() < cacheSize) { // ĳ�ÿ� �ڸ��� �ִٸ�
        			cache.put(i, city);
        			cityName.put(city, i);
        		}else { // �ڸ� ���ٸ�
        			String rmCity = cache.firstEntry().getValue();
        			int order = cityName.remove(rmCity);
        			cache.remove(order);
        			
        			cache.put(i, city);
        			cityName.put(city, i);
        		}
        	}
        }
        return time;
    }
}
