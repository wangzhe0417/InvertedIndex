package InvertedIndex;

import java.util.LinkedList;

/**
 * 将所有TokenList合并为一个TokenList
 */
import java.util.Map;
import java.util.TreeMap;

public class mergeAllTokenList {
	
	public static LinkedList<TokenListElement> convertTokenListToHeap(){
		LinkedList<FileListElement> fileTokenList = new LinkedList<>();
		fileTokenList = getFileTokenList.readFileListToTokenList();
		
		int maxSize = fileTokenList.size();
		int num = 0;
		TokenListElement token = new TokenListElement(null, 0, 0);
		TokenListElement temp = new TokenListElement(null, 0, 0);
		//用LinkedList存储所有token
		LinkedList<TokenListElement> finalToken = new LinkedList<>();
		
		//建堆
		MinHeap TokenHeap = new MinHeap(maxSize + 1);
		for(int index = 0; index < maxSize; index ++){
			token = fileTokenList.get(index).getAndRemoveToken();
			//处理文档的TokenList中元素全部读取完的情况
			if(fileTokenList.get(index).getTokenListSize() == 0){
				fileTokenList.get(index).setFlag();
				num ++;
			}
			TokenHeap.insertHeap(token);
		}
		//测试建堆是否成功
		//System.out.println(TokenHeap.getMin().getCounts());
		int fileID = 0;
		while(num != fileTokenList.size()){//fileTokenList中元素的TokenList为空的数量与fileTokenList 中元素数量相等时跳出，即fileTokenList已空
			token = TokenHeap.getMin();
			finalToken.add(token);
			fileID = token.getdocID(); 
			if(fileTokenList.get(fileID).getFlag() == 0){//取出的堆最小元素对应的文件tokenlist已空
				for(FileListElement element: fileTokenList){
					if(element.getFlag() != 0){
						token = element.getAndRemoveToken();
						TokenHeap.insertHeap(token);
						if(element.getTokenListSize() == 0){
							element.setFlag();
							num ++;
						}
					}
				}
			}else{
				token = fileTokenList.get(fileID).getAndRemoveToken();
				TokenHeap.insertHeap(token);
				if(fileTokenList.get(fileID).getTokenListSize() == 0){
					fileTokenList.get(fileID).setFlag();
					num ++;
				}
			}
		}
		while(!(TokenHeap.isEmpty())){//取出所有堆中剩余的元素
			token = TokenHeap.getMin();
			finalToken.add(token);
		}
		
			
		//打印测试finalToken是否正确生成
//		for(TokenListElement token1 : finalToken){
//			System.out.println(token1.getWord() +" " + token1.getCounts() + " " + token1.getdocID());
//		}
		return finalToken;
	}
	
	//用于测试生成的finalToken
//	public static void main(String[] args){
//		LinkedList<TokenListElement> FinalToken = new LinkedList<TokenListElement>();
//		FinalToken = convertTokenListToHeap();
//	}
}
