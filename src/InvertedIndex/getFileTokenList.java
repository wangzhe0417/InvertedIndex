package InvertedIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;


/**
 * 
 * 输入：FileList
 * 输出：TokenList
 * @author wangzhe
 *
 */
public class getFileTokenList {
	
	public static LinkedList<FileListElement> readFileListToTokenList(){
		String encoding = "utf-8";
		
		//调用getFileList获取文件列表
		List<FileListElement> FileList = new ArrayList();
		FileList = getFileList.getList();
		LinkedList<FileListElement> FileTokenList = new LinkedList();
	
		for(int filelistindex = 0; filelistindex < FileList.size() ; filelistindex ++){//循环读取FileList列表中所有n个文件，并生成n个TokenList		
			File path = FileList.get(filelistindex).getFileName();	
			InputStreamReader read = null;
			try {
				read = new InputStreamReader(new FileInputStream(path),encoding);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(read);
			String LineTxt = null;
			
			
			FileListElement fileTokenList = new FileListElement(1, filelistindex, path, null);
			List<String> wordList = new ArrayList<>();
			//读取一篇文章并生成一个fileTokenList
			try {
				while((LineTxt = bufferedReader.readLine()) != null){//读取文件内容，一次一行直至全部读取完
					LineTxt = LineTxt.replaceAll("[\\pP\\p{Punct}]", " ");
					LineTxt.replace('\r', ' ').replace('\n', ' ');
					
					String[] words = LineTxt.split(" ");
					for(String word : words){//将内存中分好的词存入tokenList
						wordList.add(word);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Collections.sort(wordList);
			LinkedList<TokenListElement> tokenList = new LinkedList<>();
			String temp = null;
			String temp2 = null;
			int count = 1;
			for(int i = 0, j = 1; i < wordList.size() && j < wordList.size() ; ){
				temp = wordList.get(i);
				temp2 = wordList.get(j);
				if(temp.equals(temp2)){
					count ++;
					i ++;
					j ++;
				}else{
					i = j;
					j ++;	
					TokenListElement token = new TokenListElement(temp, count, filelistindex);
					tokenList.add(token);
					count = 1;
				}
			}
			if(temp.equals(temp2)){
				TokenListElement token = new TokenListElement(temp, count, filelistindex);
				tokenList.add(token);
			}else if(! temp.equals(temp2)){
				TokenListElement token = new TokenListElement(temp2, 1, filelistindex);
				tokenList.add(token);
			}
			
			
			fileTokenList.addToken(tokenList);
			
			//将fileTokenList添加到文章FileTokenList对应的元素中
			FileTokenList.add(fileTokenList);
					
		}
		return FileTokenList;
	}
	
	//用于测设
//	public static void main(String[] args){
//		List<FileListElement> fileTokenList = new LinkedList<>();
//		FileListElement temp = null;
//		fileTokenList = readFileListToTokenList();
//		for(int index = 0; index < fileTokenList.size(); index ++){
//			temp = fileTokenList.get(index);
//			temp.testPrint(temp);
//		}
//	}
}
