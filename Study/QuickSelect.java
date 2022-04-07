import java.util.*;

/*
* Kth smallest element in unsorted array
*/

class QuickSelect{
	
	// find kth smallest -- by comparing K and returned pivot after partioning
	
	public static int getKthSmallest(int[] arr, int l, int r, int k){
	
		// to avoid k is out of traversal range
		if(k > 0 && k <= r-l+1){ // there is no zero in ordinal number
			
			int newPivot = getRandomPivot(arr, l, r);
			if(newPivot-1==k-1) return arr[newPivot]; // if newPivot is same as k
			else if(newPivot-1<k-1) return getKthSmallest(arr, newPivot+1, r, k+l-newPivot-1); // if newPivot is smaller than k 
			return getKthSmallest(arr, 1, newPivot-1, k); // if newPivot is bigger than k
		}
		
		return Integer.MAX_VALUE;
	
	}
	
	// get a ramdom pivot
	public static int getRandomPivot(int[] arr, int l, int r){
		int pivot = (int)(Math.random()*(r-l)); // 0부터 탐색범위-1 인덱스까지 랜덤하게 뽑는 함수 (인덱스라서 r-l+1이 아님)
		swap(arr, l+pivot, r); // move pivot value to rightmost index for partioning
		return partitioning(arr, l, r);
		
	}
	
	// partioning based on a selected random pivot
	public static int partitioning(int[] arr, int l, int r){
		int pivotValue = arr[r], bigger = l;
		for(int i = l; i < r; ++i){
			if(arr[i]<=pivotValue){
				swap(arr, i, bigger); // swap element less than pivot with first bigger one
				bigger++; // update index of fisrt element bigger than pivot value
			}
		}
		swap(arr, bigger, r); // reposition to the position where the pivot value should be
		return bigger; // return position of pivot value
	}
	
	// swap -- utility method - parameter (array, left index, right index)
	public static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	
	public static void main(String[] args){
		
		int[] arr = {12, 3, 5, 7, 4 ,19, 26};
		int k = 3;
		
		// 메소드를 바로 사용할거면 전부 public static으로 선언해줘야 한다
		// 접근자를 public으로 하지 않으면 인스턴스를 생성해서 사용해야 한다.
		System.out.print(getKthSmallest(arr, 0, arr.length-1, k));
	
	}
	
}
