#include <iostream> 
#include <cstring> 

using namespace std; 

const int MAX = 333334; 
char input[MAX+1]; 

void oct2Dex(char o) 
{ 
    if (o == '0') cout << "000"; 
    else if (o == '1') cout << "001"; 
    else if (o == '2') cout << "010"; 
    else if (o == '3') cout << "011"; 
    else if (o == '4') cout << "100"; 
    else if (o == '5') cout << "101"; 
    else if (o == '6') cout << "110"; 
    else if (o == '7') cout << "111"; 
} 

int main() 
{ 
    cin >> input; 
    int len = strlen(input); 
    if (input[0] != '0') 
    { 
        if (input[0] == '1') cout << 1; 
        else if (input[0] == '2') cout << 10; 
        else if (input[0] == '3') cout << 11; 
        else oct2Dex(input[0]); 
        for (int i = 1; i < len; i++) oct2Dex(input[i]); 
    } else cout << 0; 
    
    return 0; 
}
