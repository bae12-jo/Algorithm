#include <iostream>
#include <vector>

using namespace std;

int main(void){

    int dec, ntt;
    cin >> dec >> ntt;

    vector<int> vec;

    // 10진수를 입력받은 진수로 나눈 나머지를 벡터 컨테이너에 차곡차곡 넣어줌
    while(1){
        vec.push_back(dec%ntt);
        dec/=ntt;
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