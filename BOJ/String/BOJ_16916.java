package GOLD;

/*
 * [G3] 부분 문자열
 * KMP 문자열 알고리즘
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_16916 {
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String S = br.readLine();
		String P = br.readLine();
		
		bw.write(String.valueOf(KMP(S, P)));
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static int KMP(String S, String P) {
		
		int[] table = makeTable(P);
		
		int idx = 0; // 현재 대응되는 글자 수
		for(int i=0; i<S.length(); i++) {
			while(idx>0 && S.charAt(i)!=P.charAt(idx)) idx = table[idx-1];
			if(S.charAt(i)==P.charAt(idx)) {
				if(idx==P.length()-1) {
					//idx = table[idx];
					return 1;
				}
				else ++idx;
			}
		}
		return 0;
		
	}
	
	private static int[] makeTable(String P) { // 가장 긴 동일한 접두사, 접미사 길이를 기억해서 탐색 범위를 건너뛰는 것이 목표
		
		int[] table = new int[P.length()];
		
		int idx = 0; // 접미사와 동일한 접두사 중 가장 마지막 위치
		for(int i=1; i<P.length(); i++) {
			// 일치하지 않는 경우, 일치했던 가장 긴 접두사의 마지막 위치에 저장된 값으로 이동 (이미 동일하다고 확인된 부분문자열을 재탐색하지 않고 건너뛰는 효과)
			while(idx>0 && P.charAt(i)!=P.charAt(idx)) idx = table[idx-1];
			// 일치하는 경우, 접두사의 마지막 문자 위치를 갱신하고, 대응되는 점미사의 위치에 접두사 길이 저장
			if(P.charAt(i)==P.charAt(idx)) table[i] = ++idx;
		}
		
		return table;
		
	}

}
