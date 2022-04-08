/* medians of medians 이용 */

import java.util.*;

class QuickSelectWithMOM{
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    {   
        // If k is smaller than 
    // number of elements in array
    if (k > 0 && k <= r - l + 1)
    {
        int n = r - l + 1 ; // Number of elements in arr[l..r]

        // Divide arr[] in groups of size 5, 
        // calculate median of every group
        //  and store it in median[] array.
        int i;
        
         // There will be floor((n+4)/5) groups;
        int []median = new int[(n + 4) / 5]; // 올림 효과, 나머지가 생기지 않도록 방지
        for (i = 0; i < n/5; i++)
            median[i] = findMedian(arr, l+i*5, l+i*5+5);
            
        // For last group with less than 5 elements
        if (i*5 < n) 
        {
            median[i] = findMedian(arr, l+i*5, l+i*5+n%5); 
            i++;
        } 

        // Find median of all medians using recursive call.
        // If median[] has only one element, then no need
        // of recursive call
        int medOfMed = (i == 1)? median[i - 1]:
                                kthSmallest(median, 0, i - 1, i / 2);

        // Partition the array around a random element and
        // get position of pivot element in sorted array
        int pos = partition(arr, l, r, medOfMed);

        // If position is same as k
        if (pos-l == k - 1)
            return arr[pos];
        if (pos-l > k - 1) // If position is more, recur for left
            return kthSmallest(arr, l, pos - 1, k);

        // Else recur for right subarray
        return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
    }

    // If k is more than number of elements in array
    return Integer.MAX_VALUE;
    }
    
    public static int[] swap(int []arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
    
    // It searches for x in arr[l..r], and 
    // partitions the array around x.
    public static int partition(int arr[], int l,
                            int r, int x)
    {
        // Search for x in arr[l..r] and move it to end
        int i;
        for (i = l; i < r; i++)
            if (arr[i] == x)
            break;
        swap(arr, i, r);
    
        // Standard partition algorithm
        i = l;
        for (int j = l; j <= r - 1; j++)
        {
            if (arr[j] <= x)
            {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }
    
    // int partition(int arr[], int l, int r, int k);

    // A simple function to find median of arr[]. This is called
    // only for an array of size 5 in this program.
    public static int findMedian(int arr[], int i,int n)
    {
            Arrays.sort(arr, i, n);
            return arr[i+(n-i)/2];                    // sort the array and return middle element
    }
	
	public static void main(String[] args){
		
		int[] arr = {7, 10, 4, 3, 20, 15};
		int k = 3;
		
		// 메소드를 바로 사용할거면 전부 public static으로 선언해줘야 한다
		// 접근자를 public으로 하지 않으면 인스턴스를 생성해서 사용해야 한다.
		// kthSmallest = ob = new kthSmallest();
		// ob.kthSmallest(arr, 0, n-1, k); // main과 다른 클래스에 선언된 경우
		System.out.print(kthSmallest(arr, 0, arr.length-1, k));
	
	}
    
}



/* 랜덤 함수 이용 */
import java.util.*;

/*
* Kth smallest element in unsorted array
* 이 코드는 매번 값이 다르게 나옴
*/

class QuickSelect{
    public static int kthSmallest(int arr[], int l, int r, int k)
    {
        // If k is smaller than number of elements in array
        if (k > 0 && k <= r - l + 1)
        {
            // Partition the array around a random element and
            // get position of pivot element in sorted array
            int pos = randomPartition(arr, l, r);
 
            // If position is same as k
            if (pos-l == k-1)
                return arr[pos];
 
            // If position is more, recur for left subarray
            if (pos-l > k-1)
                return kthSmallest(arr, l, pos-1, k);
 
            // Else recur for right subarray
            return kthSmallest(arr, pos+1, r, k-pos+l-1);
        }
 
        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }
 
    // Utility method to swap arr[i] and arr[j]
    public static void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    // Standard partition process of QuickSort(). It considers
    // the last element as pivot and moves all smaller element
    // to left of it and greater elements to right. This function
    // is used by randomPartition()
    public static int partition(int arr[], int l, int r)
    {
        int x = arr[r], i = l;
        for (int j = l; j < r; j++)
        {
            if (arr[j] <= x)
            {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }
 
    // Picks a random pivot element between l and r and
    // partitions arr[l..r] arount the randomly picked
    // element using partition()
    public static int randomPartition(int arr[], int l, int r)
    {
        int n = r - l + 1;
        int pivot = (int)(Math.random() * (n - 1));
        swap(arr, l + pivot, r);
        return partition(arr, l, r);
    }
    
	public static void main(String[] args){
		
		int[] arr = {7, 10, 4, 3, 20, 15};
		int k = 3;
		
		// 메소드를 바로 사용할거면 전부 public static으로 선언해줘야 한다
		// 접근자를 public으로 하지 않으면 인스턴스를 생성해서 사용해야 한다.
		// kthSmallest = ob = new kthSmallest();
		// ob.kthSmallest(arr, 0, n-1, k); // main과 다른 클래스에 선언된 경우
		System.out.print(kthSmallest(arr, 0, arr.length-1, k));
	
	}
	
}
