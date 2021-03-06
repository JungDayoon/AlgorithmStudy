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
        
        for(int i=0; i<cities.length; i++) { // i가 순서
        	String city = cities[i].toUpperCase(); // 대소문자 구분X => 모두 대문자로
        	
        	if(cache.containsValue(city)) { // 캐시에 있다면
        		time += 1; // cache hit!
        		
        		int order = cityName.remove(city);
        		cache.remove(order);
        		cache.put(i, city);
        		cityName.put(city, i);
        	}else {
        		time += 5; //cache miss!
        		if(cacheSize == 0)	continue;
        		if(cache.size() < cacheSize) { // 캐시에 자리가 있다면
        			cache.put(i, city);
        			cityName.put(city, i);
        		}else { // 자리 없다면
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
