package InvertedIndex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 
 * 实现布尔查询，不同的token是否在同一个文档中
 * @author wangzhe
 *
 */
public class BoolSearch {
	public static ArrayList<Integer> boolsearch(){
		ArrayList<Integer> result = new ArrayList<>();
		Map<MapKeyElement, LinkedList> invertedIndex =  new HashMap<>();
		invertedIndex = convertListToMap.getInvertedIndex();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入第一个单词：");
		String token1 = sc.next();
		//System.out.println();
		System.out.println("请输入布尔操作符AND,OR,NOT: ");
		String operator = sc.next();
		//System.out.println();
		System.out.println("请输入第二个单词：");
		String token2 = sc.next();
		
		LinkedList<Integer> tokenList1 = new LinkedList<>();
		LinkedList<Integer> tokenList2 = new LinkedList<>();
		
		for(Entry<MapKeyElement, LinkedList> entry : invertedIndex.entrySet()){
			if( entry.getKey().getWord().equals(token1) ){
				tokenList1.addAll(entry.getValue());
			}
			if( entry.getKey().getWord().equals(token2) ){
				tokenList2.addAll(entry.getValue());
			}
		}
		
//		switch(operator){
//		case "AND" : if(tokenList1 == null){
//			
//		}
//		case "OR"  :
//		case "NOT" :
//		}
		int i = 0, j = 0;
		if(operator.equals("AND")){
			if(tokenList1.size() == 0 || tokenList2.size() == 0){
				System.out.println("There is no Document both contain " + token1 +" and " + token2);
			}else{
				while(i < tokenList1.size() && j < tokenList2.size()){
					if(tokenList1.get(i) == tokenList2.get(j)){
						result.add(tokenList1.get(i));
						i ++;
						j ++;
					}else if(tokenList1.get(i) < tokenList2.get(j)){
						i ++;
					}else if(tokenList1.get(i) > tokenList2.get(j)){
						j ++;
					}
				}
				System.out.print("The Document both contain " + token1 + " and " + token2 +": ");
			}
		}else if(operator.equals("OR")){
			if((tokenList1.size() == 0 ) && (tokenList2.size() == 0)){
				System.out.println("There is no Document contain " + token1 + " or " + token2);
			}else if((tokenList1.size() == 0) &&(tokenList2.size() != 0)){
				result.addAll(tokenList2);
				System.out.println("The Document contain " + token1 + " or " + token2 + ": ");
			}else if((tokenList1.size() != 0) &&(tokenList2.size() == 0)){
				result.addAll(tokenList1);
				System.out.println("The Document contain " + token1 + " or " + token2 + ": ");
			}else if((tokenList1.size() != 0) &&(tokenList2.size() != 0)){
				while(i < tokenList1.get(i) && j < tokenList2.size()){
					if(tokenList1.get(i) == tokenList2.get(j)){
						result.add(tokenList1.get(i));
						i ++;
						j ++;
					}else if(tokenList1.get(i) < tokenList2.get(j)){
						result.add(tokenList1.get(i));
						i ++;
					}else if(tokenList1.get(i) > tokenList2.get(j)){
						result.add(tokenList1.get(j));
						j ++;
					}
				}
				System.out.println("The Document contain " + token1 + " or " + token2 + ": ");
			}
		}else if(operator.equals("NOT")){
			if((tokenList1.size() == 0 ) && (tokenList2.size() == 0)){
				System.out.println("There is no Document contain " + token1 + " not " + token2);
			}else if((tokenList1.size() == 0) &&(tokenList2.size() != 0)){
				System.out.println("There is no Document contain " + token1 + " not " + token2);
			}else if((tokenList1.size() != 0) &&(tokenList2.size() == 0)){
				result.addAll(tokenList1);
				System.out.println("The Document contain " + token1 + " not " + token2 + ": ");
			}else if((tokenList1.size() != 0) &&(tokenList2.size() != 0)){
				while(i < tokenList1.get(i) && j < tokenList2.size()){
					if(tokenList1.get(i) == tokenList2.get(j)){
						i ++;
						j ++;
					}else if(tokenList1.get(i) < tokenList2.get(j)){
						result.add(tokenList1.get(i));
						i ++;
					}else if(tokenList1.get(i) > tokenList2.get(j)){
						j ++;
					}
				}
				System.out.println("The Document contain " + token1 + " not " + token2 + ": ");
			}
		}else {
			System.out.println("请正确输入！");
		}
		return result;
	}
	
//	public static void main(String[] args){
//		ArrayList<Integer> Result = new ArrayList<>();
//		Result = boolsearch();
//		System.out.println(Result);
//	}
}
