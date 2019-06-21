package util;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtil {
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map, int limit) {
    	Map<K,V> sorted =
			    map.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .limit(limit)
			       .collect(Collectors.toMap(
			          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    	
    	return sorted;
    }
    
	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {          
			System.out.println( entry.getKey() + " : " + entry.getValue() );
		}
	}
    
}
