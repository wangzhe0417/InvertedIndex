package InvertedIndex;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 读取文件，生成FileList
 * @author wangzhe
 *
 */

public class getFileList {
	
	public static List<FileListElement> getList(){
		
		List<FileListElement> FileList = new ArrayList<>();
		String InputPath = "/Users/wangzhe/Workspace/JavaProjects/inputFile";
		File filePath = new File(InputPath);
		
		if(!filePath.isDirectory()){
			System.out.println("路径错误！");
			return null;
		}else{
			
			//扫描路径下所有.txt文件，并生成directoryList数组
			File[] directoryList = filePath.listFiles(new FileFilter(){
				public boolean accept(File file){
					if(file.isFile() && file.getName().indexOf("txt") > -1) {
						return true;
					}else{
						return false;
					}
				}
			});		
			
			
			//实现directoryList中只存储文件名
//			String[] directoryList = filePath.list(new FilenameFilter(){
//				public boolean accept(File file , String name){
//					if(file.isFile() && file.getName().indexOf("txt") > -1) {
//						return true;
//					}else{
//						return false;
//					}
//				}
//			});	
			
			
			//对directory数组进行扫描并生成FileList
			for (int i = 0; i < directoryList.length; i ++){
				FileListElement temp = new FileListElement(1, 0, null, null);
				temp.setFileID(i);
				temp.setFileName(directoryList[i]);
				
				FileList.add(temp);
			}
		}
		return FileList;
		}
	
}
	
	

	

