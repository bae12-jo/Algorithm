#include <iostream>
#include <vector>

using namespace std;

int main(void){

    int dec, ntt;
    cin >> dec >> ntt;

    vector<int> vec;

    // 10������ �Է¹��� ������ ���� �������� ���� �����̳ʿ� �������� �־���
    while(1){
        vec.push_back(dec%ntt);
        dec/=ntt;
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