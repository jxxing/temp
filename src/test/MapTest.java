package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map bmap = new  HashMap();bmap.remove("B");
		bmap.put("A", false);
		boolean re = true;
		Set<String> key = bmap.keySet();        
		for (Iterator it = key.iterator(); it.hasNext();) { 
			String s = (String) it.next();
			boolean temp = (Boolean)bmap.get(s);
			if("A".equals(s)){
				bmap.remove(s);
				re = temp&&re;
				bmap.put(s, re);
			}
			System.out.println(bmap.get(s));        
		}
		bmap.put("B", true);
		bmap.put("A", true);
		bmap.put("C", true);
		System.out.println(bmap.size());
		System.out.println(bmap.get("A"));  
		
		
		Map<String,Integer> smap = new HashMap<String,Integer>();
//		int sst = (Integer)smap.get("a");
		System.out.println((Integer)smap.get("a"));
		
		new MapTest().testMap();
		
	}
	
	public void testMap(){

		System.out.println("testMap  start");
		TreeSet aaa;
		HashSet bbb;
		
		 ArrayList<String> list = new ArrayList<String>(); 
		   Map<Object,Object> hash = new HashMap<Object,Object>(); 
		   TreeMap<Object,Object> treeMap = new TreeMap<Object,Object>(); 
		   list.add("a"); 
		   list.add("b"); 
		   list.add("c"); 
		  
		   hash.put(3, 3); 
		   hash.put(4, 4); 
		   hash.put(5, 5); 
		   hash.put(6, 6); 
		   hash.put(1, 1); 
		   hash.put(2, 2); 
		  
		   treeMap.put(new Integer(1), 1); 
		   treeMap.put(2, 2); 
		   treeMap.put(3, 3); 
		   treeMap.put(4, 4); 
		   treeMap.put(5, 5); 
		   treeMap.put(6, 6); 
		  
		   //list遍历 

			System.out.println("list  start");
		   for(String m: list){ 
		    System.out.println(m); 
		   } 
		   // hashmap entrySet() 遍历 
			System.out.println("hashmap entrySet()  start");
		   for(Map.Entry<Object,Object> m: hash.entrySet()){ 
		    System.out.println(m.getKey()+"---"+m.getValue()); 
		   } 
		   //hashmap keySet() 遍历 
			System.out.println("hashmap keySet()  start");
		   for(Object m: hash.keySet()){ 
		    System.out.println(m+"---"+hash.get(m)); 
		   } 
		   // treemap keySet()遍历 
			System.out.println("treemap keySet()  start");
		   for(Object m: treeMap.keySet()){ 
		    System.out.println(m+"---"+treeMap.get(m)); 
		   } 
		   // treemap entrySet() 遍历 
			System.out.println("treemap entrySet()  start");
		   for(Map.Entry<Object,Object> m: treeMap.entrySet()){ 
			    System.out.println(m.getKey()); 
		    System.out.println(m.getKey()+"---"+m.getValue()); 
		    
		   } 
		   //note :  entrySet()  keySet() 在map继承类中的方法，再去调用 EntrySet KeySet类去生成对象，类中重写 iterator()方法
		    Set sett = new TreeSet();
		    sett.add("df");
		    Set sett2 = new HashSet();
		   
		   Map map = new HashMap(); 
		   map.put(11, 11); 
		   map.put(12, 12); 
		   map.put(13, 13); 
		   map.put(14, 14); 
			System.out.println("map entrySet()  start22");
		   Iterator it = map.entrySet().iterator(); 
		   while (it.hasNext()) { 
		   Map.Entry entry = (Map.Entry) it.next(); 
		   Object key = entry.getKey(); 
		   Object value = entry.getValue(); 
		    System.out.println(key+"---"+value); 
		   } 
			System.out.println("map keySet()  start22");
		   Iterator it2 = map.keySet().iterator(); 
		   while (it2.hasNext()) { 
			   Object key = (Object) it2.next(); 
			   Object value = map.get(key);
			   System.out.println(key+"---"+value); 
		   } 
		   
//		   for(Map.Entry m: (Map.Entry)map.entrySet()){ 
//		    System.out.println(m.getKey()+"---"+m.getValue()); 
//		   } 
		   Object aaaa = 1;
		   System.out.println(aaaa);

	}

}
