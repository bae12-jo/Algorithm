// https://kimhwon.tistory.com/category/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98?page=3
import java.util.*;
import java.io.*;

public class BOJ_3363 {
    static int[][] operands = new int[3][8]; // 입력된 숫자들
    static char[] operators = new char[3]; // 연산자
    static int[] countNum = new int[13]; // 숫자당 입력된 횟수
    static int[][] weight  = new int[2][13]; // 동전의 무게: 무거우면 0, 가벼우면 1
    
    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for( int i=0; i<3; i++){
            String str = br.readLine();
            if(str.equals("")){
                i--;
                continue;
            }else {
                StringTokenizer st = new StringTokenizer(str);
                for (int j = 0; j < 9; j++) {
                    if (j > 4) {
                        operands[i][j - 1] = Integer.parseInt(st.nextToken());
                        countNum[operands[i][j - 1]]++;
                    } else if (j < 4) {
                        operands[i][j] = Integer.parseInt(st.nextToken());
                        countNum[operands[i][j]]++;
                    } else {
                        operators[i] = st.nextToken().charAt(0);
                    }
                }
            }
        }
        br.close();
    }
    
    static void solution(){
        int lineNum = 3; // 입력된 수식의 수
        boolean impossible = true; // 조건이 불충분한 경우
        int count = 0; // 만족하는 조건의 개수
        int coin = 0; // 코인의 번호
        char weightOP = 0; // 무거운지, 가벼운지
        
        // = 연산이 있는 경우 무효
        for( int i=0; i<3; i++){
            if(operators[i] == '=') {
                lineNum--;
                for (int j = 0; j < 8; j++) {
                    countNum[operands[i][j]] = 0;
                }
            }
        }
        
    // 수식 개수보다 적게 등장하는 수는 무효
		// 무거운 쪽 > 가벼운 쪽의 횟수 업데이트
        for( int i=0; i<3; i++){
            if(operators[i] != '='){
                for(int j=0; j<8;j++) {
                    if(countNum[operands[i][j]]!=lineNum) operands[i][j] = 0;
                    else{
                        if((j<4 && operators[i] =='<')||(j>=4 && operators[i] =='>') ){
                            weight[1][operands[i][j]]++;
                        }else{
                            weight[0][operands[i][j]]++;
                        }
                    }
                }
            }
        }
        
    // 일관되게 무거웠거나 가벼웠던 번호를 찾기
		// 아무것도 없다면 데이터가 모순되는 것 (impossible)
		// 2개 이상 존재한다면 데이터가 더 필요한 것 (indefinite)
        for( int i=0; i<2; i++){
            for(int j=0; j<13;j++) {
                if(weight[i][j] == lineNum){
                    if(i==0){
                        coin = j;
                        weightOP = '+';
                        impossible = false;
                        count++;
                    }else{
                        coin = j;
                        weightOP = '-';
                        count++;
                        impossible = false;
                    }
                }
            }
        }
        // 결과 출력
        if(impossible) {
            System.out.println("impossible");
        }else if(count >=2) {
            System.out.println("indefinite");
        }else{
            System.out.println(Integer.toString(coin)+weightOP);
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        input();
        solution();

    }
}
