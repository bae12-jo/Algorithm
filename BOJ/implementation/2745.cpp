#include <iostream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

int main(void){

    string num;
    int B, result=0;
    int count=0;
    cin >> num >> B;

    int length = num.length();

    for(int i=length-1; i>=0; i--){
        int tmp = num[i];
        if (tmp >= 'A' && tmp <= 'Z')
        { 
            result += (tmp-'A'+10) * ((int)pow(B, count));
        }
        else
        {
            result += (tmp-'0') * ((int)pow(B, count));
        }
        count++;            
    }

    cout << result <<'\n';


    return 0;
}

// 같은 변수 한 번에 바꾸기 ctrl+shift+l
// 찾아서 바꾸기 ctrl+h