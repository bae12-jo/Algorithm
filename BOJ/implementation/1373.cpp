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

    // 10������ �Է¹��� ������ ���� �������� ���� �����̳ʿ� �������� �־���
    while(1){
        vec.push_back(dec%8);
        dec/=8;
        if(dec==0) break;
    }

    // ������ iterator ����Ͽ� �ڿ������� ���
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