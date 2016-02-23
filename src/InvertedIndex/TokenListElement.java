package InvertedIndex;
import java.util.LinkedList;

public class TokenListElement{
	
	private String key;
	private int counts;
	private int docID;
	
	//构造器
	public TokenListElement(String word, int count, int ID){
		key = word;
		counts = count;
		docID = ID;
	}
	
	public void setKey(String word){
		key = word;
	}
	
	public void addCounts(int count){
		counts = counts + count;
	}
	
	public void setdocID(int ID){
		docID = ID;
	}
	
	public int compareToAnother(TokenListElement element){
		return (this.getWord().compareTo(element.getWord()));
	}
	
	
	
	
	//用于测试的方法
	public String getWord(){
		return key;
	}
	
	public int getCounts(){
		return counts;
	}
	
	public int getdocID(){
		return docID;
	}
}
