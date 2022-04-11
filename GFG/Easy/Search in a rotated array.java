class Solution
{
    int search(int arr[], int l, int h, int key)
    {
        // Complete this function
        
        if(l > h) return -1;
        
        int mid = (h+l) / 2;
        
        if(arr[mid]==key) return mid;
        
        if(arr[l]<=arr[mid]){
            if(key>=arr[l] && key<=arr[mid]) return search(arr, l, mid-1, key);
            return search(arr, mid+1, h, key);
        }
        
        // arr[mid] < arr[l] 
        if(key>=arr[mid] && key<=arr[h]) return search(arr, mid+1, h, key);
        return search(arr, l, mid-1, key);
    }
}
