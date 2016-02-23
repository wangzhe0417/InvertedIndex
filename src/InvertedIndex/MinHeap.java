package InvertedIndex;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * 小根堆
 * @author wangzhe
 * 	创建：
 *	插入：a.将要插入的节点按照完全二叉树的形式插入到当前树形结构的最末尾
 *	     b.对这个刚刚插入的节点进行向上浮动，浮动的原理是：比较当前的节点和其父节点，如果比父节点小，那么与父节点交换，然后递归的进行，直到浮动不动了或者到了根节点
 *	删除：a.小根堆的删除是删除当前的根节点，也就是返回当前根节点的值，然后用当前树形结构的最后一个节点来代替根节点
 *		 b.从当前属性结构的最后一个非叶节点开始，向下浮动，浮动的原理是：
 *				如果有比自己小的子节点，那么与这个子节点交换，然后递归的对刚刚交换下去的子节点进行向下浮动，（如果当前两个子节点都比自己小，那么就与最小的那个交换）
 */
public class MinHeap{
	private TokenListElement[] Heap;//堆容器	
	private int maxSize;//堆的最大容量
	private int currentSize;//堆的当前大小
	
	//构造器
	public MinHeap(int _maxSize){
		Heap = new TokenListElement[_maxSize];
		maxSize = _maxSize;
		currentSize = 0;
	}
	
	//向下调整
	public void filterDown(int start, int endOfHeap){
		int i = start;
		int j = 2 * i + 1;//j是i的左孩子位置
		TokenListElement temp = new TokenListElement(Heap[i].getWord(), Heap[i].getCounts(), Heap[i].getdocID());
		
		while(j <= endOfHeap){//检查是否到最后位置
			if((j < endOfHeap) && (Heap[j].compareToAnother(Heap[j+1])) > 0){//让j指向左右两孩子中的小者
				j ++;
			}else if(temp.compareToAnother(Heap[j]) <= 0){//小则不做调整
				break;
			}else{//否则小者上移
				Heap[i] = Heap[j];
				i = j;
				j = 2 * j + 1;
			}
		}
		Heap[i] = temp;
	}
	
	//向上调整
	public void filterUp(int start){	
		int j = start;
		int i = (j - 1) / 2;//父结点下标
		TokenListElement temp = Heap[j];
		
		while(j > 0 ){//沿父结点点路径向上直达根节点
			//System.out.println(i);
			if(Heap[i].compareToAnother(temp) <= 0){//父结点值小，不调整
				break;
			}else{//父结点值大，调整
				Heap[j] = Heap[i];
				j = i;
				i = (i - 1) / 2;
			}
			Heap[j] = temp;
		}
	}
	
	//插入
	public boolean insertHeap(TokenListElement element){
		boolean bool = true;
		if(isFull()){
			bool = false;
		}else{
			Heap[currentSize] = element;
			filterUp(currentSize);
			currentSize ++;
		}
		return bool;
		
	}
	
	//取堆顶元素
	public TokenListElement getMin(){
		TokenListElement root = new TokenListElement(Heap[0].getWord(), Heap[0].getCounts(), Heap[0].getdocID());
		Heap[0] = Heap[currentSize - 1];
		currentSize --;
		filterDown(0, currentSize - 1);
		return root;
	}
	
	public boolean isEmpty(){//判空
		return currentSize == 0;
	}
	
	public boolean isFull(){//判满
		return currentSize == maxSize;
	}
	


}
