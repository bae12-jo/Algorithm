/* 
* 24시간 중 1~10끼를 먹을 수 있다. 
* 7시간마다 배가 고파지는 사람이 하루종일 배부르기 위해 먹어야 하는 최소 끼니의 수를 구하라. 
* 소수점 첫째자리 까지 계산하라. 
* 잠자는 시간은 고려하지 않는다.
*/


import java.util.*;

public class parametricSearch{
	public static double parametricSearchforDouble(double start, double end){
		double mid = 0, tempMid = 0;
		while(start<=end){
			mid = tempMid; // 딱 맞는 값을 찾지 못하고 역전되는 경우 가장 근사한 값인 직전 값을 반환하기 위함
			tempMid = (start+end)/2.0;
			if(24/tempMid > 7) start = tempMid + 0.1; // 나눈 값이 7보다 크다면 나누는 값이 충분히 크지 않다는 뜻이므로 시작점을 키운다.
			else if(24/tempMid < 7) end = tempMid - 0.1; // 나눈 값이 7보다 작다면 나누는 값이 충분히 작지 않다는 뜻이므로 종료점을 줄인다.
			else return tempMid; // 딱 맞는 값을 찾았다면 종료한다. 
		}
		 return mid;
	}
	
	public static void main(String[] args){
		// double 형 반환
		double result = Math.round(parametricSearchforDouble(0, 10)*100)/100.0;
		System.out.println(result);

		// String 형 반환
		String result = String.format("%.1f", parametricSearchforDouble(0, 10));
		System.out.println(result);
}
