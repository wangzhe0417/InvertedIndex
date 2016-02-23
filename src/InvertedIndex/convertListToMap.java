package InvertedIndex;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Map的形式是key为word，value是一个int型链表，链表的第一个元素存储的是counts，后面fileID
 * @author wangzhe
 *
 */
public class convertListToMap {
	
	public static Map<MapKeyElement, LinkedList> getInvertedIndex(){
		Map<MapKeyElement, LinkedList> invertedIndex= new HashMap<>();
		LinkedList<TokenListElement> FinalList = new LinkedList<>();
	
		FinalList = mergeAllTokenList.convertTokenListToHeap();//获取最终词项列表
		
		int i = 0;
		int j = 1;
		int count = 0;
		List<Integer> arr = new LinkedList<>();
		while( i < FinalList.size() ){
			while(j < FinalList.size() ){
				if( ! (FinalList.get(i).getWord().equals( FinalList.get(j).getWord() )) ){
					MapKeyElement key = new MapKeyElement();
					LinkedList<Integer> invertedID = new LinkedList<>();
				
					key.setWord(FinalList.get(i).getWord());
					key.addCounts(count);
					key.addCounts(FinalList.get(i).getCounts());
					invertedID.addAll(arr);
					invertedID.add(FinalList.get(i).getdocID());
					Collections.sort(invertedID);
					invertedIndex.put(key, invertedID);
					
					count = 0;
					arr.clear();
					i = j;
					j ++;
				}else{
					count = count + FinalList.get(i).getCounts();
					arr.add(FinalList.get(i).getdocID());
					i = j;
					j ++;
				}
			}
			MapKeyElement key = new MapKeyElement();
			LinkedList<Integer> invertedID = new LinkedList<>();
			key.setWord(FinalList.get(i).getWord());
			key.addCounts(count);
			key.addCounts(FinalList.get(i).getCounts());
			invertedID.addAll(arr);
			invertedID.add(FinalList.get(i).getdocID());
			
			Collections.sort(invertedID);
			invertedIndex.put(key, invertedID);
			i ++;
		}
		return invertedIndex;
	}
	
	public static void main(String[] args){
		Map<MapKeyElement, LinkedList> InvertedIndex = new HashMap<>();
		InvertedIndex = getInvertedIndex();
		//打印测试倒排索引
		for(Entry<MapKeyElement, LinkedList> entry : InvertedIndex.entrySet()){
			System.out.println(entry.getKey().getWord() + " " + entry.getKey().getCounts()+ ": "  + entry.getValue());
		}
	}
}
