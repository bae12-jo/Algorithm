/* Bubble Sort with a changed flag*/

class BubbleSort{
	public static void main(String[] args){
		int[] numArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		for(Integer num: numArr)
			System.out.print(num);
		System.out.println();
		int count = 0;
		
		for(int i=0; i<numArr.length-1;i++){
			boolean changed = false;
			
			for(int j=0; j<numArr.length-1-i; j++){
				if(numArr[j] > numArr[j+1]){
					int temp = numArr[j];
					numArr[j] = numArr[j+1];
					numArr[j+1] = temp;
					
					changed = true;
					count++;
				}
			}
			
			if(!changed) break;
		}
		
		for(Integer num: numArr)
			System.out.print(num);
		System.out.println();
		System.out.println(count);
	
	}

}
