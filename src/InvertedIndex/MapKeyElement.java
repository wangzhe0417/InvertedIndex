package InvertedIndex;

public class MapKeyElement {
	private String keyWord;
	int counts;
	
	public void setWord(String word){
		keyWord = word;
	}
	
	public void addCounts(int count){
		counts += count;
	}
	
	public void clearCounts(){
		counts = 0 ;
	}
	
	public String getWord(){
		return keyWord;
	}
	
	public int getCounts(){
		return counts;
	}
	
	//由于map的key有两个元素，所以需要重写hashCode和equals方法
	@Override
	public int hashCode(){
		return keyWord != null ? keyWord.hashCode() : 0;
	}
	@Override
	public boolean equals(Object o) {
		if( this.equals(o) ) 
			return true;
		if( o == null || getClass() != o.getClass() )
			return false;
		MapKeyElement key = (MapKeyElement) o;
		
		if ( (keyWord != null) ? (!keyWord.equals(key.keyWord)) : (key.keyWord != null))
			return false;
		return true;
//		return (o instanceof MapKeyElement ) && ( keyWord.equals( ( (MapKeyElement)o ).keyWord )) 
//				&& ( keyWord == null && ( (MapKeyElement)o ).keyWord == null);
	}
	
	
}
