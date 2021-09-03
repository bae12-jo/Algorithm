#include <iostream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

int n2dec(string num){

    int count=0, result=0;

    int length = num.length();

    for(int i=length-1; i>=0; i--){
        int tmp = num[i];
        if (tmp >= 'A' && tmp <= 'Z')
        { 
            result += (tmp-'A'+10) * ((int)pow(2, count));
        }
        else
        {
            result += (tmp-'0') * ((int)pow(2, count));
        }
        count++;            
    }

    return result;
}

int main(void){

    string num;
    cin >> num;

    int dec = n2dec(num);

    vector<int> vec;

    // 10진수를 입력받은 진수로 나눈 나머지를 벡터 컨테이너에 차곡차곡 넣어줌
    while(1){
        vec.push_back(dec%8);
        dec/=8;
        if(dec==0) break;
    }

    // 역방향 iterator 사용하여 뒤에서부터 출력
    vector<int>::reverse_iterator riter;
    for(riter = vec.rbegin(); riter!=vec.rend(); riter++){
        if(*riter>=10){
            char c = *riter-10+'A';
            cout << c;
        }else{
            cout << *riter;
        }
    }


    return 0;
}