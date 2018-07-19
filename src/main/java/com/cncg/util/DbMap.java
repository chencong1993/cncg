package com.cncg.util; 

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.map.ListOrderedMap;

public class DbMap {

	public static void main(String[] args) {  
		  
        Map<String, String> map = new HashMap<String, String>();  
        map.put("1", "Test1");  
        map.put("2", "Test2");  
        map.put("3", "Test3");  
        map.put("4", "Test4");  
  
        Set<Entry<String, String>> entrySet1 = map.entrySet();  
        for (Entry<String, String> entry : entrySet1) {  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
  
        Map<String, String> listOrderedMap = ListOrderedMap.decorate(new HashMap());  
        listOrderedMap.put("1", "Test1");  
        listOrderedMap.put("2", "Test2");  
        listOrderedMap.put("3", "Test3");  
        listOrderedMap.put("4", "Test4");  
  
        Set<Entry<String, String>> entrySet2 = listOrderedMap.entrySet();  
        for (Entry<String, String> entry : entrySet2) {  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
    }  

}
