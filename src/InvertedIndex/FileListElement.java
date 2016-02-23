package InvertedIndex;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 文件列表FileList元素组成
 * @author wangzhe
 *
 */
public class FileListElement {
	
	private int flag;
	private int FileID;
	private File FileName;
	private LinkedList<TokenListElement> TokenList;
	
	
	//构造器
	public FileListElement(int tag, int ID, File Name, LinkedList<TokenListElement> list){
		flag = tag;
		FileID = ID;
		FileName = Name;
		list = null;
		TokenList  = new LinkedList<>();
	}
	
	public void setFileID(int index){
		FileID = FileID + index;
	}
	
	public void setFileName(File directoryList){
		FileName = directoryList;
	}
	
	//flag==0则该文件对应的tokenList为空
	public void setFlag(){
		flag = 0;
	}
	
	public int getFlag(){
		return flag;
	}
	
	
	
	public int getFileID(){
		return FileID;
	}
	
	public File getFileName(){
		return FileName;
	}
	
	//读取TokenList指定位置的元素
	public TokenListElement getTokenListElement(FileListElement element, int index){
		return element.TokenList.get(index);
	}
	//获取并移除TokenList的第一个元素
	public TokenListElement getAndRemoveToken(){
		return this.TokenList.pollFirst();
	}
	
	public void addToken(LinkedList<TokenListElement> tokenList){
			TokenList.addAll(tokenList);
	}
	
	public int getTokenListSize(){
		return TokenList.size();
	}
	
	//用于测试的方法
//	public void testPrint(FileListElement element){
//		File fileName = element.getFileName();
//		System.out.println(fileName + ":");
//		TokenListElement temp = null;
//		for(int index = 0; index < element.getTokenListSize(); index ++){
//			temp = element.getTokenListElement(element, index);
//			System.out.println(temp.getWord() + " " + temp.getCounts() + " " + temp.getdocID());
//		}
//	}

	


}
